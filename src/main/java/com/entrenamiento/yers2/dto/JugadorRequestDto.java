package com.entrenamiento.yers2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data 
public class JugadorRequestDto {

    @NotBlank (message = ("el nombre es obligatorio"))
    private String name;

    @Email (message = ("debe tener formato de email"))
    private  String email;
    
}
