package com.example.multimodule.servicio.fachada.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.implementacion.EquipoEnsamblador;
import com.example.multimodule.servicio.ensamblador.dto.implementacion.PosicionEnsamblador;
import com.example.multimodule.servicio.fachada.EquipoFachada;
import com.example.multimodule.servicio.fachada.PosicionFachada;
import com.example.multimodule.servicio.negocio.EquipoServicio;
import com.example.multimodule.servicio.negocio.PosicionServicio;
import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.dominio.PosicionDominio;
import main.com.example.multimodule.dto.Equipo;
import main.com.example.multimodule.dto.Posicion;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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





}