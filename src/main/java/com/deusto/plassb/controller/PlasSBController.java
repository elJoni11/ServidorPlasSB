package com.deusto.plassb.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deusto.plassb.service.PlasSBService;
import com.deusto.plassb.dto.CapacidadResponseDTO;
import com.deusto.plassb.dto.NotificacionRequestDTO;
import com.deusto.plassb.dto.NotificacionResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/plassb")
public class PlasSBController {

	private final PlasSBService service;

    public PlasSBController(PlasSBService service) {
        this.service = service;
    }
    
    // 1. Endpoint: Consultar Capacidad (GET)
    @Operation(summary = "Consultar capacidad disponible", 
            description = "Devuelve la capacidad de procesamiento disponible para una fecha específica.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Capacidad encontrada exitosamente.",
    				content = @Content(schema = @Schema(implementation = CapacidadResponseDTO.class))),
    		@ApiResponse(responseCode = "400", description = "Error: Formato de fecha inválido.", content = @Content),
    		@ApiResponse(responseCode = "404", description = "Not found: No hay datos de planificación para la fecha solicitada.", 
    				content = @Content),
    		@ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
    })
    
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
    @Operation(summary = "Notificar asignación de contenedores", 
            description = "Registra la recepción de nuevos contenedores y actualiza el stock disponible.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Asignación registrada correctamente.",
    				content = @Content(schema = @Schema(implementation = NotificacionResponseDTO.class))),
    		@ApiResponse(responseCode = "400", description = "Error: Capacidad insuficiente o fecha no válida.",
    				content = @Content),
    		@ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
    })    
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
