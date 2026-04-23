package com.example.Kanaeru_Back.templates;

public enum EmailTemplate {
    PASSWORD_RESET(
        "【kanaeru】パスワードリセットのご案内",
        "{name} 様\n\n" +
        "いつもkanaeruをご利用いただき、ありがとうございます。\n\n" +
        "パスワードリセットのリクエストを承りました。\n" +
        "以下のURLより、新しいパスワードの設定をお願いいたします。\n\n" +
        "{resetUrl}\n\n" +
        "※このURLの有効期限は{expirationMinutes}分間となっております。\n" +
        "※本リクエストにお心当たりがない場合は、このメールを破棄していただきますようお願いいたします。\n\n" +
        "ご不明な点がございましたら、お気軽にお問い合わせください。\n\n" +
        "━━━━━━━━━━━━━━━━━━━━\n" +
        "kanaeru運営チーム"
    ),
    
    WELCOME(
        "【kanaeru】会員登録が完了しました",
        "{name} 様\n\n" +
        "この度は、kanaeruにご登録いただき、誠にありがとうございます。\n\n" +
        "会員登録のお手続きが正常に完了いたしました。\n" +
        "以下のURLより、ログインしてサービスをご利用いただけます。\n\n" +
        "{loginUrl}\n\n" +
        "ご利用にあたりご不明な点やお困りのことがございましたら、\n" +
        "お気軽に下記サポートまでお問い合わせください。\n\n" +
        "今後とも、kanaeruをどうぞよろしくお願いいたします。\n\n" +
        "━━━━━━━━━━━━━━━━━━━━\n" +
        "kanaeru運営チーム\n" +
        "サポート窓口: {supportEmail}"
    ),
    
    PASSWORD_CHANGED(
        "【kanaeru】パスワードが変更されました",
        "{name} 様\n\n" +
        "いつもkanaeruをご利用いただき、ありがとうございます。\n\n" +
        "パスワードの変更が正常に完了いたしました。\n\n" +
        "※本変更にお心当たりがない場合は、第三者による不正アクセスの可能性がございます。\n" +
        "お手数をおかけいたしますが、至急下記サポートまでご連絡くださいますようお願いいたします。\n\n" +
        "━━━━━━━━━━━━━━━━━━━━\n" +
        "kanaeru運営チーム\n" +
        "サポート窓口: {supportEmail}"
    ),
    
    ACCOUNT_DELETED(
        "【kanaeru】退会手続きが完了しました",
        "{name} 様\n\n" +
        "この度は、kanaeruをご利用いただき、誠にありがとうございました。\n\n" +
        "退会のお手続きが正常に完了いたしました。\n\n" +
        "ご利用期間中にご不便をおかけした点や、\n" +
        "改善のご要望などがございましたら、\n" +
        "下記サポートまでお気軽にお寄せいただけますと幸いです。\n\n" +
        "またのご利用を、心よりお待ちしております。\n\n" +
        "━━━━━━━━━━━━━━━━━━━━\n" +
        "kanaeru運営チーム\n" +
        "サポート窓口: {supportEmail}"
    ),
    SUBSCRIPTION_UPGRADED(
        "【kanaeru】有料プランへのアップグレードが完了しました",
        "{name} 様\n\n" +
        "いつもkanaeruをご利用いただき、ありがとうございます。\n\n" +
        "有料プランへのアップグレードが正常に完了いたしました。\n\n" +
        "■ 申込日：{upgradeDate}\n" +
        "■ 月額料金：¥2,480（税込）\n" +
        "■ 請求サイクル：毎月自動更新\n\n" +
        "これより、以下の機能がご利用いただけます。\n" +
        "・メンターとの相談チャット\n" +
        "・メンターからのアドバイス\n\n" +
        "※ 有料プランはいつでも解約可能です。解約をご希望の場合は、\n" +
        "設定画面の「プラン変更」よりお手続きいただけます。\n" +
        "解約後も契約期間終了日まで引き続きご利用いただけます。\n\n" +
        "ご不明な点がございましたら、お気軽にお問い合わせください。\n\n" +
        "━━━━━━━━━━━━━━━━━━━━\n" +
        "kanaeru運営チーム\n" +
        "サポート窓口: {supportEmail}"
    ),
    
    SUBSCRIPTION_CANCELED(
        "【kanaeru】有料プランの解約申請が完了しました",
        "{name} 様\n\n" +
        "いつもkanaeruをご利用いただき、ありがとうございます。\n\n" +
        "有料プランの解約申請が完了いたしました。\n\n" +
        "■ 解約申請日：{canceledDate}\n" +
        "■ 有料プラン終了日：{periodEnd}\n\n" +
        "{periodEnd}までは引き続き有料プランの全機能をご利用いただけます。\n" +
        "期間終了後は自動的に無料プランへ移行いたします。\n\n" +
        "またのご利用を心よりお待ちしております。\n\n" +
        "━━━━━━━━━━━━━━━━━━━━\n" +
        "kanaeru運営チーム\n" +
        "サポート窓口: {supportEmail}"
    ),
    ADMIN_USER_REGISTERED(
        "【kanaeru管理】新規会員登録のお知らせ",
        "新規ユーザーが登録されました。\n" +
        "管理者ユーザーでログインをしてご確認ください。\n\n" +
        "ログインURL：{loginUrl}\n\n" +
        "■ 氏名：{name}\n" +
        "■ メールアドレス：{email}\n" +
        "■ 登録日時：{registeredAt}\n" +
        "━━━━━━━━━━━━━━━━━━━━\n" +
        "kanaeru運営チーム"
    ),
    
    ADMIN_SUBSCRIPTION_UPGRADED(
        "【kanaeru管理】有料プランアップグレードのお知らせ",
        "ユーザーが有料プランにアップグレードしました。\n" +
        "管理者ユーザーでログインをしてご確認ください。\n\n" +
        "ログインURL：{loginUrl}\n\n" +
        "■ 氏名：{name}\n" +
        "■ メールアドレス：{email}\n" +
        "■ アップグレード日時：{upgradedAt}\n" +
        "━━━━━━━━━━━━━━━━━━━━\n" +
        "kanaeru運営チーム"
    ),

    CHAT_NOTIFICATION(
        "【kanaeru】メンターからメッセージが届いています",
        "{name} 様\n\n" +
        "メンター {senderName} からメッセージが届いています。\n\n" +
        "アプリからご確認ください。\n" +
        "{appUrl}/support\n\n" +
        "※このメールは自動送信です。返信はできません。\n\n" +
        "━━━━━━━━━━━━━━━━━━━━\n" +
        "kanaeru運営チーム\n" +
        "サポート窓口: {supportEmail}"
    ),
    
    ADVICE_NOTIFICATION(
        "【kanaeru】メンターからアドバイスが届いています",
        "{name} 様\n\n" +
        "メンター {senderName} からアドバイスが登録されました。\n\n" +
        "アプリからご確認ください。\n" +
        "{appUrl}/support\n\n" +
        "※このメールは自動送信です。返信はできません。\n\n" +
        "━━━━━━━━━━━━━━━━━━━━\n" +
        "kanaeru運営チーム\n" +
        "サポート窓口: {supportEmail}"
    );

    private final String subject;
    private final String body;

    EmailTemplate(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}