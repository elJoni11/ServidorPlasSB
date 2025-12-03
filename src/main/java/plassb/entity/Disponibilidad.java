package plassb.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "disponibilidad")
public class Disponibilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private int capacidadToneladas;

    public Disponibilidad() {} // Constructor vacío obligatorio

    public Disponibilidad(LocalDate fecha, int capacidadToneladas) {
        this.fecha = fecha;
        this.capacidadToneladas = capacidadToneladas;
    }

    // Getters
    public LocalDate getFecha() { return fecha; }
    public int getCapacidadToneladas() { return capacidadToneladas; }
}
