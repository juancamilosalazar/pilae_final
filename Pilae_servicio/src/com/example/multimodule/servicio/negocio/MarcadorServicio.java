package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.MarcadorDominio;

import java.util.List;

public interface MarcadorServicio {

    List<MarcadorDominio> obtenerTodos() ;
    MarcadorDominio obtenerPorId(Long id) ;
    void crear(MarcadorDominio marcador, Long torneoId) ;
    void actualizar( MarcadorDominio nuevo);
    void borrar(Long id);
}
