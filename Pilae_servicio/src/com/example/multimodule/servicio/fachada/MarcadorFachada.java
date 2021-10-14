package com.example.multimodule.servicio.fachada;

import main.com.example.multimodule.dto.Marcador;

import java.util.List;


public interface MarcadorFachada {

	List<Marcador> obtenerTodos() ;
	Marcador obtenerPorId(Long id) ;
	void crear(Marcador marcador, Long torneoId) ;
	void actualizar( Marcador marcadorNuevo);
	void borrar(Long id);
	List<Marcador> obtenerPorIdPartido(Long id);
	List<Marcador> obtenerPorIdTorneo(Long id);
}
