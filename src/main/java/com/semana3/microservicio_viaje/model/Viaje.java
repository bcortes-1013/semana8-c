package com.semana3.microservicio_viaje.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="viaje")

public class Viaje{
    @Id
    @Min(value = 1, message = "El ID debe ser mayor a 0")
    @Positive(message = "El ID debe ser un número positivo")
    private Long id;

    @NotNull(message = "La fecha de viaje no puede estar vacía")
    @FutureOrPresent(message = "No puede ser anterior al día actual")
    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate fechaViaje;

    @NotNull(message = "El nombre de cliente no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre de cliente no puede ser mayor a 100 carácteres")
    private String cliente;

    @NotNull(message = "La categoría no puede estar vacía")
    @Size(min = 1, max = 20, message = "La categoría no puede ser mayor a 20 caracteres")
    private String categoria;

    @NotNull(message = "El nombre de mascota no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre de mascota no puede ser mayor a 100 caracteres")
    private String mascota;

    @NotNull(message = "El nombre del conductor no puede estar vacío")
    @Size(min = 1, max = 100, message = "El conductor no puede ser mayor 100 caracteres")
    private String conductor;

    @NotNull(message = "El tipo de pago no puede estar vacío")
    @Size(min = 1, max = 20, message = "El tipo de pago no puede ser mayor a 20 caracteres")
    private String tipoPago;

    @NotNull(message = "El total no puede estar vacío")
    @Min(value = 1, message = "El total debe ser mayor a 0")
    @Max(value = 100000, message = "El total no puede ser mayor a 100 mil")
    private Integer total;
}