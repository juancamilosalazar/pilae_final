package com.example.multimodule.servicio.fachada.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.implementacion.EquipoEnsamblador;
import com.example.multimodule.servicio.ensamblador.dto.implementacion.MarcadorEnsamblador;
import com.example.multimodule.servicio.ensamblador.dto.implementacion.PartidoEnsamblador;
import com.example.multimodule.servicio.fachada.PartidoFachada;
import com.example.multimodule.servicio.negocio.PartidoServicio;
import main.com.example.multimodule.dominio.MarcadorDominio;
import main.com.example.multimodule.dominio.PartidoDominio;
import main.com.example.multimodule.dto.Marcador;
import main.com.example.multimodule.dto.Partido;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PartidoFachadaImplementacion implements PartidoFachada {

    @Autowired
    private PartidoServicio servicio;


    @Override
    public List<Partido> obtenerTodos() {
        return PartidoEnsamblador.obtenerPartidoEnsambladorDTO().ensamblarListaDTO(servicio.obtenerTodos());
    }

    @Override
    public Partido obtenerPorId(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        PartidoDominio dominio = servicio.obtenerPorId(id);
        return PartidoEnsamblador.obtenerPartidoEnsambladorDTO().ensamblarDTO(dominio);
    }




    @Override
    public void actualizar(Partido dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "Partido no puede ser nulo";
            String mensajeTecnico = "Partido nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        PartidoDominio dominio = PartidoEnsamblador.obtenerPartidoEnsambladorDTO().ensamblarDominio(dto);
        servicio.actualizar(dominio);
    }

    @Override
    public void borrar(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        servicio.borrar(id);
    }

    //TODO REALIZAR METODOS
    @Override
    public void crear(Partido partido, Long idLocal, Long idVisitante, Long torneoId) {
        if (UtilObjeto.objetoEsNulo(partido)) {
            String mensajeUsuario = "Partido no puede ser nulo";
            String mensajeTecnico = "Partido nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        PartidoDominio dominio = PartidoEnsamblador.obtenerPartidoEnsambladorDTO().ensamblarDominio(partido);
        servicio.crear(dominio,idLocal,idVisitante,torneoId);
    }

    @Override
    public void jugarPartido(Marcador marcador, Long idPartido) {
        if (UtilObjeto.objetoEsNulo(marcador)) {
            String mensajeUsuario = "Marcador no puede ser nulo";
            String mensajeTecnico = "Marcador nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        MarcadorDominio dominio = MarcadorEnsamblador.obtenerMarcadorEnsambladorDTO().ensamblarDominio(marcador);
        servicio.jugarPartido(dominio,idPartido);
    }

    @Override
    public List<Partido> obtenerPorTorneo(Long idTorneo) {
        if (UtilTexto.estaVacia(idTorneo.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return PartidoEnsamblador.obtenerPartidoEnsambladorDTO().ensamblarListaDTO(servicio.obtenerPorTorneo(idTorneo));
    }

    @Override
    public List<Partido> crearFixtureSoloIda(Long idTorneo) {
        if (UtilTexto.estaVacia(idTorneo.toString())) {
            String mensajeUsuario = "se requiere id para la generación de fixture";
            String mensajeTecnico = "se requiere id para la generación de fixture";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return PartidoEnsamblador.obtenerPartidoEnsambladorDTO().ensamblarListaDTO(servicio.crearFixtureSoloIda(idTorneo));
    }

    @Override
    public List<Partido> crearFixtureIdaYvuelta(Long idTorneo) {
        if (UtilTexto.estaVacia(idTorneo.toString())) {
            String mensajeUsuario = "se requiere id para la generación de fixture";
            String mensajeTecnico = "se requiere id para la generación de fixture";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return PartidoEnsamblador.obtenerPartidoEnsambladorDTO().ensamblarListaDTO(servicio.crearFixtureIdaYvuelta(idTorneo));
    }
}