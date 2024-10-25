package com.dvgiang.electricitybillingsystem.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class FailResponseDTO {
    private String status;
    private int statusCode;
    private String message;
    private Object details;
    private Throwable throwable;

    public FailResponseDTO(){}

    public FailResponseDTO(HttpStatus status, String message, Object details, Throwable throwable) {
        this.setStatus(status.name());
        this.setStatusCode(status.value());
        this.setMessage(message);
        this.setDetails(details);
        this.setThrowable(throwable);
    }

    public static ResponseEntity<Object> buildResponse(HttpStatus status, String message, Object details, Throwable throwable) {
        FailResponseDTO body = new FailResponseDTO();
        body.setStatus(status.name());
        body.setStatusCode(status.value());
        body.setMessage(message);
        body.setDetails(details);
        body.setThrowable(throwable);

        return new ResponseEntity<>(body, status);
    }
}
