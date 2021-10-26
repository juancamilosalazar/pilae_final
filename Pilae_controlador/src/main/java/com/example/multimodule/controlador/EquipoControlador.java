package com.example.multimodule.controlador;

import com.example.multimodule.servicio.fachada.EquipoFachada;
import com.example.multimodule.utilitario.Validadores;
import main.com.example.multimodule.dto.Equipo;
import main.com.example.multimodule.transversal.excepciones.PILAEExcepcion;
import main.com.example.multimodule.transversal.mensajes.CodigosMensajes;
import main.com.example.multimodule.transversal.respuesta.EstadoRespuestaEnum;
import main.com.example.multimodule.transversal.respuesta.Respuesta;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static main.com.example.multimodule.transversal.mensajes.MensajesHelper.obtenerMensaje;

@RestController
@RequestMapping(path = "equipo")
public class EquipoControlador {

	@Autowired
	private EquipoFachada equipoFachada;

	@Autowired
	Validadores<Equipo> validadores = new Validadores<>();


	@PostMapping(params = {"torneoId"})
	public ResponseEntity<Respuesta<Equipo>> crear(@RequestParam(value = "torneoId") final Long torneoId, @RequestBody final Equipo equipo) {

		ResponseEntity<Respuesta<Equipo>> respuestaSolicitud;
		Respuesta<Equipo> respuesta = new Respuesta<>();

		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(equipo)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_ERROR_DATOS_VACIOS_CREAR_EQUIPO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				validadores.validarDatosNombre(equipo.getNombre(),respuesta,datosValidos);
			}

			if (datosValidos) {
				equipoFachada.crear(equipo,torneoId);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_INFORMACION_CREAR_EQUIPO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.OK);
			} else {
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
			}
		} catch (PILAEExcepcion excepcion) {
			respuesta.agregarMensaje(excepcion.getTextoUsuario());
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		} catch (Exception excepcion) {
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_ERROR_INESPERADO_CREAR_EQUIPO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@PutMapping(params = {"id"})
	public ResponseEntity<Respuesta<Equipo>> actualizar(@RequestParam(value = "id") final Long id, @RequestBody final Equipo equipoNuevo) {

		ResponseEntity<Respuesta<Equipo>> respuestaSolicitud;
		Respuesta<Equipo> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(equipoNuevo)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_ERROR_DATOS_VACIOS_ACTUALIZAR_EQUIPO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				equipoNuevo.setCodigo(id);
				validadores.validarDatosNombre(equipoNuevo.getNombre(),respuesta,datosValidos);

			}

			if (datosValidos) {
				equipoFachada.actualizar(equipoNuevo);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_INFORMACION_ACTUALIZAR_EQUIPO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.OK);
			} else {
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
			}
		} catch (PILAEExcepcion excepcion) {
			respuesta.agregarMensaje(excepcion.getTextoUsuario());
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		} catch (Exception excepcion) {
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_ERROR_INESPERADO_ACTUALIZAR_EQUIPO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@DeleteMapping(params = {"id"})
	public ResponseEntity<Respuesta<Equipo>> eliminar(@RequestParam("id") final Long id) {

		ResponseEntity<Respuesta<Equipo>> respuestaSolicitud;
		Respuesta<Equipo> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {
			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				equipoFachada.borrar(id);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_INFORMACION_ELIMINAR_EQUIPO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.OK);
			} else {
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
			}
		} catch (PILAEExcepcion excepcion) {
			respuesta.agregarMensaje(excepcion.getTextoUsuario());
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		} catch (Exception excepcion) {
			String mensajeUsuario =obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_ERROR_INESPERADO_ELIMINAR_EQUIPO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@GetMapping(params = {"id"})
	public ResponseEntity<Respuesta<Equipo>> consultarPorCodigo(@RequestParam("id") final Long id) {

		ResponseEntity<Respuesta<Equipo>> respuestaSolicitud;
		Respuesta<Equipo> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Equipo> listaEquipos = new ArrayList<>();
				listaEquipos.add(equipoFachada.obtenerPorId(id));
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_INFORMACION_OBTENER_EQUIPO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaEquipos);
				respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.OK);
			} else {
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
			}
		} catch (PILAEExcepcion excepcion) {
			respuesta.agregarMensaje(excepcion.getTextoUsuario());
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		} catch (Exception excepcion) {
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_ERROR_INESPERADO_OBTENER_EQUIPO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@GetMapping(params = {"idTorneo"})
	public ResponseEntity<Respuesta<Equipo>> consultarPorTorneo(@RequestParam("idTorneo") final Long id) {

		ResponseEntity<Respuesta<Equipo>> respuestaSolicitud;
		Respuesta<Equipo> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Equipo> listaEquipos = equipoFachada.obtenerPorTorneo(id);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_INFORMACION_OBTENER_EQUIPO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaEquipos);
				respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.OK);
			} else {
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
			}
		} catch (PILAEExcepcion excepcion) {
			respuesta.agregarMensaje(excepcion.getTextoUsuario());
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		} catch (Exception excepcion) {
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_ERROR_INESPERADO_OBTENER_EQUIPO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}


	@GetMapping
	public ResponseEntity<Respuesta<Equipo>> consultar() {
		ResponseEntity<Respuesta<Equipo>> respuestaSolicitud;
		Respuesta<Equipo> respuesta = new Respuesta<>();

		try {
			List<Equipo> listaEquipos = equipoFachada.obtenerTodos();
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_INFORMACION_OBTENER_EQUIPO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.EXITO);
			respuesta.setResultado(listaEquipos);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (PILAEExcepcion excepcion) {
			respuesta.agregarMensaje(excepcion.getTextoUsuario());
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		} catch (Exception excepcion) {
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosEquipoControlador.USUARIO_ERROR_INESPERADO_OBTENER_EQUIPO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

}
