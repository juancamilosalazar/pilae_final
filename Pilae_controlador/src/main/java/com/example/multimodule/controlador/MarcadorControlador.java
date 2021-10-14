package com.example.multimodule.controlador;

import com.example.multimodule.servicio.fachada.MarcadorFachada;
import com.example.multimodule.utilitario.Validadores;
import main.com.example.multimodule.dto.Marcador;
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

import java.util.List;

import static main.com.example.multimodule.transversal.mensajes.MensajesHelper.obtenerMensaje;

@RestController
@RequestMapping(path = "marcador")
public class MarcadorControlador {

	@Autowired
	private MarcadorFachada marcadorFachada;

	@Autowired
	Validadores<Marcador> validadores = new Validadores<>();




	@GetMapping(params = {"idPartido"})
	public ResponseEntity<Respuesta<Marcador>> consultarPorPartido(@RequestParam("idPartido") final Long id) {

		ResponseEntity<Respuesta<Marcador>> respuestaSolicitud;
		Respuesta<Marcador> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Marcador> listaMarcadors = marcadorFachada.obtenerPorIdPartido(id);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosMarcadorControlador.USUARIO_INFORMACION_OBTENER_MARCADOR).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaMarcadors);
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosMarcadorControlador.USUARIO_ERROR_INESPERADO_OBTENER_MARCADOR).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@GetMapping(params = {"idTorneo"})
	public ResponseEntity<Respuesta<Marcador>> consultarPorTorneo(@RequestParam("idTorneo") final Long id) {

		ResponseEntity<Respuesta<Marcador>> respuestaSolicitud;
		Respuesta<Marcador> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Marcador> listaMarcadores = marcadorFachada.obtenerPorIdTorneo(id);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosMarcadorControlador.USUARIO_INFORMACION_OBTENER_MARCADOR).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaMarcadores);
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosMarcadorControlador.USUARIO_ERROR_INESPERADO_OBTENER_MARCADOR).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

}
