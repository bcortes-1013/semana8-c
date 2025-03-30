package com.semana3.microservicio_viaje.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Viaje{
    private Long id;
    private LocalDate fechaViaje;
    private String cliente;
    private String categoria;
    private String nombreMascota;
    private String conductor;
    private String tipoPago;
    private Integer total;
}