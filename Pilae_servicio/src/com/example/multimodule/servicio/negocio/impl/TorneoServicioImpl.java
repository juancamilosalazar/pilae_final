package com.example.multimodule.servicio.negocio.impl;

import com.example.multimodule.entidad.DeporteEntidad;
import com.example.multimodule.entidad.TorneoEntidad;
import com.example.multimodule.infraestructura.deporte.DeporteRepositorioJpa;
import com.example.multimodule.infraestructura.torneo.TorneoRepositorioJpa;
import com.example.multimodule.servicio.ensamblador.entidad.implementacion.TorneoEnsambladorEntidad;
import com.example.multimodule.servicio.negocio.TorneoServicio;
import com.example.multimodule.servicio.utilitario.DeporteConvertorUtilitario;
import main.com.example.multimodule.dominio.DeporteDominio;
import main.com.example.multimodule.dominio.TorneoDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TorneoServicioImpl implements TorneoServicio {

    private TorneoRepositorioJpa repositorio;
    private DeporteRepositorioJpa deporteRepositorioJpa;


    @Autowired
    public TorneoServicioImpl(TorneoRepositorioJpa repositorio, DeporteRepositorioJpa deporteRepositorioJpa) {
        this.repositorio = repositorio;
        this.deporteRepositorioJpa = deporteRepositorioJpa;
    }

    @Override
    public List<TorneoDominio> obtenerTodos() {
        List<TorneoEntidad> entidadList = repositorio.findAll();

        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de Torneos está vacía";
            String mensajeTecnico = "la lista de Torneos está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<TorneoDominio> dominios = TorneoEnsambladorEntidad.obtenerTorneoEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }



    @Override
    public TorneoDominio obtenerPorId(Long id)  {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        TorneoEntidad resultadosConsulta = repositorio.findById(id).orElseThrow(()->{
            String mensajeUsuario = "Torneo no existe";
            String mensajeTecnico = "Torneo no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });

        TorneoDominio dominio = TorneoEnsambladorEntidad.obtenerTorneoEnsambladorEntidad().ensamblarDominio(resultadosConsulta);
        return dominio;
    }

    @Override
    public void crear(TorneoDominio dominio, Long deporteId)  {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "Torneo no puede estar vacío";
            String mensajeTecnico = "Torneo no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        if(Objects.nonNull(dominio.getCodigo())){
            Optional<TorneoEntidad> resultadosConsulta = repositorio.findById(dominio.getCodigo());

            if (resultadosConsulta.isPresent()) {
                String mensajeUsuario = "Torneo con el código existente";
                String mensajeTecnico = "Torneo con el código existente";
                throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
            }
        }
        ObtenerDeporteDelTorneo(deporteId,dominio);
        TorneoEntidad entidad = TorneoEnsambladorEntidad.obtenerTorneoEnsambladorEntidad().ensamblarEntidad(dominio);
        repositorio.save(entidad);
    }

    @Override
    public void actualizar(TorneoDominio nuevo) {

        if (UtilObjeto.objetoEsNulo(nuevo)) {
            String mensajeUsuario = "Torneo no puede estar vacío";
            String mensajeTecnico = "Torneo no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        TorneoDominio resultadosConsulta = obtenerPorId(nuevo.getCodigo());

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "Torneo no existe";
            String mensajeTecnico = "Torneo no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        cambiarValores(nuevo,resultadosConsulta);
        TorneoEntidad entidad = TorneoEnsambladorEntidad.obtenerTorneoEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        repositorio.save(entidad);
    }

    @Override
    public void borrar(Long id) {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        TorneoDominio resultadosConsulta = obtenerPorId(id);

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "Torneo no existe";
            String mensajeTecnico = "Torneo no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        TorneoEntidad entidad = TorneoEnsambladorEntidad.obtenerTorneoEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        repositorio.delete(entidad);
    }

    private void cambiarValores(TorneoDominio nuevo, TorneoDominio actual) {
        actual.setNombre(nuevo.getNombre());
        actual.setDescripcion(nuevo.getDescripcion());
    }

    private void ObtenerDeporteDelTorneo(Long deporteId, TorneoDominio dominio) {
        DeporteEntidad deporteEntidad =deporteRepositorioJpa.findById(deporteId).orElseThrow(()->{
            String mensajeUsuario = "Deporte no encontrado";
            String mensajeTecnico = "Deporte no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        DeporteDominio deporteDominio = DeporteConvertorUtilitario.convertirDeporteEntidadEnDeporteDominio(deporteEntidad);
        dominio.setFkDeporte(deporteDominio);
    }

}