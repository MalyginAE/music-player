package com.hse.nn.musicplayerdictionary.exception;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String trackNotFound) {
        super(trackNotFound);
    }
}
