package main.com.example.multimodule.dto;


//@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public class Partido implements java.io.Serializable {
    private Long codigo;
    private Equipo fkEquipoLocal;
    private Equipo fkEquipoVisitante;
    private String ronda;
    private Long fechaDelPartido;
    private String idaVuelta;
    private String estadoPartido;
    private Torneo fkTorneo;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Equipo getFkEquipoLocal() {
        return fkEquipoLocal;
    }

    public void setFkEquipoLocal(Equipo fkEquipoLocal) {
        this.fkEquipoLocal = fkEquipoLocal;
    }

    public Equipo getFkEquipoVisitante() {
        return fkEquipoVisitante;
    }

    public void setFkEquipoVisitante(Equipo fkEquipoVisitante) {
        this.fkEquipoVisitante = fkEquipoVisitante;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public Long getFechaDelPartido() {
        return fechaDelPartido;
    }

    public void setFechaDelPartido(Long fechaDelPartido) {
        this.fechaDelPartido = fechaDelPartido;
    }

    public String getIdaVuelta() {
        return idaVuelta;
    }

    public void setIdaVuelta(String idaVuelta) {
        this.idaVuelta = idaVuelta;
    }

    public String getEstadoPartido() {
        return estadoPartido;
    }

    public void setEstadoPartido(String estadoPartido) {
        this.estadoPartido = estadoPartido;
    }

    public Torneo getFkTorneo() {
        return fkTorneo;
    }

    public void setFkTorneo(Torneo fkTorneo) {
        this.fkTorneo = fkTorneo;
    }
}
