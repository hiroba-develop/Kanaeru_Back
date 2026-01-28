package com.example.Kanaeru_Back.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    /**
     * バリデーションエラーハンドラー
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        Map<String, String> fieldErrors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
            logger.error("バリデーションエラー - フィールド: {}, メッセージ: {}", fieldName, errorMessage);
        });
        
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("error", "Validation Failed");
        errors.put("message", "入力値のバリデーションに失敗しました");
        errors.put("fieldErrors", fieldErrors);
        
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * JSONパースエラーハンドラー
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex) {
        logger.error("JSONパースエラー: {}", ex.getMessage(), ex);
        
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("error", "Bad Request");
        
        // 開発環境では詳細なエラーメッセージを返す
        if (isDevelopmentEnvironment()) {
            errors.put("message", "リクエストボディのJSONが正しくありません: " + ex.getMostSpecificCause().getMessage());
        } else {
            errors.put("message", "リクエストボディのJSONが正しくありません");
        }
        
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * 一般的な例外ハンドラー
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        logger.error("予期しないエラーが発生しました: {}", ex.getMessage(), ex);
        
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errors.put("error", "Internal Server Error");
        
        // 開発環境では詳細なエラーメッセージを返す（本番環境では内部情報を隠す）
        if (isDevelopmentEnvironment()) {
            errors.put("message", "サーバー内部エラーが発生しました: " + ex.getMessage());
        } else {
            errors.put("message", "サーバー内部エラーが発生しました。管理者にお問い合わせください。");
        }
        
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * 開発環境かどうかを判定
     */
    private boolean isDevelopmentEnvironment() {
        return "default".equals(activeProfile) || 
               "dev".equals(activeProfile) || 
               "local".equals(activeProfile);
    }
}
