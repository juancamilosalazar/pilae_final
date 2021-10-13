package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.MarcadorDominio;
import main.com.example.multimodule.dominio.PartidoDominio;

import java.util.List;

public interface PartidoServicio {

    List<PartidoDominio> obtenerTodos() ;
    PartidoDominio obtenerPorId(Long id) ;
    void crear(PartidoDominio partido, Long equipoVisitante,Long equipoLocal,Long torneoId) ;
    void actualizar( PartidoDominio nuevo);
    void borrar(Long id);
    void jugarPartido(MarcadorDominio dominio, Long idPartido);
    List<PartidoDominio> crearFixtureIdaYvuelta(Long idTorneo);
    List<PartidoDominio> crearFixtureSoloIda(Long idTorneo);
}