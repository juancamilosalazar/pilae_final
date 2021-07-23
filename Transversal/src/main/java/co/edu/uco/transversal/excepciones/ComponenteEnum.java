package co.edu.uco.transversal.excepciones;

public enum ComponenteEnum {
	DATOS, NEGOCIO, API, DOMINIO, ENTIDAD, DTO, TRANSVERSAL, GENERAL;
	
	public static ComponenteEnum obtenerDefecto() {
		return GENERAL;
	}
}
