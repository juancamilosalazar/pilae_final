package com.example.multimodule.controlador;

import com.example.multimodule.servicio.fachada.PosicionFachada;
import com.example.multimodule.utilitario.Validadores;
import main.com.example.multimodule.dto.Posicion;
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
public class PosicionControlador {

	@Autowired
	private PosicionFachada posicionFachada;

	@Autowired
	Validadores<Posicion> validadores = new Validadores<>();


	@PostMapping(params = {"torneoId"})
	public ResponseEntity<Respuesta<Posicion>> crear(@RequestParam(value = "torneoId") final Long torneoId, @RequestBody final Posicion Posicion) {

		ResponseEntity<Respuesta<Posicion>> respuestaSolicitud;
		Respuesta<Posicion> respuesta = new Respuesta<>();

		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(Posicion)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPosicionControlador.USUARIO_ERROR_DATOS_VACIOS_CREAR_POSICION).getContenido();
				//"Los datos del Posicion no pueden estar vacíos!";
				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				validadores.validarDatosCodigo(Posicion.getCodigo().toString(),respuesta,datosValidos);
			}

			if (datosValidos) {
				posicionFachada.crear(Posicion,torneoId);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPosicionControlador.USUARIO_INFORMACION_CREAR_POSICION).getContenido();
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPosicionControlador.USUARIO_ERROR_INESPERADO_CREAR_POSICION).getContenido();
			//"error inesperado al crear el Posicion";
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}



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
			//"Se ha presentado un problema inesperado consultando la información del Posicion";
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

}
