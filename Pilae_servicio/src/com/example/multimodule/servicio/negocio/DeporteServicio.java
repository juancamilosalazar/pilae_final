package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.DeporteDominio;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@SpringBootApplication(scanBasePackages = "com.example.multimodule")
public interface DeporteServicio {

    List<DeporteDominio> obtenerTodos() ;
    DeporteDominio obtenerPorId(Long id) ;
    void crear(DeporteDominio deporte) ;
    void actualizar( DeporteDominio nuevo);
    void borrar(Long id);
}
