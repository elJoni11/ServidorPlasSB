package com.deusto.plassb.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deusto.plassb.service.PlasSBService;
import com.deusto.plassb.dto.CapacidadResponseDTO;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/plassb")
public class PlasSBController {

	private final PlasSBService service;

    public PlasSBController(PlasSBService service) {
        this.service = service;
    }

    @GetMapping("/capacidad")
    public ResponseEntity<CapacidadResponseDTO> getCapacidad(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        
        System.out.println("--> PlasSB Controller: Petición recibida para fecha " + fecha);

        return service.consultarCapacidad(fecha)
                .map(capacidad -> {
                    // Mapeamos la Entidad Capacidad al DTO de respuesta
                    CapacidadResponseDTO dto = new CapacidadResponseDTO();
                    dto.setPlantaID("PlasSB Ltd.");
                    dto.setFecha(capacidad.getFecha());
                    dto.setCapacidadTotal(capacidad.getCapacidadTotal());
                    dto.setCapacidadDisponible(capacidad.getCapacidadDisponible());
                    dto.setUnidad(capacidad.getUnidad());
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> {
                    System.out.println("--> PlasSB Controller: Fecha no encontrada -> 404");
                    return ResponseEntity.notFound().build();
                });
    }
}
