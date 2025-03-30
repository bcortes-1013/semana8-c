package com.semana3.microservicio_viaje.service;

import com.semana3.microservicio_viaje.model.Viaje;
import com.semana3.microservicio_viaje.exception.NoEncontradoException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViajeService {
    private final List<Viaje> viajes = new ArrayList<>();

    public ViajeService(){
        viajes.add(new Viaje(1L, LocalDate.of(2022, 1, 15), "Gabriel Lopez", "Bronce", "Estrella", "Armando", "debito", 11300));
        viajes.add(new Viaje(2L, LocalDate.of(2023, 8, 21), "Francisca Segovia", "Plata", "Sam", "Ernesto", "credito", 17100));
        viajes.add(new Viaje(3L, LocalDate.of(2024, 2, 27), "Javiera Herrera", "Oro", "Pino", "Priscilla", "saldo", 9200));
        viajes.add(new Viaje(4L, LocalDate.of(2025, 7, 13), "Ana González", "Platino", "Lobo", "Diego", "debito", 13900));
        viajes.add(new Viaje(5L, LocalDate.of(2022, 3, 19), "Roberto Fontova", "Bronce", "Nieve", "Camila", "credito", 6400));
        viajes.add(new Viaje(6L, LocalDate.of(2023, 6, 25), "Laura Fernández", "Plata", "Negro", "Pedro", "saldo", 19500));
        viajes.add(new Viaje(7L, LocalDate.of(2024, 4, 11), "Miguel Claro", "Oro", "Altair", "Laura", "debito", 7100));
        viajes.add(new Viaje(8L, LocalDate.of(2025, 5, 17), "Ana Velez", "Platino", "Domo", "Francisco", "credito", 14800));
    }

    public List<Viaje> obtenerViajes(){
        return viajes;
    }

    public Viaje obtenerViajePorId(Long id){
        return viajes.stream()
        .filter(p -> p.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new NoEncontradoException("No se encontró el ID Viaje " + id));
    }

    public List<Viaje> obtenerViajesPorMontoMinimo(int montoMinimo) {
        if (montoMinimo < 0) {
            throw new NoEncontradoException("El valor debe ser mayor a 0");
        }

        List<Viaje> resultado = viajes.stream()
            .filter(v -> v.getTotal() > montoMinimo)
            .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new NoEncontradoException("No hay viajes con monto mayor a " + montoMinimo);
        }

        return resultado;
    }
}
