package com.example.multimodule.servicio.utilitario;

import com.example.multimodule.entidad.EquipoEntidad;
import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.dto.Equipo;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EquipoConvertorUtilitario {

    private static ModelMapper modelMapper = new ModelMapper();

    public static EquipoDominio convertirEquipoEntidadEnEquipoDominio(EquipoEntidad entrada){
        return modelMapper.map(entrada,EquipoDominio.class);
    }

    public static EquipoEntidad convertirEquipoDominioEnEquipoEntidad(EquipoDominio entrada){
        return modelMapper.map(entrada,EquipoEntidad.class);
    }

    public static Equipo convertirEquipoDominioEnEquipoDto(EquipoDominio entrada){
        return modelMapper.map(entrada,Equipo.class);
    }

    public static EquipoDominio convertirEquipoDtoEnEquipoDominio(Equipo entrada){
        return modelMapper.map(entrada,EquipoDominio.class);
    }

    public static List<Equipo> construirListaEquiposDto(List<EquipoDominio> equiposDominio) {
        return equiposDominio.stream()
                .map(equipoDominio -> EquipoConvertorUtilitario.convertirEquipoDominioEnEquipoDto(equipoDominio))
                .collect(Collectors.toList());
    }

    public static List<EquipoDominio> construirListaDeEquipoDominio(List<EquipoEntidad> equipos) throws Exception {
        return equipos.stream()
                .map(equipoEntidad -> EquipoConvertorUtilitario.convertirEquipoEntidadEnEquipoDominio(equipoEntidad))
                .collect(Collectors.toList());
    }
}
