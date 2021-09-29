package com.example.multimodule.servicio.ensamblador.entidad.implementacion;

import com.example.multimodule.entidad.PartidoEntidad;
import com.example.multimodule.servicio.ensamblador.entidad.EnsambladorEntidad;
import main.com.example.multimodule.dominio.PartidoDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class PartidoEnsambladorEntidad implements EnsambladorEntidad<PartidoEntidad, PartidoDominio> {

    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorEntidad<PartidoEntidad, PartidoDominio> instancia = new PartidoEnsambladorEntidad();

    private PartidoEnsambladorEntidad() {
    }

    public static EnsambladorEntidad<PartidoEntidad, PartidoDominio> obtenerPartidoEnsambladorEntidad() {
        return instancia;
    }

    @Override
    public PartidoEntidad ensamblarEntidad(PartidoDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,PartidoEntidad.class);
    }

    @Override
    public PartidoDominio ensamblarDominio(PartidoEntidad entidad) {

        if (UtilObjeto.objetoEsNulo(entidad)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(entidad,PartidoDominio.class);
    }

    @Override
    public List<PartidoDominio> ensamblarListaDominio(List<PartidoEntidad> listaEntidades) {
        List<PartidoDominio> listaDominios = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaEntidades)) {
            for (PartidoEntidad partidoEntidad : listaEntidades) {
                listaDominios.add(ensamblarDominio(partidoEntidad));
            }
        }

        return listaDominios;
    }
}
