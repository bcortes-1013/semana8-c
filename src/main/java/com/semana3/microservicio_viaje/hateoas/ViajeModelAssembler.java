package com.semana3.microservicio_viaje.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.semana3.microservicio_viaje.controller.ViajeController;
import com.semana3.microservicio_viaje.model.Viaje;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ViajeModelAssembler implements RepresentationModelAssembler<Viaje, EntityModel<Viaje>> {

    @Override
    public EntityModel<Viaje> toModel(Viaje viaje) {
        return EntityModel.of(
                viaje, // Entidad original

                // Enlace al detalle de la película (GET /viajes/{id})
                linkTo(methodOn(ViajeController.class)
                        .obtenerViajePorId(viaje.getId()))
                        .withSelfRel(),

                // Enlace para eliminar (DELETE /veterinario/{id})
                linkTo(methodOn(ViajeController.class)
                        .eliminarViaje(viaje.getId()))
                        .withRel("delete"),

                // Enlace para actualizar (PUT /veterinario/{id}) – cuerpo ignorado aquí
                linkTo(methodOn(ViajeController.class)
                        .actualizarViaje(viaje.getId(), null))
                        .withRel("update"),

                // Enlace para ver todas las películas (GET /veterinario)
                linkTo(methodOn(ViajeController.class)
                        .obtenerViajes())
                        .withRel("all"));
    }
}
