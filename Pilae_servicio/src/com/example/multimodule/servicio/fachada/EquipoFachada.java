package com.example.multimodule.servicio.fachada;

import java.util.List;

import main.com.example.multimodule.dto.Equipo;


public interface EquipoFachada {

	List<Equipo> obtenerTodos() ;
	Equipo obtenerPorId(Long id) ;
	void crear(Equipo equipo, Long torneoId) ;
	void actualizar( Equipo equipoNuevo);
	void borrar(Long id);
}
