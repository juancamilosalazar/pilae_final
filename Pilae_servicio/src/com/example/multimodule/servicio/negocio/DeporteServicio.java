package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.DeporteDominio;

import java.util.List;

public interface DeporteServicio {

    List<DeporteDominio> obtenerTodos() ;
    DeporteDominio obtenerPorId(Long id) ;
    void crear(DeporteDominio deporte) ;
    void actualizar( DeporteDominio nuevo);
    void borrar(Long id);
}
