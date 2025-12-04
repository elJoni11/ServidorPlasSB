package com.deusto.plassb.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deusto.plassb.service.PlasSBService;
import com.deusto.plassb.dto.CapacidadResponseDTO;
import com.deusto.plassb.dto.NotificacionRequestDTO;
import com.deusto.plassb.dto.NotificacionResponseDTO;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/plassb")
public class PlasSBController {

	private final PlasSBService service;

    public PlasSBController(PlasSBService service) {
        this.service = service;
    }
    
    // 1. Endpoint: Consultar Capacidad (GET)
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
    
    // 2. Endpoint: Notificar Asignación (POST)
    @PostMapping("/notificacion")
    public ResponseEntity<NotificacionResponseDTO> recibirNotificacion(
            @RequestBody NotificacionRequestDTO request) {
        
        System.out.println("--> PlasSB Controller: Notificación recibida. Asignación ID: " + request.getAsignacionID());

        boolean exito = service.registrarNotificacion(
                request.getAsignacionID(),
                request.getContenedoresIDs(),
                request.getFecha(),
                request.getCantidadEstimada()
        );

        if (exito) {
            NotificacionResponseDTO response = new NotificacionResponseDTO();
            response.setMensaje("Asignación registrada y capacidad actualizada.");
            response.setAsignacionID(request.getAsignacionID());
            response.setConfirmacion(true);
            return ResponseEntity.ok(response);
        } else {
            // Fallo por capacidad insuficiente o fecha inválida
            return ResponseEntity.badRequest().body(
                new NotificacionResponseDTO("Error: Capacidad insuficiente o fecha no válida.", request.getAsignacionID(), false)
            );
        }
    }
}
