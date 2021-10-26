package com.example.multimodule.infraestructura.marcador;

import com.example.multimodule.entidad.MarcadorEntidad;
import com.example.multimodule.entidad.PartidoEntidad;
import com.example.multimodule.entidad.TorneoEntidad;
import main.com.example.multimodule.dto.Marcador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface MarcadorRepositorioJpa  extends JpaRepository<MarcadorEntidad, Long> {
    List<MarcadorEntidad> findByFkPartido(PartidoEntidad fkPartido);
    List<MarcadorEntidad> findByFkPartidoFkTorneo(final TorneoEntidad id);
}
