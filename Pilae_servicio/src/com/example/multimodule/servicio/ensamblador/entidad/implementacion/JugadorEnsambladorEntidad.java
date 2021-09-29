package com.example.multimodule.servicio.ensamblador.entidad.implementacion;

import com.example.multimodule.entidad.JugadorEntidad;
import com.example.multimodule.servicio.ensamblador.entidad.EnsambladorEntidad;
import main.com.example.multimodule.dominio.JugadorDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class JugadorEnsambladorEntidad implements EnsambladorEntidad<JugadorEntidad, JugadorDominio> {

    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorEntidad<JugadorEntidad, JugadorDominio> instancia = new JugadorEnsambladorEntidad();

    private JugadorEnsambladorEntidad() {
    }

    public static EnsambladorEntidad<JugadorEntidad, JugadorDominio> obtenerJugadorEnsambladorEntidad() {
        return instancia;
    }

    @Override
    public JugadorEntidad ensamblarEntidad(JugadorDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,JugadorEntidad.class);
    }

    @Override
    public JugadorDominio ensamblarDominio(JugadorEntidad entidad) {

        if (UtilObjeto.objetoEsNulo(entidad)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(entidad,JugadorDominio.class);
    }

    @Override
    public List<JugadorDominio> ensamblarListaDominio(List<JugadorEntidad> listaEntidades) {
        List<JugadorDominio> listaDominios = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaEntidades)) {
            for (JugadorEntidad jugadorEntidad : listaEntidades) {
                listaDominios.add(ensamblarDominio(jugadorEntidad));
            }
        }

        return listaDominios;
    }
}
