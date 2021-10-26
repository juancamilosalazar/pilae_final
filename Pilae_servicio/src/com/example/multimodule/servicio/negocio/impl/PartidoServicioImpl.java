package com.example.multimodule.servicio.negocio.impl;

import com.example.multimodule.entidad.*;
import com.example.multimodule.infraestructura.equipo.EquipoRepositorioJpa;
import com.example.multimodule.infraestructura.marcador.MarcadorRepositorioJpa;
import com.example.multimodule.infraestructura.partido.PartidoRepositorioJpa;
import com.example.multimodule.infraestructura.posicion.PosicionRepositorioJpa;
import com.example.multimodule.infraestructura.torneo.TorneoRepositorioJpa;
import com.example.multimodule.servicio.ensamblador.entidad.implementacion.MarcadorEnsambladorEntidad;
import com.example.multimodule.servicio.ensamblador.entidad.implementacion.PartidoEnsambladorEntidad;
import com.example.multimodule.servicio.negocio.PartidoServicio;
import com.example.multimodule.servicio.negocio.impl.utilitario.GeneracionFixtureIdaYVuelta;
import com.example.multimodule.servicio.negocio.impl.utilitario.GeneracionFixtureSoloIda;
import com.example.multimodule.servicio.utilitario.EquipoConvertorUtilitario;
import com.example.multimodule.servicio.utilitario.TorneoConvertorUtilitario;
import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.dominio.MarcadorDominio;
import main.com.example.multimodule.dominio.PartidoDominio;
import main.com.example.multimodule.dominio.TorneoDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PartidoServicioImpl implements PartidoServicio {

    private PartidoRepositorioJpa repositorio;
    private EquipoRepositorioJpa equipoRepositorioJpa;
    private TorneoRepositorioJpa torneoRepositorioJpa;
    private PosicionRepositorioJpa posicionRepositorio;
    private MarcadorRepositorioJpa marcadorRepositorio;
    private GeneracionFixtureIdaYVuelta generacionFixtureIdaYVuelta;
    private GeneracionFixtureSoloIda generacionFixtureSoloIda;


    @Autowired
    public PartidoServicioImpl(PartidoRepositorioJpa repositorio, EquipoRepositorioJpa equipoRepositorioJpa, TorneoRepositorioJpa torneoRepositorioJpa, PosicionRepositorioJpa posicionRepositorio, MarcadorRepositorioJpa marcadorRepositorio, GeneracionFixtureIdaYVuelta generacionFixtureIdaYVuelta, GeneracionFixtureSoloIda generacionFixtureSoloIda) {
        this.repositorio = repositorio;
        this.equipoRepositorioJpa = equipoRepositorioJpa;
        this.torneoRepositorioJpa = torneoRepositorioJpa;
        this.posicionRepositorio = posicionRepositorio;
        this.marcadorRepositorio = marcadorRepositorio;
        this.generacionFixtureIdaYVuelta = generacionFixtureIdaYVuelta;
        this.generacionFixtureSoloIda = generacionFixtureSoloIda;
    }

    @Override
    public List<PartidoDominio> obtenerTodos() {
        List<PartidoEntidad> entidadList = repositorio.findAll();

        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de Partidos está vacía";
            String mensajeTecnico = "la lista de Partidos está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<PartidoDominio> dominios = PartidoEnsambladorEntidad.obtenerPartidoEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }

    @Override
    public List<PartidoDominio> obtenerPorTorneo(Long idTorneo) {
        List<PartidoEntidad> entidadList = repositorio.findByFkTorneo(ObtenerTorneoEntidad(idTorneo));
        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de Partidos está vacía";
            String mensajeTecnico = "la lista de Partidos está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<PartidoDominio> dominios = PartidoEnsambladorEntidad.obtenerPartidoEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }

    @Override
    public PartidoDominio obtenerPorId(Long id)  {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        PartidoEntidad resultadosConsulta = repositorio.findById(id).orElseThrow(()->{
            String mensajeUsuario = "Partido no existe";
            String mensajeTecnico = "Partido no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });

        PartidoDominio dominio = PartidoEnsambladorEntidad.obtenerPartidoEnsambladorEntidad().ensamblarDominio(resultadosConsulta);
        return dominio;
    }

    @Override
    public void crear(PartidoDominio dominio, Long equipoLocal,Long equipoVisitante,Long torneoId)  {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "Partido no puede estar vacío";
            String mensajeTecnico = "Partido no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        if(Objects.nonNull(dominio.getCodigo())) {
            Optional<PartidoEntidad> resultadosConsulta = repositorio.findById(dominio.getCodigo());

            if (resultadosConsulta.isPresent()) {
                String mensajeUsuario = "Partido con el código existente";
                String mensajeTecnico = "Partido con el código existente";
                throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
            }
        }
        dominio.setFkEquipoLocal(ObtenerEquipoDelPartido(equipoLocal));
        dominio.setFkEquipoVisitante(ObtenerEquipoDelPartido(equipoVisitante));
        ObtenerTorneoDeLPartido(torneoId,dominio);
        PartidoEntidad entidad = PartidoEnsambladorEntidad.obtenerPartidoEnsambladorEntidad().ensamblarEntidad(dominio);
        repositorio.save(entidad);
    }

    @Override
    public void actualizar(PartidoDominio nuevo) {

        if (UtilObjeto.objetoEsNulo(nuevo)) {
            String mensajeUsuario = "Partido no puede estar vacío";
            String mensajeTecnico = "Partido no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        PartidoDominio resultadosConsulta = obtenerPorId(nuevo.getCodigo());

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "Partido no existe";
            String mensajeTecnico = "Partido no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        cambiarValores(nuevo,resultadosConsulta);
        PartidoEntidad entidad = PartidoEnsambladorEntidad.obtenerPartidoEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        repositorio.save(entidad);
    }

    @Override
    public void borrar(Long id) {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        PartidoDominio resultadosConsulta = obtenerPorId(id);

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "Partido no existe";
            String mensajeTecnico = "Partido no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        PartidoEntidad entidad = PartidoEnsambladorEntidad.obtenerPartidoEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        repositorio.delete(entidad);
    }

    @Override
    public void jugarPartido(MarcadorDominio marcadorDominio, Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        PartidoDominio resultadosConsulta = obtenerPorId(id);

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "Partido no existe";
            String mensajeTecnico = "Partido no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        cambiarValoresAPartidoJugado(resultadosConsulta);
        marcadorDominio.setFkPartido(resultadosConsulta);
        MarcadorEntidad marcadorEntidad = MarcadorEnsambladorEntidad.obtenerMarcadorEnsambladorEntidad().ensamblarEntidad(marcadorDominio);
        marcadorRepositorio.save(marcadorEntidad);
        PartidoEntidad entidad = PartidoEnsambladorEntidad.obtenerPartidoEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        actualizarTablaDePosiciones(entidad,marcadorDominio.getEquipoLocalMrc(),marcadorDominio.getEquipoVisitanteMrc());
        repositorio.save(entidad);
    }

    @Override
    public List<PartidoDominio> crearFixtureIdaYvuelta(Long idTorneo) {
        if (UtilTexto.estaVacia(idTorneo.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        TorneoEntidad torneoEntidad = obtenerTorneoEntidad(idTorneo);

        List<EquipoEntidad> equipos = equipoRepositorioJpa.findByFkTorneo(torneoEntidad);
        if(equipos.isEmpty()){
            String mensajeUsuario = "la lista de equipos está vacía";
            String mensajeTecnico = "la lista de equipos está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        List<PartidoEntidad> entidadList = generarFixtureIdaYvuelta(equipos,torneoEntidad);
        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de Partidos está vacía";
            String mensajeTecnico = "la lista de Partidos está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<PartidoDominio> dominios = PartidoEnsambladorEntidad.obtenerPartidoEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }

    private TorneoEntidad obtenerTorneoEntidad(Long idTorneo) {
        TorneoEntidad torneo = torneoRepositorioJpa.findById(idTorneo).orElseThrow(()->{
            String mensajeUsuario = "Torneo no encontrado";
            String mensajeTecnico = "Torneo no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        return torneo;
    }

    @Override
    public List<PartidoDominio> crearFixtureSoloIda(Long idTorneo) {
        if (UtilTexto.estaVacia(idTorneo.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        TorneoEntidad torneoEntidad = obtenerTorneoEntidad(idTorneo);

        List<EquipoEntidad> equipos = equipoRepositorioJpa.findByFkTorneo(torneoEntidad);
        if(equipos.isEmpty()){
            String mensajeUsuario = "la lista de equipos está vacía";
            String mensajeTecnico = "la lista de equipos está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        List<PartidoEntidad> entidadList = generarFixturesoloIda(equipos,torneoEntidad);
        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de Partidos está vacía";
            String mensajeTecnico = "la lista de Partidos está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<PartidoDominio> dominios = PartidoEnsambladorEntidad.obtenerPartidoEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }

    private void cambiarValoresAPartidoJugado(PartidoDominio resultadosConsulta) {
        resultadosConsulta.setEstadoPartido("jugado");
    }

    public List<PartidoEntidad> generarFixtureIdaYvuelta(List<EquipoEntidad> equipos, TorneoEntidad id) {
        HashMap<Integer, EquipoEntidad> generateFixture = new HashMap<>();
        int idEquipo = 0;
        for (EquipoEntidad equipo : equipos) {
            //para cada equipo le agrega un id que ayudara a generar el fixture
            idEquipo++;
            //inserta el equipo con el id generado para el fixture y el id
            generateFixture.put(idEquipo, equipo);
        }
        //calcula el fixture con el hashmap generado
        return generacionFixtureIdaYVuelta.mostrarPartidos(generacionFixtureIdaYVuelta.calcularLiga(generateFixture), generateFixture, id);
    }

    public List<PartidoEntidad> generarFixturesoloIda(List<EquipoEntidad> equipos, TorneoEntidad id) {
        HashMap<Integer, EquipoEntidad> generateFixture = new HashMap<>();
        int idEquipo = 0;
        for (EquipoEntidad equipo : equipos) {
            //para cada equipo le agrega un id que ayudara a generar el fixture
            idEquipo++;
            //inserta el equipo con el id generado para el fixture y el id
            generateFixture.put(idEquipo, equipo);
        }
        //calcula el fixture con el hashmap generado
        return generacionFixtureSoloIda.mostrarPartidos(generacionFixtureIdaYVuelta.calcularLiga(generateFixture), generateFixture, id);
    }

    private void actualizarTablaDePosiciones(PartidoEntidad partido, int marcadorEquipoLocal, int marcadorEquipoVisitante) {
        PosicionEntidad posicionEntidadLocal = posicionRepositorio.findByfkEquipo(partido.getFkEquipoLocal());
        PosicionEntidad posicionEntidadVisitante = posicionRepositorio.findByfkEquipo(partido.getFkEquipoVisitante());
        posicionEntidadLocal.setFkEquipo(partido.getFkEquipoLocal());
        posicionEntidadLocal.setGolesContra(posicionEntidadLocal.getGolesContra() + marcadorEquipoVisitante);
        posicionEntidadLocal.setGolesFavor(posicionEntidadLocal.getGolesFavor() + marcadorEquipoLocal);
        posicionEntidadLocal.setGolesDiferencia(posicionEntidadLocal.getGolesFavor() - posicionEntidadLocal.getGolesContra());
        posicionEntidadLocal.setPartidosJugados(posicionEntidadLocal.getPartidosJugados() + 1);
        posicionEntidadVisitante.setFkEquipo(partido.getFkEquipoVisitante());
        posicionEntidadVisitante.setGolesContra(posicionEntidadVisitante.getGolesContra() + marcadorEquipoLocal);
        posicionEntidadVisitante.setGolesFavor(posicionEntidadVisitante.getGolesFavor() + marcadorEquipoVisitante);
        posicionEntidadVisitante.setGolesDiferencia(posicionEntidadVisitante.getGolesFavor() - posicionEntidadVisitante.getGolesContra());
        posicionEntidadVisitante.setPartidosJugados(posicionEntidadVisitante.getPartidosJugados() + 1);
        validarEquipoGanadorPosicion(marcadorEquipoLocal, marcadorEquipoVisitante, posicionEntidadLocal, posicionEntidadVisitante);
        posicionRepositorio.save(posicionEntidadVisitante);
        posicionRepositorio.save(posicionEntidadLocal);
    }

    private void validarEquipoGanadorPosicion(int equipoLocal, int equipoVisitante, PosicionEntidad posicionEntidadLocal, PosicionEntidad posicionEntidadVisitante) {
        if (equipoLocal > equipoVisitante) {
            posicionEntidadLocal.setPartidosGanados(posicionEntidadLocal.getPartidosGanados() + 1);
            posicionEntidadVisitante.setPartidosPerdidos(posicionEntidadVisitante.getPartidosPerdidos() + 1);
            posicionEntidadLocal.setPuntos(posicionEntidadLocal.getPuntos() + 3);
        } else if (equipoLocal == equipoVisitante) {
            posicionEntidadLocal.setPartidosEmpatados(posicionEntidadLocal.getPartidosEmpatados() + 1);
            posicionEntidadVisitante.setPartidosEmpatados(posicionEntidadVisitante.getPartidosEmpatados() + 1);
            posicionEntidadLocal.setPuntos(posicionEntidadLocal.getPuntos() + 1);
            posicionEntidadVisitante.setPuntos(posicionEntidadVisitante.getPuntos() + 1);
        } else {
            posicionEntidadVisitante.setPartidosGanados(posicionEntidadVisitante.getPartidosGanados() + 1);
            posicionEntidadLocal.setPartidosPerdidos(posicionEntidadLocal.getPartidosPerdidos() + 1);
            posicionEntidadVisitante.setPuntos(posicionEntidadVisitante.getPuntos() + 3);
        }
    }

    private void cambiarValores(PartidoDominio nuevo, PartidoDominio actual) {
        actual.setEstadoPartido(nuevo.getEstadoPartido());
        actual.setFechaDelPartido(nuevo.getFechaDelPartido());
        actual.setIdaVuelta(nuevo.getIdaVuelta());
        actual.setRonda(nuevo.getRonda());
    }

    private EquipoDominio ObtenerEquipoDelPartido(Long equipoId) {
        EquipoEntidad equipoEntidad =equipoRepositorioJpa.findById(equipoId).orElseThrow(()->{
            String mensajeUsuario = "Torneo no encontrado";
            String mensajeTecnico = "Torneo no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        EquipoDominio equipoDominio = EquipoConvertorUtilitario.convertirEquipoEntidadEnEquipoDominio(equipoEntidad);
        return equipoDominio;
    }

    private void ObtenerTorneoDeLPartido(Long torneoId, PartidoDominio dominio) {
        TorneoEntidad torneo = torneoRepositorioJpa.findById(torneoId).orElseThrow(()->{
            String mensajeUsuario = "Torneo no encontrado";
            String mensajeTecnico = "Torneo no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        TorneoDominio torneoDominio = TorneoConvertorUtilitario.convertirTorneoEntidadEnTorneoDominio(torneo);
        dominio.setFkTorneo(torneoDominio);
    }

    private TorneoEntidad ObtenerTorneoEntidad(Long torneoId) {
        TorneoEntidad torneo = torneoRepositorioJpa.findById(torneoId).orElseThrow(()->{
            String mensajeUsuario = "Torneo no encontrado";
            String mensajeTecnico = "Torneo no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        return torneo;
    }
}
