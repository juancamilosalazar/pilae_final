package com.example.multimodule.infraestructura.partido;

import com.example.multimodule.entidad.PartidoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PartidoRepositorioJpa  extends JpaRepository<PartidoEntidad, Long> {
}
