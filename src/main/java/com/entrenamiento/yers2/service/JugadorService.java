package com.entrenamiento.yers2.service;

import org.springframework.stereotype.Service;

import com.entrenamiento.yers2.Exception.JugadorDuplicadoException;
import com.entrenamiento.yers2.dto.JugadorRequestDto;
import com.entrenamiento.yers2.dto.JugadorResponseDto;
import com.entrenamiento.yers2.entity.Jugador;
import com.entrenamiento.yers2.repository.JugadoRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class JugadorService {

   private final JugadoRepository jugadoRepository;

    public JugadorResponseDto createJugador(JugadorRequestDto request){
         jugadoRepository.findFirstByEmail(request.getEmail())
        .ifPresent((jugador) -> {
            throw new JugadorDuplicadoException("el jugador ya existe");
        });

        Jugador jugador = new Jugador();
        jugador.setEmail(request.getEmail());
        jugador.setName(request.getName());
        jugadoRepository.save(jugador);

        JugadorResponseDto response = new JugadorResponseDto();
        response.setId(jugador.getId());
        response.setName(jugador.getName());
        response.setEmail(jugador.getEmail());
        return response;
    }
    
}
