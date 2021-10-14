package com.example.multimodule.infraestructura.posicion;

import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.entidad.PosicionEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PosicionRepositorioJpa  extends JpaRepository<PosicionEntidad, Long> {
    PosicionEntidad findByfkEquipo(EquipoEntidad fkEquipo);
}
