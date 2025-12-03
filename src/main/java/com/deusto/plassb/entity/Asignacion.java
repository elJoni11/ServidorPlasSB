package com.deusto.plassb.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad JPA que representa una asignación de contenedores
 * notificada por el Servidor Ecoembes.
 * 
 * @author Equipo DS-03
 */
@Entity
@Table(name = "asignaciones")
public class Asignacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String asignacionID;
    
    @ElementCollection
    @CollectionTable(name = "asignacion_contenedores", 
                     joinColumns = @JoinColumn(name = "asignacion_id"))
    @Column(name = "contenedor_id")
    private List<String> contenedoresIDs;
    
    @Column(nullable = false)
    private LocalDate fecha;
    
    @Column(nullable = false)
    private Integer cantidadEstimada; // kg
    
    @Column(nullable = false)
    private LocalDateTime fechaRecepcion;
    
    @Column(nullable = false)
    private Boolean confirmacion = true;
    
    // Constructores
    public Asignacion() {
        this.fechaRecepcion = LocalDateTime.now();
    }
    
    public Asignacion(String asignacionID, List<String> contenedoresIDs, 
                      LocalDate fecha, Integer cantidadEstimada) {
        this.asignacionID = asignacionID;
        this.contenedoresIDs = contenedoresIDs;
        this.fecha = fecha;
        this.cantidadEstimada = cantidadEstimada;
        this.fechaRecepcion = LocalDateTime.now();
        this.confirmacion = true;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public LocalDateTime getFechaRecepcion() {
        return fechaRecepcion;
    }
    
    public void setFechaRecepcion(LocalDateTime fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
    
    public Boolean getConfirmacion() {
        return confirmacion;
    }
    
    public void setConfirmacion(Boolean confirmacion) {
        this.confirmacion = confirmacion;
    }
}