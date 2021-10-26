package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.MarcadorDominio;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@SpringBootApplication(scanBasePackages = "com.example.multimodule")
public interface MarcadorServicio {

    List<MarcadorDominio> obtenerTodos() ;
    MarcadorDominio obtenerPorId(Long id) ;
    void crear(MarcadorDominio marcador, Long torneoId) ;
    void actualizar( MarcadorDominio nuevo);
    void borrar(Long id);
    List<MarcadorDominio> obtenerPorPartido(Long id);

    List<MarcadorDominio> obtenerPorTorneo(Long id);
}
