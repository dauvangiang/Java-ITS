package com.dvgiang.electricitybillingsystem.exception;

import com.dvgiang.electricitybillingsystem.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException (NotFoundException e) {
        return new ResponseEntity<>(
                BaseResponse.fail(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                BaseResponse.fail(Objects.requireNonNull(e.getFieldError()).getDefaultMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e) {
        log.warn("Có người dùng không xác định đang cố gắng đăng nhập!");
        return new ResponseEntity<>(
                BaseResponse.fail("Tên đăng nhập hoặc mật khẩu không đúng!"),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException e) {
        return new ResponseEntity<>(
                BaseResponse.fail(e.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(
                BaseResponse.fail(e.getMessage()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(value = AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<Object> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException e) {
        return new ResponseEntity<>(
                BaseResponse.fail(e.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }
}
