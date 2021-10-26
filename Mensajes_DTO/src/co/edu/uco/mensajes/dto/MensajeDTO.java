package co.edu.uco.mensajes.dto;

import co.edu.uco.mensajes.enumerador.CategoriaMensajeEnum;
import co.edu.uco.mensajes.enumerador.TipoMensajeEnum;
import co.edu.uco.transversal.utilitarios.ParametroDTO;
import co.edu.uco.transversal.utilitarios.UtilObjeto;
import co.edu.uco.transversal.utilitarios.UtilTexto;

import java.io.Serializable;

public class MensajeDTO implements Serializable {

	private static final long serialVersionUID = 1902304558018333420L;
	private String codigo;
	private TipoMensajeEnum tipoMensaje;
	private CategoriaMensajeEnum categoria;
	private String titulo;
	private String contenido;
	private AplicacionDTO aplicacion;

	public MensajeDTO() {
		super();
	}

	public static MensajeDTO crear() {
		return new MensajeDTO();
	}

	public String getCodigo() {
		if (UtilObjeto.objetoEsNulo(codigo)) {
			setCodigo(codigo);
		}
		return codigo;
	}

	public MensajeDTO setCodigo(String codigo) {
		this.codigo = UtilTexto.aplicarTrim(codigo);
		return this;
	}

	public TipoMensajeEnum getTipoMensaje() {
		if (UtilObjeto.objetoEsNulo(tipoMensaje)) {
			setTipoMensaje(tipoMensaje);
		}
		return tipoMensaje;
	}

	public MensajeDTO setTipoMensaje(TipoMensajeEnum tipoMensaje) {
		this.tipoMensaje = UtilObjeto.obtenerValorPorDefecto(tipoMensaje, TipoMensajeEnum.TECNICO);
		return this;
	}

	public CategoriaMensajeEnum getCategoria() {
		if (UtilObjeto.objetoEsNulo(categoria)) {
			setCategoria(categoria);
		}
		return categoria;
	}

	public MensajeDTO setCategoria(CategoriaMensajeEnum categoria) {
		this.categoria = UtilObjeto.obtenerValorPorDefecto(categoria, CategoriaMensajeEnum.INFORMACION);
		return this;
	}

	public String getTitulo() {
		if (UtilObjeto.objetoEsNulo(titulo)) {
			setTitulo(titulo);
		}
		return titulo;
	}

	public MensajeDTO setTitulo(String titulo) {
		this.titulo = UtilTexto.aplicarTrim(titulo);
		return this;
	}

	public String getContenido() {
		if (UtilObjeto.objetoEsNulo(contenido)) {
			setContenido(contenido);
		}
		return contenido;
	}

	public MensajeDTO setContenido(String contenido) {
		this.contenido = UtilTexto.aplicarTrim(contenido);
		return this;
	}

	public AplicacionDTO getAplicacion() {
		if (UtilObjeto.objetoEsNulo(aplicacion)) {
			setAplicacion(aplicacion);
		}
		return aplicacion;
	}

	public MensajeDTO setAplicacion(AplicacionDTO aplicacion) {
		this.aplicacion = UtilObjeto.obtenerValorPorDefecto(aplicacion, AplicacionDTO.crear());
		return this;
	}

	public String getContenidoParametrizado(ParametroDTO... parametros) {
		return UtilTexto.reemplazar(getContenido(), parametros);
	}

	public String getContenidoParametrizado(String... valores) {
		return getContenidoParametrizado(prepararParametros(valores));
	}

	public String getTituloParametrizado(ParametroDTO... parametros) {
		return UtilTexto.reemplazar(getTitulo(), parametros);
	}

	public String getTituloParametrizado(String... valores) {
		return getTituloParametrizado(prepararParametros(valores));
	}

	private ParametroDTO[] prepararParametros(String... valores) {
		ParametroDTO[] parametros = new ParametroDTO[valores.length];
		String patron = "{#}";

		for (int indice = 0; indice < valores.length; indice++) {
			ParametroDTO comodin = ParametroDTO.crear("#", String.valueOf(indice + 1));
			parametros[indice] = ParametroDTO.crear(UtilTexto.reemplazar(patron, comodin), valores[indice]);
		}

		return parametros;
	}
}