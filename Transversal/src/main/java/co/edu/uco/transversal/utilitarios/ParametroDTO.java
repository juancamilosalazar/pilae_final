package co.edu.uco.transversal.utilitarios;

import static co.edu.uco.transversal.utilitarios.UtilTexto.aplicarTrim;

public class ParametroDTO {

	private String clave;
	private String valor;

	private ParametroDTO(String clave, String valor) {
		super();
		setClave(clave);
		setValor(valor);
	}

	public static ParametroDTO crear(String clave, String valor) {
		return new ParametroDTO(clave, valor);
	}

	private void setClave(String clave) {
		this.clave = aplicarTrim(clave);
	}

	private void setValor(String valor) {
		this.valor = aplicarTrim(valor);
	}

	public String getClave() {
		return clave;
	}

	public String getValor() {
		return valor;
	}
}