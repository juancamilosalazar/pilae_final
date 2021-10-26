package com.example.multimodule.controlador;

import com.example.multimodule.servicio.fachada.PartidoFachada;
import com.example.multimodule.utilitario.Validadores;
import main.com.example.multimodule.dto.Marcador;
import main.com.example.multimodule.dto.Partido;
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

import java.util.List;

import static main.com.example.multimodule.transversal.mensajes.MensajesHelper.obtenerMensaje;

@RestController
@RequestMapping(path = "partido")
public class PartidoControlador {

	@Autowired
	private PartidoFachada partidoFachada;

	@Autowired
	Validadores<Partido> validadores = new Validadores<>();


	@PostMapping(params = {"idTorneo", "idLocal", "idVisitante"})
	public ResponseEntity<Respuesta<Partido>> crear(@RequestParam(value = "idTorneo") final Long idTorneo, @RequestParam(value = "idLocal") final Long idLocal, @RequestParam(value = "idVisitante") final Long idVisitante, @RequestBody final Partido partido) {

		ResponseEntity<Respuesta<Partido>> respuestaSolicitud;
		Respuesta<Partido> respuesta = new Respuesta<>();

		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(partido)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_ERROR_DATOS_VACIOS_CREAR_PARTIDO).getContenido();
				//"Los datos del partido no pueden estar vacíos!";
				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				//validadores.validarDatosNombre(partido.get(),respuesta,datosValidos);

			}

			if (datosValidos) {
				partidoFachada.crear(partido,idLocal,idVisitante,idTorneo);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_INFORMACION_CREAR_PARTIDO).getContenido();
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_ERROR_INESPERADO_CREAR_PARTIDO).getContenido();
			//"error inesperado al crear el partido";
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@PutMapping(params = {"id"})
	public ResponseEntity<Respuesta<Partido>> actualizar(@RequestParam(value = "id") final Long id, @RequestBody final Partido partidoNuevo) {

		ResponseEntity<Respuesta<Partido>> respuestaSolicitud;
		Respuesta<Partido> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(partidoNuevo)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_ERROR_DATOS_VACIOS_ACTUALIZAR_PARTIDO).getContenido();
				//"Los datos del Partido no pueden estar vacíos!";
				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				partidoNuevo.setCodigo(id);
				//validadores.validarDatosNombre(partidoNuevo.getNombre(),respuesta,datosValidos);
				validadores.validarDatosCodigo(partidoNuevo.getCodigo().toString(),respuesta,datosValidos);
			}

			if (datosValidos) {
				partidoFachada.actualizar(partidoNuevo);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_INFORMACION_ACTUALIZAR_PARTIDO).getContenido();
				//"La información del Partido se ha modificado exitosamente";
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_ERROR_INESPERADO_ACTUALIZAR_PARTIDO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@DeleteMapping(params = {"id"})
	public ResponseEntity<Respuesta<Partido>> eliminar(@RequestParam("id") final Long id) {

		ResponseEntity<Respuesta<Partido>> respuestaSolicitud;
		Respuesta<Partido> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {
			validadores.validarDatosCodigo(id.toString(),respuesta,datosValidos);

			if (datosValidos) {
				partidoFachada.borrar(id);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_INFORMACION_ELIMINAR_PARTIDO).getContenido();
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
			String mensajeUsuario =obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_ERROR_INESPERADO_ELIMINAR_PARTIDO).getContenido();
			//"Se ha presentado un problema inesperado dadndo de baja la información del Partido";
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}

	@GetMapping(params = {"idTorneo"})
	public ResponseEntity<Respuesta<Partido>> consultarPorTorneo(@RequestParam("idTorneo") final Long idTorneo) {

		ResponseEntity<Respuesta<Partido>> respuestaSolicitud;
		Respuesta<Partido> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(idTorneo.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Partido> listaPartidos = partidoFachada.obtenerPorTorneo(idTorneo);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_INFORMACION_OBTENER_PARTIDO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaPartidos);
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_ERROR_INESPERADO_OBTENER_PARTIDO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}


	@PostMapping(params = {"idPartido"})
	public ResponseEntity<Respuesta<Partido>> jugarPartido(@RequestParam(value = "idPartido") final Long idPartido,  @RequestBody final Marcador marcador) {

		ResponseEntity<Respuesta<Partido>> respuestaSolicitud;
		Respuesta<Partido> respuesta = new Respuesta<>();

		boolean datosValidos = true;

		try {

			if (UtilObjeto.objetoEsNulo(marcador)) {
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_ERROR_DATOS_VACIOS_JUGAR_PARTIDO).getContenido();

				respuesta.agregarMensaje(mensajeUsuario);
				datosValidos = false;
			} else {
				validadores.validarDatosCodigo(idPartido.toString(),respuesta,datosValidos);
			}

			if (datosValidos) {
				partidoFachada.jugarPartido(marcador,idPartido);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_INFORMACION_JUGAR_PARTIDO).getContenido();
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_ERROR_INESPERADO_CREAR_PARTIDO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}

		return respuestaSolicitud;
	}


	@GetMapping(params = {"idTorneoIdaVuelta"})
	public ResponseEntity<Respuesta<Partido>> crearFixtureIdaYvuelta(@RequestParam(value = "idTorneoIdaVuelta") final Long idTorneo) {
		ResponseEntity<Respuesta<Partido>> respuestaSolicitud;
		Respuesta<Partido> respuesta = new Respuesta<>();
		boolean datosValidos = true;
		try {
			validadores.validarDatosCodigo(idTorneo.toString(),respuesta,datosValidos);
			if (datosValidos) {
				List<Partido> listaPartidos = partidoFachada.crearFixtureIdaYvuelta(idTorneo);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_INFORMACION_CREAR_FIXTURE_PARTIDO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaPartidos);
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_ERROR_INESPERADO_CREAR_FIXTURE_PARTIDO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}
		return respuestaSolicitud;
	}

	@GetMapping(params = {"idTorneoSoloIda"})
	public ResponseEntity<Respuesta<Partido>> crearFixtureSoloIda(@RequestParam(value = "idTorneoSoloIda") final Long idTorneo) {
		ResponseEntity<Respuesta<Partido>> respuestaSolicitud;
		Respuesta<Partido> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		try {

			validadores.validarDatosCodigo(idTorneo.toString(),respuesta,datosValidos);

			if (datosValidos) {
				List<Partido> listaPartidos = partidoFachada.crearFixtureSoloIda(idTorneo);
				String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_INFORMACION_CREAR_FIXTURE_PARTIDO).getContenido();
				respuesta.agregarMensaje(mensajeUsuario);
				respuesta.setEstado(EstadoRespuestaEnum.EXITO);
				respuesta.setResultado(listaPartidos);
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
			String mensajeUsuario = obtenerMensaje(CodigosMensajes.CodigosPartidoControlador.USUARIO_ERROR_INESPERADO_CREAR_FIXTURE_PARTIDO).getContenido();
			respuesta.agregarMensaje(mensajeUsuario);
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			respuestaSolicitud = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}
		return respuestaSolicitud;
	}


}
