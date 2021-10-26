package com.example.multimodule.infraestructura.partido;

import com.example.multimodule.entidad.PartidoEntidad;
import com.example.multimodule.entidad.TorneoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface PartidoRepositorioJpa  extends JpaRepository<PartidoEntidad, Long> {

    List<PartidoEntidad> findByFkTorneo(TorneoEntidad fkTorneo);
}
