package com.example.multimodule.servicio.fachada;

import main.com.example.multimodule.dto.Equipo;

import java.util.List;


public interface EquipoFachada {

	List<Equipo> obtenerTodos();
	List<Equipo> obtenerPorTorneo(Long torneoId);
	Equipo obtenerPorId(Long id) ;
	void crear(Equipo equipo, Long torneoId) ;
	void actualizar( Equipo equipoNuevo);
	void borrar(Long id);
}
