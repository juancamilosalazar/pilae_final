package co.edu.uco.transversal.excepciones;

import co.edu.uco.transversal.utilitarios.UtilObjeto;
import co.edu.uco.transversal.utilitarios.UtilTexto;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class BaseExcepcion extends RuntimeException {

	private static final long serialVersionUID = -7398921408524414314L;
	private TipoExcepcionEnum tipo;
	private String textoUsuario;
	private String textoTecnico;
	private ComponenteEnum componente;
	private Exception excepcionRaiz;

	protected BaseExcepcion(TipoExcepcionEnum tipo, String textoUsuario, String textoTecnico, ComponenteEnum componente, Exception excepcionRaiz) {
		super();
		setTipo(tipo);
		setTextoUsuario(textoUsuario);
		setTextoTecnico(textoTecnico);
		setComponente(componente);
		setExcepcionRaiz(excepcionRaiz);
	}

	public TipoExcepcionEnum getTipo() {
		return tipo;
	}

	public String getTextoUsuario() {
		return textoUsuario;
	}

	public String getTextoTecnico() {
		return textoTecnico;
	}

	public ComponenteEnum getComponente() {
		return componente;
	}

	public Exception getExcepcionRaiz() {
		return excepcionRaiz;
	}

	private void setTipo(TipoExcepcionEnum tipo) {
		this.tipo = UtilObjeto.obtenerValorPorDefecto(tipo, TipoExcepcionEnum.obtenerDefecto());
	}

	private void setTextoUsuario(String textoUsuario) {
		this.textoUsuario = UtilTexto.aplicarTrim(textoUsuario);
	}

	private void setTextoTecnico(String textoTecnico) {
		this.textoTecnico = UtilTexto.aplicarTrim(textoTecnico);
	}

	private void setComponente(ComponenteEnum componente) {
		this.componente = UtilObjeto.obtenerValorPorDefecto(componente, ComponenteEnum.obtenerDefecto());
	}

	private void setExcepcionRaiz(Exception excepcionRaiz) {
		this.excepcionRaiz = UtilObjeto.obtenerValorPorDefecto(excepcionRaiz, new Exception());
	}

	@Override
	public String toString() {
		
		StringBuilder traza = new StringBuilder();
		traza.append("Tipo excepci�n: ").append(getTipo()).append("\n");
		traza.append("Componente: ").append(getComponente()).append("\n");
		traza.append("Texto usuario: ").append(getTextoUsuario()).append("\n");
		traza.append("Texto t�cnico: ").append(getTextoTecnico()).append("\n");
		traza.append("Excepci�n ra�z: ").append(ExceptionUtils.getStackTrace(getExcepcionRaiz())).append("\n");
		
		return traza.toString();
	}
}