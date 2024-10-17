package com.dvgiang.electricitybillingsystem.dto.response;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class ResponseBuilder {
    private Object data;
    private HttpHeaders headers;
    private HttpServletRequest request;
    private Object config;
    private HttpStatus status;
    private int statusCode;

    public ResponseBuilder data(Object data) {
        this.data = data;
        return this;
    }

    public ResponseBuilder headers(HttpHeaders headers) {
        this.headers = headers;
        return this;
    }

    public ResponseBuilder request(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    public ResponseBuilder config(Object config) {
        this.config = config;
        return this;
    }

    public ResponseBuilder status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ResponseBuilder statusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Response build() {
        return new Response(data, headers, request, config, status, statusCode);
    }
}
