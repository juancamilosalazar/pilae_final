package co.edu.uco.mensajes.api.controlador;

import co.edu.uco.mensajes.dto.AplicacionDTO;
import co.edu.uco.mensajes.dto.MensajeDTO;
import co.edu.uco.mensajes.negocio.MensajeNegocio;
import co.edu.uco.transversal.respuesta.rest.EstadoRespuestaEnum;
import co.edu.uco.transversal.respuesta.rest.Respuesta;
import co.edu.uco.transversal.utilitarios.UtilObjeto;
import co.edu.uco.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensaje")
public class MensajeControlador {

	@Autowired
	private MensajeNegocio mensajeNegocio;

	@GetMapping("/dummy")
	public MensajeDTO obtenerEjemplo() {
		return MensajeDTO.crear();
	}

	@GetMapping("/{codigoAplicacion}")
	public Respuesta<MensajeDTO> consultarMensajesAplicacion(@PathVariable String codigoAplicacion) {

		Respuesta<MensajeDTO> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		if (UtilTexto.estaVacia(codigoAplicacion)) {
			respuesta.agregarMensaje("El código de la aplicación es un dato obligatorio para llevar a cabo la operación de consulta...");
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			datosValidos = false;
		} else if (UtilTexto.aplicarTrim(codigoAplicacion).length() > 30) {
			respuesta.agregarMensaje("El código de la aplicación no puede superar los 30 caracteres...");
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			datosValidos = false;
		}

		if (datosValidos) {
			MensajeDTO mensaje = MensajeDTO.crear().setAplicacion(AplicacionDTO.crear().setCodigo(codigoAplicacion));
			List<MensajeDTO> mensajes = mensajeNegocio.consultar(mensaje);

			if (mensajes.isEmpty()) {
				respuesta.agregarMensaje("No existen mensajes para mostrar relacionados con el código de la aplicación " + codigoAplicacion);
			} else {
				respuesta.setResultado(mensajes);
			}

			respuesta.setEstado(EstadoRespuestaEnum.EXITO);
		}

		return respuesta;
	}

	@GetMapping("/{codigoAplicacion}/{codigoMensaje}")
	public Respuesta<MensajeDTO> consultarMensajeAplicacion(@PathVariable String codigoAplicacion, @PathVariable String codigoMensaje) {

		Respuesta<MensajeDTO> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		if (UtilTexto.estaVacia(codigoAplicacion)) {
			respuesta.agregarMensaje("El código de la aplicación es un dato obligatorio para llevar a cabo la operación de consulta...");
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			datosValidos = false;
		} else if (UtilTexto.aplicarTrim(codigoAplicacion).length() > 30) {
			respuesta.agregarMensaje("El código de la aplicación no puede superar los 30 caracteres...");
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			datosValidos = false;
		}

		if (UtilTexto.estaVacia(codigoMensaje)) {
			respuesta.agregarMensaje("El código del mensaje es un dato obligatorio para llevar a cabo la operación de consulta...");
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			datosValidos = false;
		} else if (UtilTexto.aplicarTrim(codigoMensaje).length() > 60) {
			respuesta.agregarMensaje("El código del mensaje no puede superar los 60 caracteres...");
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			datosValidos = false;
		}

		if (datosValidos) {
			MensajeDTO mensaje = MensajeDTO.crear().setAplicacion(AplicacionDTO.crear().setCodigo(codigoAplicacion)).setCodigo(codigoMensaje);
			List<MensajeDTO> mensajes = mensajeNegocio.consultar(mensaje);

			if (mensajes.isEmpty()) {
				respuesta.agregarMensaje("No existe un mensaje con el código " + codigoMensaje + " relacionado a la aplicación con el código " + codigoAplicacion);
			} else {
				respuesta.setResultado(mensajes);
			}

			respuesta.setEstado(EstadoRespuestaEnum.EXITO);
		}

		return respuesta;
	}

