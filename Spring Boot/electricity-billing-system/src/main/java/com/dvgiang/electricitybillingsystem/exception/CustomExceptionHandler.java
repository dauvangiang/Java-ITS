package com.dvgiang.electricitybillingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
