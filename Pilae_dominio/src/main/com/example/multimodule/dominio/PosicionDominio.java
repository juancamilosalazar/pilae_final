package main.com.example.multimodule.dominio;

public class PosicionDominio {

    private Long codigo;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosPerdidos;
    private int partidosEmpatados;
    private int golesFavor;
    private int golesContra;
    private int golesDiferencia;
    private int puntos;
    private EquipoDominio fkEquipo;
    private TorneoDominio fkTorneo;

    public PosicionDominio() {
    }

    public PosicionDominio(Long codigo, int partidosJugados, int partidosGanados, int partidosPerdidos, int partidosEmpatados, int golesFavor, int golesContra, int golesDiferencia, int puntos, EquipoDominio fkEquipo, TorneoDominio fkTorneo) {
        this.codigo = codigo;
        this.partidosJugados = partidosJugados;
        this.partidosGanados = partidosGanados;
        this.partidosPerdidos = partidosPerdidos;
        this.partidosEmpatados = partidosEmpatados;
        this.golesFavor = golesFavor;
        this.golesContra = golesContra;
        this.golesDiferencia = golesDiferencia;
        this.puntos = puntos;
        this.fkEquipo = fkEquipo;
        this.fkTorneo = fkTorneo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public int getGolesDiferencia() {
        return golesDiferencia;
    }

    public void setGolesDiferencia(int golesDiferencia) {
        this.golesDiferencia = golesDiferencia;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public EquipoDominio getFkEquipo() {
        return fkEquipo;
    }

    public void setFkEquipo(EquipoDominio fkEquipo) {
        this.fkEquipo = fkEquipo;
    }

    public TorneoDominio getFkTorneo() {
        return fkTorneo;
    }

    public void setFkTorneo(TorneoDominio fkTorneo) {
        this.fkTorneo = fkTorneo;
    }
}

