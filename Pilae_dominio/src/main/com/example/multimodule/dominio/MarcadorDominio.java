package main.com.example.multimodule.dominio;

public class MarcadorDominio {
    private Long codigo;
    private String equipoGanador;
    private int equipoLocalMrc;
    private int equipoVisitanteMrc;
    private PartidoDominio fkPartido;

    public MarcadorDominio(Long codigo, String equipoGanador, int equipoLocalMrc, int equipoVisitanteMrc, PartidoDominio fkPartido) {
        this.codigo = codigo;
        this.equipoGanador = equipoGanador;
        this.equipoLocalMrc = equipoLocalMrc;
        this.equipoVisitanteMrc = equipoVisitanteMrc;
        this.fkPartido = fkPartido;
    }

    public MarcadorDominio() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getEquipoGanador() {
        return equipoGanador;
    }

    public void setEquipoGanador(String equipoGanador) {
        this.equipoGanador = equipoGanador;
    }

    public int getEquipoLocalMrc() {
        return equipoLocalMrc;
    }

    public void setEquipoLocalMrc(int equipoLocalMrc) {
        this.equipoLocalMrc = equipoLocalMrc;
    }

    public int getEquipoVisitanteMrc() {
        return equipoVisitanteMrc;
    }

    public void setEquipoVisitanteMrc(int equipoVisitanteMrc) {
        this.equipoVisitanteMrc = equipoVisitanteMrc;
    }

    public PartidoDominio getFkPartido() {
        return fkPartido;
    }

    public void setFkPartido(PartidoDominio fkPartido) {
        this.fkPartido = fkPartido;
    }
}
