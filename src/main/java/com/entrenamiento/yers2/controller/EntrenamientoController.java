package com.entrenamiento.yers2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrenamiento.yers2.dto.EntrenamientoRequestDTO;
import com.entrenamiento.yers2.dto.EntrenamientoResponseDto;
import com.entrenamiento.yers2.dto.JugadoresTitularesDto;
import com.entrenamiento.yers2.service.EntrenamientoService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/entrenamiento")
public class EntrenamientoController {

    private final EntrenamientoService entrenamientoService;

    @PostMapping ("/create")
    public ResponseEntity<EntrenamientoResponseDto> entrenamiento(@Validated @RequestBody EntrenamientoRequestDTO request){
        EntrenamientoResponseDto response = entrenamientoService.crearEntrenamiento(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping ("/titulares")
    public ResponseEntity<List<JugadoresTitularesDto>> titulares(){
        List<JugadoresTitularesDto> response = entrenamientoService.equipoTitular();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
