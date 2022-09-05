package com.example.NykaaAppAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public UserExistException(String msg) {
        super(msg);
    }
}