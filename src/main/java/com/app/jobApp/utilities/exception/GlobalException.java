package com.app.jobApp.utilities.exception;

public class GlobalException extends RuntimeException{
    public GlobalException(){
        super("Resource not found");
    }
}