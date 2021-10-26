package com.example.multimodule.servicio.fachada.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.implementacion.TorneoEnsamblador;
import com.example.multimodule.servicio.fachada.TorneoFachada;
import com.example.multimodule.servicio.negocio.TorneoServicio;
import main.com.example.multimodule.dominio.TorneoDominio;
import main.com.example.multimodule.dto.Torneo;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TorneoFachadaImplementacion implements TorneoFachada {

    @Autowired
    private TorneoServicio servicio;


    @Override
    public List<Torneo> obtenerTodos() {
        return TorneoEnsamblador.obtenerTorneoEnsambladorDTO().ensamblarListaDTO(servicio.obtenerTodos());
    }

    @Override
    public Torneo obtenerPorId(Long id) {
        if (UtilTexto.estaVacia(id.toString())) {
            String mensajeUsuario = "se requiere id para la consulta";
            String mensajeTecnico = "se requiere id para la consulta";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        TorneoDominio dominio = servicio.obtenerPorId(id);
        return TorneoEnsamblador.obtenerTorneoEnsambladorDTO().ensamblarDTO(dominio);
    }

    @Override
    public void crear(Torneo dto, Long id) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "Torneo no puede ser nulo";
            String mensajeTecnico = "eqyuipo nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        TorneoDominio dominio = TorneoEnsamblador.obtenerTorneoEnsambladorDTO().ensamblarDominio(dto);
        servicio.crear(dominio,id);
    }

    @Override
    public void actualizar(Torneo dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "Torneo no puede ser nulo";
            String mensajeTecnico = "Torneo nulo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        TorneoDominio dominio = TorneoEnsamblador.obtenerTorneoEnsambladorDTO().ensamblarDominio(dto);
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
