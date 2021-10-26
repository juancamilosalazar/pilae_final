package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.PosicionDominio;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SpringBootApplication(scanBasePackages = "com.example.multimodule")
public interface PosicionServicio {

    PosicionDominio obtenerPorId(Long id) ;

    List<PosicionDominio> obtenerPorTorneo(Long id);
}
