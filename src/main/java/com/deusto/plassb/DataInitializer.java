package com.deusto.plassb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.deusto.plassb.entity.Capacidad;
import com.deusto.plassb.repository.CapacidadRepository;

import java.time.LocalDate;

/**
 * Inicializador de datos para la base de datos H2.
 * Se ejecuta automáticamente al arrancar.
 * 
 * @author Equipo DS-03
 */
@Component
public class DataInitializer implements CommandLineRunner {
    
    private final CapacidadRepository capacidadRepository;
    
    public DataInitializer(CapacidadRepository capacidadRepository) {
        this.capacidadRepository = capacidadRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n📦 Inicializando base de datos con datos de prueba...");
        
        LocalDate hoy = LocalDate.now();
        
        // Inicializar 30 días de capacidades
        for (int i = 0; i < 30; i++) {
            LocalDate fecha = hoy.plusDays(i);
            
            // Capacidad total variable (400-600 kg)
            int capacidadTotal = 400 + (i * 7) % 200;
            
            // Capacidad disponible inicialmente igual a total
            int capacidadDisponible = capacidadTotal;
            
            // Para algunos días, simular que ya hay asignaciones
            if (i % 5 == 0 && i > 0) {
                capacidadDisponible = capacidadTotal - 100; // Ya se usaron 100 kg
            }
            
            Capacidad capacidad = new Capacidad(fecha, capacidadTotal, capacidadDisponible);
            capacidadRepository.save(capacidad);
        }
        
        long totalCapacidades = capacidadRepository.count();
        
        System.out.println("✅ Base de datos PlasSB inicializada:");
        System.out.println("   - " + totalCapacidades + " días de capacidad");
        System.out.println("   - Rango: " + hoy + " a " + hoy.plusDays(29));
        System.out.println("   - Capacidad por día: 400-600 kg\n");
    }
}