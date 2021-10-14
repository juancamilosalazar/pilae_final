package com.example.multimodule.servicio.ensamblador.dto.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.EnsambladorDTO;
import main.com.example.multimodule.dominio.DeporteDominio;
import main.com.example.multimodule.dto.Deporte;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DeporteEnsamblador implements EnsambladorDTO<Deporte, DeporteDominio> {
   
    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorDTO<Deporte, DeporteDominio> instancia = new DeporteEnsamblador();

    private DeporteEnsamblador() {
    }

    public static EnsambladorDTO<Deporte, DeporteDominio> obtenerDeporteEnsambladorDTO() {
        return instancia;
    }

    @Override
    public DeporteDominio ensamblarDominio(Deporte dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return modelMapper.map(dto,DeporteDominio.class);
    }

    @Override
    public Deporte ensamblarDTO(DeporteDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,Deporte.class);
    }

    @Override
    public List<Deporte> ensamblarListaDTO(List<DeporteDominio> listaDominios) {

        List<Deporte> listaDTOs = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaDominios)) {
            for (DeporteDominio DeporteDominio : listaDominios) {
                listaDTOs.add(ensamblarDTO(DeporteDominio));
            }
        }
        return listaDTOs;
    }
}
