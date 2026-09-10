package com.entrenamiento.yers2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.entrenamiento.yers2.Exception.JugadorDuplicadoException;
import com.entrenamiento.yers2.Exception.JugadorNoEncontradoException;
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

    public JugadorResponseDto updateJugador(JugadorRequestDto request,Long id){
        Jugador jugador = jugadoRepository.findById(id)
        .orElseThrow(() -> new JugadorNoEncontradoException("el jugador no exixte en la base de datos"));

        jugador.setName(request.getName());
        jugador.setEmail(request.getEmail());
        jugadoRepository.save(jugador);

        JugadorResponseDto response = new JugadorResponseDto();
        response.setId(jugador.getId());
        response.setEmail(jugador.getEmail());
        response.setName(jugador.getName());
        return response;
    }

    public List<JugadorResponseDto>seeAll(){
        List<Jugador> jugador = jugadoRepository.findAll();
        List<JugadorResponseDto> response = new ArrayList<>();

        for (Jugador jugadores  : jugador) {
            JugadorResponseDto dto = new JugadorResponseDto();
            dto.setId(jugadores.getId());
            dto.setEmail(jugadores.getEmail());
            dto.setName(jugadores.getName());
            response.add(dto);
            
        }
        return response;
        
    }

    public JugadorResponseDto seeId(Long id){

        Jugador jugador = jugadoRepository.findById(id)
        .orElseThrow(() -> new JugadorNoEncontradoException("el jugador no exiate en la base de datos"));

        JugadorResponseDto response = new JugadorResponseDto();
        response.setId(jugador.getId());
        response.setEmail(jugador.getEmail());
        response.setName(jugador.getName());
        return response;
    }

    public String deleteJugador(long id){
        Jugador jugador = jugadoRepository.findById(id)
        .orElseThrow(() -> new JugadorNoEncontradoException("el jugador no exiate en la base de datos"));

        jugadoRepository.delete(jugador);
        return "jugador eliminado ";
        

    }
    
}
