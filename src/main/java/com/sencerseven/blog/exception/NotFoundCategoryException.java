package com.sencerseven.blog.exception;

public class NotFoundCategoryException extends RuntimeException {

    public NotFoundCategoryException() {
    }

    public NotFoundCategoryException(String message) {
        super(message);
    }

    public NotFoundCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
