package ru.vk.competition.minbenchmark.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public void handleRfidAccessDeniedException(Exception ex) {
       log.info("exception from EXCEPTION HANDLER " + ex.getMessage(), ex.getCause(), ex.getStackTrace());
    }

    @ExceptionHandler({QueryNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleRfidAccessDeniedException(QueryNotFoundException ex) {
        log.info("exception from EXCEPTION HANDLER " + ex.getMessage(), ex.getCause(), ex.getStackTrace());
    }

}
