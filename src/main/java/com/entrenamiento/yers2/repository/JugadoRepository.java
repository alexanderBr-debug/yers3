package com.entrenamiento.yers2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entrenamiento.yers2.entity.Jugador;

public interface JugadoRepository extends JpaRepository<Jugador,Long> {

    Optional<Jugador> findFirstByEmail(String email);

    
}
