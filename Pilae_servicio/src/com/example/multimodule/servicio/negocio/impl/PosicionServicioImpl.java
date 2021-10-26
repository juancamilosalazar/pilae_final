package com.example.multimodule.servicio.negocio.impl;

import com.example.multimodule.entidad.PosicionEntidad;
import com.example.multimodule.entidad.TorneoEntidad;
import com.example.multimodule.infraestructura.posicion.PosicionRepositorioJpa;
import com.example.multimodule.infraestructura.torneo.TorneoRepositorioJpa;
import com.example.multimodule.servicio.ensamblador.entidad.implementacion.PosicionEnsambladorEntidad;
import com.example.multimodule.servicio.negocio.PosicionServicio;
import main.com.example.multimodule.dominio.PosicionDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosicionServicioImpl implements PosicionServicio {

    private PosicionRepositorioJpa repositorio;
    private TorneoRepositorioJpa torneoRepositorio;

    @Autowired
    public PosicionServicioImpl(PosicionRepositorioJpa repositorio, TorneoRepositorioJpa torneoRepositorio){
        this.repositorio = repositorio;
        this.torneoRepositorio = torneoRepositorio;
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

    @Override
    public List<PosicionDominio> obtenerPorTorneo(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        List<PosicionEntidad> entidadList = repositorio.findByFkTorneo(obtenerTorneoEntidad(id));

        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de posiciones está vacía";
            String mensajeTecnico = "la lista de posiciones está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<PosicionDominio> dominios = PosicionEnsambladorEntidad.obtenerPosicionEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }
    private TorneoEntidad obtenerTorneoEntidad(Long torneoId) {
        TorneoEntidad torneoEntidad = torneoRepositorio.findById(torneoId).orElseThrow(()->{
            String mensajeUsuario = "Partido no encontrado";
            String mensajeTecnico = "Partido no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        return torneoEntidad;
    }

}