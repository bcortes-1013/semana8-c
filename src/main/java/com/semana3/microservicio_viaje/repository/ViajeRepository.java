package com.semana3.microservicio_viaje.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semana3.microservicio_viaje.model.Viaje;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    
}
