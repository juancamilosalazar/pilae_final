package com.example.multimodule.infraestructura.equipo;


import com.example.multimodule.entidad.EquipoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EquipoRepositorioJpa extends JpaRepository<EquipoEntidad, Long> {

}
