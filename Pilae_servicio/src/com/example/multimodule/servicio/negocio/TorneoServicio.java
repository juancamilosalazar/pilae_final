package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.TorneoDominio;

import java.util.List;

public interface TorneoServicio {

    List<TorneoDominio> obtenerTodos() ;
    TorneoDominio obtenerPorId(Long id) ;
    void crear(TorneoDominio torneo, Long torneoId) ;
    void actualizar( TorneoDominio nuevo);
    void borrar(Long id);
}
