package com.deusto.plassb.dto;


import java.time.LocalDate;
import java.util.List;

/**
 * DTO para recibir notificación de asignación.
 **/
public class NotificacionRequestDTO {
    
    private String asignacionID;
    private List<String> contenedoresIDs;
    private LocalDate fecha;
    private Integer cantidadEstimada;
    
    public NotificacionRequestDTO() {
    }
    
    // Getters y Setters
    public String getAsignacionID() {
        return asignacionID;
    }
    
    public void setAsignacionID(String asignacionID) {
        this.asignacionID = asignacionID;
    }
    
    public List<String> getContenedoresIDs() {
        return contenedoresIDs;
    }
    
    public void setContenedoresIDs(List<String> contenedoresIDs) {
        this.contenedoresIDs = contenedoresIDs;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public Integer getCantidadEstimada() {
        return cantidadEstimada;
    }
    
    public void setCantidadEstimada(Integer cantidadEstimada) {
        this.cantidadEstimada = cantidadEstimada;
    }
}