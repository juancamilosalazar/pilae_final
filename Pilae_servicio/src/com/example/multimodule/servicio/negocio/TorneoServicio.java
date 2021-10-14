package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.TorneoDominio;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@SpringBootApplication(scanBasePackages = "com.example.multimodule")
public interface TorneoServicio {

    List<TorneoDominio> obtenerTodos() ;
    TorneoDominio obtenerPorId(Long id) ;
    void crear(TorneoDominio torneo, Long torneoId) ;
    void actualizar( TorneoDominio nuevo);
    void borrar(Long id);
}
