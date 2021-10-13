package com.example.multimodule.infraestructura.equipo;


import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.entidad.TorneoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface EquipoRepositorioJpa extends JpaRepository<EquipoEntidad, Long> {
    List<EquipoEntidad> findByFkTorneo(TorneoEntidad fkTorneo);
}
