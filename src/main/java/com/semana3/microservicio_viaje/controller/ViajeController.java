package com.semana3.microservicio_viaje.controller;

import com.semana3.microservicio_viaje.model.Viaje;
import com.semana3.microservicio_viaje.service.ViajeService;
import com.semana3.microservicio_viaje.model.ResponseWrapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService){
        this.viajeService = viajeService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerViajes() {
        List<Viaje> viajes = viajeService.obtenerViajes();

        if (viajes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay viajes registrados");
        }

        return ResponseEntity.ok(new ResponseWrapper<>("OK", viajes.size(), viajes));
    }

    @GetMapping("/{id}")
    public Viaje obtenerViajesPorId(@PathVariable("id") Long id) {
        return viajeService.obtenerViajePorId(id);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Viaje>> crearViaje(@Valid @RequestBody Viaje viaje) {

        Viaje creado = viajeService.guardarViaje(viaje);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>("Viaje creado exitosamente", 1, List.of(creado)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Viaje>> actualizarViaje(@PathVariable Long id, @Valid @RequestBody Viaje viajeActualizado) {
        
        Viaje actualizado = viajeService.actualizarViaje(id, viajeActualizado);
        return ResponseEntity.ok(new ResponseWrapper<>("Viaje actualizado exitosamente", 1, List.of(actualizado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarViaje(@PathVariable Long id) {
        viajeService.eliminarViaje(id);

        return ResponseEntity.ok(new ResponseWrapper<>("Viaje eliminado exitosamente", 0, null));
    }
    
}
