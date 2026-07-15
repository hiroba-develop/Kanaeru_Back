package com.example.Kanaeru_Back.service.slack;

public class SlackOAuthStateInvalidException extends RuntimeException {
    public SlackOAuthStateInvalidException(String message) {
        super(message);
    }

    public SlackOAuthStateInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
