package com.example.multimodule.infraestructura.marcador;

import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.entidad.MarcadorEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcadorRepositorioJpa  extends JpaRepository<MarcadorEntidad, Long> {
}
