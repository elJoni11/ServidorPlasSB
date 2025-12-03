package com.deusto.plassb.dto;

/**
 * DTO para la respuesta de notificación.
 * 
 * @author Equipo DS-03
 */
public class NotificacionResponseDTO {
    
    private String mensaje;
    private String asignacionID;
    private Boolean confirmacion;
    
    public NotificacionResponseDTO() {
    }
    
    public NotificacionResponseDTO(String mensaje, String asignacionID, Boolean confirmacion) {
        this.mensaje = mensaje;
        this.asignacionID = asignacionID;
        this.confirmacion = confirmacion;
    }
    
    // Getters y Setters
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getAsignacionID() {
        return asignacionID;
    }
    
    public void setAsignacionID(String asignacionID) {
        this.asignacionID = asignacionID;
    }
    
    public Boolean getConfirmacion() {
        return confirmacion;
    }
    
    public void setConfirmacion(Boolean confirmacion) {
        this.confirmacion = confirmacion;
    }
}