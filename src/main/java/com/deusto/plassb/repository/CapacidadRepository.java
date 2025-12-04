package com.deusto.plassb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deusto.plassb.entity.Capacidad;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Capacidad.
 **/

@Repository
public interface CapacidadRepository extends JpaRepository<Capacidad, Long> {
    
    Optional<Capacidad> findByFecha(LocalDate fecha);
    
    boolean existsByFecha(LocalDate fecha);
}