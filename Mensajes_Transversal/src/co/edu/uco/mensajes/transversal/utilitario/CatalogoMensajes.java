package co.edu.uco.mensajes.transversal.utilitario;

import co.edu.uco.mensajes.dto.MensajeDTO;
import co.edu.uco.mensajes.enumerador.CategoriaMensajeEnum;
import co.edu.uco.mensajes.enumerador.TipoMensajeEnum;
import co.edu.uco.mensajes.transversal.excepciones.MensajesExcepcion;
import co.edu.uco.mensajes.transversal.utilitario.CodigosMensajesBase.CodigosCatalogoMensajes;
import co.edu.uco.transversal.respuesta.rest.EstadoRespuestaEnum;
import co.edu.uco.transversal.respuesta.rest.Respuesta;
import co.edu.uco.transversal.utilitarios.ParametroDTO;
import co.edu.uco.transversal.utilitarios.UtilTexto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static co.edu.uco.transversal.utilitarios.UtilObjeto.objetoEsNulo;
import static co.edu.uco.transversal.utilitarios.UtilTexto.estaVacia;

public class CatalogoMensajes {

	private static String endPoint;
	private static String nombreAplicacion;
	private static Map<String, MensajeDTO> mensajeriaBase = new HashMap<>();

	private CatalogoMensajes() {
	}

	public static void inicializar(String endPoint, String nombreAplicacion) {
		mensajeriaBase.put(CodigosCatalogoMensajes.ERROR_OBJETO_MENSAJE_ESTA_VACIO, MensajeDTO.crear().setCategoria(CategoriaMensajeEnum.ERROR).setCodigo(CodigosCatalogoMensajes.ERROR_OBJETO_MENSAJE_ESTA_VACIO).setContenido("La informaci�n base del mensaje que se desea sincronizar est� vac�a y es un dato obligatorio!").setTipoMensaje(TipoMensajeEnum.TECNICO));
		mensajeriaBase.put(CodigosCatalogoMensajes.ERROR_CODIGO_MENSAJE_ESTA_VACIO, MensajeDTO.crear().setCategoria(CategoriaMensajeEnum.ERROR).setCodigo(CodigosCatalogoMensajes.ERROR_CODIGO_MENSAJE_ESTA_VACIO).setContenido("El c�digo del mensaje que se desea obtener es un dato obligatorio!").setTipoMensaje(TipoMensajeEnum.TECNICO));
		mensajeriaBase.put(CodigosCatalogoMensajes.ERROR_CODIGO_MENSAJE_NO_ENCONTRADO, MensajeDTO.crear().setCategoria(CategoriaMensajeEnum.ERROR).setCodigo(CodigosCatalogoMensajes.ERROR_CODIGO_MENSAJE_NO_ENCONTRADO).setContenido("El c�digo del mensaje \"{1}\" enviado no existe en el cat�logo de mensajes para la aplicaci�n \"{2}\"!").setTipoMensaje(TipoMensajeEnum.TECNICO));
		mensajeriaBase.put(CodigosCatalogoMensajes.ERROR_CONSUMIENDO_SERVICIO_CONSULTA_CACHE_MENSAJES, MensajeDTO.crear().setCategoria(CategoriaMensajeEnum.ERROR).setCodigo(CodigosCatalogoMensajes.ERROR_CONSUMIENDO_SERVICIO_CONSULTA_CACHE_MENSAJES).setContenido("Se ha presentado un problema tratando de consumir el com.example.multimodule.repository.servicio de consulta de mensajes correspondiente a la caché de mensajería de la aplicación \"{1}\"!").setTipoMensaje(TipoMensajeEnum.TECNICO));

		setEndPoint(endPoint);
		setNombreAplicacion(nombreAplicacion);
	}

	private static void setEndPoint(String url) {
		if (UtilTexto.estaVacia(url)) {
			throw MensajesExcepcion.crear(obtenerMensaje(CodigosCatalogoMensajes.ERROR_END_POINT_NO_ENVIADO).getContenido());
		}

		endPoint = UtilTexto.aplicarTrim(url);
	}

	private static void setNombreAplicacion(String aplicacion) {
		if (UtilTexto.estaVacia(aplicacion)) {
			throw MensajesExcepcion.crear(obtenerMensaje(CodigosCatalogoMensajes.ERROR_NOMBRE_APLICACION_NO_ENVIADO).getContenido());
		}

		nombreAplicacion = UtilTexto.aplicarTrim(aplicacion);
	}

	public static void sincronizarMensajeBase(MensajeDTO mensaje) {

		if (objetoEsNulo(mensaje)) {
			throw MensajesExcepcion.crear(obtenerMensaje(CodigosCatalogoMensajes.ERROR_OBJETO_MENSAJE_ESTA_VACIO).getContenido());
		}

		mensajeriaBase.put(mensaje.getCodigo(), mensaje);
	}

	public static MensajeDTO obtenerMensaje(String codigo) {

		MensajeDTO mensajeRetorno = null;

		if (estaVacia(codigo)) {
			MensajeDTO mensaje = obtenerMensaje(CodigosCatalogoMensajes.ERROR_CODIGO_MENSAJE_ESTA_VACIO);
			throw MensajesExcepcion.crear(mensaje.getContenido());
		}

		if (mensajeriaBase.containsKey(codigo)) {
			mensajeRetorno = mensajeriaBase.get(codigo);
		} else {
			mensajeRetorno = consultarMensajeEnCache(codigo);
		}

		if (objetoEsNulo(mensajeRetorno)) {
			MensajeDTO mensaje = obtenerMensaje(CodigosCatalogoMensajes.ERROR_CODIGO_MENSAJE_NO_ENCONTRADO);
			throw MensajesExcepcion.crear(mensaje.getContenidoParametrizado(codigo, nombreAplicacion));
		}

		return mensajeRetorno;

	}

	public static MensajeDTO consultarMensajeEnCache(String codigo) {

		if (estaVacia(codigo)) {
			MensajeDTO mensaje = obtenerMensaje(CodigosCatalogoMensajes.ERROR_CODIGO_MENSAJE_ESTA_VACIO);
			throw MensajesExcepcion.crear(mensaje.getContenido());
		}

		MensajeDTO mensajeRetorno = null;

		Map<String, String> parametrosConsumoServicio = new HashMap<>();

		Respuesta<MensajeDTO> respuesta = null;

		try {
			RestTemplate plantillaRest = new RestTemplate();
			String url = UtilTexto.reemplazar(endPoint, ParametroDTO.crear("{1}", nombreAplicacion), ParametroDTO.crear("{2}", codigo));
			respuesta = plantillaRest.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Respuesta<MensajeDTO>>() {
			}, parametrosConsumoServicio).getBody();
		} catch (Exception excepcion) {
			MensajeDTO mensaje = obtenerMensaje(CodigosCatalogoMensajes.ERROR_CONSUMIENDO_SERVICIO_CONSULTA_CACHE_MENSAJES);
			throw MensajesExcepcion.crear(mensaje.getContenidoParametrizado(nombreAplicacion), excepcion);
		}

		if (EstadoRespuestaEnum.ERROR.equals(respuesta.getEstado())) {

			String mensajes = "";

			for (String mensajeRespuesta : respuesta.getMensajesRepuesta()) {
				mensajes = mensajes.concat(mensajeRespuesta).concat("\n");
			}

			throw MensajesExcepcion.crear(mensajes);
		}

		if (!respuesta.getResultado().isEmpty()) {
			mensajeRetorno = respuesta.getResultado().get(0);
		}

		return mensajeRetorno;
	}
}
