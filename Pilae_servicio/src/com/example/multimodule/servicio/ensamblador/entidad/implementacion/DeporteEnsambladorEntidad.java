package com.example.multimodule.servicio.ensamblador.entidad.implementacion;

import com.example.multimodule.entidad.DeporteEntidad;
import com.example.multimodule.servicio.ensamblador.entidad.EnsambladorEntidad;
import main.com.example.multimodule.dominio.DeporteDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DeporteEnsambladorEntidad implements EnsambladorEntidad<DeporteEntidad, DeporteDominio> {

    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorEntidad<DeporteEntidad, DeporteDominio> instancia = new DeporteEnsambladorEntidad();

    private DeporteEnsambladorEntidad() {
    }

    public static EnsambladorEntidad<DeporteEntidad, DeporteDominio> obtenerDeporteEnsambladorEntidad() {
        return instancia;
    }

    @Override
    public DeporteEntidad ensamblarEntidad(DeporteDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,DeporteEntidad.class);
    }

    @Override
    public DeporteDominio ensamblarDominio(DeporteEntidad entidad) {

        if (UtilObjeto.objetoEsNulo(entidad)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(entidad,DeporteDominio.class);
    }

    @Override
    public List<DeporteDominio> ensamblarListaDominio(List<DeporteEntidad> listaEntidades) {
        List<DeporteDominio> listaDominios = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaEntidades)) {
            for (DeporteEntidad deporteEntidad : listaEntidades) {
                listaDominios.add(ensamblarDominio(deporteEntidad));
            }
        }

        return listaDominios;
    }
}
