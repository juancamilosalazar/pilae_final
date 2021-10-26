package com.example.multimodule.servicio.fachada;

import main.com.example.multimodule.dto.Jugador;

import java.util.List;


public interface JugadorFachada {

	List<Jugador> obtenerTodos() ;
	Jugador obtenerPorId(Long id) ;
	void crear(Jugador jugador, Long equipoId) ;
	void actualizar( Jugador jugadorNuevo);
	void borrar(Long id);
    List<Jugador> obtenerPorEquipo(Long id);
}
