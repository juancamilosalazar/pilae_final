package com.example.multimodule.servicio.ensamblador.entidad.implementacion;

import com.example.multimodule.entidad.PosicionEntidad;
import com.example.multimodule.servicio.ensamblador.entidad.EnsambladorEntidad;
import main.com.example.multimodule.dominio.PosicionDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class PosicionEnsambladorEntidad implements EnsambladorEntidad<PosicionEntidad, PosicionDominio> {

    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorEntidad<PosicionEntidad, PosicionDominio> instancia = new PosicionEnsambladorEntidad();

    private PosicionEnsambladorEntidad() {
    }

    public static EnsambladorEntidad<PosicionEntidad, PosicionDominio> obtenerPosicionEnsambladorEntidad() {
        return instancia;
    }

    @Override
    public PosicionEntidad ensamblarEntidad(PosicionDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,PosicionEntidad.class);
    }

    @Override
    public PosicionDominio ensamblarDominio(PosicionEntidad entidad) {

        if (UtilObjeto.objetoEsNulo(entidad)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(entidad,PosicionDominio.class);
    }

    @Override
    public List<PosicionDominio> ensamblarListaDominio(List<PosicionEntidad> listaEntidades) {
        List<PosicionDominio> listaDominios = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaEntidades)) {
            for (PosicionEntidad posicionEntidad : listaEntidades) {
                listaDominios.add(ensamblarDominio(posicionEntidad));
            }
        }

        return listaDominios;
    }
}