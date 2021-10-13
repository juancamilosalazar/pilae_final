package com.example.multimodule.servicio.utilitario;

import com.example.multimodule.entidad.DeporteEntidad;
import main.com.example.multimodule.dominio.DeporteDominio;
import main.com.example.multimodule.dto.Deporte;
import org.modelmapper.ModelMapper;

public class DeporteConvertorUtilitario {

    private static ModelMapper modelMapper = new ModelMapper();

    public static DeporteDominio convertirDeporteEntidadEnDeporteDominio(DeporteEntidad entrada){
        return modelMapper.map(entrada,DeporteDominio.class);
    }

    public static Deporte convertirDeporteDominioEnDeporteDto(DeporteDominio entrada){
        return modelMapper.map(entrada,Deporte.class);
    }
}
