package com.dvgiang.electricitybillingsystem.dto.response;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResponseDTO {
    private Object data;
    private HttpHeaders headers;
    private HttpServletRequest request;
    private Object config;
    private HttpStatus status;
    private int statusCode;

    ResponseDTO(
        Object data,
        HttpHeaders headers,
        HttpServletRequest request,
        Object config,
        HttpStatus status,
        int statusCode
    ) {
        this.headers = headers;
        this.data = data;
        this.request = request;
        this.config = config;
        this.status = status;
        this.statusCode = statusCode;
    }
}
