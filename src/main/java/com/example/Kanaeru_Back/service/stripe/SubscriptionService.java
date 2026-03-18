package com.example.Kanaeru_Back.service.stripe;

import com.example.Kanaeru_Back.entity.SubscriptionEntity;
import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.CreateSubscriptionResponse;
import com.example.Kanaeru_Back.model.SubscriptionSchema;
import com.example.Kanaeru_Back.repository.SubscriptionRepository;
import com.example.Kanaeru_Back.repository.UserRepository;
import com.example.Kanaeru_Back.service.email.EmailTemplateService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerSearchResult;
import com.stripe.model.Event;
import com.stripe.model.Invoice;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSearchParams;
import com.stripe.param.SubscriptionCreateParams;
import com.stripe.param.SubscriptionUpdateParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
   
    @Value("${stripe.api.secret-key}")
    private String stripeSecretKey;

    @Value("${stripe.price.monthly-id}")
    private String monthlyPriceId;

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    @Autowired
    private EmailTemplateService emailTemplateService;


    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
        log.info("Stripe API 初期化完了");
    }
    /**
     * サブスクリプション新規作成
     * Stripe Customer + Subscription を作成し、SUBSCRIPTIONSテーブルに仮登録（status=incomplete）してから clientSecret を返す
     * Webhook (invoice.paid) が届いた際に status=active へ更新される
     */
    @Transactional
    public CreateSubscriptionResponse createSubscription(String userId) throws StripeException {
        log.info("サブスクリプション作成開始 userId={}", userId);

        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("USER_NOT_FOUND: userId=" + userId));
        String email = user.getEmail();

        // アクティブなサブスクリプションが既に存在する場合はエラー
        subscriptionRepository.findByUserId(userId).ifPresent(existing -> {
            if ("active".equals(existing.getStatus())) {
                throw new IllegalStateException("SUBSCRIPTION_ALREADY_EXISTS");
            }
        });

        // Stripe Customer を取得または作成
        String customerId = resolveStripeCustomerIdByEmail(userId, email, user.getName());

        // Stripe Subscription 作成（状態: incomplete）
        com.stripe.model.Subscription stripeSubscription = com.stripe.model.Subscription.create(
            SubscriptionCreateParams.builder()
                .setCustomer(customerId)
                .addItem(
                    SubscriptionCreateParams.Item.builder()
                        .setPrice(monthlyPriceId)
                        .build()
                )
                .setPaymentBehavior(SubscriptionCreateParams.PaymentBehavior.DEFAULT_INCOMPLETE)
                .setPaymentSettings(
                    SubscriptionCreateParams.PaymentSettings.builder()
                        .setSaveDefaultPaymentMethod(
                            SubscriptionCreateParams.PaymentSettings.SaveDefaultPaymentMethod.ON_SUBSCRIPTION
                        )
                        .build()
                )
                .addAllExpand(java.util.Arrays.asList("latest_invoice.payment_intent"))
                .build()
        );

        // PaymentIntent から clientSecret を取得
        Invoice latestInvoice = stripeSubscription.getLatestInvoiceObject();
        PaymentIntent paymentIntent = latestInvoice.getPaymentIntentObject();
        String clientSecret = paymentIntent.getClientSecret();

        long unitAmount = stripeSubscription.getItems().getData().get(0).getPrice().getUnitAmount();

        // SUBSCRIPTIONSテーブルに仮登録（Webhook受信後に active へ更新される）
        SubscriptionEntity subscriptionEntity = subscriptionRepository.findByUserId(userId)
            .orElse(new SubscriptionEntity());
        subscriptionEntity.setUserId(userId);
        subscriptionEntity.setStripeSubscriptionId(stripeSubscription.getId());
        subscriptionEntity.setStripeCustomerId(customerId);
        subscriptionEntity.setStatus("incomplete");
        subscriptionEntity.setAmount((int) unitAmount);
        subscriptionEntity.setCancelAtPeriodEnd(false);
        subscriptionEntity.setCanceledAt(null);
        LocalDateTime now = LocalDateTime.now();
        if (subscriptionEntity.getCreatedAt() == null) {
            subscriptionEntity.setCreatedAt(now);
        }
        subscriptionEntity.setUpdatedAt(now);
        subscriptionRepository.save(subscriptionEntity);

        log.info("サブスクリプション仮登録完了 userId={}, subscriptionId={}", userId, stripeSubscription.getId());

        CreateSubscriptionResponse response = new CreateSubscriptionResponse();
        response.setResponseStatus(1);
        response.setClientSecret(clientSecret);
        response.setSubscriptionId(stripeSubscription.getId());
        response.setCustomerId(customerId);
        response.setAmount((int) unitAmount);
        return response;
    }

    /**
     * 現在のサブスクリプション情報を取得
     */
    public SubscriptionSchema getCurrentSubscription(String userId) {
        log.info("サブスクリプション取得 userId={}", userId);

        return subscriptionRepository.findByUserId(userId)
            .map(this::convertToSubscriptionSchema)
            .orElse(null);
    }

    /**
     * サブスクリプションを期間終了時に解約
     */
    @Transactional
    public SubscriptionSchema cancelSubscription(String userId) throws StripeException {
        log.info("サブスクリプション解約開始 userId={}", userId);

        SubscriptionEntity subscriptionEntity = subscriptionRepository.findByUserId(userId)
            .orElseThrow(() -> new IllegalArgumentException("SUBSCRIPTION_NOT_FOUND"));

        // Stripe 最新情報取得
        com.stripe.model.Subscription stripeSubscription =
            com.stripe.model.Subscription.retrieve(subscriptionEntity.getStripeSubscriptionId());

        // DB ステータスが active でない場合は Stripe の最新ステータスに同期
        if (!"active".equals(subscriptionEntity.getStatus())) {
            log.info("DB ステータス '{}' を Stripe と同期", subscriptionEntity.getStatus());
            subscriptionEntity.setStatus(stripeSubscription.getStatus());
            subscriptionEntity.setCurrentPeriodStart(toLocalDateTime(stripeSubscription.getCurrentPeriodStart()));
            subscriptionEntity.setCurrentPeriodEnd(toLocalDateTime(stripeSubscription.getCurrentPeriodEnd()));
            subscriptionEntity.setUpdatedAt(LocalDateTime.now());
            subscriptionRepository.save(subscriptionEntity);

            if (!"active".equals(stripeSubscription.getStatus())) {
                throw new IllegalStateException("SUBSCRIPTION_NOT_ACTIVE");
            }
        }

        // Stripe 側で期間終了時解約に設定
        stripeSubscription.update(
            SubscriptionUpdateParams.builder()
                .setCancelAtPeriodEnd(true)
                .build()
        );

        // DB 更新
        subscriptionEntity.setStatus("canceled");
        subscriptionEntity.setCancelAtPeriodEnd(true);
        subscriptionEntity.setCanceledAt(LocalDateTime.now());
        subscriptionEntity.setUpdatedAt(LocalDateTime.now());
        subscriptionRepository.save(subscriptionEntity);

        // 解約予約完了メール送信
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String periodEnd = subscriptionEntity.getCurrentPeriodEnd() != null
            ? subscriptionEntity.getCurrentPeriodEnd().format(formatter)
            : "契約期間終了時";
        String canceledDate = LocalDateTime.now().format(formatter);

        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity != null) {
            emailTemplateService.sendSubscriptionCanceledEmail(
                userEntity.getEmail(),
                userEntity.getName(),
                canceledDate,
                periodEnd
            );
        }

        log.info("サブスクリプション解約完了 userId={}", userId);
        return convertToSubscriptionSchema(subscriptionEntity);
    }

    /**
     * Stripe Webhook イベントを処理
     */
    @Transactional
    public void handleWebhook(String payload, String sigHeader) throws StripeException {
        log.info("Stripe Webhook 受信");

        Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
        log.info("Webhook イベント種別: {}", event.getType());

        switch (event.getType()) {
            case "invoice.paid":
            case "invoice_payment.paid":
            case "invoice.payment_succeeded":
                handleInvoicePaid(event);
                break;
            case "invoice.payment_failed":
                handleInvoicePaymentFailed(event);
                break;
            case "customer.subscription.deleted":
                handleSubscriptionDeleted(event);
                break;
            default:
                log.info("未処理のイベント種別: {}", event.getType());
        }
    }

    // --- private メソッド ---

    /**
     * userIdまたはemailで Stripe Customer を検索し、存在しない場合は新規作成する
     */
    private String resolveStripeCustomerIdByEmail(String userId, String email, String name) throws StripeException {

        // SUBSCRIPTIONSテーブルに既存のcustomerIdがあれば再利用
        Optional<SubscriptionEntity> existing = subscriptionRepository.findByUserId(userId);
        if (existing.isPresent() && existing.get().getStripeCustomerId() != null) {
            log.info("既存の Stripe Customer を再利用 customerId={}", existing.get().getStripeCustomerId());
            return existing.get().getStripeCustomerId();
        }

        // 新規作成（この時点ではユーザーがDBに未登録のため、name は設定しない）
        Customer customer = Customer.create(
            CustomerCreateParams.builder()
                .setEmail(email)
                .setName(name)
                .build()
        );
        log.info("Stripe Customer 新規作成 customerId={}", customer.getId());
        return customer.getId();
    }

    /**
     * UserEntity の stripeCustomerId を確認し、存在しない場合は Stripe Customer を新規作成する
     * （Webhook処理など、ユーザーがDBに登録済みの場合に使用）
     */
    private String resolveStripeCustomerId(UserEntity user, String email) throws StripeException {
        if (user.getStripeCustomerId() != null && !user.getStripeCustomerId().isBlank()) {
            log.info("既存の Stripe Customer を使用 customerId={}", user.getStripeCustomerId());
            return user.getStripeCustomerId();
        }

        // メールで既存 Customer を検索
        CustomerSearchResult searchResult = Customer.search(
            CustomerSearchParams.builder()
                .setQuery("email:'" + email + "'")
                .build()
        );
        if (!searchResult.getData().isEmpty()) {
            String existingCustomerId = searchResult.getData().get(0).getId();
            log.info("Stripe で既存 Customer を発見 customerId={}", existingCustomerId);
            return existingCustomerId;
        }

        // 新規作成
        Customer customer = Customer.create(
            CustomerCreateParams.builder()
                .setEmail(email)
                .setName(user.getName())
                .putMetadata("userId", user.getUserId())
                .build()
        );
        log.info("Stripe Customer 新規作成 customerId={}", customer.getId());
        return customer.getId();
    }

    private void handleInvoicePaid(Event event) throws StripeException {
        log.debug("invoice.paid 処理 eventId={}", event.getId());
        try {
            String rawJson = event.getDataObjectDeserializer().getRawJson();
            JsonNode jsonNode = objectMapper.readTree(rawJson);
    
            String subscriptionId = null;
    
            // invoice.paid の場合: parent.subscription_details.subscription から取得
            if (jsonNode.has("parent") && !jsonNode.get("parent").isNull()) {
                JsonNode parent = jsonNode.get("parent");
                if (parent.has("subscription_details") && !parent.get("subscription_details").isNull()) {
                    JsonNode subDetails = parent.get("subscription_details");
                    if (subDetails.has("subscription") && !subDetails.get("subscription").isNull()) {
                        subscriptionId = subDetails.get("subscription").asText();
                    }
                }
            }
    
            // invoice_payment.paid の場合: invoiceIDからSubscriptionを取得
            if (subscriptionId == null && jsonNode.has("invoice") && !jsonNode.get("invoice").isNull()) {
                String invoiceId = jsonNode.get("invoice").asText();
                Invoice invoice = Invoice.retrieve(invoiceId);
                // getParent()の代わりにrawJSONから取得
                String invoiceRawJson = invoice.toJson();
                JsonNode invoiceJson = objectMapper.readTree(invoiceRawJson);
                if (invoiceJson.has("parent") && !invoiceJson.get("parent").isNull()) {
                    JsonNode parent = invoiceJson.get("parent");
                    if (parent.has("subscription_details") && !parent.get("subscription_details").isNull()) {
                        JsonNode subDetails = parent.get("subscription_details");
                        if (subDetails.has("subscription") && !subDetails.get("subscription").isNull()) {
                            subscriptionId = subDetails.get("subscription").asText();
                        }
                    }
                }
            }
    
            if (subscriptionId == null) {
                log.warn("subscription ID が取得できませんでした");
                return;
            }
    
            final String finalSubscriptionId = subscriptionId;
            com.stripe.model.Subscription stripeSubscription =
                com.stripe.model.Subscription.retrieve(finalSubscriptionId);
    
            subscriptionRepository.findByStripeSubscriptionId(finalSubscriptionId).ifPresent(entity -> {
                entity.setStatus("active");
                entity.setCurrentPeriodStart(toLocalDateTime(stripeSubscription.getCurrentPeriodStart()));
                entity.setCurrentPeriodEnd(toLocalDateTime(stripeSubscription.getCurrentPeriodEnd()));
                entity.setUpdatedAt(LocalDateTime.now());
                subscriptionRepository.save(entity);
                log.info("サブスクリプション有効化 subscriptionId={}", finalSubscriptionId);
                updateUserRoleToSubscribed(entity.getUserId());
            });
        } catch (Exception e) {
            log.error("invoice.paid 処理中にエラー", e);
            throw new RuntimeException("invoice.paid イベントの処理に失敗しました", e);
        }
    }

    private void handleInvoicePaymentFailed(Event event) {
        log.debug("invoice.payment_failed 処理 eventId={}", event.getId());
        try {
            String rawJson = event.getDataObjectDeserializer().getRawJson();
            JsonNode jsonNode = objectMapper.readTree(rawJson);
    
            String subscriptionId = null;
    
            // ★ invoice.paid と同じ方法で取得
            if (jsonNode.has("parent") && !jsonNode.get("parent").isNull()) {
                JsonNode parent = jsonNode.get("parent");
                if (parent.has("subscription_details") && !parent.get("subscription_details").isNull()) {
                    JsonNode subDetails = parent.get("subscription_details");
                    if (subDetails.has("subscription") && !subDetails.get("subscription").isNull()) {
                        subscriptionId = subDetails.get("subscription").asText();
                    }
                }
            }
    
            // フォールバック：旧形式
            if (subscriptionId == null && jsonNode.has("subscription") && !jsonNode.get("subscription").isNull()) {
                subscriptionId = jsonNode.get("subscription").asText();
            }
    
            if (subscriptionId == null) {
                log.warn("Invoice に subscription ID がありません");
                return;
            }
    
            final String finalSubscriptionId = subscriptionId;
            subscriptionRepository.findByStripeSubscriptionId(finalSubscriptionId).ifPresent(entity -> {
                // ★ 追加：すでにcanceledの場合は更新しない
                if ("canceled".equals(entity.getStatus())) {
                    log.info("サブスクリプションはすでにcanceled状態のためスキップ subscriptionId={}", finalSubscriptionId);
                    return;
                }
                entity.setStatus("past_due");
                entity.setUpdatedAt(LocalDateTime.now());
                subscriptionRepository.save(entity);
                log.info("サブスクリプションを past_due に更新 subscriptionId={}", finalSubscriptionId);
            });
        } catch (Exception e) {
            log.error("invoice.payment_failed 処理中にエラー", e);
        }
    }

    private void handleSubscriptionDeleted(Event event) {
        log.debug("customer.subscription.deleted 処理 eventId={}", event.getId());
        try {
            String rawJson = event.getDataObjectDeserializer().getRawJson();
            JsonNode jsonNode = objectMapper.readTree(rawJson);

            if (!jsonNode.has("id") || jsonNode.get("id").isNull()) {
                log.warn("Subscription イベントに id がありません");
                return;
            }

            String subscriptionId = jsonNode.get("id").asText();
            subscriptionRepository.findByStripeSubscriptionId(subscriptionId).ifPresent(entity -> {
                entity.setStatus("canceled");
                entity.setUpdatedAt(LocalDateTime.now());
                subscriptionRepository.save(entity);
                log.info("サブスクリプションをキャンセル済みに更新 subscriptionId={}", subscriptionId);
            });
        } catch (Exception e) {
            log.error("customer.subscription.deleted 処理中にエラー", e);
        }
    }

    private void updateUserRoleToSubscribed(String userId) {
        userRepository.findById(userId).ifPresent(user -> {
            String previousRole = user.getRole();
            user.setRole("4");
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            log.info("ユーザーロールを 4 に更新 userId={}, previousRole={}", userId, previousRole);
    
            // アップグレード完了メール送信
            emailTemplateService.sendSubscriptionUpgradedEmail(user.getEmail(), user.getName());
        });
    }
    
    // アップグレードを申請途中で辞めた場合にデータを削除
    @Transactional
    public boolean cancelIncompleteSubscription(String userId) throws StripeException {
        log.info("未完了サブスクリプション削除開始 userId={}", userId);

        Optional<SubscriptionEntity> optional = subscriptionRepository.findByUserId(userId);
        if (optional.isEmpty()) {
            log.warn("未完了サブスクリプションが見つかりません userId={}", userId);
            return false;
        }

        SubscriptionEntity entity = optional.get();

        // Stripe 上のサブスクリプションを取得してキャンセル
        if (entity.getStripeSubscriptionId() != null) {
            com.stripe.model.Subscription sub =
                com.stripe.model.Subscription.retrieve(entity.getStripeSubscriptionId());
            if ("incomplete".equals(sub.getStatus())) {
                sub.cancel();
                log.info("Stripe サブスクリプションをキャンセル subscriptionId={}", entity.getStripeSubscriptionId());
            }
        }

        // Stripe Customer を削除
        if (entity.getStripeCustomerId() != null) {
            Customer customer = Customer.retrieve(entity.getStripeCustomerId());
            customer.delete();
            log.info("Stripe Customer を削除 customerId={}", entity.getStripeCustomerId());
        }

        // DB からレコードを削除
        subscriptionRepository.delete(entity);
        log.info("未完了サブスクリプション削除完了 userId={}", userId);
        return true;
    }

    private SubscriptionSchema convertToSubscriptionSchema(SubscriptionEntity entity) {
        SubscriptionSchema schema = new SubscriptionSchema();
        schema.setId(entity.getStripeSubscriptionId());
        schema.setStatus(entity.getStatus());
        schema.setCurrentPeriodStart(entity.getCurrentPeriodStart());
        schema.setCurrentPeriodEnd(entity.getCurrentPeriodEnd());
        schema.setCancelAtPeriodEnd(entity.getCancelAtPeriodEnd());
        schema.setCanceledAt(entity.getCanceledAt());
        schema.setAmount(entity.getAmount());
        schema.setBillingCycle("monthly");
        schema.setCreatedAt(entity.getCreatedAt());
        return schema;
    }

    private LocalDateTime toLocalDateTime(Long epochSecond) {
        if (epochSecond == null) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.systemDefault());
    }
}
