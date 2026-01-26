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