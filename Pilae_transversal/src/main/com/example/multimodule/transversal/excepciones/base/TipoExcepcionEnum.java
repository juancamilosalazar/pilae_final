package main.com.example.multimodule.transversal.excepciones.base;

public enum TipoExcepcionEnum {

	NEGOCIO("Negocio", "Corresponde a un tipo de excepci�n que es lanzada desde un flujo de negocio, por la violaci�n de una regla de negocio espec�fica."), APLICACION("Aplicaci�n", "Corresponde a un tipo de excepci�n que es lanzada desde cualquier parte de la aplicaci�n.");

	private String nombre;
	private String descripcion;

	private TipoExcepcionEnum(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public static TipoExcepcionEnum obtenerDefecto() {
		return APLICACION;
	}
}
