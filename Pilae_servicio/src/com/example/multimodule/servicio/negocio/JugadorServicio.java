package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.JugadorDominio;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@SpringBootApplication(scanBasePackages = "com.example.multimodule")
public interface JugadorServicio {

    List<JugadorDominio> obtenerTodos() ;
    JugadorDominio obtenerPorId(Long id) ;
    void crear(JugadorDominio jugador, Long torneoId) ;
    void actualizar( JugadorDominio nuevo);
    void borrar(Long id);

    List<JugadorDominio> obtenerPorEquipo(Long id);
}
