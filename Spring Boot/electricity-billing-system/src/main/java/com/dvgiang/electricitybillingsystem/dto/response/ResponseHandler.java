package com.dvgiang.electricitybillingsystem.dto.response;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder
            (Object responseObj, HttpStatus httpStatus, HttpHeaders httpHeaders, HttpServletRequest request)
    {
        Map<String, Object> response = new HashMap<>();
        response.put("data", responseObj);
        response.put("status", httpStatus.value());
        response.put("statusText", httpStatus.name());
//        response.put("headers", httpHeaders);
//        response.put("config", config);
        response.put("request", request);

        return new ResponseEntity<>(response, httpStatus);
    }
}
