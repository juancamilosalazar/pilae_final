package main.com.example.multimodule.dto;

public class Equipo implements java.io.Serializable{

    private Long codigo;
    private String nombre;
    private String locacion;
    private String genero;
    private Torneo fkTorneo;

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

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Torneo getFkTorneo() {
        return fkTorneo;
    }

    public void setFkTorneo(Torneo fkTorneo) {
        this.fkTorneo = fkTorneo;
    }
}