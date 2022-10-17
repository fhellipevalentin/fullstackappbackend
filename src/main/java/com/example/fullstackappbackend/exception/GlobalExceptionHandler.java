package com.example.fullstackappbackend.exception;

// essa classe assume o papel de "gerenciadora" de ocorrencias de exceção
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//Indicar a annotation adequada para que a classe assuma o papel de gerenciador de ocorrências de exceção
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //Referenciar a annotation @ExceptionHandler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        //Referência da classe ErrorDetails para lidar com suas propriedades
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));

        //Estabelecer a expressão de retorno
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globeExceptionHandler(Exception ex, WebRequest request) {
        //Referência da classe ErrorDetails para lidar com suas propriedades
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));

        //Estabelecer a expressão de retorno
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
