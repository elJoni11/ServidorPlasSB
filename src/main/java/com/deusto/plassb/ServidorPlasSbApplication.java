package com.deusto.plassb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 Características:
 * - Puerto: 8081
 * - Base de datos: H2 en memoria
 * - Swagger UI: http://localhost:8081/swagger-ui.html
 * - H2 Console: http://localhost:8081/h2-console
 **/

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.deusto.plassb",
		"com.deusto.plassb.controller"})

public class ServidorPlasSbApplication {

	public static void main(String[] args) {
		System.out.println("═══════════════════════════════════════════════════════════");
		System.out.println("   		SERVIDOR PLASSB");
		System.out.println("Sistema de Gestión de Reciclaje de Envases");
		System.out.println("  Equipo DS-03 - Universidad de Deusto");
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