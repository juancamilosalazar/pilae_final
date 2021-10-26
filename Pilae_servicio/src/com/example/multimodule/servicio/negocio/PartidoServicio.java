package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.MarcadorDominio;
import main.com.example.multimodule.dominio.PartidoDominio;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@SpringBootApplication(scanBasePackages = "com.example.multimodule")
public interface PartidoServicio {

    List<PartidoDominio> obtenerTodos() ;
    List<PartidoDominio> obtenerPorTorneo(Long idToreno) ;
    PartidoDominio obtenerPorId(Long id) ;
    void crear(PartidoDominio partido, Long equipoVisitante,Long equipoLocal,Long torneoId) ;
    void actualizar( PartidoDominio nuevo);
    void borrar(Long id);
    void jugarPartido(MarcadorDominio dominio, Long idPartido);
    List<PartidoDominio> crearFixtureIdaYvuelta(Long idTorneo);
    List<PartidoDominio> crearFixtureSoloIda(Long idTorneo);
}
