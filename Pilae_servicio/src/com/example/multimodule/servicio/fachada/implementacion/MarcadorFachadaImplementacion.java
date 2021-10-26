package com.example.multimodule.servicio.fachada.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.implementacion.MarcadorEnsamblador;
import com.example.multimodule.servicio.fachada.MarcadorFachada;
import com.example.multimodule.servicio.negocio.MarcadorServicio;
import main.com.example.multimodule.dominio.MarcadorDominio;
import main.com.example.multimodule.dto.Marcador;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarcadorFachadaImplementacion implements MarcadorFachada {

    @Autowired
    private MarcadorServicio servicio;


    @Override
    public List<Marcador> obtenerTodos() {
        return MarcadorEnsamblador.obtenerMarcadorEnsambladorDTO().ensamblarListaDTO(servicio.obtenerTodos());
    }

    @Override
    public Marcador obtenerPorId(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        MarcadorDominio dominio = servicio.obtenerPorId(id);
        return MarcadorEnsamblador.obtenerMarcadorEnsambladorDTO().ensamblarDTO(dominio);
    }

    @Override
    public void crear(Marcador dto, Long id) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "Marcador no puede ser nulo";
            String mensajeTecnico = "eqyuipo nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        MarcadorDominio dominio = MarcadorEnsamblador.obtenerMarcadorEnsambladorDTO().ensamblarDominio(dto);
        servicio.crear(dominio,id);
    }

    @Override
    public void actualizar(Marcador dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "Marcador no puede ser nulo";
            String mensajeTecnico = "eqyuipo nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        MarcadorDominio dominio = MarcadorEnsamblador.obtenerMarcadorEnsambladorDTO().ensamblarDominio(dto);
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

    //TODO HACER CONSULTA
    @Override
    public List<Marcador> obtenerPorIdPartido(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return MarcadorEnsamblador.obtenerMarcadorEnsambladorDTO().ensamblarListaDTO(servicio.obtenerPorPartido(id));
    }

    @Override
    public List<Marcador> obtenerPorIdTorneo(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return MarcadorEnsamblador.obtenerMarcadorEnsambladorDTO().ensamblarListaDTO(servicio.obtenerPorTorneo(id));
    }
}