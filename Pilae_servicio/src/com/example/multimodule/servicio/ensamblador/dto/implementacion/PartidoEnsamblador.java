package com.example.multimodule.servicio.ensamblador.dto.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.EnsambladorDTO;
import main.com.example.multimodule.dominio.PartidoDominio;
import main.com.example.multimodule.dto.Partido;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class PartidoEnsamblador implements EnsambladorDTO<Partido, PartidoDominio> {
    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorDTO<Partido, PartidoDominio> instancia = new PartidoEnsamblador();

    private PartidoEnsamblador() {
    }

    public static EnsambladorDTO<Partido, PartidoDominio> obtenerPartidoEnsambladorDTO() {
        return instancia;
    }

    @Override
    public PartidoDominio ensamblarDominio(Partido dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return modelMapper.map(dto,PartidoDominio.class);
    }

    @Override
    public Partido ensamblarDTO(PartidoDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,Partido.class);
    }

    @Override
    public List<Partido> ensamblarListaDTO(List<PartidoDominio> listaDominios) {

        List<Partido> listaDTOs = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaDominios)) {
            for (PartidoDominio PartidoDominio : listaDominios) {
                listaDTOs.add(ensamblarDTO(PartidoDominio));
            }
        }
        return listaDTOs;
    }
}
