package com.entrenamiento.yers2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entrenamiento.yers2.entity.Entrenamiento;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento,Long>{
    List<Entrenamiento> findByJugadorId(Long jugadorId);
    
}
