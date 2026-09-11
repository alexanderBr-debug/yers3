package com.entrenamiento.yers2.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandlre {

    @ExceptionHandler (JugadorNoEncontradoException.class)
    public ResponseEntity<String> manejarJugadorNoEncontrado(JugadorNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler (JugadorDuplicadoException.class)
    public ResponseEntity<String>manejarJugadorDuplicado(JugadorDuplicadoException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler (InformacionInsuficienteException.class)
    public ResponseEntity<String> manejarInformacionInsuficiente(InformacionInsuficienteException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    
    
}
