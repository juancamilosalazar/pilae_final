package com.example.multimodule.entidad;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipo")
public class EquipoEntidad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo", nullable = false)
    private long codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "locacion")
    private String locacion;
    @Column(name = "genero")
    private String genero;

    @JoinColumn(name = "id_torneo")
    @ManyToOne(cascade = CascadeType.MERGE)
    private TorneoEntidad fkTorneo;


    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
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

    public TorneoEntidad getFkTorneo() {
        return fkTorneo;
    }

    public void setFkTorneo(TorneoEntidad torneoEntidad) {
        this.fkTorneo = torneoEntidad;
    }
}
