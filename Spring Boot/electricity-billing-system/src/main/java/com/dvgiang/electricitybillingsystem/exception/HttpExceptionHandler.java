package com.dvgiang.electricitybillingsystem.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
//Đánh dấu lớp xử lý các ngoại lệ toàn cục xảy ra trong các controller
@ControllerAdvice
public class HttpExceptionHandler {
    //Bắt ngoại lệ NotFoundException
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException (NotFoundException e) {
        Map<String, Object> notFound = new HashMap<>();
        notFound.put("httpStatus", HttpStatus.NOT_FOUND);
        notFound.put("httpStatusCode", HttpStatus.NOT_FOUND.value());
        notFound.put("throwable", e.getCause());
        notFound.put("details", null);
        notFound.put("message", e.getMessage());

        return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, Object> inValid = new HashMap<>();
        inValid.put("httpStatus", e.getStatusCode());
        inValid.put("throwable", e.getCause());
        inValid.put("details", e.getBody());
        inValid.put("message", Objects.requireNonNull(e.getFieldError()).getDefaultMessage());

        return new ResponseEntity<>(inValid, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e) {
        Map <String, Object> unauthor = new HashMap<>();
        unauthor.put("message", "Account or password is incorrect!");
        unauthor.put("httpStatus", HttpStatus.UNAUTHORIZED);
        unauthor.put("httpStatusCode", HttpStatus.UNAUTHORIZED.value());
        unauthor.put("details", e.getMessage());

        log.warn("An unauthorized user is trying to log in");

        return new ResponseEntity<>(unauthor, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException e) {
        Map <String, Object> conflict = new HashMap<>();
        conflict.put("message", e.getMessage());
        conflict.put("httpStatus", HttpStatus.CONFLICT);
        conflict.put("httpStatusCode", HttpStatus.CONFLICT.value());
        conflict.put("details", null);

        log.warn(e.getMessage());

        return new ResponseEntity<>(conflict, HttpStatus.CONFLICT);
    }
}
