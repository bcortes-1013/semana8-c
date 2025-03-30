package com.semana3.microservicio_viaje.controller;

import com.semana3.microservicio_viaje.model.Viaje;
import com.semana3.microservicio_viaje.service.ViajeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService){
        this.viajeService = viajeService;
    }

    @GetMapping
    public List<Viaje> obtenerViajes() {
        return viajeService.obtenerViajes();
    }

    @GetMapping("/{id}")
    public Viaje obtenerViajesPorId(@PathVariable("id") Long id) {
        return viajeService.obtenerViajePorId(id);
    }

    @GetMapping("/totalMin/{total}")
    public List<Viaje> obtenerViajesPorMontoMinimo(@PathVariable("total") Integer total) {
        return viajeService.obtenerViajesPorMontoMinimo(total);
    }
    
}
