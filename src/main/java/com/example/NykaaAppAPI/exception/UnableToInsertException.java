package com.example.NykaaAppAPI.exception;

public class UnableToInsertException extends RuntimeException {
    public UnableToInsertException(String msg) {
        super(msg);
    }
}