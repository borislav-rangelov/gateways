package com.example.demo.model;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ErrorModel<T> {
    private final String timestamp;
    private final String path;
    private int code;
    private final T message;

    public ErrorModel(String path, int code, T message) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.path = path;
        this.code = code;
        this.message = message;
    }

    public static <T> ErrorModel<T> badRequest(HttpServletRequest request, T message) {
        return new ErrorModel<>(request.getRequestURI(), 400, message);
    }
}
