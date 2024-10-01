package com.dvgiang.electricitybillingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//Đánh dấu lớp xử lý các ngoại lệ toàn cục xảy ra trong các controller
@ControllerAdvice
public class CustomExceptionHandler {
    //Bắt ngoại lệ NotFoundException
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException (NotFoundException notFoundException) {
        CustomException customException = new CustomException(
                notFoundException.getMessage(),
                notFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValodationException(MethodArgumentNotValidException e) {
        Map<String, Object> validException = new HashMap<>();
        validException.put("httpStatus", e.getStatusCode());
        validException.put("throwable", e.getCause());
        validException.put("details", e.getBody());
        validException.put("message", Objects.requireNonNull(e.getFieldError()).getDefaultMessage());

        return new ResponseEntity<>(validException, HttpStatus.BAD_REQUEST);
    }
}
