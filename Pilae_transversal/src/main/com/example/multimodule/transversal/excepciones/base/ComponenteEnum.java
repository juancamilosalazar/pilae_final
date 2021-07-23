package main.com.example.multimodule.transversal.excepciones.base;

public enum ComponenteEnum {
	DATOS, NEGOCIO, API, DOMINIO, ENTIDAD, DTO, TRANSVERSAL, GENERAL;
	
	public static ComponenteEnum obtenerDefecto() {
		return GENERAL;
	}
}
