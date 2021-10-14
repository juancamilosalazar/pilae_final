package com.example.multimodule.servicio.utilitario;

import com.example.multimodule.entidad.PartidoEntidad;
import main.com.example.multimodule.dominio.PartidoDominio;
import main.com.example.multimodule.dto.Partido;
import org.modelmapper.ModelMapper;

public class PartidoConvertorUtilitario {

    private static ModelMapper modelMapper = new ModelMapper();

    public static PartidoDominio convertirPartidoEntidadEnPartidoDominio( PartidoEntidad entrada){
        return modelMapper.map(entrada,PartidoDominio.class);
    }

    public static Partido convertirPartidoDominioEnPartidoDto(PartidoDominio entrada){
        return modelMapper.map(entrada,Partido.class);
    }
}
