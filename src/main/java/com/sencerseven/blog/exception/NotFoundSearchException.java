package com.sencerseven.blog.exception;

public class NotFoundSearchException extends RuntimeException {

    public NotFoundSearchException() {
    }

    public NotFoundSearchException(String message) {
        super(message);
    }

    public NotFoundSearchException(String message, Throwable cause) {
        super(message, cause);
    }
}
