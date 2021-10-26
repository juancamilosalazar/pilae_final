package com.example.multimodule.servicio.fachada.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.implementacion.PosicionEnsamblador;
import com.example.multimodule.servicio.fachada.PosicionFachada;
import com.example.multimodule.servicio.negocio.PosicionServicio;
import main.com.example.multimodule.dominio.PosicionDominio;
import main.com.example.multimodule.dto.Posicion;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosicionFachadaImplementacion implements PosicionFachada {

    @Autowired
    private PosicionServicio servicio;


    @Override
    public Posicion obtenerPorId(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        PosicionDominio dominio = servicio.obtenerPorId(id);
        return PosicionEnsamblador.obtenerPosicionEnsambladorDTO().ensamblarDTO(dominio);
    }

    @Override
    public List<Posicion> consultarPorTorneo(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return PosicionEnsamblador.obtenerPosicionEnsambladorDTO().ensamblarListaDTO(servicio.obtenerPorTorneo(id));
    }


}