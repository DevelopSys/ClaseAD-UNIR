package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tareas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String titulo;


    private String descripcion;


    @Enumerated(EnumType.STRING)
    private PrioridadTarea prioridad;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estado;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    private LocalDate fechaLimite;

    private Integer horasEstimadas;

    private Integer horasReales;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    @JsonBackReference
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleadoAsignado;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
