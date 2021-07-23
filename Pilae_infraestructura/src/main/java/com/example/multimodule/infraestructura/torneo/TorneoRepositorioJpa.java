package com.example.multimodule.infraestructura.torneo;

import com.example.multimodule.entidad.TorneoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TorneoRepositorioJpa  extends JpaRepository<TorneoEntidad, Long> {

}