package com.dvgiang.electricitybillingsystem.exception;

import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.websocket.AuthenticationException;
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
public class CustomExceptionHandler {
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

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException e) {
        Map <String, Object> forbidden = new HashMap<>();
        forbidden.put("message", e.getMessage());
        forbidden.put("httpStatus", HttpStatus.FORBIDDEN);
        forbidden.put("httpStatusCode", HttpStatus.FORBIDDEN.value());
        forbidden.put("details", null);

        log.warn(e.getMessage());

        return new ResponseEntity<>(forbidden, HttpStatus.FORBIDDEN);
    }
}
