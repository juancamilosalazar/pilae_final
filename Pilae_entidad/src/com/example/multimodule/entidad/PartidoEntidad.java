package com.example.multimodule.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "partido")
public class PartidoEntidad implements Serializable {
    @Id
    @Column(name = "id_partido", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "fecha_partido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDelpartido;
    @Column(name = "ida_vuelta")
    private String idaVuelta;
    @Column(name = "ronda")
    private String ronda;
    @Column(name = "estado_partido")
    private String estadoPartido;


    @JoinColumn(name = "id_equipo_local", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private EquipoEntidad fkEquipoLocal;

    @JoinColumn(name = "id_equipo_visitante", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private EquipoEntidad fkEquipoVisitante;

    @JoinColumn(name = "id_torneo", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private TorneoEntidad fkTorneo;
}
