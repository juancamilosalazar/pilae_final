package main.com.example.multimodule.dto;

public class Torneo implements java.io.Serializable{
    private Long codigo;
    private String nombre;
    private String descripcion;
    private Deporte fkDeporte;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Deporte getFkDeporte() {
        return fkDeporte;
    }

    public void setFkDeporte(Deporte fkDeporte) {
        this.fkDeporte = fkDeporte;
    }
}
