package com.semana3.microservicio_viaje;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.data.domain.Sort;

import com.semana3.microservicio_viaje.model.Viaje;
import com.semana3.microservicio_viaje.repository.ViajeRepository;
import com.semana3.microservicio_viaje.service.ViajeService;

public class ViajeServiceTest {

    private ViajeRepository viajeRepository;
    private ViajeService viajeService;

    @BeforeEach
    public void setUp() {
        viajeRepository = mock(ViajeRepository.class);
        viajeService = new ViajeService(viajeRepository);
    }

    @Test
    public void testObtenerTodas() {
        Viaje p1 = new Viaje(1L, "Juan Gomez", "oro", "Mono", "Sabrina", "debito", 10000);
        Viaje p2 = new Viaje(2L, "Sigrid Vargas", "platino", "Negrito", "Ernesto", "credito", 20000);

        when(viajeRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(p1, p2));

        List<Viaje> resultado = viajeService.obtenerViajes();

        assertEquals(2, resultado.size());
        assertEquals("debito", resultado.get(0).getTipoPago());
    }

    @Test
    public void testObtenerPorId_existente() {
        Viaje viaje = new Viaje(3L, "Javier Mena", "bronce", "Max", "Sebastian", "saldo", 9000);

        when(viajeRepository.findById(3L)).thenReturn(Optional.of(viaje));

        Viaje resultado = viajeService.obtenerViajePorId(3L);

        assertEquals("bronce", resultado.getCategoria());
        assertEquals(9000, resultado.getTotal());
    }

    @Test
    public void testGuardarViaje() {
        Viaje viaje = new Viaje(4L, "Alvaro Rodriguez", "plata", "Alma", "Juan", "debito", 15000);

        when(viajeRepository.existsById(4L)).thenReturn(false);

        when(viajeRepository.save(viaje)).thenReturn(viaje);

        Viaje resultado = viajeService.guardar(viaje);

        assertNotNull(resultado);
        assertEquals("Alvaro Rodriguez", resultado.getCliente());
        verify(viajeRepository, times(1)).save(viaje);
    }

    @Test
    public void testGuardarVeterinarioConIdExistente() {
        Viaje viaje = new Viaje(5L, "Ana Saez", "oro", "Sami", "Roberto", "credito", 8500);

        when(viajeRepository.existsById(5L)).thenReturn(true);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            viajeService.guardar(viaje);
        });

        assertEquals("Ya existe un viaje con el ID 5", thrown.getMessage());
    }

    @Test
    public void testActualizarViaje() {
        Viaje original = new Viaje(6L, "Camila Pardo", "platino", "Lupi", "Pedro", "saldo", 4500);
        Viaje actualizada = new Viaje(7L, "Francisca Zamora", "bronce", "Thor", "Esteban", "debito", 5000);

        when(viajeRepository.findById(1L)).thenReturn(Optional.of(original));
        when(viajeRepository.save(any(Viaje.class))).thenReturn(actualizada);

        Viaje resultado = viajeService.actualizar(1L, actualizada);

        assertEquals("bronce", resultado.getCategoria());
        assertEquals("Thor", resultado.getMascota());
        assertEquals("Esteban", resultado.getConductor());
    }

    @Test
    public void testEliminarViaje() {
        Viaje viaje = new Viaje(8L, "Sergio Vargas", "plata", "Mulan", "Carolina", "credito", 7500);
    
        when(viajeRepository.findById(8L)).thenReturn(Optional.of(viaje));
    
        viajeService.eliminar(8L);
    
        verify(viajeRepository, times(1)).deleteById(8L);
    }
}
