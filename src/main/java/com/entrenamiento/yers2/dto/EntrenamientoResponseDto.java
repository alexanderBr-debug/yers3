package com.entrenamiento.yers2.dto;

import java.time.LocalDate;

import lombok.Data;

@Data 
public class EntrenamientoResponseDto {
    private Long id;
    private String nombreJugador;
    private Double potenciaTiro;
    private Double velocidad;
    private Integer pasesEfectivos;
    private Double resultado;
    private LocalDate fecha;
}