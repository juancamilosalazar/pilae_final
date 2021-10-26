package com.example.multimodule.controlador;

import com.example.multimodule.servicio.fachada.JugadorFachada;
import com.example.multimodule.utilitario.Validadores;
import main.com.example.multimodule.dto.Jugador;
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
@RequestMapping(path = "jugador")
public class JugadorControlador {

	@Autowired
	private JugadorFachada jugadorFachada;

	@Autowired
	Validadores<Jugador> validadores = new Validadores<>();


	@PostMapping(params = {"equipoId"})
	public ResponseEntity<Respuesta<Jugador>> crear(@RequestParam(value = "equipoId") final Long equipoId, @RequestBody final Jugador jugador) {

		ResponseEntity<Respuesta<Jugador>> respuestaSolicitud;
		Respuesta<Jugador> respuesta = new Respuesta<>();

		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(jugador)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_ERROR_DATOS_VACIOS_CREAR_JUGADOR).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				validadores.validarDatosNombre(jugador.getNombre(),respuesta,datosValidos);

			}

			if (datosValidos) {
				jugadorFachada.crear(jugador,equipoId);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_INFORMACION_CREAR_JUGADOR).getContenido();
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_ERROR_INESPERADO_CREAR_JUGADOR).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@PutMapping(params = {"id"})
	public ResponseEntity<Respuesta<Jugador>> actualizar(@RequestParam(value = "id") final Long id, @RequestBody final Jugador jugadorNuevo) {

		ResponseEntity<Respuesta<Jugador>> respuestaSolicitud;
		Respuesta<Jugador> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(jugadorNuevo)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_ERROR_DATOS_VACIOS_ACTUALIZAR_JUGADOR).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				jugadorNuevo.setCodigo(id);
				validadores.validarDatosNombre(jugadorNuevo.getNombre(),respuesta,datosValidos);
				validadores.validarDatosCodigo(jugadorNuevo.getCodigo().toString(),respuesta,datosValidos);
			}

			if (datosValidos) {
				jugadorFachada.actualizar(jugadorNuevo);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_INFORMACION_ACTUALIZAR_JUGADOR).getContenido();
				//"La información del Jugador se ha modificado exitosamente";
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_ERROR_INESPERADO_ACTUALIZAR_JUGADOR).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@DeleteMapping(params = {"id"})
	public ResponseEntity<Respuesta<Jugador>> eliminar(@RequestParam("id") final Long id) {

		ResponseEntity<Respuesta<Jugador>> respuestaSolicitud;
		Respuesta<Jugador> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {
			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				jugadorFachada.borrar(id);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_INFORMACION_ELIMINAR_JUGADOR).getContenido();
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
			String mensajeUsuario =obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_ERROR_INESPERADO_ELIMINAR_JUGADOR).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@GetMapping(params = {"id"})
	public ResponseEntity<Respuesta<Jugador>> consultarPorCodigo(@RequestParam("id") final Long id) {

		ResponseEntity<Respuesta<Jugador>> respuestaSolicitud;
		Respuesta<Jugador> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Jugador> listaJugadors = new ArrayList<>();
				listaJugadors.add(jugadorFachada.obtenerPorId(id));
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_INFORMACION_OBTENER_JUGADOR).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaJugadors);
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_ERROR_INESPERADO_OBTENER_JUGADOR).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@GetMapping(params = {"idEquipo"})
	public ResponseEntity<Respuesta<Jugador>> consultarPorEquipo(@RequestParam("idEquipo") final Long id) {

		ResponseEntity<Respuesta<Jugador>> respuestaSolicitud;
		Respuesta<Jugador> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Jugador> listaJugadors = jugadorFachada.obtenerPorEquipo(id);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_INFORMACION_OBTENER_JUGADOR).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaJugadors);
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_ERROR_INESPERADO_OBTENER_JUGADOR).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}
	@GetMapping
	public ResponseEntity<Respuesta<Jugador>> consultar() {
		ResponseEntity<Respuesta<Jugador>> respuestaSolicitud;
		Respuesta<Jugador> respuesta = new Respuesta<>();

		try {
			List<Jugador> listaJugadors = jugadorFachada.obtenerTodos();
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_INFORMACION_OBTENER_JUGADOR).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.EXITO);
			respuesta.setResultado(listaJugadors);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (PILAEExcepcion excepcion) {
			respuesta.agregarMensaje(excepcion.getTextoUsuario());
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		} catch (Exception excepcion) {
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosJugadorControlador.USUARIO_ERROR_INESPERADO_OBTENER_JUGADOR).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

}
