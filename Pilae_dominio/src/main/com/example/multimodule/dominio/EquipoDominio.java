package main.com.example.multimodule.dominio;

public class EquipoDominio implements java.io.Serializable{

    private Long codigo;
    private String nombre;
    private String locacion;
    private String genero;
    private TorneoDominio torneo;

    public EquipoDominio(Long codigo, String nombre, String locacion, String genero, TorneoDominio torneo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.locacion = locacion;
        this.genero = genero;
        this.torneo = torneo;
    }

    public EquipoDominio() {
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

    public TorneoDominio getTorneo() {
        return torneo;
    }

    public void setTorneo(TorneoDominio torneo) {
        this.torneo = torneo;
    }
}