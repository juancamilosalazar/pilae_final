package com.example.multimodule.servicio.negocio.impl;

import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.entidad.JugadorEntidad;
import com.example.multimodule.infraestructura.equipo.EquipoRepositorioJpa;
import com.example.multimodule.infraestructura.jugador.JugadorRepositorioJpa;
import com.example.multimodule.servicio.ensamblador.entidad.implementacion.JugadorEnsambladorEntidad;
import com.example.multimodule.servicio.negocio.JugadorServicio;
import com.example.multimodule.servicio.utilitario.EquipoConvertorUtilitario;
import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.dominio.JugadorDominio;
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
public class JugadorServicioImpl implements JugadorServicio {

    private JugadorRepositorioJpa repositorio;
    private EquipoRepositorioJpa equipoRepositorioJpa;


    @Autowired
    public JugadorServicioImpl(JugadorRepositorioJpa repositorio, EquipoRepositorioJpa equipoRepositorioJpa) {
        this.repositorio = repositorio;
        this.equipoRepositorioJpa = equipoRepositorioJpa;
    }

    @Override
    public List<JugadorDominio> obtenerTodos() {
        List<JugadorEntidad> entidadList = repositorio.findAll();

        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de Jugadores está vacía";
            String mensajeTecnico = "la lista de Jugadores está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<JugadorDominio> dominios = JugadorEnsambladorEntidad.obtenerJugadorEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }



    @Override
    public JugadorDominio obtenerPorId(Long id)  {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        JugadorEntidad resultadosConsulta = repositorio.findById(id).orElseThrow(()->{
            String mensajeUsuario = "Jugador no existe";
            String mensajeTecnico = "Jugador no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });

        JugadorDominio dominio = JugadorEnsambladorEntidad.obtenerJugadorEnsambladorEntidad().ensamblarDominio(resultadosConsulta);
        return dominio;
    }

    @Override
    public void crear(JugadorDominio dominio, Long equipoId)  {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "Jugador no puede estar vacío";
            String mensajeTecnico = "Jugador no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        if(Objects.nonNull(dominio.getCodigo())) {
            Optional<JugadorEntidad> resultadosConsulta = repositorio.findById(dominio.getCodigo());

            if (resultadosConsulta.isPresent()) {
                String mensajeUsuario = "Jugador con el código existente";
                String mensajeTecnico = "Jugador con el código existente";
                throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
            }
        }
        obtenerEquipoDelJugador(equipoId,dominio);
        JugadorEntidad entidad = JugadorEnsambladorEntidad.obtenerJugadorEnsambladorEntidad().ensamblarEntidad(dominio);
        repositorio.save(entidad);
    }

    @Override
    public void actualizar(JugadorDominio nuevo) {

        if (UtilObjeto.objetoEsNulo(nuevo)) {
            String mensajeUsuario = "Jugador no puede estar vacío";
            String mensajeTecnico = "Jugador no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        JugadorDominio resultadosConsulta = obtenerPorId(nuevo.getCodigo());

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "Jugador no existe";
            String mensajeTecnico = "Jugador no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        cambiarValores(nuevo,resultadosConsulta);
        JugadorEntidad entidad = JugadorEnsambladorEntidad.obtenerJugadorEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        repositorio.save(entidad);
    }

    @Override
    public void borrar(Long id) {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        JugadorDominio resultadosConsulta = obtenerPorId(id);

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "Jugador no existe";
            String mensajeTecnico = "Jugador no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        JugadorEntidad entidad = JugadorEnsambladorEntidad.obtenerJugadorEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        repositorio.delete(entidad);
    }

    @Override
    public List<JugadorDominio> obtenerPorEquipo(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        List<JugadorEntidad> entidadList = repositorio.findByFkEquipo(obtenerEquipoEntidad(id));
        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de Jugadores está vacía";
            String mensajeTecnico = "la lista de Jugadores está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<JugadorDominio> dominios = JugadorEnsambladorEntidad.obtenerJugadorEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }

    private void cambiarValores(JugadorDominio nuevo, JugadorDominio actual) {
        actual.setNombre(nuevo.getNombre());
        actual.setFkEquipo(nuevo.getFkEquipo());
        actual.setIdentificacion(nuevo.getIdentificacion());
        actual.setFechaNacimiento(nuevo.getFechaNacimiento());
    }

    private void obtenerEquipoDelJugador(Long equipoId, JugadorDominio dominio) {
        EquipoEntidad equipoEntidad =equipoRepositorioJpa.findById(equipoId).orElseThrow(()->{
            String mensajeUsuario = "Torneo no encontrado";
            String mensajeTecnico = "Torneo no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        EquipoDominio equipoDominio = EquipoConvertorUtilitario.convertirEquipoEntidadEnEquipoDominio(equipoEntidad);
        dominio.setFkEquipo(equipoDominio);
    }

    private EquipoEntidad obtenerEquipoEntidad(Long equipoId) {
        EquipoEntidad equipoEntidad =equipoRepositorioJpa.findById(equipoId).orElseThrow(()->{
            String mensajeUsuario = "Torneo no encontrado";
            String mensajeTecnico = "Torneo no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        return equipoEntidad;
    }

}