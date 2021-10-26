package com.example.multimodule.controlador;

import com.example.multimodule.servicio.fachada.DeporteFachada;
import com.example.multimodule.utilitario.Validadores;
import main.com.example.multimodule.dto.Deporte;
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
@RequestMapping(path = "deporte")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class DeporteControlador {

	@Autowired
	private DeporteFachada deporteFachada;

	@Autowired
	Validadores<Deporte> validadores = new Validadores<>();


	@PostMapping
	public ResponseEntity<Respuesta<Deporte>> crear(@RequestBody final Deporte deporte) {

		ResponseEntity<Respuesta<Deporte>> respuestaSolicitud;
		Respuesta<Deporte> respuesta = new Respuesta<>();

		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(deporte)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_ERROR_DATOS_VACIOS_CREAR_DEPORTE).getContenido();
				//"Los datos del deporte no pueden estar vacíos!";
				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				validadores.validarDatosNombre(deporte.getNombre(),respuesta,datosValidos);

			}

			if (datosValidos) {
				deporteFachada.crear(deporte);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_INFORMACION_CREAR_DEPORTE).getContenido();
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_ERROR_INESPERADO_CREAR_DEPORTE).getContenido();
			//"error inesperado al crear el deporte";
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@PutMapping(params = {"id"})
	public ResponseEntity<Respuesta<Deporte>> actualizar(@RequestParam(value = "id") final Long id, @RequestBody final Deporte deporteNuevo) {

		ResponseEntity<Respuesta<Deporte>> respuestaSolicitud;
		Respuesta<Deporte> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(deporteNuevo)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_ERROR_DATOS_VACIOS_ACTUALIZAR_DEPORTE).getContenido();
				//"Los datos del Deporte no pueden estar vacíos!";
				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				deporteNuevo.setCodigo(id);
				validadores.validarDatosNombre(deporteNuevo.getNombre(),respuesta,datosValidos);
			}

			if (datosValidos) {
				deporteFachada.actualizar(deporteNuevo);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_INFORMACION_ACTUALIZAR_DEPORTE).getContenido();
				//"La información del Deporte se ha modificado exitosamente";
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_ERROR_INESPERADO_ACTUALIZAR_DEPORTE).getContenido();
			//"Se ha presentado un problema inesperado modificando la información del Deporte";
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@DeleteMapping(params = {"id"})
	public ResponseEntity<Respuesta<Deporte>> eliminar(@RequestParam("id") final Long id) {

		ResponseEntity<Respuesta<Deporte>> respuestaSolicitud;
		Respuesta<Deporte> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {
			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				deporteFachada.borrar(id);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_INFORMACION_ELIMINAR_DEPORTE).getContenido();
				//"La información del Deporte se ha dado de baja exitosamente";
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
			String mensajeUsuario =obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_ERROR_INESPERADO_ELIMINAR_DEPORTE).getContenido();
			//"Se ha presentado un problema inesperado dadndo de baja la información del Deporte";
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@GetMapping(params = {"id"})
	public ResponseEntity<Respuesta<Deporte>> consultarPorCodigo(@RequestParam("id") final Long id) {

		ResponseEntity<Respuesta<Deporte>> respuestaSolicitud;
		Respuesta<Deporte> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Deporte> listaDeportes = new ArrayList<>();
				listaDeportes.add(deporteFachada.obtenerPorId(id));
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_INFORMACION_OBTENER_DEPORTE).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaDeportes);
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_ERROR_INESPERADO_OBTENER_DEPORTE).getContenido();
			//"Se ha presentado un problema inesperado consultando la información del Deporte";
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@GetMapping
	public ResponseEntity<Respuesta<Deporte>> consultar() {
		ResponseEntity<Respuesta<Deporte>> respuestaSolicitud;
		Respuesta<Deporte> respuesta = new Respuesta<>();

		try {
			List<Deporte> listaDeportes = deporteFachada.obtenerTodos();
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_INFORMACION_OBTENER_DEPORTE).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.EXITO);
			respuesta.setResultado(listaDeportes);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (PILAEExcepcion excepcion) {
			respuesta.agregarMensaje(excepcion.getTextoUsuario());
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		} catch (Exception excepcion) {
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosDeporteControlador.USUARIO_ERROR_INESPERADO_OBTENER_DEPORTE).getContenido();
			//"Se ha presentado un problema inesperado consultando la información de los Deportes";
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

}
