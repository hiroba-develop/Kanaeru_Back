package com.example.Kanaeru_Back.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 画像アップロードに関するグローバル例外ハンドラー
 */
@RestControllerAdvice
public class ImageUploadExceptionHandler {

    /**
     * IllegalArgumentException（バリデーションエラー）のハンドリング
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("responseStatus", 0);
        response.put("message", e.getMessage());
        response.put("imageUrl", null);
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * ImageUploadException（カスタム例外）のハンドリング
     */
    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<Map<String, Object>> handleImageUploadException(ImageUploadException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("responseStatus", 0);
        response.put("message", e.getMessage());
        response.put("imageUrl", null);
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * IOException（ファイル読み込みエラー）のハンドリング
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, Object>> handleIOException(IOException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("responseStatus", 0);
        response.put("message", "画像のアップロード中にエラーが発生しました");
        response.put("imageUrl", null);
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    /**
     * MaxUploadSizeExceededException（ファイルサイズ超過）のハンドリング
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, Object>> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("responseStatus", 0);
        response.put("message", "ファイルサイズが大きすぎます。5MB以下のファイルをアップロードしてください");
        response.put("imageUrl", null);
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * RuntimeException（その他の実行時エラー）のハンドリング
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("responseStatus", 0);
        response.put("message", "予期しないエラーが発生しました: " + e.getMessage());
        response.put("imageUrl", null);
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}