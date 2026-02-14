package com.example.demo.service;


import com.example.demo.model.Empleado;
import com.example.demo.model.EstadoProyecto;
import com.example.demo.model.EstadoTarea;
import com.example.demo.model.Proyecto;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.ProyectoRepository;
import com.example.demo.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private TareaRepository tareaRepository;

    public Page<Proyecto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return proyectoRepository.findAll(pageable);
    }

    public Proyecto getById(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    public Proyecto create(Proyecto proyecto) {
        validarFechasProyecto(proyecto);
        return proyectoRepository.save(proyecto);
    }

    public Proyecto update(Long id, Proyecto datos) {
        Proyecto existente = getById(id);
        if (existente == null) return null;

        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setFechaInicio(datos.getFechaInicio());
        existente.setFechaFin(datos.getFechaFin());
        existente.setEstado(datos.getEstado());
        existente.setPresupuesto(datos.getPresupuesto());
        existente.setCliente(datos.getCliente());

        validarFechasProyecto(existente);
        return proyectoRepository.save(existente);
    }

    public boolean delete(Long id) {
        Proyecto proyecto = getById(id);
        if (proyecto == null) return false;

        boolean tienePendientes = proyecto.getTareas().stream()
                .anyMatch(t -> t.getEstado() != EstadoTarea.COMPLETADA);
        if (tienePendientes) return false;

        proyectoRepository.delete(proyecto);
        return true;
    }

    public List<Proyecto> findByEstado(EstadoProyecto estado) {
        return proyectoRepository.findByEstado(estado);
    }

    public List<Proyecto> findByCliente(String cliente) {
        return proyectoRepository.findByClienteContainingIgnoreCase(cliente);
    }

    public Map<String, Object> getEstadisticas(Long id) {
        Proyecto proyecto = getById(id);
        if (proyecto == null) return null;

        long total = proyecto.getTareas().size();
        long completadas = proyecto.getTareas().stream()
                .filter(t -> t.getEstado() == EstadoTarea.COMPLETADA).count();
        long pendientes = proyecto.getTareas().stream()
                .filter(t -> t.getEstado() == EstadoTarea.PENDIENTE ||
                        t.getEstado() == EstadoTarea.EN_PROGRESO).count();

        double porcentaje = total == 0 ? 0 : (completadas * 100.0) / total;

        return Map.of(
                "totalTareas", total,
                "completadas", completadas,
                "pendientes", pendientes,
                "porcentajeCompletitud", porcentaje
        );
    }

    public Proyecto asignarEmpleado(Long proyectoId, Long empleadoId) {
        Proyecto proyecto = getById(proyectoId);
        Empleado empleado = empleadoRepository.findById(empleadoId).orElse(null);

        if (proyecto != null && empleado != null) {
            proyecto.getEmpleados().add(empleado);
            empleado.getProyectos().add(proyecto);
            empleadoRepository.save(empleado);
            return proyectoRepository.save(proyecto);
        }
        return null;
    }

    public Proyecto removerEmpleado(Long proyectoId, Long empleadoId) {
        Proyecto proyecto = getById(proyectoId);
        Empleado empleado = empleadoRepository.findById(empleadoId).orElse(null);

        if (proyecto != null && empleado != null) {
            proyecto.getEmpleados().remove(empleado);
            empleado.getProyectos().remove(proyecto);
            empleadoRepository.save(empleado);
            return proyectoRepository.save(proyecto);
        }
        return null;
    }

    private void validarFechasProyecto(Proyecto proyecto) {
        if (proyecto.getFechaFin() != null &&
                proyecto.getFechaFin().isBefore(proyecto.getFechaInicio())) {
            throw new RuntimeException("Fecha fin anterior a fecha inicio");
        }
    }
}
