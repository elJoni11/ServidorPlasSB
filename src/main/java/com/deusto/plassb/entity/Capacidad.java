package com.deusto.plassb.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad JPA que representa la capacidad diaria disponible
 * de la planta PlasSB para procesar residuos plásticos.
 * 
 * Almacenada en base de datos H2 en memoria.
 * 
 * @author Equipo DS-03
 */
@Entity
@Table(name = "capacidades")
public class Capacidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private LocalDate fecha;
    
    @Column(nullable = false)
    private Integer capacidadTotal; // en kg
    
    @Column(nullable = false)
    private Integer capacidadDisponible; // en kg
    
    @Column(nullable = false)
    private String unidad = "kg";
    
    // Constructores
    public Capacidad() {
    }
    
    public Capacidad(LocalDate fecha, Integer capacidadTotal, Integer capacidadDisponible) {
        this.fecha = fecha;
        this.capacidadTotal = capacidadTotal;
        this.capacidadDisponible = capacidadDisponible;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    @Override
    public String toString() {
        return "Capacidad{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", capacidadTotal=" + capacidadTotal +
                ", capacidadDisponible=" + capacidadDisponible +
                ", unidad='" + unidad + '\'' +
                '}';
    }
}