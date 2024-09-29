package com.dvgiang.electricitybillingsystem.exception;

import org.springframework.http.HttpStatus;

public class CustomException {
    private final String message;
    private final Throwable throwable;
    private final int httpStatusCode;
    private final HttpStatus httpStatus;

    public CustomException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatusCode = httpStatus.value();
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
