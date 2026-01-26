package com.example.Kanaeru_Back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Set;

@Service
public class S3Service {

    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.base-url}")
    private String baseUrl;

    // 許可する画像形式
    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
        "image/jpeg",
        "image/jpg", 
        "image/png",
        "image/gif"
    );

    // 許可する拡張子
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(
        "jpg", "jpeg", "png", "gif"
    );

    public String uploadImage(String userId, MultipartFile file) throws IOException {
        // 1. ファイルの基本的なバリデーション
        validateFile(file);

        // 2. ファイル名と拡張子の取得・サニタイズ
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        }

        // 3. 拡張子のバリデーション
        String extensionWithoutDot = extension.startsWith(".") ? extension.substring(1) : extension;
        if (!ALLOWED_EXTENSIONS.contains(extensionWithoutDot)) {
            throw new IllegalArgumentException(
                "許可されていないファイル拡張子です。jpg、jpeg、png、gifのみ使用できます"
            );
        }

        // 4. Content-Typeのバリデーション
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType.toLowerCase())) {
            throw new IllegalArgumentException(
                "許可されていない画像形式です。JPEG、PNG、GIFのみアップロードできます"
            );
        }

        // 5. S3にアップロードするファイル名を生成（ユーザーIDベース）
        String fileName = sanitizeUserId(userId) + extension;
        String key = "kanaeru-folder/uploads/profile-icons/" + fileName;

        // 6. S3へのアップロード
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(contentType)
                    .contentLength(file.getSize())
                    // セキュリティヘッダーを追加（オプション）
                    .contentDisposition("inline; filename=\"" + sanitizeFilename(originalFilename) + "\"")
                    .build();

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            return baseUrl + "/uploads/profile-icons/" + fileName;
        } catch (Exception e) {
            throw new IOException("S3へのアップロード中にエラーが発生しました: " + e.getMessage(), e);
        }
    }

    /**
     * ファイルの基本的なバリデーション
     */
    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("画像ファイルが選択されていません");
        }

        if (file.getOriginalFilename() == null || file.getOriginalFilename().isEmpty()) {
            throw new IllegalArgumentException("ファイル名が不正です");
        }
    }

    /**
     * ユーザーIDのサニタイズ
     */
    private String sanitizeUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("ユーザーIDが不正です");
        }
        // 英数字、ハイフン、アンダースコアのみ許可
        return userId.replaceAll("[^a-zA-Z0-9_-]", "_");
    }

    /**
     * ファイル名のサニタイズ
     */
    private String sanitizeFilename(String filename) {
        if (filename == null) {
            return "image";
        }
        // パスセパレータや危険な文字を除去
        String sanitized = filename.replaceAll("[^a-zA-Z0-9._-]", "_");
        
        // ファイル名が長すぎる場合は切り詰める
        if (sanitized.length() > 255) {
            String extension = "";
            int lastDot = sanitized.lastIndexOf('.');
            if (lastDot > 0) {
                extension = sanitized.substring(lastDot);
                sanitized = sanitized.substring(0, lastDot);
            }
            sanitized = sanitized.substring(0, Math.min(sanitized.length(), 255 - extension.length())) + extension;
        }
        
        return sanitized;
    }
}