package main.com.example.multimodule.dominio;

public class DeporteDominio implements java.io.Serializable{

    private Long codigo;
    private String nombre;

    public DeporteDominio(Long codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public DeporteDominio() {
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
}