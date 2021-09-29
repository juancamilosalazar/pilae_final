package com.example.multimodule.servicio.fachada.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.implementacion.EquipoEnsamblador;
import com.example.multimodule.servicio.ensamblador.dto.implementacion.PartidoEnsamblador;
import com.example.multimodule.servicio.fachada.EquipoFachada;
import com.example.multimodule.servicio.fachada.PartidoFachada;
import com.example.multimodule.servicio.negocio.EquipoServicio;
import com.example.multimodule.servicio.negocio.PartidoServicio;
import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.dominio.PartidoDominio;
import main.com.example.multimodule.dto.Equipo;
import main.com.example.multimodule.dto.Marcador;
import main.com.example.multimodule.dto.Partido;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
            String mensajeTecnico = "eqyuipo nulo";
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
    public void crear(Partido partido, Long idTorneo, Long idVisitante, Long torneoId) {

    }

    @Override
    public void jugarPartido(Marcador marcador, Long idPartido) {

    }

    @Override
    public List<Partido> obtenerPorTorneo(Long idTorneo) {
        return null;
    }
}