package com.example.multimodule.servicio.fachada;

import main.com.example.multimodule.dto.Equipo;
import main.com.example.multimodule.dto.Posicion;

import java.util.List;


public interface PosicionFachada {

	Posicion obtenerPorId(Long id) ;
	void crear(Posicion posicion, Long torneoId) ;
}
