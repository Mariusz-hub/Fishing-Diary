package com.fisher.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(RuntimeException e, WebRequest request){
        String param = request.getParameter("id");
        ExceptionBody exceptionBody = new ExceptionBody.Builder()
                .message(e.getMessage() + " id: " + param)
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getDescription(true))
                .timesstamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, exceptionBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);


    }

}
