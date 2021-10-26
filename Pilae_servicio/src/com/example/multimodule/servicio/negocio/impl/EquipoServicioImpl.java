package com.example.multimodule.servicio.negocio.impl;

import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.entidad.PosicionEntidad;
import com.example.multimodule.entidad.TorneoEntidad;
import com.example.multimodule.infraestructura.equipo.EquipoRepositorioJpa;
import com.example.multimodule.infraestructura.posicion.PosicionRepositorioJpa;
import com.example.multimodule.infraestructura.torneo.TorneoRepositorioJpa;
import com.example.multimodule.servicio.ensamblador.entidad.implementacion.EquipoEnsambladorEntidad;
import com.example.multimodule.servicio.negocio.EquipoServicio;
import com.example.multimodule.servicio.utilitario.EquipoConvertorUtilitario;
import com.example.multimodule.servicio.utilitario.TorneoConvertorUtilitario;
import main.com.example.multimodule.dominio.EquipoDominio;
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
public class EquipoServicioImpl  implements EquipoServicio {

    private EquipoRepositorioJpa equipoRepositorio;
    private TorneoRepositorioJpa torneoRepositorio;
    private PosicionRepositorioJpa posicionRepositorioJpa;



    @Autowired
    public EquipoServicioImpl(EquipoRepositorioJpa equipoRepositorio, TorneoRepositorioJpa torneoRepositorio, PosicionRepositorioJpa posicionRepositorioJpa) {
        this.equipoRepositorio = equipoRepositorio;
        this.torneoRepositorio = torneoRepositorio;
        this.posicionRepositorioJpa = posicionRepositorioJpa;
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
        if(Objects.nonNull(equipoDominio.getCodigo())) {
            Optional<EquipoEntidad> resultadosConsulta = equipoRepositorio.findById(equipoDominio.getCodigo());
            if (resultadosConsulta.isPresent()) {
                String mensajeUsuario = "equipo con el codigo existente";
                String mensajeTecnico = "equipo con el codigo existente";
                throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
            }
        }
        ObtenerTorneoDelPartido(torneoId,equipoDominio);
        EquipoEntidad equipoEntidad = EquipoEnsambladorEntidad.obtenerEquipoEnsambladorEntidad().ensamblarEntidad(equipoDominio);
        equipoRepositorio.save(equipoEntidad);
        crearTablaDePosiciones(equipoEntidad);
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

    @Override
    public List<EquipoDominio> obtenerPorTorneo(Long torneoId) {
        if (UtilTexto.estaVacia(torneoId.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        List<EquipoEntidad> equiposEntidad = equipoRepositorio.findByFkTorneo(obtenerTorneoEntidad(torneoId));
        if(equiposEntidad.isEmpty()){
            String mensajeUsuario = "la lista de equipos está vacía";
            String mensajeTecnico = "la lista de equipos está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<EquipoDominio> equipoDominios = EquipoEnsambladorEntidad.obtenerEquipoEnsambladorEntidad().ensamblarListaDominio(equiposEntidad);
            return equipoDominios;
        }
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
        equipo.setFkTorneo(torneoDominio);
    }
    private TorneoEntidad obtenerTorneoEntidad(Long torneoId) {
        TorneoEntidad torneo =torneoRepositorio.findById(torneoId).orElseThrow(()->{
            String mensajeUsuario = "Torneo no encontrado";
            String mensajeTecnico = "Torneo no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        return torneo;
    }

    public void crearTablaDePosiciones(EquipoEntidad equipoDominio) {
        PosicionEntidad posicionEntidad = new PosicionEntidad();
        posicionEntidad.setPuntos(0);
        posicionEntidad.setPartidosEmpatados(0);
        posicionEntidad.setPartidosPerdidos(0);
        posicionEntidad.setPartidosGanados(0);
        posicionEntidad.setPartidosJugados(0);
        posicionEntidad.setGolesDiferencia(0);
        posicionEntidad.setGolesFavor(0);
        posicionEntidad.setGolesContra(0);
        posicionEntidad.setFkTorneo(equipoDominio.getFkTorneo());
        posicionEntidad.setFkEquipo(equipoDominio);
        posicionRepositorioJpa.save(posicionEntidad);
    }

}
