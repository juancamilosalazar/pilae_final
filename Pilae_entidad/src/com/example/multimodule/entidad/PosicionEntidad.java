package com.example.multimodule.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posiciones")
public class PosicionEntidad implements Serializable {
    @Id
    @Column(name = "id_posiciones")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "partidos_jugados")
    private int partidosJugados;
    @Column(name = "partidos_ganados")
    private int partidosGanados;
    @Column(name = "partidos_perdidos")
    private int partidosPerdidos;
    @Column(name = "partidos_empatados")
    private int partidosEmpatados;
    @Column(name = "goles_favor")
    private int golesFavor;
    @Column(name = "goles_contra")
    private int golesContra;
    @Column(name = "goles_diferencia")
    private int golesDiferencia;
    @Column(name = "puntos")
    private int puntos;

    @JoinColumn(name = "id_equipo", nullable = false)
    @OneToOne(optional = false, cascade = CascadeType.MERGE)
    private EquipoEntidad fkEquipo;

    @JoinColumn(name = "id_torneo", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private TorneoEntidad fkTorneo;
}
