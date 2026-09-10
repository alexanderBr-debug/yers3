package com.entrenamiento.yers2.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity 
@Data 
@NoArgsConstructor 
public class Entrenamiento {

@Id 
@GeneratedValue (strategy = GenerationType.IDENTITY)
@Column (name = ("id"))
private Long id;

private Double potenciaTiro;
private Double velocidad;
private Integer pasesEfectivos;
private Double resultado;
private LocalDate fecha;

@ManyToOne 
@JoinColumn (name = ("jugador_id"))
private  Jugador jugador;
}
