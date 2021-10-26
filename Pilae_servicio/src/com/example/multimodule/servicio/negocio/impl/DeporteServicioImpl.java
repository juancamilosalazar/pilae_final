package com.example.multimodule.servicio.negocio.impl;

import com.example.multimodule.entidad.DeporteEntidad;
import com.example.multimodule.infraestructura.deporte.DeporteRepositorioJpa;
import com.example.multimodule.servicio.ensamblador.entidad.implementacion.DeporteEnsambladorEntidad;
import com.example.multimodule.servicio.negocio.DeporteServicio;
import main.com.example.multimodule.dominio.DeporteDominio;
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
public class DeporteServicioImpl implements DeporteServicio {

    private DeporteRepositorioJpa repositorio;


    @Autowired
    public DeporteServicioImpl(DeporteRepositorioJpa repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<DeporteDominio> obtenerTodos() {
        List<DeporteEntidad> entidadList = repositorio.findAll();

        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de Deportes está vacía";
            String mensajeTecnico = "la lista de Deportes está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<DeporteDominio> dominios = DeporteEnsambladorEntidad.obtenerDeporteEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }



    @Override
    public DeporteDominio obtenerPorId(Long id)  {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        DeporteEntidad resultadosConsulta = repositorio.findById(id).orElseThrow(()->{
            String mensajeUsuario = "Deporte no existe";
            String mensajeTecnico = "Deporte no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });

        DeporteDominio dominio = DeporteEnsambladorEntidad.obtenerDeporteEnsambladorEntidad().ensamblarDominio(resultadosConsulta);
        return dominio;
    }

    @Override
    public void crear(DeporteDominio deporteDominio)  {

        if (UtilObjeto.objetoEsNulo(deporteDominio)) {
            String mensajeUsuario = "Deporte no puede estar vacío";
            String mensajeTecnico = "Deporte no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        if(Objects.nonNull(deporteDominio.getCodigo())) {
            Optional<DeporteEntidad> resultadosConsulta = repositorio.findById(deporteDominio.getCodigo());

            if (resultadosConsulta.isPresent()) {
                String mensajeUsuario = "Deporte con el código existente";
                String mensajeTecnico = "Deporte con el código existente";
                throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
            }
        }
        DeporteEntidad DeporteEntidad = DeporteEnsambladorEntidad.obtenerDeporteEnsambladorEntidad().ensamblarEntidad(deporteDominio);
        repositorio.save(DeporteEntidad);
    }

    @Override
    public void actualizar(DeporteDominio nuevo) {

        if (UtilObjeto.objetoEsNulo(nuevo)) {
            String mensajeUsuario = "Deporte no puede estar vacío";
            String mensajeTecnico = "Deporte no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        DeporteDominio resultadosConsulta = obtenerPorId(nuevo.getCodigo());

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "Deporte no existe";
            String mensajeTecnico = "Deporte no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        cambiarValores(nuevo,resultadosConsulta);
        DeporteEntidad entidad = DeporteEnsambladorEntidad.obtenerDeporteEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        repositorio.save(entidad);
    }

    @Override
    public void borrar(Long id) {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        DeporteDominio resultadosConsulta = obtenerPorId(id);

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "Deporte no existe";
            String mensajeTecnico = "Deporte no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        DeporteEntidad entidad = DeporteEnsambladorEntidad.obtenerDeporteEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        repositorio.delete(entidad);
    }

    private void cambiarValores(DeporteDominio nuevo, DeporteDominio actual) {
        actual.setNombre(nuevo.getNombre());
    }

}