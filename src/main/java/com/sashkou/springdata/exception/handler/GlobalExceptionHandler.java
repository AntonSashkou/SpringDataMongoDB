package com.sashkou.springdata.exception.handler;

import com.sashkou.springdata.exception.DataNotFoundException;
import com.sashkou.springdata.exception.ORMException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({DataNotFoundException.class})
    public ExceptionResponse onUserNotFoundException(DataNotFoundException ex) {
        log.error("DocumentNotFoundException {}:", ex.getMessage());

        return new ExceptionResponse(List.of(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ORMException.class})
    public ExceptionResponse onORMException(ORMException ex) {
        log.error("ORMException {}:", ex.getMessage());

        return new ExceptionResponse(List.of(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ExceptionResponse onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException {}:", ex.getMessage());

        List<String> errorMessages = ex.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ExceptionResponse(errorMessages);
    }
}
