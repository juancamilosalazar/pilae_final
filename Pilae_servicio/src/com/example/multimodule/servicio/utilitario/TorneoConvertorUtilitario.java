package com.example.multimodule.servicio.utilitario;

import com.example.multimodule.entidad.TorneoEntidad;
import main.com.example.multimodule.dominio.TorneoDominio;
import main.com.example.multimodule.dto.Torneo;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class TorneoConvertorUtilitario {

    private static ModelMapper modelMapper = new ModelMapper();

    public static TorneoDominio convertirTorneoEntidadEnTorneoDominio(TorneoEntidad entrada){
        return modelMapper.map(entrada,TorneoDominio.class);
    }

    public static TorneoEntidad convertirTorneoDominioEnTorneoEntidad(TorneoDominio entrada){
        return modelMapper.map(entrada,TorneoEntidad.class);
    }

    public static Torneo convertirTorneoDominioEnTorneoDto(TorneoDominio entrada){
        return modelMapper.map(entrada,Torneo.class);
    }

    public static TorneoDominio convertirTorneoDtoEnTorneoDominio(Torneo entrada){
        return modelMapper.map(entrada,TorneoDominio.class);
    }

    public static List<Torneo> construirListaTorneosDto(List<TorneoDominio> TorneosDominio) {
        return TorneosDominio.stream()
                .map(TorneoDominio -> TorneoConvertorUtilitario.convertirTorneoDominioEnTorneoDto(TorneoDominio))
                .collect(Collectors.toList());
    }

    public static List<TorneoDominio> construirListaDeTorneoDominio(List<TorneoEntidad> Torneos) throws Exception {
        return Torneos.stream()
                .map(TorneoEntidad -> TorneoConvertorUtilitario.convertirTorneoEntidadEnTorneoDominio(TorneoEntidad))
                .collect(Collectors.toList());
    }
}
