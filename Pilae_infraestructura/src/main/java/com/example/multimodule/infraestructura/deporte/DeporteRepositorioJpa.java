package com.example.multimodule.infraestructura.deporte;


import com.example.multimodule.entidad.EquipoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface DeporteRepositorioJpa extends JpaRepository<EquipoEntidad, Long> {

}
