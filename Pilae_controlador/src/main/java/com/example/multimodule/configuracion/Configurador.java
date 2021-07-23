package com.example.multimodule.configuracion;

import main.com.example.multimodule.transversal.mensajes.MensajesHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Configurador {

    @Value("${pilae.mensajeria.estrategia}")
    public void configurarEstrategiaMensajeria(String estrategia) {
        MensajesHelper.configurarEstrategiaMensajeria(estrategia);
    }
}
