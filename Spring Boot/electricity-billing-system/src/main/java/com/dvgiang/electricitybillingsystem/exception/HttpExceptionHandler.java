package com.dvgiang.electricitybillingsystem.exception;

import com.dvgiang.electricitybillingsystem.dto.response.FailResponseDTO;
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
        return FailResponseDTO.buildResponse(
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                null,
                e.getCause()
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        return FailResponseDTO.buildResponse(
                HttpStatus.BAD_REQUEST,
                Objects.requireNonNull(e.getFieldError()).getDefaultMessage(),
                e.getBody(),
                e.getCause()
        );
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e) {
        log.warn("An unauthorized user is trying to log in");
        return FailResponseDTO.buildResponse(
                HttpStatus.UNAUTHORIZED,
                "Account or password is incorrect!",
                e.getMessage(),
                e.getCause()
        );
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException e) {
        log.warn(e.getMessage());
        return FailResponseDTO.buildResponse(
                HttpStatus.CONFLICT,
                e.getMessage(),
                null,
                e.getCause()
        );
    }
}
