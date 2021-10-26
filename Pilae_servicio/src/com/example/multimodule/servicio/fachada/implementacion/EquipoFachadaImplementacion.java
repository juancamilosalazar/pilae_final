package com.example.multimodule.servicio.fachada.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.implementacion.EquipoEnsamblador;
import com.example.multimodule.servicio.fachada.EquipoFachada;
import com.example.multimodule.servicio.negocio.EquipoServicio;
import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.dto.Equipo;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EquipoFachadaImplementacion implements EquipoFachada {

	@Autowired
	private EquipoServicio equipoServicio;


	@Override
	public List<Equipo> obtenerTodos() {
		return EquipoEnsamblador.obtenerEquipoEnsambladorDTO().ensamblarListaDTO(equipoServicio.obtenerTodos());
	}

	@Override
	public List<Equipo> obtenerPorTorneo(Long torneoId) {
		if (UtilTexto.estaVacia(torneoId.toString())) {
			String mensajeUsuario = "se requiere id para la consulta";
			String mensajeTecnico = "se requiere id para la consulta";
			throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
		}
		return EquipoEnsamblador.obtenerEquipoEnsambladorDTO().ensamblarListaDTO(equipoServicio.obtenerPorTorneo(torneoId));
	}

	@Override
	public Equipo obtenerPorId(Long id) {
		if (UtilTexto.estaVacia(id.toString())) {
			String mensajeUsuario = "se requiere id para la consulta";
			String mensajeTecnico = "se requiere id para la consulta";
			throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
		}
		EquipoDominio equipoDominio = equipoServicio.obtenerPorId(id);
		return EquipoEnsamblador.obtenerEquipoEnsambladorDTO().ensamblarDTO(equipoDominio);
	}

	@Override
	public void crear(Equipo equipo, Long torneoId) {
		if (UtilObjeto.objetoEsNulo(equipo)) {
			String mensajeUsuario = "equipo no puede ser nulo";
			String mensajeTecnico = "eqyuipo nulo";
			throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
		}

		EquipoDominio equipoDominio = EquipoEnsamblador.obtenerEquipoEnsambladorDTO().ensamblarDominio(equipo);
		equipoServicio.crear(equipoDominio,torneoId);
	}

	@Override
	public void actualizar(Equipo equipoNuevo) {
		if (UtilObjeto.objetoEsNulo(equipoNuevo)) {
			String mensajeUsuario = "equipo no puede ser nulo";
			String mensajeTecnico = "eqyuipo nulo";
			throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
		}

		EquipoDominio equipoDominio = EquipoEnsamblador.obtenerEquipoEnsambladorDTO().ensamblarDominio(equipoNuevo);
		equipoServicio.actualizar(equipoDominio);
	}

	@Override
	public void borrar(Long id) {
		if (UtilTexto.estaVacia(id.toString())) {
			String mensajeUsuario = "se requiere id para la consulta";
			String mensajeTecnico = "se requiere id para la consulta";
			throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
		}
		equipoServicio.borrar(id);
	}
}
