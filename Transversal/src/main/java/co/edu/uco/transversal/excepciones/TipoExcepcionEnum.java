package co.edu.uco.transversal.excepciones;

public enum TipoExcepcionEnum {

	NEGOCIO("Negocio", "Corresponde a un tipo de excepción que es lanzada desde un flujo de negocio, por la violación de una regla de negocio específica."), APLICACION("Aplicación", "Corresponde a un tipo de excepción que es lanzada desde cualquier parte de la aplicación.");

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
