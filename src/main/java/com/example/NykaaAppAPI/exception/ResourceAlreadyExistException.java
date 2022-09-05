package com.example.NykaaAppAPI.exception;

public class ResourceAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ResourceAlreadyExistException(String msg) { super(msg);
    }
}
