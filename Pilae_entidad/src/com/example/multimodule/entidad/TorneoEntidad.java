package com.example.multimodule.entidad;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "torneo")
public class TorneoEntidad  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_torneo")
    private Long codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;

    @JoinColumn(name = "id_deporte")
    @ManyToOne(cascade = CascadeType.MERGE)
    private DeporteEntidad fkDeporte;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DeporteEntidad getFkDeporte() {
        return fkDeporte;
    }

    public void setFkDeporte(DeporteEntidad deporteEntidad) {
        this.fkDeporte = deporteEntidad;
    }
}
