package com.example.multimodule.infraestructura.marcador;

import com.example.multimodule.entidad.MarcadorEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface MarcadorRepositorioJpa  extends JpaRepository<MarcadorEntidad, Long> {
}
