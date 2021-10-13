package com.example.multimodule.servicio.negocio;

import main.com.example.multimodule.dominio.PosicionDominio;

import java.util.List;

public interface PosicionServicio {

    PosicionDominio obtenerPorId(Long id) ;
}
