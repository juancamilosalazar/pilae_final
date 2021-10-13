package com.example.multimodule.infraestructura.posicion;

import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.entidad.PosicionEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosicionRepositorioJpa  extends JpaRepository<PosicionEntidad, Long> {
    PosicionEntidad findByFkEquipo(EquipoEntidad fkEquipo);
}
