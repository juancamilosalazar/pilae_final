package com.example.multimodule.infraestructura.partido;

import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.entidad.PartidoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepositorioJpa  extends JpaRepository<PartidoEntidad, Long> {
}
