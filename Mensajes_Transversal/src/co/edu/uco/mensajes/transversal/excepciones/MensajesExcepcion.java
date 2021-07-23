package co.edu.uco.mensajes.transversal.excepciones;

import co.edu.uco.transversal.excepciones.BaseExcepcion;
import co.edu.uco.transversal.excepciones.ComponenteEnum;
import co.edu.uco.transversal.excepciones.TipoExcepcionEnum;

public class MensajesExcepcion extends BaseExcepcion {

	private static final long serialVersionUID = 3560959357731341930L;

	private MensajesExcepcion(TipoExcepcionEnum tipo, String textoUsuario, String textoTecnico, Exception excepcionRaiz) {
		super(tipo, textoUsuario, textoTecnico, ComponenteEnum.TRANSVERSAL, excepcionRaiz);
	}

	public static MensajesExcepcion crear(TipoExcepcionEnum tipo, String textoUsuario, String textoTecnico, Exception excepcionRaiz) {
		return new MensajesExcepcion(tipo, textoUsuario, textoTecnico, excepcionRaiz);
	}
	
	public static MensajesExcepcion crear(String textoUsuario) {
		return new MensajesExcepcion(TipoExcepcionEnum.APLICACION, textoUsuario, textoUsuario, new Exception(textoUsuario));
	}
	
	public static MensajesExcepcion crear(String textoUsuario, Exception excepcionRaiz) {
		return new MensajesExcepcion(TipoExcepcionEnum.APLICACION, textoUsuario, textoUsuario, excepcionRaiz);
	}
}
