package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.JugadorDominio;

import java.util.List;

public interface JugadorServicio {

    List<JugadorDominio> obtenerTodos() ;
    JugadorDominio obtenerPorId(Long id) ;
    void crear(JugadorDominio jugador, Long torneoId) ;
    void actualizar( JugadorDominio nuevo);
    void borrar(Long id);
}
