package com.deusto.plassb.dto;

import java.time.LocalDate;

/**
 * DTO para la respuesta de consulta de capacidad.
 **/

public class CapacidadResponseDTO {
    
    private String plantaID = "PLASSB01";
    private LocalDate fecha;
    private Integer capacidadTotal;
    private Integer capacidadDisponible;
    private String unidad = "kg";
    
    public CapacidadResponseDTO() {
    }
    
    public CapacidadResponseDTO(LocalDate fecha, Integer capacidadTotal, 
                                Integer capacidadDisponible) {
        this.fecha = fecha;
        this.capacidadTotal = capacidadTotal;
        this.capacidadDisponible = capacidadDisponible;
    }
    
    // Getters y Setters
    public String getPlantaID() {
        return plantaID;
    }
    
    public void setPlantaID(String plantaID) {
        this.plantaID = plantaID;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public Integer getCapacidadTotal() {
        return capacidadTotal;
    }
    
    public void setCapacidadTotal(Integer capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }
    
    public Integer getCapacidadDisponible() {
        return capacidadDisponible;
    }
    
    public void setCapacidadDisponible(Integer capacidadDisponible) {
        this.capacidadDisponible = capacidadDisponible;
    }
    
    public String getUnidad() {
        return unidad;
    }
    
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}