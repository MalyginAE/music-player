package com.hse.nn.musicplayerdictionary.controller;

import com.hse.nn.musicplayerdictionary.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void exception(DataNotFoundException dataNotFoundException) {
        logger.debug(dataNotFoundException);
    }

}
