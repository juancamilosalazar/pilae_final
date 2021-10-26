package com.example.multimodule.servicio.fachada.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.implementacion.JugadorEnsamblador;
import com.example.multimodule.servicio.fachada.JugadorFachada;
import com.example.multimodule.servicio.negocio.JugadorServicio;
import main.com.example.multimodule.dominio.JugadorDominio;
import main.com.example.multimodule.dto.Jugador;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorFachadaImplementacion implements JugadorFachada {

    @Autowired
    private JugadorServicio servicio;


    @Override
    public List<Jugador> obtenerTodos() {
        return JugadorEnsamblador.obtenerJugadorEnsambladorDTO().ensamblarListaDTO(servicio.obtenerTodos());
    }

    @Override
    public Jugador obtenerPorId(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        JugadorDominio dominio = servicio.obtenerPorId(id);
        return JugadorEnsamblador.obtenerJugadorEnsambladorDTO().ensamblarDTO(dominio);
    }

    @Override
    public void crear(Jugador dto, Long id) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "Jugador no puede ser nulo";
            String mensajeTecnico = "eqyuipo nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        JugadorDominio dominio = JugadorEnsamblador.obtenerJugadorEnsambladorDTO().ensamblarDominio(dto);
        servicio.crear(dominio,id);
    }

    @Override
    public void actualizar(Jugador dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "Jugador no puede ser nulo";
            String mensajeTecnico = "eqyuipo nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        JugadorDominio dominio = JugadorEnsamblador.obtenerJugadorEnsambladorDTO().ensamblarDominio(dto);
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

    @Override
    public List<Jugador> obtenerPorEquipo(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return JugadorEnsamblador.obtenerJugadorEnsambladorDTO().ensamblarListaDTO(servicio.obtenerPorEquipo(id));
    }
}