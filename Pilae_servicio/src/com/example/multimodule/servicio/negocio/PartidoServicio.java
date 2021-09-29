package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.PartidoDominio;

import java.util.List;

public interface PartidoServicio {

    List<PartidoDominio> obtenerTodos() ;
    PartidoDominio obtenerPorId(Long id) ;
    void crear(PartidoDominio partido, Long torneoId) ;
    void actualizar( PartidoDominio nuevo);
    void borrar(Long id);
}
