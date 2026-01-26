package com.example.Kanaeru_Back.service.setting;

import com.example.Kanaeru_Back.entity.SettingEntity;
import com.example.Kanaeru_Back.repository.SettingRepository;
import com.example.Kanaeru_Back.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserImageService {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private SettingRepository settingRepository;

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

    // ファイルサイズの上限（5MB）
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    // 画像の最小サイズ
    private static final int MIN_WIDTH = 100;
    private static final int MIN_HEIGHT = 100;

    // 画像の最大サイズ
    private static final int MAX_WIDTH = 8000;
    private static final int MAX_HEIGHT = 8000;

    @Transactional
    public String uploadUserImage(String userId, MultipartFile imageFile) throws IOException {
        // 1. ファイルが空でないかチェック
        if (imageFile == null || imageFile.isEmpty()) {
            throw new IllegalArgumentException("画像ファイルが選択されていません");
        }

        // 2. ファイルサイズのチェック
        if (imageFile.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException(
                String.format("ファイルサイズが大きすぎます。%dMB以下のファイルをアップロードしてください", 
                    MAX_FILE_SIZE / (1024 * 1024))
            );
        }

        // 3. Content-Typeのチェック
        String contentType = imageFile.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType.toLowerCase())) {
            throw new IllegalArgumentException(
                "許可されていない画像形式です。JPEG、PNG、GIFのみアップロードできます"
            );
        }

        // 4. ファイル拡張子のチェック
        String originalFilename = imageFile.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("ファイル名が不正です");
        }

        String extension = getFileExtension(originalFilename).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new IllegalArgumentException(
                "許可されていないファイル拡張子です。jpg、jpeg、png、gifのみ使用できます"
            );
        }

        // 5. マジックナンバー（ファイルシグネチャ）の検証
        byte[] fileBytes = imageFile.getBytes();
        if (!isValidImageMagicNumber(fileBytes, contentType)) {
            throw new IllegalArgumentException("ファイルの内容が画像形式と一致しません");
        }

        // 6. 画像の寸法チェック
        validateImageDimensions(fileBytes);

        // 7. ファイル名のサニタイズ
        String sanitizedFilename = sanitizeFilename(originalFilename);

        // 8. S3にアップロード
        String imageUrl = s3Service.uploadImage(userId, imageFile);

        // 9. データベース更新
        Optional<SettingEntity> settingOptional = settingRepository.findByUserId(userId);
        SettingEntity setting;
        
        if (settingOptional.isPresent()) {
            setting = settingOptional.get();
        } else {
            throw new RuntimeException("設定情報が見つかりません: userId=" + userId);
        }

        setting.setUserImageUrl(imageUrl);
        setting.setUpdatedAt(LocalDateTime.now());
        settingRepository.save(setting);

        return imageUrl;
    }

    /**
     * ファイル拡張子を取得
     */
    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "";
        }
        return filename.substring(lastDotIndex + 1);
    }

    /**
     * マジックナンバー（ファイルシグネチャ）の検証
     */
    private boolean isValidImageMagicNumber(byte[] bytes, String contentType) {
        if (bytes == null || bytes.length < 12) {
            return false;
        }

        // JPEG: FF D8 FF
        if (contentType.contains("jpeg") || contentType.contains("jpg")) {
            return bytes[0] == (byte) 0xFF && 
                   bytes[1] == (byte) 0xD8 && 
                   bytes[2] == (byte) 0xFF;
        }

        // PNG: 89 50 4E 47 0D 0A 1A 0A
        if (contentType.contains("png")) {
            return bytes[0] == (byte) 0x89 &&
                   bytes[1] == (byte) 0x50 &&
                   bytes[2] == (byte) 0x4E &&
                   bytes[3] == (byte) 0x47 &&
                   bytes[4] == (byte) 0x0D &&
                   bytes[5] == (byte) 0x0A &&
                   bytes[6] == (byte) 0x1A &&
                   bytes[7] == (byte) 0x0A;
        }

        // GIF: 47 49 46 38 (GIF8)
        if (contentType.contains("gif")) {
            return bytes[0] == (byte) 0x47 &&
                   bytes[1] == (byte) 0x49 &&
                   bytes[2] == (byte) 0x46 &&
                   bytes[3] == (byte) 0x38;
        }

        return false;
    }

    /**
     * 画像の寸法を検証
     */
    private void validateImageDimensions(byte[] imageBytes) throws IOException {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            BufferedImage image = ImageIO.read(bais);
            
            if (image == null) {
                throw new IllegalArgumentException("画像の読み込みに失敗しました");
            }

            int width = image.getWidth();
            int height = image.getHeight();

            // 最小サイズチェック
            if (width < MIN_WIDTH || height < MIN_HEIGHT) {
                throw new IllegalArgumentException(
                    String.format("画像サイズが小さすぎます。%dx%dピクセル以上の画像をアップロードしてください",
                        MIN_WIDTH, MIN_HEIGHT)
                );
            }

            // 最大サイズチェック
            if (width > MAX_WIDTH || height > MAX_HEIGHT) {
                throw new IllegalArgumentException(
                    String.format("画像サイズが大きすぎます。%dx%dピクセル以下の画像をアップロードしてください",
                        MAX_WIDTH, MAX_HEIGHT)
                );
            }
        } catch (IOException e) {
            throw new IOException("画像の検証中にエラーが発生しました", e);
        }
    }

    /**
     * ファイル名のサニタイズ（危険な文字を除去）
     */
    private String sanitizeFilename(String filename) {
        // パスセパレータや危険な文字を除去
        String sanitized = filename.replaceAll("[^a-zA-Z0-9._-]", "_");
        
        // ファイル名が長すぎる場合は切り詰める（拡張子は保持）
        if (sanitized.length() > 255) {
            String extension = getFileExtension(sanitized);
            int maxNameLength = 255 - extension.length() - 1;
            String nameWithoutExt = sanitized.substring(0, sanitized.lastIndexOf('.'));
            sanitized = nameWithoutExt.substring(0, Math.min(nameWithoutExt.length(), maxNameLength)) 
                        + "." + extension;
        }
        
        return sanitized;
    }
}