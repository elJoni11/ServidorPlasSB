package com.deusto.plassb.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deusto.plassb.entity.Asignacion;
import com.deusto.plassb.entity.Capacidad;
import com.deusto.plassb.repository.AsignacionRepository;
import com.deusto.plassb.repository.CapacidadRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Servicio con lógica de negocio para PlasSB.
 * 
 * @author Equipo DS-03
 */
@Service
public class PlasSBService {
    
    private final CapacidadRepository capacidadRepository;
    private final AsignacionRepository asignacionRepository;
    
    public PlasSBService(CapacidadRepository capacidadRepository, 
                         AsignacionRepository asignacionRepository) {
        this.capacidadRepository = capacidadRepository;
        this.asignacionRepository = asignacionRepository;
    }
    
    /**
     * Consulta la capacidad disponible para una fecha.
     */
    public Optional<Capacidad> consultarCapacidad(LocalDate fecha) {
        System.out.println("📊 Consultando capacidad para fecha: " + fecha);
        Optional<Capacidad> capacidad = capacidadRepository.findByFecha(fecha);
        
        if (capacidad.isPresent()) {
            System.out.println("✅ Capacidad encontrada: " + 
                             capacidad.get().getCapacidadDisponible() + " kg");
        } else {
            System.out.println("⚠️  No hay planificación para la fecha: " + fecha);
        }
        
        return capacidad;
    }
    
    /**
     * Registra una notificación de asignación.
     * Valida capacidad y la reduce automáticamente.
     */
    @Transactional
    public boolean registrarNotificacion(String asignacionID, List<String> contenedoresIDs,
                                        LocalDate fecha, Integer cantidadEstimada) {
        System.out.println("📨 Recibiendo notificación de asignación:");
        System.out.println("   ID: " + asignacionID);
        System.out.println("   Contenedores: " + contenedoresIDs);
        System.out.println("   Fecha: " + fecha);
        System.out.println("   Cantidad: " + cantidadEstimada + " kg");
        
        // Buscar capacidad para esa fecha
        Optional<Capacidad> capacidadOpt = capacidadRepository.findByFecha(fecha);
        
        if (capacidadOpt.isEmpty()) {
            System.out.println("⚠️  No hay planificación para la fecha: " + fecha);
            return false;
        }
        
        Capacidad capacidad = capacidadOpt.get();
        
        // Validar que hay capacidad suficiente
        if (capacidad.getCapacidadDisponible() < cantidadEstimada) {
            System.out.println("⚠️  Capacidad insuficiente:");
            System.out.println("   Disponible: " + capacidad.getCapacidadDisponible() + " kg");
            System.out.println("   Solicitado: " + cantidadEstimada + " kg");
            return false;
        }
        
        // Crear y guardar la asignación
        Asignacion asignacion = new Asignacion(
            asignacionID,
            contenedoresIDs,
            fecha,
            cantidadEstimada
        );
        asignacionRepository.save(asignacion);
        
        // Actualizar capacidad disponible
        int nuevaCapacidad = capacidad.getCapacidadDisponible() - cantidadEstimada;
        capacidad.setCapacidadDisponible(nuevaCapacidad);
        capacidadRepository.save(capacidad);
        
        System.out.println("✅ Asignación registrada exitosamente");
        System.out.println("   Nueva capacidad disponible: " + nuevaCapacidad + " kg");
        
        return true;
    }
    
    /**
     * Actualiza la capacidad disponible para una fecha.
     */
    @Transactional
    public void actualizarCapacidad(LocalDate fecha, Integer nuevaCapacidad) {
        Optional<Capacidad> capacidadOpt = capacidadRepository.findByFecha(fecha);
        
        if (capacidadOpt.isPresent()) {
            Capacidad capacidad = capacidadOpt.get();
            capacidad.setCapacidadDisponible(nuevaCapacidad);
            capacidadRepository.save(capacidad);
            System.out.println("✅ Capacidad actualizada para " + fecha + ": " + nuevaCapacidad + " kg");
        } else {
            // Crear nueva capacidad
            Capacidad nuevaCap = new Capacidad(fecha, nuevaCapacidad, nuevaCapacidad);
            capacidadRepository.save(nuevaCap);
            System.out.println("✅ Nueva capacidad creada para " + fecha + ": " + nuevaCapacidad + " kg");
        }
    }
}