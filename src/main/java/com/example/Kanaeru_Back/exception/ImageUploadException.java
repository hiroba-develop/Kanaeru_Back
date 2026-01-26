package com.example.Kanaeru_Back.exception;

/**
 * 画像アップロードに関する例外
 */
public class ImageUploadException extends RuntimeException {
    
    public ImageUploadException(String message) {
        super(message);
    }
    
    public ImageUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}