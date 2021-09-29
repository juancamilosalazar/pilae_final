package com.example.multimodule.infraestructura.jugador;

import com.example.multimodule.entidad.JugadorEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepositorioJpa  extends JpaRepository<JugadorEntidad, Long> {
}
