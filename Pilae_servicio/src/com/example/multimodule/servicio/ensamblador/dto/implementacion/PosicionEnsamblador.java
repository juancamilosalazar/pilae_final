package com.example.multimodule.servicio.ensamblador.dto.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.EnsambladorDTO;
import main.com.example.multimodule.dominio.PosicionDominio;
import main.com.example.multimodule.dto.Posicion;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class PosicionEnsamblador implements EnsambladorDTO<Posicion, PosicionDominio> {
    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorDTO<Posicion, PosicionDominio> instancia = new PosicionEnsamblador();

    private PosicionEnsamblador() {
    }

    public static EnsambladorDTO<Posicion, PosicionDominio> obtenerPosicionEnsambladorDTO() {
        return instancia;
    }

    @Override
    public PosicionDominio ensamblarDominio(Posicion dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return modelMapper.map(dto,PosicionDominio.class);
    }

    @Override
    public Posicion ensamblarDTO(PosicionDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,Posicion.class);
    }

    @Override
    public List<Posicion> ensamblarListaDTO(List<PosicionDominio> listaDominios) {

        List<Posicion> listaDTOs = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaDominios)) {
            for (PosicionDominio PosicionDominio : listaDominios) {
                listaDTOs.add(ensamblarDTO(PosicionDominio));
            }
        }
        return listaDTOs;
    }
}
