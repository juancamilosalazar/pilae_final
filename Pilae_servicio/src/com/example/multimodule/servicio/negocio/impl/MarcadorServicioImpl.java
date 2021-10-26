package com.example.multimodule.servicio.negocio.impl;

import com.example.multimodule.entidad.MarcadorEntidad;
import com.example.multimodule.entidad.PartidoEntidad;
import com.example.multimodule.entidad.TorneoEntidad;
import com.example.multimodule.infraestructura.marcador.MarcadorRepositorioJpa;
import com.example.multimodule.infraestructura.partido.PartidoRepositorioJpa;
import com.example.multimodule.infraestructura.torneo.TorneoRepositorioJpa;
import com.example.multimodule.servicio.ensamblador.entidad.implementacion.MarcadorEnsambladorEntidad;
import com.example.multimodule.servicio.negocio.MarcadorServicio;
import com.example.multimodule.servicio.utilitario.PartidoConvertorUtilitario;
import main.com.example.multimodule.dominio.MarcadorDominio;
import main.com.example.multimodule.dominio.PartidoDominio;
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
public class MarcadorServicioImpl  implements MarcadorServicio {

    private MarcadorRepositorioJpa repositorio;
    private PartidoRepositorioJpa partidoRepositorioJpa;
    private TorneoRepositorioJpa torneoRepositorio;


    @Autowired
    public MarcadorServicioImpl(MarcadorRepositorioJpa repositorio, PartidoRepositorioJpa partidoRepositorioJpa, TorneoRepositorioJpa torneoRepositorio) {
        this.repositorio = repositorio;
        this.partidoRepositorioJpa = partidoRepositorioJpa;
        this.torneoRepositorio = torneoRepositorio;
    }

    @Override
    public List<MarcadorDominio> obtenerTodos() {
        List<MarcadorEntidad> entidadList = repositorio.findAll();

        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de Marcadores está vacía";
            String mensajeTecnico = "la lista de Marcadores está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<MarcadorDominio> dominios = MarcadorEnsambladorEntidad.obtenerMarcadorEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }



    @Override
    public MarcadorDominio obtenerPorId(Long id)  {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        MarcadorEntidad resultadosConsulta = repositorio.findById(id).orElseThrow(()->{
            String mensajeUsuario = "Marcador no existe";
            String mensajeTecnico = "Marcador no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });

        MarcadorDominio dominio = MarcadorEnsambladorEntidad.obtenerMarcadorEnsambladorEntidad().ensamblarDominio(resultadosConsulta);
        return dominio;
    }

    @Override
    public void crear(MarcadorDominio dominio, Long partidoId)  {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "Marcador no puede estar vacío";
            String mensajeTecnico = "Marcador no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        if(Objects.nonNull(dominio.getCodigo())) {
            Optional<MarcadorEntidad> resultadosConsulta = repositorio.findById(dominio.getCodigo());

            if (resultadosConsulta.isPresent()) {
                String mensajeUsuario = "Marcador con el código existente";
                String mensajeTecnico = "Marcador con el código existente";
                throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
            }
        }
        ObtenerEquipoDelMarcador(partidoId,dominio);
        MarcadorEntidad entidad = MarcadorEnsambladorEntidad.obtenerMarcadorEnsambladorEntidad().ensamblarEntidad(dominio);
        repositorio.save(entidad);
    }

    @Override
    public void actualizar(MarcadorDominio nuevo) {

        if (UtilObjeto.objetoEsNulo(nuevo)) {
            String mensajeUsuario = "Marcador no puede estar vacío";
            String mensajeTecnico = "Marcador no puede estar vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        MarcadorDominio resultadosConsulta = obtenerPorId(nuevo.getCodigo());

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "Marcador no existe";
            String mensajeTecnico = "Marcador no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        cambiarValores(nuevo,resultadosConsulta);
        MarcadorEntidad entidad = MarcadorEnsambladorEntidad.obtenerMarcadorEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        repositorio.save(entidad);
    }

    @Override
    public void borrar(Long id) {

        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        MarcadorDominio resultadosConsulta = obtenerPorId(id);

        if (UtilObjeto.objetoEsNulo(resultadosConsulta)) {
            String mensajeUsuario = "Marcador no existe";
            String mensajeTecnico = "Marcador no existe";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        MarcadorEntidad entidad = MarcadorEnsambladorEntidad.obtenerMarcadorEnsambladorEntidad().ensamblarEntidad(resultadosConsulta);
        repositorio.delete(entidad);
    }

    @Override
    public List<MarcadorDominio> obtenerPorPartido(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        List<MarcadorEntidad> entidadList = repositorio.findByFkPartido(obtenerPartidoEntidad(id));

        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de Marcadores está vacía";
            String mensajeTecnico = "la lista de Marcadores está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<MarcadorDominio> dominios = MarcadorEnsambladorEntidad.obtenerMarcadorEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }

    @Override
    public List<MarcadorDominio> obtenerPorTorneo(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "el id esta vacío";
            String mensajeTecnico = "el id esta vacío";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        List<MarcadorEntidad> entidadList = repositorio.findByFkPartidoFkTorneo(obtenerTorneoEntidad(id));

        if(entidadList.isEmpty()){
            String mensajeUsuario = "la lista de Marcadores está vacía";
            String mensajeTecnico = "la lista de Marcadores está vacía";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }else{
            List<MarcadorDominio> dominios = MarcadorEnsambladorEntidad.obtenerMarcadorEnsambladorEntidad().ensamblarListaDominio(entidadList);
            return dominios;
        }
    }

    private void cambiarValores(MarcadorDominio nuevo, MarcadorDominio actual) {
        actual.setEquipoGanador(nuevo.getEquipoGanador());
        actual.setEquipoLocalMrc(nuevo.getEquipoLocalMrc());
        actual.setEquipoVisitanteMrc(nuevo.getEquipoVisitanteMrc());
        actual.setFkPartido(nuevo.getFkPartido());
    }

    private void ObtenerEquipoDelMarcador(Long partidoId, MarcadorDominio dominio) {
        PartidoEntidad partidoEntidad = partidoRepositorioJpa.findById(partidoId).orElseThrow(()->{
            String mensajeUsuario = "Partido no encontrado";
            String mensajeTecnico = "Partido no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        PartidoDominio partidoDominio = PartidoConvertorUtilitario.convertirPartidoEntidadEnPartidoDominio(partidoEntidad);
        dominio.setFkPartido(partidoDominio);
    }

    private PartidoEntidad obtenerPartidoEntidad(Long partidoId) {
        PartidoEntidad partidoEntidad = partidoRepositorioJpa.findById(partidoId).orElseThrow(()->{
            String mensajeUsuario = "Partido no encontrado";
            String mensajeTecnico = "Partido no encontrado";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        });
        return partidoEntidad;
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
