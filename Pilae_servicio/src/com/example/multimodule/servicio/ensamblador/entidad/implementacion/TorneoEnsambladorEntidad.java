package com.example.multimodule.servicio.ensamblador.entidad.implementacion;

import com.example.multimodule.entidad.TorneoEntidad;
import com.example.multimodule.servicio.ensamblador.entidad.EnsambladorEntidad;
import main.com.example.multimodule.dominio.TorneoDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class TorneoEnsambladorEntidad implements EnsambladorEntidad<TorneoEntidad, TorneoDominio> {

    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorEntidad<TorneoEntidad, TorneoDominio> instancia = new TorneoEnsambladorEntidad();

    private TorneoEnsambladorEntidad() {
    }

    public static EnsambladorEntidad<TorneoEntidad, TorneoDominio> obtenerTorneoEnsambladorEntidad() {
        return instancia;
    }

    @Override
    public TorneoEntidad ensamblarEntidad(TorneoDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,TorneoEntidad.class);
    }

    @Override
    public TorneoDominio ensamblarDominio(TorneoEntidad entidad) {

        if (UtilObjeto.objetoEsNulo(entidad)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(entidad,TorneoDominio.class);
    }

    @Override
    public List<TorneoDominio> ensamblarListaDominio(List<TorneoEntidad> listaEntidades) {
        List<TorneoDominio> listaDominios = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaEntidades)) {
            for (TorneoEntidad torneoEntidad : listaEntidades) {
                listaDominios.add(ensamblarDominio(torneoEntidad));
            }
        }

        return listaDominios;
    }
}
