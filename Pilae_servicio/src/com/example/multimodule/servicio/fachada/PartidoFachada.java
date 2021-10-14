package com.example.multimodule.servicio.fachada;

import main.com.example.multimodule.dto.Marcador;
import main.com.example.multimodule.dto.Partido;

import java.util.List;


public interface PartidoFachada {

	List<Partido> obtenerTodos() ;
	Partido obtenerPorId(Long id) ;
	void crear(Partido partido, Long equipoLocal, Long equipoVisitante, Long torneoId) ;
	void actualizar( Partido partidoNuevo);
	void borrar(Long id);
	void jugarPartido(Marcador marcador, Long idPartido);
	List<Partido> obtenerPorTorneo(Long idTorneo);
	List<Partido> crearFixtureSoloIda(Long idTorneo);
	List<Partido> crearFixtureIdaYvuelta(Long idTorneo);
}
