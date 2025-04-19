package com.semana3.microservicio_viaje.service;

import com.semana3.microservicio_viaje.model.Viaje;
import com.semana3.microservicio_viaje.exception.ViajeNotFoundException;
import com.semana3.microservicio_viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ViajeService{
    @Autowired
    private ViajeRepository repository;

    public List<Viaje> obtenerViajes(){
        return repository.findAll();
    } 

    public Viaje obtenerViajePorId(Long id){
        return repository.findById(id).orElseThrow(() -> new ViajeNotFoundException("No se ha encontrado el ID " + id));
    }

    public Viaje guardarViaje(Viaje viaje){
        if(repository.existsById(viaje.getId())){
            throw new IllegalArgumentException("Ya existe un viaje con el ID " + viaje.getId());
        }

        return repository.save(viaje);

    }

    public Viaje actualizarViaje(Long id, Viaje viajeActualizado){
        Viaje existente = repository.findById(id).orElseThrow(() -> new ViajeNotFoundException("No se ha encontrado el viaje"));

        existente.setFechaViaje(viajeActualizado.getFechaViaje());
        existente.setCliente(viajeActualizado.getCliente());
        existente.setCategoria(viajeActualizado.getCategoria());
        existente.setMascota(viajeActualizado.getMascota());
        existente.setConductor(viajeActualizado.getConductor());
        existente.setTipoPago(viajeActualizado.getTipoPago());
        existente.setTotal(viajeActualizado.getTotal());

        return repository.save(existente);
    }

    public void eliminarViaje(Long id) {
        Viaje existente = repository.findById(id).orElseThrow(() -> new ViajeNotFoundException("Se ha eliminado el viaje ID " + id));

        repository.delete(existente);
    }
}
