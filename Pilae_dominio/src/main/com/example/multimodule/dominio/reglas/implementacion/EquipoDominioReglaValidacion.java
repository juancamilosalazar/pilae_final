package main.com.example.multimodule.dominio.reglas.implementacion;


import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.dominio.reglas.ReglaValidacion;
import main.com.example.multimodule.dominio.reglas.enumerador.ReglaEnum;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilTexto;

public class EquipoDominioReglaValidacion implements ReglaValidacion {

	private EquipoDominio dominio;

	private EquipoDominioReglaValidacion(EquipoDominio dominio) {
		this.dominio = dominio;
	}

	public static ReglaValidacion crear(EquipoDominio dominio) {
		return new EquipoDominioReglaValidacion(dominio);
	}

	@Override
	public void validar(ReglaEnum regla) {
		switch (regla) {
		case CREAR:
		case ACTUALIZAR:
		case RETORNO_DATOS:
			validarCodigo();
			validarNombre();
			break;
		case ELIMINAR:
			validarCodigo();
			break;
		case CONSULTAR:
			if (!UtilTexto.estaVacia(dominio.getCodigo().toString())) {
				validarCodigo();
			}
			if (!UtilTexto.estaVacia(dominio.getNombre())) {
				validarNombre();
			}
			break;
		}
	}

	private void validarCodigo() {
		validarFormatoCodigo();
		validarRangoCodigo();
		validarObligatoriedadCodigo();
		validarLongitudCodigo();
	}

	private void validarNombre() {
		validarFormatoNombre();
		validarObligatoriedadNombre();
		validarLongitudNombre();
	}

	private void validarFormatoCodigo() {
		if (!UtilTexto.contieneSoloDigitos(dominio.getCodigo().toString())) {

			String mensajeUsuario = "formato invalido";

			String mensajeTecnico = "formato invalido";
			throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
		}
	}

	private void validarRangoCodigo() {

	}

	private void validarObligatoriedadCodigo() {
		if (UtilTexto.estaVacia(dominio.getCodigo().toString())) {

			String mensajeUsuario = "codigo vacío";

			String mensajeTecnico = "codigo vacío";
			throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
		}
	}

	private void validarLongitudCodigo() {

	}

	private void validarFormatoNombre() {

	}

	private void validarObligatoriedadNombre() {

	}

	private void validarLongitudNombre() {

	}
}