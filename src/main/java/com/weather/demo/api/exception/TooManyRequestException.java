package com.weather.demo.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooManyRequestException extends RuntimeException {

    private static final long serialVersionUID = -3916525550413865316L;

    public TooManyRequestException() {
        super();
    }

    public TooManyRequestException(String message) {
        super(message);
    }

    public TooManyRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
