package com.entrenamiento.yers2.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data 
public class EntrenamientoRequestDTO {

    @NotNull (message = "Debe indicar el id del jugador")
    private Long jugadorId;

    @NotNull(message = "La potencia de tiro es obligatoria")
    @Positive (message = "Debe ser un valor positivo")
    private Double potenciaTiro;

    @NotNull(message = "La velocidad es obligatoria")
    @Positive(message = "Debe ser un valor positivo")
    private Double velocidad;

    @NotNull(message = "Los pases efectivos son obligatorios")
    @Positive(message = "Debe ser un valor positivo")
    private Integer pasesEfectivos;
}