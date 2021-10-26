package main.com.example.multimodule.dominio;

public class TorneoDominio implements java.io.Serializable{
    private Long codigo;
    private String nombre;
    private String descripcion;
    private DeporteDominio fkDeporte;

    public TorneoDominio(Long codigo, String nombre, String descripcion, DeporteDominio fkDeporte) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fkDeporte = fkDeporte;
    }

    public TorneoDominio() {
    }

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

    public DeporteDominio getFkDeporte() {
        return fkDeporte;
    }

    public void setFkDeporte(DeporteDominio fkDeporte) {
        this.fkDeporte = fkDeporte;
    }
}
