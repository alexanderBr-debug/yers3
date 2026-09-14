package com.entrenamiento.yers2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrenamiento.yers2.dto.JugadorRequestDto;
import com.entrenamiento.yers2.dto.JugadorResponseDto;
import com.entrenamiento.yers2.service.JugadorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/jugadores")

public class JugadorController {
    private final JugadorService jugadorService;
    
    @PostMapping ("/create")
    public ResponseEntity<JugadorResponseDto>createJugadores(@Valid @RequestBody JugadorRequestDto request){
        JugadorResponseDto response = jugadorService.createJugador(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping ("/Update/{id}")
    public ResponseEntity<JugadorResponseDto> updateJugadores(@Valid @RequestBody JugadorRequestDto request,@PathVariable Long id){
        JugadorResponseDto response = jugadorService.updateJugador(request,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping ("/get/{id}")
    public ResponseEntity<JugadorResponseDto>seeId (@PathVariable Long id){
        JugadorResponseDto response = jugadorService.seeId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping ("/seeAll")
    public ResponseEntity<List<JugadorResponseDto>> seeAll(){
        List<JugadorResponseDto> response = jugadorService.seeAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteJugador(@PathVariable Long id){
        String response = jugadorService.deleteJugador(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
