package com.sencerseven.blog.exception;

public class NotFoundPostInCategoryException extends RuntimeException {

    public NotFoundPostInCategoryException() {
    }

    public NotFoundPostInCategoryException(String message) {
        super(message);
    }

    public NotFoundPostInCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
