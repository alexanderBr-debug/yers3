package com.entrenamiento.yers2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrenamiento.yers2.dto.JugadorRequestDto;
import com.entrenamiento.yers2.dto.JugadorResponseDto;
import com.entrenamiento.yers2.service.JugadorService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("jugadores")

public class JugadorController {
    private final JugadorService jugadorService;
    
    @PostMapping ("/create")
    public ResponseEntity<JugadorResponseDto>createJugadores(@Validated @RequestBody JugadorRequestDto request){
        JugadorResponseDto response = jugadorService.createJugador(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
