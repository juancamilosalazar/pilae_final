package main.com.example.multimodule.dto;




public class Marcador {
    private Long codigo;
    private String equipoGanador;
    private int equipoLocalMrc;
    private int equipoVisitanteMrc;
    private Partido fkPartido;

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

    public Partido getFkPartido() {
        return fkPartido;
    }

    public void setFkPartido(Partido fkPartido) {
        this.fkPartido = fkPartido;
    }
}
