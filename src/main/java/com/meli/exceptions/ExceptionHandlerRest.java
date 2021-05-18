package com.meli.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerRest extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(ExceptionBusiness.class)
    protected ResponseEntity<Object>handleErrorPosition(ExceptionBusiness ex){

        return  buildResponseEntity(createNotFound(ex.getMessage()));

    }


	@ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object>handleErrorPosition(RuntimeException ex){

        return  buildResponseEntity(createNotFound("Error Inesperado"));

    }


    private ResponseEntity<Object>buildResponseEntity(ErrorServ error){
        return  new ResponseEntity<>(error, error.getHttpStatus());


    }

    private ErrorServ createNotFound(String message){
        return  new ErrorServ(NOT_FOUND, message);
    };
}