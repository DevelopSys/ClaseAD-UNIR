package com.example.demo.repository;

import com.example.demo.model.EstadoTarea;
import com.example.demo.model.PrioridadTarea;
import com.example.demo.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByPrioridad(PrioridadTarea prioridad);

    List<Tarea> findByProyectoId(Long proyectoId);

    List<Tarea> findByEmpleadoAsignadoId(Long empleadoId);

    List<Tarea> findByFechaLimiteBeforeAndEstadoNot(LocalDate fecha, EstadoTarea estado);
}

