package com.stackroute.soulmateservice.Exceptions;

public class UserAlreadyExistsException extends Exception{
    private String message;

    public UserAlreadyExistsException(String message) {
        super();
        this.message = message;
    }

    public UserAlreadyExistsException() {
    }
}
