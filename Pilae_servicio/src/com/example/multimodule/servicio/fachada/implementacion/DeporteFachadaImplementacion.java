package com.example.multimodule.servicio.fachada.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.implementacion.DeporteEnsamblador;
import com.example.multimodule.servicio.fachada.DeporteFachada;
import com.example.multimodule.servicio.negocio.DeporteServicio;
import main.com.example.multimodule.dominio.DeporteDominio;
import main.com.example.multimodule.dto.Deporte;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeporteFachadaImplementacion implements DeporteFachada {

    @Autowired
    private DeporteServicio servicio;


    @Override
    public List<Deporte> obtenerTodos() {
        return DeporteEnsamblador.obtenerDeporteEnsambladorDTO().ensamblarListaDTO(servicio.obtenerTodos());
    }

    @Override
    public Deporte obtenerPorId(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        DeporteDominio dominio = servicio.obtenerPorId(id);
        return DeporteEnsamblador.obtenerDeporteEnsambladorDTO().ensamblarDTO(dominio);
    }

    @Override
    public void crear(Deporte dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "Deporte no puede ser nulo";
            String mensajeTecnico = "eqyuipo nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        DeporteDominio dominio = DeporteEnsamblador.obtenerDeporteEnsambladorDTO().ensamblarDominio(dto);
        servicio.crear(dominio);
    }

    @Override
    public void actualizar(Deporte dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "Deporte no puede ser nulo";
            String mensajeTecnico = "eqyuipo nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        DeporteDominio dominio = DeporteEnsamblador.obtenerDeporteEnsambladorDTO().ensamblarDominio(dto);
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
}