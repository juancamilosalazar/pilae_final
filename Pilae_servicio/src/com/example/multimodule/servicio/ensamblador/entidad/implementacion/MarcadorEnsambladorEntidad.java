package com.example.multimodule.servicio.ensamblador.entidad.implementacion;

import com.example.multimodule.entidad.MarcadorEntidad;
import com.example.multimodule.servicio.ensamblador.entidad.EnsambladorEntidad;
import main.com.example.multimodule.dominio.MarcadorDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class MarcadorEnsambladorEntidad implements EnsambladorEntidad<MarcadorEntidad, MarcadorDominio> {

    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorEntidad<MarcadorEntidad, MarcadorDominio> instancia = new MarcadorEnsambladorEntidad();

    private MarcadorEnsambladorEntidad() {
    }

    public static EnsambladorEntidad<MarcadorEntidad, MarcadorDominio> obtenerMarcadorEnsambladorEntidad() {
        return instancia;
    }

    @Override
    public MarcadorEntidad ensamblarEntidad(MarcadorDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,MarcadorEntidad.class);
    }

    @Override
    public MarcadorDominio ensamblarDominio(MarcadorEntidad entidad) {

        if (UtilObjeto.objetoEsNulo(entidad)) {
            String mensajeUsuario = "objeto nulo";
            String mensajeTecnico = "objeto nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(entidad,MarcadorDominio.class);
    }

    @Override
    public List<MarcadorDominio> ensamblarListaDominio(List<MarcadorEntidad> listaEntidades) {
        List<MarcadorDominio> listaDominios = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaEntidades)) {
            for (MarcadorEntidad marcadorEntidad : listaEntidades) {
                listaDominios.add(ensamblarDominio(marcadorEntidad));
            }
        }

        return listaDominios;
    }
}
