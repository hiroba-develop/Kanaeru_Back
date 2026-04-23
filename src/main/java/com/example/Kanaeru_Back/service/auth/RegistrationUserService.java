package com.example.Kanaeru_Back.service.auth;

import com.example.Kanaeru_Back.entity.GrossProfitEntity;
import com.example.Kanaeru_Back.entity.OperatingProfitEntity;
import com.example.Kanaeru_Back.entity.SalesEntity;
import com.example.Kanaeru_Back.entity.SettingEntity;
import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiAuthTermsAgreePost200Response;
import com.example.Kanaeru_Back.model.ApiAuthRegistrationUserPostRequest;
import com.example.Kanaeru_Back.model.SettingSchema;
import com.example.Kanaeru_Back.model.UserSchema;
import com.example.Kanaeru_Back.repository.GrossProfitRepository;
import com.example.Kanaeru_Back.repository.OperatingProfitRepository;
import com.example.Kanaeru_Back.repository.SalesRepository;
import com.example.Kanaeru_Back.repository.SettingRepository;
import com.example.Kanaeru_Back.repository.UserRepository;
import com.example.Kanaeru_Back.service.email.EmailTemplateService; // ★★★ 変更 ★★★
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RegistrationUserService {

    // ★★★ ロガー追加 ★★★
    private static final Logger logger = LoggerFactory.getLogger(RegistrationUserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailTemplateService emailTemplateService; // ★★★ 変更 ★★★

    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private GrossProfitRepository grossProfitRepository;

    @Autowired
    private OperatingProfitRepository operatingProfitRepository;

    /**
     * 会員登録処理（トランザクション外から呼び出し）
     * DB登録が成功した場合のみメール送信を行う
     */
    public ApiAuthTermsAgreePost200Response registerUser(ApiAuthRegistrationUserPostRequest request) {
        ApiAuthTermsAgreePost200Response response = new ApiAuthTermsAgreePost200Response();

        try {
            UserSchema userSchema = request.getUserSchema();
            SettingSchema settingSchema = request.getSettingSchema();

            if (userSchema == null) {
                logger.error("UserSchema is null");
                response.setResponseStatus(0);
                return response;
            }

            // DB登録処理を実行（トランザクション内）
            String registrationError = registerUserInternal(request);

            if (registrationError != null) {
                response.setResponseStatus(0);
                return response;
            }

            // DB登録が成功した場合のみメール送信（トランザクション外）
            try {
                boolean emailSent = emailTemplateService.sendWelcomeEmail(
                    userSchema.getEmail(), 
                    userSchema.getName()
                );
                
                if (emailSent) {
                    logger.info("Welcome email sent successfully to: {}", userSchema.getEmail());
                } else {
                    logger.warn("Failed to send welcome email to: {}", userSchema.getEmail());
                    // メール送信失敗してもユーザー登録は成功とする
                }
            } catch (Exception e) {
                logger.error("Error sending welcome email to: {}", userSchema.getEmail(), e);
                // メール送信失敗してもユーザー登録は成功とする
            }

            try {
                emailTemplateService.sendAdminUserRegisteredNotification(
                    userSchema.getEmail(),
                    userSchema.getName()
                );
            } catch (Exception e) {
                logger.error("Error sending admin notification for user registration: {}", userSchema.getEmail(), e);
                // 管理者通知失敗してもユーザー登録は成功とする
            }

            response.setResponseStatus(1);
            
        } catch (Exception e) {
            logger.error("Error during user registration", e);
            response.setResponseStatus(0);
        }

        return response;
    }

    /**
     * 会員登録のDB登録処理（トランザクション内で実行）
     * @return 登録成功の場合null、失敗の場合エラーコード（"EMAIL_EXISTS"など）
     */
    @Transactional
    private String registerUserInternal(ApiAuthRegistrationUserPostRequest request) {
        try {
            UserSchema userSchema = request.getUserSchema();
            SettingSchema settingSchema = request.getSettingSchema();

            // ★★★ メールアドレスの重複チェック ★★★
            if (userRepository.findByEmail(userSchema.getEmail()).isPresent()) {
                logger.warn("Email already exists: {}", userSchema.getEmail());
                return "EMAIL_EXISTS";
            }

            UserEntity userEntity = new UserEntity();
            String userId = UUID.randomUUID().toString();
            userEntity.setUserId(userId);
            userEntity.setEmail(userSchema.getEmail());
            userEntity.setPasswordHash(userSchema.getPasswordHash());
            userEntity.setName(userSchema.getName());
            userEntity.setCompany(userSchema.getCompany());
            userEntity.setRole(userSchema.getRole() != null ? userSchema.getRole() : "U");
            userEntity.setDelFlg("0");
            userEntity.setCreatedAt(LocalDateTime.now());
            userEntity.setUpdatedAt(LocalDateTime.now());
            userEntity.setBusinessStartHour(userSchema.getBusinessStartHour());
            userEntity.setBusinessEndHour(userSchema.getBusinessEndHour());
            userEntity.setStripeCustomerId("");
            userEntity.setTermsAgreedAt(LocalDateTime.now());

            userRepository.save(userEntity);

            logger.info("User registered successfully: userId={}, email={}", userId, userSchema.getEmail());

            if (settingSchema != null) {
                SettingEntity settingEntity = new SettingEntity();
                settingEntity.setUserId(userId);
                settingEntity.setAdminId(settingSchema.getAdminId() != null ? settingSchema.getAdminId() : "");
                settingEntity.setCompanySize(settingSchema.getCompanySize() != null ? Integer.parseInt(settingSchema.getCompanySize()) : null);
                settingEntity.setIndustry(settingSchema.getIndustry() != null ? Integer.parseInt(settingSchema.getIndustry()) : null);
                settingEntity.setCapital(settingSchema.getCapital() != null ? settingSchema.getCapital().longValue() : null);
                settingEntity.setFinancialKnowledge(settingSchema.getFinancialKnowledge() != null ? Integer.parseInt(settingSchema.getFinancialKnowledge()) : null);
                settingEntity.setFiscalYearStartYear(settingSchema.getFiscalYearStartYear());
                settingEntity.setFiscalYearStartMonth(settingSchema.getFiscalYearStartMonth());
                settingEntity.setCreatedAt(LocalDateTime.now());
                settingEntity.setUpdatedAt(LocalDateTime.now());

                settingRepository.save(settingEntity);

                // 事業年度開始年月が設定されている場合、10年分のレコードを作成
                if (settingEntity.getFiscalYearStartYear() != null && settingEntity.getFiscalYearStartMonth() != null) {
                    createInitialRecords(userId, settingEntity.getFiscalYearStartYear(), settingEntity.getFiscalYearStartMonth());
                }
            }

            return null;
            
        } catch (Exception e) {
            logger.error("Error during user registration DB operation", e);
            // 例外を再スローしてトランザクションをロールバックさせる
            throw e;
        }
    }

    /**
     * 事業年度開始年月から10年分のレコードをSALES、GROSS_PROFITS、OPERATING_PROFITSテーブルに作成
     * 事業年度開始月を基準に、10年分×12か月（120レコード）を作成
     * 
     * @param userId ユーザーID
     * @param fiscalYearStartYear 事業年度開始年
     * @param fiscalYearStartMonth 事業年度開始月
     */
    private void createInitialRecords(String userId, Integer fiscalYearStartYear, Integer fiscalYearStartMonth) {
        LocalDateTime now = LocalDateTime.now();
        List<SalesEntity> salesEntities = new ArrayList<>();
        List<GrossProfitEntity> grossProfitEntities = new ArrayList<>();
        List<OperatingProfitEntity> operatingProfitEntities = new ArrayList<>();

        // 事業年度開始年月から10年分×12か月（120レコード）を作成
        // 例：2025年8月が事業年度開始年月の場合
        // 1年目: 2025/8-12, 2026/1-7
        // 2年目: 2026/8-12, 2027/1-7
        // ...
        // 10年目: 2034/8-12, 2035/1-7
        for (int yearOffset = 0; yearOffset < 10; yearOffset++) {
            for (int monthOffset = 0; monthOffset < 12; monthOffset++) {
                // 現在の年月を計算
                int currentMonth = fiscalYearStartMonth + monthOffset;
                int currentYear = fiscalYearStartYear + yearOffset;
                
                // 月が13以上の場合、年を繰り上げて月を調整
                if (currentMonth > 12) {
                    currentYear += (currentMonth - 1) / 12;
                    currentMonth = ((currentMonth - 1) % 12) + 1;
                }
                
                // SALESテーブル
                SalesEntity salesEntity = new SalesEntity();
                salesEntity.setUserId(userId);
                salesEntity.setYear(currentYear);
                salesEntity.setMonth(currentMonth);
                salesEntity.setCreatedAt(now);
                salesEntity.setUpdatedAt(now);
                salesEntities.add(salesEntity);

                // GROSS_PROFITSテーブル
                GrossProfitEntity grossProfitEntity = new GrossProfitEntity();
                grossProfitEntity.setUserId(userId);
                grossProfitEntity.setYear(currentYear);
                grossProfitEntity.setMonth(currentMonth);
                grossProfitEntity.setCreatedAt(now);
                grossProfitEntity.setUpdatedAt(now);
                grossProfitEntities.add(grossProfitEntity);

                // OPERATING_PROFITSテーブル
                OperatingProfitEntity operatingProfitEntity = new OperatingProfitEntity();
                operatingProfitEntity.setUserId(userId);
                operatingProfitEntity.setYear(currentYear);
                operatingProfitEntity.setMonth(currentMonth);
                operatingProfitEntity.setCreatedAt(now);
                operatingProfitEntity.setUpdatedAt(now);
                operatingProfitEntities.add(operatingProfitEntity);
            }
        }

        // 一括保存
        salesRepository.saveAll(salesEntities);
        grossProfitRepository.saveAll(grossProfitEntities);
        operatingProfitRepository.saveAll(operatingProfitEntities);
        
        logger.info("Created {} initial records (10 years x 12 months) for userId: {}, fiscal year start: {}/{}", 
            salesEntities.size(), userId, fiscalYearStartYear, fiscalYearStartMonth);
    }
}