package main.com.example.multimodule.dominio;

public class PartidoDominio {

    private Long codigo;
    private EquipoDominio fkEquipoLocal;
    private EquipoDominio fkEquipoVisitante;
    private String ronda;
    private Long fechaDelPartido;
    private String idaVuelta;
    private String estadoPartido;
    private TorneoDominio fkTorneo;

    public PartidoDominio(Long codigo, EquipoDominio fkEquipoLocal, EquipoDominio fkEquipoVisitante, String ronda, Long fechaDelPartido, String idaVuelta, String estadoPartido, TorneoDominio fkTorneo) {
        this.codigo = codigo;
        this.fkEquipoLocal = fkEquipoLocal;
        this.fkEquipoVisitante = fkEquipoVisitante;
        this.ronda = ronda;
        this.fechaDelPartido = fechaDelPartido;
        this.idaVuelta = idaVuelta;
        this.estadoPartido = estadoPartido;
        this.fkTorneo = fkTorneo;
    }

    public PartidoDominio() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public EquipoDominio getFkEquipoLocal() {
        return fkEquipoLocal;
    }

    public void setFkEquipoLocal(EquipoDominio fkEquipoLocal) {
        this.fkEquipoLocal = fkEquipoLocal;
    }

    public EquipoDominio getFkEquipoVisitante() {
        return fkEquipoVisitante;
    }

    public void setFkEquipoVisitante(EquipoDominio fkEquipoVisitante) {
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

    public TorneoDominio getFkTorneo() {
        return fkTorneo;
    }

    public void setFkTorneo(TorneoDominio fkTorneo) {
        this.fkTorneo = fkTorneo;
    }
}
