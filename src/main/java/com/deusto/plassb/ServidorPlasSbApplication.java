package com.deusto.plassb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Servidor PlasSB - Prototipo 2
 * 
 * REST API para gestión de capacidades de planta de reciclaje.
 * 
 * Características:
 * - Puerto: 8081
 * - Base de datos: H2 en memoria
 * - Swagger UI: http://localhost:8081/swagger-ui.html
 * - H2 Console: http://localhost:8081/h2-console
 * 
 * API REST según especificación PDF Prototipo 2:
 * - GET /api/v1/plassb/capacidad?fecha=YYYY-MM-DD
 * - POST /api/v1/plassb/notificacion
 * - GET /api/v1/plassb/estado
 * - PUT /api/v1/plassb/capacidad
 * 
 * @author Equipo DS-03
 * @version Prototipo 2
 */
@SpringBootApplication
public class ServidorPlasSbApplication {

	public static void main(String[] args) {
		System.out.println("═══════════════════════════════════════════════════════════");
		System.out.println("   SERVIDOR PLASSB - PROTOTIPO 2");
		System.out.println("   Sistema de Gestión de Reciclaje de Plásticos");
		System.out.println("   Equipo DS-03 - Universidad de Deusto");
		System.out.println("═══════════════════════════════════════════════════════════");
		
		SpringApplication.run(ServidorPlasSbApplication.class, args);
		
		System.out.println("\n✅ Servidor PlasSB iniciado correctamente");
		System.out.println("📡 Puerto: 8081");
		System.out.println("💾 Base de datos: H2 (en memoria)");
		System.out.println("📚 Swagger UI: http://localhost:8081/swagger-ui.html");
		System.out.println("🗄️  H2 Console: http://localhost:8081/h2-console");
		System.out.println("   JDBC URL: jdbc:h2:mem:plassbdb");
		System.out.println("   User: sa");
		System.out.println("   Password: (dejar vacío)\n");
	}
}