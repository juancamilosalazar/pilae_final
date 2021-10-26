package com.example.multimodule.controlador;

import com.example.multimodule.servicio.fachada.TorneoFachada;
import com.example.multimodule.utilitario.Validadores;
import main.com.example.multimodule.dto.Torneo;
import main.com.example.multimodule.transversal.excepciones.PILAEExcepcion;
import main.com.example.multimodule.transversal.mensajes.CodigosMensajes;
import main.com.example.multimodule.transversal.respuesta.EstadoRespuestaEnum;
import main.com.example.multimodule.transversal.respuesta.Respuesta;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static main.com.example.multimodule.transversal.mensajes.MensajesHelper.obtenerMensaje;

@RestController
@RequestMapping(path = "torneo")
public class TorneoControlador {

	@Autowired
	private TorneoFachada torneoFachada;

	@Autowired
	Validadores<Torneo> validadores = new Validadores<>();


	@PostMapping(params = {"deporteId"})
	public ResponseEntity<Respuesta<Torneo>> crear(@RequestParam(value = "deporteId") final Long deporteId, @RequestBody final Torneo Torneo) {

		ResponseEntity<Respuesta<Torneo>> respuestaSolicitud;
		Respuesta<Torneo> respuesta = new Respuesta<>();

		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(Torneo)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_ERROR_DATOS_VACIOS_CREAR_TORNEO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				validadores.validarDatosNombre(Torneo.getNombre(),respuesta,datosValidos);
			}

			if (datosValidos) {
				torneoFachada.crear(Torneo,deporteId);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_INFORMACION_CREAR_TORNEO).getContenido();
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_ERROR_INESPERADO_CREAR_TORNEO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@PutMapping(params = {"id"})
	public ResponseEntity<Respuesta<Torneo>> actualizar(@RequestParam(value = "id") final Long id, @RequestBody final Torneo torneoNuevo) {

		ResponseEntity<Respuesta<Torneo>> respuestaSolicitud;
		Respuesta<Torneo> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(torneoNuevo)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_ERROR_DATOS_VACIOS_ACTUALIZAR_TORNEO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				torneoNuevo.setCodigo(id);
				validadores.validarDatosNombre(torneoNuevo.getNombre(),respuesta,datosValidos);
				validadores.validarDatosCodigo(torneoNuevo.getCodigo().toString(),respuesta,datosValidos);
			}

			if (datosValidos) {
				torneoFachada.actualizar(torneoNuevo);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_INFORMACION_ACTUALIZAR_TORNEO).getContenido();
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_ERROR_INESPERADO_ACTUALIZAR_TORNEO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@DeleteMapping(params = {"id"})
	public ResponseEntity<Respuesta<Torneo>> eliminar(@RequestParam("id") final Long id) {

		ResponseEntity<Respuesta<Torneo>> respuestaSolicitud;
		Respuesta<Torneo> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {
			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				torneoFachada.borrar(id);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_INFORMACION_ELIMINAR_TORNEO).getContenido();
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
			String mensajeUsuario =obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_ERROR_INESPERADO_ELIMINAR_TORNEO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@GetMapping(params = {"id"})
	public ResponseEntity<Respuesta<Torneo>> consultarPorCodigo(@RequestParam("id") final Long id) {

		ResponseEntity<Respuesta<Torneo>> respuestaSolicitud;
		Respuesta<Torneo> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Torneo> listaTorneos = new ArrayList<>();
				listaTorneos.add(torneoFachada.obtenerPorId(id));
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_INFORMACION_OBTENER_TORNEO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaTorneos);
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_ERROR_INESPERADO_OBTENER_TORNEO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@GetMapping
	public ResponseEntity<Respuesta<Torneo>> consultar() {
		ResponseEntity<Respuesta<Torneo>> respuestaSolicitud;
		Respuesta<Torneo> respuesta = new Respuesta<>();

		try {
			List<Torneo> listaTorneos = torneoFachada.obtenerTodos();
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_INFORMACION_OBTENER_TORNEO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.EXITO);
			respuesta.setResultado(listaTorneos);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (PILAEExcepcion excepcion) {
			respuesta.agregarMensaje(excepcion.getTextoUsuario());
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		} catch (Exception excepcion) {
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosTorneoControlador.USUARIO_ERROR_INESPERADO_OBTENER_TORNEO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

}