	@PostMapping
	public Respuesta<MensajeDTO> crear(@RequestBody MensajeDTO mensaje) {

		Respuesta<MensajeDTO> respuesta = new Respuesta<>();
		boolean datosValidos = true;

		if (UtilObjeto.objetoEsNulo(mensaje)) {
			respuesta.agregarMensaje("Los datos del mensajes son requeridos para su registro...");
			respuesta.setEstado(EstadoRespuestaEnum.ERROR);
			datosValidos = false;
		} else {

			if (UtilTexto.estaVacia(mensaje.getCodigo())) {
				respuesta.agregarMensaje("El código del mensaje es un dato obligatorio para llevar a cabo la operación de registro...");
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				datosValidos = false;
			} else if (UtilTexto.aplicarTrim(mensaje.getCodigo()).length() > 60) {
				respuesta.agregarMensaje("El código del mensaje no puede superar los 60 caracteres...");
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				datosValidos = false;
			}

			if (UtilTexto.estaVacia(mensaje.getTitulo())) {
				respuesta.agregarMensaje("El título del mensaje es un dato obligatorio para llevar a cabo la operación de registro...");
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				datosValidos = false;
			} else if (UtilTexto.aplicarTrim(mensaje.getTitulo()).length() > 2000) {
				respuesta.agregarMensaje("El título del mensaje no puede superar los 2000 caracteres...");
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				datosValidos = false;
			}

			if (UtilTexto.estaVacia(mensaje.getContenido())) {
				respuesta.agregarMensaje("El contenido del mensaje es un dato obligatorio para llevar a cabo la operación de registro...");
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				datosValidos = false;
			} else if (UtilTexto.aplicarTrim(mensaje.getContenido()).length() > 2000) {
				respuesta.agregarMensaje("El contenido del mensaje no puede superar los 2000 caracteres...");
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				datosValidos = false;
			}

			if (UtilObjeto.objetoEsNulo(mensaje.getAplicacion())) {
				respuesta.agregarMensaje("Los datos de la aplicación a la que pertenece el mensaje son requeridos para su registro...");
				respuesta.setEstado(EstadoRespuestaEnum.ERROR);
				datosValidos = false;
			} else {
				
				if (UtilTexto.estaVacia(mensaje.getAplicacion().getCodigo())) {
					respuesta.agregarMensaje("El código de la aplicación a la que pertenece el mensaje es un dato obligatorio para llevar a cabo la operación de registro...");
					respuesta.setEstado(EstadoRespuestaEnum.ERROR);
					datosValidos = false;
				} else if (UtilTexto.aplicarTrim(mensaje.getAplicacion().getCodigo()).length() > 30) {
					respuesta.agregarMensaje("El código de la aplicación a la que pertenece el mensaje no puede superar los 30 caracteres...");
					respuesta.setEstado(EstadoRespuestaEnum.ERROR);
					datosValidos = false;
				}

				if (UtilTexto.estaVacia(mensaje.getAplicacion().getNombre())) {
					respuesta.agregarMensaje("El nombre de la aplicación a la que pertenece el mensaje es un dato obligatorio para llevar a cabo la operación de registro...");
					respuesta.setEstado(EstadoRespuestaEnum.ERROR);
					datosValidos = false;
				} else if (UtilTexto.aplicarTrim(mensaje.getAplicacion().getNombre()).length() > 200) {
					respuesta.agregarMensaje("El nombre de la aplicación a la que pertenece el mensaje no puede superar los 200 caracteres...");
					respuesta.setEstado(EstadoRespuestaEnum.ERROR);
					datosValidos = false;
				}
			}
		}

		if (datosValidos) {
			mensajeNegocio.crear(mensaje);
			respuesta.setEstado(EstadoRespuestaEnum.EXITO);
			respuesta.agregarMensaje("El mensaje con código " + mensaje.getCodigo() + " se ha registrado de forma exitosa dentro de la aplicación con código " + mensaje.getAplicacion().getCodigo());
		}

		return respuesta;
	}

	@PutMapping
	public MensajeDTO modificar(@RequestBody MensajeDTO mensaje) {
		mensajeNegocio.modificar(mensaje);
		return mensaje;
	}

	@DeleteMapping
	public MensajeDTO eliminar(@RequestBody MensajeDTO mensaje) {
		mensajeNegocio.eliminar(mensaje);
		return mensaje;
	}

}
