package com.semana3.microservicio_viaje.service;

import com.semana3.microservicio_viaje.model.Viaje;
import com.semana3.microservicio_viaje.exception.ViajeNotFoundException;
import com.semana3.microservicio_viaje.repository.ViajeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ViajeService{
    @Autowired
    private ViajeRepository repository;

    public ViajeService(ViajeRepository repository) {
        this.repository = repository;
    }

    public List<Viaje> obtenerViajes(){
        log.debug("Servicio: obtenerViajes()");

        return repository.findAll(Sort.by("id").ascending());
    } 

    public Viaje obtenerViajePorId(Long id){
        log.debug("Servicio: obtenerViajePorId({})", id);

        return repository.findById(id).orElseThrow(() -> new ViajeNotFoundException("No se ha encontrado el ID " + id));
    }

    public Viaje guardar(Viaje viaje){
        log.debug("Servicio: guardar({})", viaje.getId());

        if(repository.existsById(viaje.getId())){
            log.error("Ya existe un viaje con ID {}", viaje.getId());

            throw new IllegalArgumentException("Ya existe un viaje con el ID " + viaje.getId());
        }

        return repository.save(viaje);

    }

    public Viaje actualizar(long id, Viaje viajeActualizado){
        log.debug("Servicio: actualizar({}, {})", id, viajeActualizado.getId());

        Viaje existente = repository.findById(id).orElseThrow(() -> new ViajeNotFoundException("No se ha encontrado el viaje ID " + id));

        existente.setCliente(viajeActualizado.getCliente());
        existente.setCategoria(viajeActualizado.getCategoria());
        existente.setMascota(viajeActualizado.getMascota());
        existente.setConductor(viajeActualizado.getConductor());
        existente.setTipoPago(viajeActualizado.getTipoPago());
        existente.setTotal(viajeActualizado.getTotal());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        log.debug("Servicio: eliminar({})", id);

        repository.findById(id).orElseThrow(() -> new ViajeNotFoundException("Se ha eliminado el viaje ID " + id));

        repository.deleteById(id);
    }
}
