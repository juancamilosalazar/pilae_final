package com.example.multimodule.servicio.negocio.impl;

import com.example.multimodule.infraestructura.equipo.EquipoRepositorioJpa;
import com.example.multimodule.infraestructura.torneo.TorneoRepositorioJpa;
import com.example.multimodule.servicio.negocio.EquipoServicio;
import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.dominio.TorneoDominio;
import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.entidad.TorneoEntidad;

import com.example.multimodule.servicio.ensamblador.entidad.implementacion.EquipoEnsambladorEntidad;
import com.example.multimodule.servicio.utilitario.TorneoConvertorUtilitario;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EquipoServicioImpl  implements EquipoServicio {

    private EquipoRepositorioJpa equipoRepositorio;
    private TorneoRepositorioJpa torneoRepositorio;



    @Autowired
    public EquipoServicioImpl(EquipoRepositorioJpa equipoRepositorio, TorneoRepositorioJpa torneoRepositorio) {
        this.equipoRepositorio = equipoRepositorio;
        this.torneoRepositorio = torneoRepositorio;
    }

    @Override
    public List<EquipoDominio> obtenerTodos() {
        List<EquipoEntidad> equiposEntidad = equipoRepositorio.findAll();

        if(equiposEntidad.isEmpty()){
            String mensajeUsuario = "la lista de equipos está vacía";
            String mensajeTecnico = "la lista de equipos está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<EquipoDominio> equipoDominios = EquipoEnsambladorEntidad.obtenerEquipoEnsambladorEntidad().ensamblarListaDominio(equiposEntidad);
            return equipoDominios;
        }
    }



    @Override
    public EquipoDominio obtenerPorId(Long id)  {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        EquipoEntidad resultadosConsulta = equipoRepositorio.findById(id).orElseThrow(()->{
            String mensajeUsuario = "equipo no existe";
            String mensajeTecnico = "equipo no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });

        EquipoDominio equipoDominio = EquipoEnsambladorEntidad.obtenerEquipoEnsambladorEntidad().ensamblarDominio(resultadosConsulta);
        return equipoDominio;
    }

    @Override
    public void crear(EquipoDominio equipoDominio, Long torneoId)  {

        if (UtilObjeto.objetoEsNulo(equipoDominio)) {
            String mensajeUsuario = "equipo no puede estar vacío";
            String mensajeTecnico = "equipo no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        EquipoDominio resultadosConsulta = obtenerPorId(equipoDominio.getCodigo());

        if (!UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "equipo con el codigo existente";
            String mensajeTecnico = "equipo con el codigo existente";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        ObtenerTorneoDelPartido(torneoId,equipoDominio);
        EquipoEntidad equipoEntidad = EquipoEnsambladorEntidad.obtenerEquipoEnsambladorEntidad().ensamblarEntidad(equipoDominio);
        equipoRepositorio.save(equipoEntidad);
    }

    @Override
    public void actualizar(EquipoDominio equipoNuevo) {

        if (UtilObjeto.objetoEsNulo(equipoNuevo)) {
            String mensajeUsuario = "equipo no puede estar vacío";
            String mensajeTecnico = "equipo no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        EquipoDominio resultadosConsulta = obtenerPorId(equipoNuevo.getCodigo());

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "equipo no existe";
            String mensajeTecnico = "equipo no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        cambiarValores(equipoNuevo,resultadosConsulta);
        EquipoEntidad equipoEntidad = EquipoEnsambladorEntidad.obtenerEquipoEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        equipoRepositorio.save(equipoEntidad);
    }

    @Override
    public void borrar(Long id) {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        EquipoDominio resultadosConsulta = obtenerPorId(id);

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "equipo no existe";
            String mensajeTecnico = "equipo no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        EquipoEntidad equipoEntidad = EquipoEnsambladorEntidad.obtenerEquipoEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        equipoRepositorio.delete(equipoEntidad);
    }

    private void cambiarValores(EquipoDominio equipoNuevo, EquipoDominio equipo) {
        equipo.setGenero(equipoNuevo.getGenero());
        equipo.setLocacion(equipoNuevo.getLocacion());
        equipo.setNombre(equipoNuevo.getNombre());
    }

    private void ObtenerTorneoDelPartido(Long torneoId, EquipoDominio equipo) {
        TorneoEntidad torneo =torneoRepositorio.findById(torneoId).orElseThrow(()->{
            String mensajeUsuario = "Torneo no encontrado";
            String mensajeTecnico = "Torneo no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        TorneoDominio torneoDominio = TorneoConvertorUtilitario.convertirTorneoEntidadEnTorneoDominio(torneo);
        equipo.setTorneo(torneoDominio);
    }

}
