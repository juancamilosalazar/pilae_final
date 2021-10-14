package com.example.multimodule.infraestructura.jugador;

import com.example.multimodule.entidad.JugadorEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface JugadorRepositorioJpa  extends JpaRepository<JugadorEntidad, Long> {
}
