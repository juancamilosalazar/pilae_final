package com.example.multimodule.servicio.fachada;

import main.com.example.multimodule.dto.Torneo;

import java.util.List;


public interface TorneoFachada {

	List<Torneo> obtenerTodos() ;
	Torneo obtenerPorId(Long id) ;
	void crear(Torneo torneo, Long deporteId) ;
	void actualizar( Torneo torneoNuevo);
	void borrar(Long id);
}
