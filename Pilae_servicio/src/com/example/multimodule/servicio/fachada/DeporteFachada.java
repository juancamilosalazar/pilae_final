package com.example.multimodule.servicio.fachada;

import main.com.example.multimodule.dto.Deporte;

import java.util.List;


public interface DeporteFachada {

	List<Deporte> obtenerTodos() ;
	Deporte obtenerPorId(Long id) ;
	void crear(Deporte deporte) ;
	void actualizar( Deporte deporteNuevo);
	void borrar(Long id);
}
