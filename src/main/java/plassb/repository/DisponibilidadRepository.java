package plassb.repository;

import plassb.entity.Disponibilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long> {
    // Método mágico de Spring Data para buscar por fecha
    Optional<Disponibilidad> findByFecha(LocalDate fecha);
}
