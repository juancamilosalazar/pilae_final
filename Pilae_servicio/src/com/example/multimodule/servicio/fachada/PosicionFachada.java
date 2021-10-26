package com.example.multimodule.servicio.fachada;

import main.com.example.multimodule.dto.Posicion;

import java.util.List;


public interface PosicionFachada {

	Posicion obtenerPorId(Long id) ;

    List<Posicion> consultarPorTorneo(Long id);
}
