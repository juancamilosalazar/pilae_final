package co.edu.uco.mensajes.dto;

import co.edu.uco.transversal.utilitarios.UtilObjeto;
import co.edu.uco.transversal.utilitarios.UtilTexto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AplicacionDTO implements Serializable {

	private static final long serialVersionUID = -8532352573927284009L;
	private String codigo;
	private String nombre;
	private List<MensajeDTO> mensajes;

	private AplicacionDTO() {
		super();
	}

	public static AplicacionDTO crear() {
		return new AplicacionDTO();
	}

	public String getCodigo() {
		if (UtilObjeto.objetoEsNulo(codigo)) {
			setCodigo(codigo);
		}
		return codigo;
	}

	public AplicacionDTO setCodigo(String codigo) {
		this.codigo = UtilTexto.aplicarTrim(codigo);
		return this;
	}

	public String getNombre() {
		if (UtilObjeto.objetoEsNulo(nombre)) {
			setNombre(nombre);
		}
		return nombre;
	}

	public AplicacionDTO setNombre(String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
		return this;
	}

	public List<MensajeDTO> getMensajes() {
		if (UtilObjeto.objetoEsNulo(mensajes)) {
			setMensajes(mensajes);
		}
		return mensajes;
	}

	public AplicacionDTO setMensajes(List<MensajeDTO> mensajes) {
		this.mensajes = UtilObjeto.obtenerValorPorDefecto(mensajes, new ArrayList<>());
		return this;
	}

}
