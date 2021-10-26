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
@Table(name = "marcador")
public class MarcadorEntidad implements Serializable {
    @Id
    @Column(name = "id_marcador", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "equipo_ganador")
    private String equipoGanador;
    @Column(name = "marcador_equipo_local")
    private int equipoLocalMrc;
    @Column(name = "marcador_equipo_visitante")
    private int equipoVisitanteMrc;

    @JoinColumn(name = "id_partido", nullable = false)
    @OneToOne(optional = false, cascade = CascadeType.MERGE)
    private PartidoEntidad fkPartido;
}
