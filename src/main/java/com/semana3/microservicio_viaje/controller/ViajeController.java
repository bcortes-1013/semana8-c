package com.semana3.microservicio_viaje.controller;

import com.semana3.microservicio_viaje.hateoas.ViajeModelAssembler;
import com.semana3.microservicio_viaje.model.Viaje;
import com.semana3.microservicio_viaje.service.ViajeService;
import com.semana3.microservicio_viaje.model.ResponseWrapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/viajes")
public class ViajeController {

    private final ViajeService viajeService;
    private final ViajeModelAssembler assembler;


        public ViajeController(ViajeService viajeService, ViajeModelAssembler assembler){
        this.viajeService = viajeService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Viaje>>> obtenerViajes() {
        log.info("GET /viajes - Obteniendo todos los viajes");

        List<Viaje> viajes = viajeService.obtenerViajes();

        if (viajes.isEmpty()) {
            log.warn("No hay películas registradas actualmente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CollectionModel.empty());
        }

        List<EntityModel<Viaje>> modelos = viajes.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
                linkTo(methodOn(ViajeController.class).obtenerViajes()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Viaje>> obtenerViajePorId(@PathVariable Long id) {
        log.info("GET /viajes/{} - Buscando viaje por ID", id);

        Viaje viaje = viajeService.obtenerViajePorId(id);

        return ResponseEntity.ok(assembler.toModel(viaje));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Viaje>> crearViaje(@Valid @RequestBody Viaje viaje) {
        log.info("POST /viajes - Creando veterinario: {}", viaje.getId());

        Viaje creado = viajeService.guardar(viaje);

        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Viaje>> actualizarViaje(@PathVariable Long id, @Valid @RequestBody Viaje viajeActualizado) {
        log.info("PUT /viajes/{} - Actualizando viaje", id);
        
        Viaje actualizada = viajeService.actualizar(id, viajeActualizado);

        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarViaje(@PathVariable Long id) {
        log.warn("DELETE /viajes/{} - Eliminando viaje", id);

        viajeService.eliminar(id);

        return ResponseEntity.ok(new ResponseWrapper<>("Viaje eliminado con éxito", 0, null));
    }
}
