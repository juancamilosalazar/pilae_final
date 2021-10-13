package com.example.multimodule.servicio.negocio.impl;

import com.example.multimodule.entidad.DeporteEntidad;
import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.entidad.PosicionEntidad;
import com.example.multimodule.entidad.TorneoEntidad;
import com.example.multimodule.infraestructura.deporte.DeporteRepositorioJpa;
import com.example.multimodule.infraestructura.equipo.EquipoRepositorioJpa;
import com.example.multimodule.infraestructura.posicion.PosicionRepositorioJpa;
import com.example.multimodule.infraestructura.torneo.TorneoRepositorioJpa;
import com.example.multimodule.servicio.ensamblador.entidad.implementacion.PosicionEnsambladorEntidad;
import com.example.multimodule.servicio.negocio.PosicionServicio;
import com.example.multimodule.servicio.utilitario.DeporteConvertorUtilitario;
import com.example.multimodule.servicio.utilitario.EquipoConvertorUtilitario;
import com.example.multimodule.servicio.utilitario.TorneoConvertorUtilitario;
import main.com.example.multimodule.dominio.DeporteDominio;
import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.dominio.PosicionDominio;
import main.com.example.multimodule.dominio.TorneoDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PosicionServicioImpl implements PosicionServicio {

    private PosicionRepositorioJpa repositorio;

    @Autowired
    public PosicionServicioImpl(PosicionRepositorioJpa repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public PosicionDominio obtenerPorId(Long id)  {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        PosicionEntidad resultadosConsulta = repositorio.findById(id).orElseThrow(()->{
            String mensajeUsuario = "Posicion no existe";
            String mensajeTecnico = "Posicion no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });

        PosicionDominio dominio = PosicionEnsambladorEntidad.obtenerPosicionEnsambladorEntidad().ensamblarDominio(resultadosConsulta);
        return dominio;
    }

}