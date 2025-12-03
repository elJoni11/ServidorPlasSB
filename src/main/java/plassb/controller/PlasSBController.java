package plassb.controller;

import plassb.entity.Disponibilidad;
import plassb.repository.DisponibilidadRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/plassb")
public class PlasSBController {

    private final DisponibilidadRepository repository;

    public PlasSBController(DisponibilidadRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/capacidad")
    public ResponseEntity<?> getCapacidad(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        
        System.out.println("--> PlasSB recibiendo petición para fecha: " + fecha);

        return repository.findByFecha(fecha)
                .map(d -> ResponseEntity.ok(Map.of(
                        "planta", "PlasSB Ltd.",
                        "fecha", d.getFecha(),
                        "capacidadDisponible", d.getCapacidadToneladas()
                )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
