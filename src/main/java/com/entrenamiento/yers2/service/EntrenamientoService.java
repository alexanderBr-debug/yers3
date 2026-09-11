package com.entrenamiento.yers2.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.entrenamiento.yers2.Exception.InformacionInsuficienteException;
import com.entrenamiento.yers2.Exception.JugadorNoEncontradoException;
import com.entrenamiento.yers2.dto.EntrenamientoRequestDTO;
import com.entrenamiento.yers2.dto.EntrenamientoResponseDto;
import com.entrenamiento.yers2.dto.JugadoresTitularesDto;
import com.entrenamiento.yers2.entity.Entrenamiento;
import com.entrenamiento.yers2.entity.Jugador;
import com.entrenamiento.yers2.repository.EntrenamientoRepository;
import com.entrenamiento.yers2.repository.JugadoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service 
@AllArgsConstructor
@Data 
public class EntrenamientoService {

    private final JugadoRepository jugadoRepository;
    private final EntrenamientoRepository entrenamientoRepository;

    public EntrenamientoResponseDto crearEntrenamiento(EntrenamientoRequestDTO request) {

    Jugador jugador = jugadoRepository.findById(request.getJugadorId())
        .orElseThrow(() -> new JugadorNoEncontradoException("Jugador no encontrado"));

    double resultado = (request.getPotenciaTiro() * 0.20)
        + (request.getVelocidad() * 0.30)
        + (request.getPasesEfectivos() * 0.50);

    Entrenamiento entrenamiento = new Entrenamiento();
    entrenamiento.setPotenciaTiro(request.getPotenciaTiro());
    entrenamiento.setVelocidad(request.getVelocidad());
    entrenamiento.setPasesEfectivos(request.getPasesEfectivos());
    entrenamiento.setResultado(resultado);
    entrenamiento.setFecha(LocalDate.now());
    entrenamiento.setJugador(jugador);
    entrenamientoRepository.save(entrenamiento);

    EntrenamientoResponseDto response = new EntrenamientoResponseDto();
    response.setId(entrenamiento.getId());
    response.setNombreJugador(entrenamiento.getJugador().getName());
    response.setPotenciaTiro(entrenamiento.getPotenciaTiro());
    response.setVelocidad(entrenamiento.getVelocidad());
    response.setPasesEfectivos(entrenamiento.getPasesEfectivos());
    response.setResultado(entrenamiento.getResultado());
    response.setFecha(entrenamiento.getFecha());
    return response;
}

    public List<JugadoresTitularesDto> equipoTitular(){

        //obtenemos la lista de todos los jugadores de la base de datos
        List<Jugador> jugadores = jugadoRepository.findAll();
        //creamos una lista auxiliar para guardar el dto de cada jugador 
        List<JugadoresTitularesDto> promedios = new ArrayList<>();

        //recorremos a cada jugador para procesar sus datos
        for (Jugador jugador : jugadores ) {

            //consultamos los entrenos de los jugadores y los guardamos en una lista
            List<Entrenamiento> entrenamientos = entrenamientoRepository.findByJugadorId(jugador.getId());

            //si el jugador no entreno 3 veces se rompe el sistema y enviamos una exception
            if (entrenamientos.size() < 3) {
                throw new InformacionInsuficienteException("el jugador" + jugador.getName() + "no hizo los 3 entrenamientos");               
            }

            //convertimos la lista entrenamients en un stream esto nos sirve manejar un flujo secuencial de objetos          
            double promedio = entrenamientos.stream()
            /*ahora convertimo ese stream en un doubleStream haciendo referencia al resulatdo,
            racticamente estamos sacando solo el resultado de todo los datos*/
            .mapToDouble(Entrenamiento::getResultado)
            //sumamos los elementos y dividimos po el nuemro de elementos
            .average()
            //un metodo que devuelve un valor por defecto que OptionalDouble
            .orElse(0);

            JugadoresTitularesDto response = new JugadoresTitularesDto();
            response.setNombrejugador(jugador.getName());
            response.setResultados(promedio);
            promedios.add(response);         
        }

        return promedios.stream()
        .sorted(Comparator.comparingDouble(JugadoresTitularesDto::getResultados).reversed())
        .limit(5)
        .collect(Collectors.toList());




        
    }

}
