package com.deusto.plassb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deusto.plassb.entity.Asignacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Asignacion.
 * 
 * @author Equipo DS-03
 */
@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    
    Optional<Asignacion> findByAsignacionID(String asignacionID);
    
    List<Asignacion> findByFecha(LocalDate fecha);
}