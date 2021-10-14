package com.example.multimodule.servicio.ensamblador.dto.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.EnsambladorDTO;
import main.com.example.multimodule.dominio.MarcadorDominio;
import main.com.example.multimodule.dto.Marcador;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class MarcadorEnsamblador implements EnsambladorDTO<Marcador, MarcadorDominio> {
    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorDTO<Marcador, MarcadorDominio> instancia = new MarcadorEnsamblador();

    private MarcadorEnsamblador() {
    }

    public static EnsambladorDTO<Marcador, MarcadorDominio> obtenerMarcadorEnsambladorDTO() {
        return instancia;
    }

    @Override
    public MarcadorDominio ensamblarDominio(Marcador dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return modelMapper.map(dto,MarcadorDominio.class);
    }

    @Override
    public Marcador ensamblarDTO(MarcadorDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,Marcador.class);
    }

    @Override
    public List<Marcador> ensamblarListaDTO(List<MarcadorDominio> listaDominios) {

        List<Marcador> listaDTOs = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaDominios)) {
            for (MarcadorDominio MarcadorDominio : listaDominios) {
                listaDTOs.add(ensamblarDTO(MarcadorDominio));
            }
        }
        return listaDTOs;
    }
}
