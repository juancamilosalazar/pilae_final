package com.example.multimodule.controlador;

import com.example.multimodule.servicio.fachada.PosicionFachada;
import com.example.multimodule.utilitario.Validadores;
import main.com.example.multimodule.dto.Posicion;
import main.com.example.multimodule.transversal.excepciones.PILAEExcepcion;
import main.com.example.multimodule.transversal.mensajes.CodigosMensajes;
import main.com.example.multimodule.transversal.respuesta.EstadoRespuestaEnum;
import main.com.example.multimodule.transversal.respuesta.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static main.com.example.multimodule.transversal.mensajes.MensajesHelper.obtenerMensaje;

@RestController
@RequestMapping(path = "posicion")
public class PosicionControlador {

	@Autowired
	private PosicionFachada posicionFachada;

	@Autowired
	Validadores<Posicion> validadores = new Validadores<>();



	@GetMapping(params = {"id"})
	public ResponseEntity<Respuesta<Posicion>> consultarPorCodigo(@RequestParam("id") final Long id) {

		ResponseEntity<Respuesta<Posicion>> respuestaSolicitud;
		Respuesta<Posicion> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Posicion> listaPosicions = new ArrayList<>();
				listaPosicions.add(posicionFachada.obtenerPorId(id));
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPosicionControlador.USUARIO_INFORMACION_OBTENER_POSICION).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaPosicions);
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPosicionControlador.USUARIO_ERROR_INESPERADO_OBTENER_POSICION).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}
	@GetMapping(params = {"idTorneo"})
	public ResponseEntity<Respuesta<Posicion>> consultarTorneo(@RequestParam("idTorneo") final Long id) {

		ResponseEntity<Respuesta<Posicion>> respuestaSolicitud;
		Respuesta<Posicion> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Posicion> listaPosicions = posicionFachada.consultarPorTorneo(id);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPosicionControlador.USUARIO_INFORMACION_OBTENER_POSICION).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaPosicions);
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPosicionControlador.USUARIO_ERROR_INESPERADO_OBTENER_POSICION).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

}
