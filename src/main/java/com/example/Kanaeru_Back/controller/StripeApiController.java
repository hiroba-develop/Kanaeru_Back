package com.example.Kanaeru_Back.controller;

import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.model.ApiStripeSubscriptionCancelPost200Response;
import com.example.Kanaeru_Back.model.ApiStripeSubscriptionGet200Response;
import com.example.Kanaeru_Back.model.ApiWebhooksStripePost200Response;
import com.example.Kanaeru_Back.model.CreateSubscriptionRequest;
import com.example.Kanaeru_Back.model.CreateSubscriptionResponse;
import com.example.Kanaeru_Back.model.SubscriptionSchema;
import com.example.Kanaeru_Back.service.stripe.SubscriptionService;
import com.stripe.exception.StripeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripeApiController implements StripeApi {

    private static final Logger logger = LoggerFactory.getLogger(StripeApiController.class);

    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public ResponseEntity<ApiAuthLogoutPost200Response> apiStripeSubscriptionCancelIncompletePost(String userId) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();
        try {
            boolean found = subscriptionService.cancelIncompleteSubscription(userId);
            if (!found) {
                response.setResponseStatus(0);
                response.setMessage("未完了のサブスクリプションが見つかりません");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            response.setResponseStatus(1);
            response.setMessage("未完了のサブスクリプションを削除しました");
        } catch (StripeException e) {
            logger.error("Stripe API エラー（未完了解約） userId={}", userId, e);
            response.setResponseStatus(0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (Exception e) {
            logger.error("未完了サブスクリプション削除中に予期せぬエラー userId={}", userId, e);
            response.setResponseStatus(0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CreateSubscriptionResponse> apiStripeSubscriptionCreatePost(
            String userId,
            CreateSubscriptionRequest createSubscriptionRequest) {

        CreateSubscriptionResponse response = new CreateSubscriptionResponse();
        try {
            response = subscriptionService.createSubscription(userId);
        } catch (IllegalStateException e) {
            logger.warn("サブスクリプション作成エラー userId={} message={}", userId, e.getMessage());
            if ("SUBSCRIPTION_ALREADY_EXISTS".equals(e.getMessage())) {
                ApiAuthLogoutPost200Response conflict = new ApiAuthLogoutPost200Response();
                conflict.setResponseStatus(0);
                conflict.setMessage("既にアクティブなサブスクリプションが存在します");
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            response.setResponseStatus(0);
        } catch (StripeException e) {
            logger.error("Stripe API エラー userId={}", userId, e);
            response.setResponseStatus(0);
        } catch (Exception e) {
            logger.error("サブスクリプション作成中に予期せぬエラー userId={}", userId, e);
            response.setResponseStatus(0);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiStripeSubscriptionGet200Response> apiStripeSubscriptionGet(String userId) {
        ApiStripeSubscriptionGet200Response response = new ApiStripeSubscriptionGet200Response();
        try {
            SubscriptionSchema subscription = subscriptionService.getCurrentSubscription(userId);
            response.setResponseStatus(1);
            response.setSubscription(subscription);
        } catch (Exception e) {
            logger.error("サブスクリプション取得中にエラー userId={}", userId, e);
            response.setResponseStatus(0);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiStripeSubscriptionCancelPost200Response> apiStripeSubscriptionCancelPost(String userId) {
        ApiStripeSubscriptionCancelPost200Response response = new ApiStripeSubscriptionCancelPost200Response();
        try {
            SubscriptionSchema subscription = subscriptionService.cancelSubscription(userId);
            response.setResponseStatus(1);
            response.setSubscription(subscription);
            String periodEnd = subscription.getCurrentPeriodEnd() != null
                ? subscription.getCurrentPeriodEnd().toLocalDate().toString()
                : "次回更新日";
            response.setMessage("サブスクリプションは " + periodEnd + " に終了します");
        } catch (IllegalArgumentException e) {
            logger.warn("サブスクリプション解約エラー userId={} message={}", userId, e.getMessage());
            response.setResponseStatus(0);
            response.setMessage("アクティブなサブスクリプションが見つかりません");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (IllegalStateException e) {
            logger.warn("サブスクリプション解約エラー userId={} message={}", userId, e.getMessage());
            response.setResponseStatus(0);
            response.setMessage("アクティブなサブスクリプションではありません");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (StripeException e) {
            logger.error("Stripe API エラー（解約） userId={}", userId, e);
            response.setResponseStatus(0);
        } catch (Exception e) {
            logger.error("サブスクリプション解約中に予期せぬエラー userId={}", userId, e);
            response.setResponseStatus(0);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiWebhooksStripePost200Response> apiWebhooksStripePost(
            String stripeSignature,
            String body) {

        ApiWebhooksStripePost200Response response = new ApiWebhooksStripePost200Response();
        try {
            subscriptionService.handleWebhook(body, stripeSignature);
            response.setReceived(true);
        } catch (com.stripe.exception.SignatureVerificationException e) {
            logger.error("Stripe 署名検証失敗", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (StripeException e) {
            logger.error("Stripe Webhook 処理エラー", e);
            response.setReceived(false);
        } catch (Exception e) {
            logger.error("Webhook 処理中に予期せぬエラー", e);
            response.setReceived(false);
        }
        return ResponseEntity.ok(response);
    }
}
