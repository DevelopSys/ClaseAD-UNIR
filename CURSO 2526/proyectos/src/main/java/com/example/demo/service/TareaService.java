package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.ProyectoRepository;
import com.example.demo.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Page<Tarea> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fechaCreacion").descending());
        return tareaRepository.findAll(pageable);
    }

    public Tarea getById(Long id) {
        return tareaRepository.findById(id).orElse(null);
    }

    public Tarea create(Tarea tarea, Long proyectoId) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId).orElse(null);
        if (proyecto != null) {
            tarea.setProyecto(proyecto);
            return tareaRepository.save(tarea);
        }
        return null;
    }

    public Tarea update(Long id, Tarea datos) {
        Tarea existente = getById(id);
        if (existente == null) return null;

        existente.setTitulo(datos.getTitulo());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrioridad(datos.getPrioridad());
        existente.setEstado(datos.getEstado());
        existente.setFechaLimite(datos.getFechaLimite());
        existente.setHorasEstimadas(datos.getHorasEstimadas());
        existente.setHorasReales(datos.getHorasReales());

        if (existente.getHorasReales() != null && existente.getHorasReales() < 0) {
            throw new RuntimeException("Horas reales no pueden ser negativas");
        }

        return tareaRepository.save(existente);
    }

    public Tarea cambiarEstado(Long id, EstadoTarea estado, Integer horasReales) {
        Tarea tarea = getById(id);
        if (tarea == null) return null;

        tarea.setEstado(estado);
        if (estado == EstadoTarea.COMPLETADA && tarea.getHorasReales() == null) {
            tarea.setHorasReales(horasReales != null ? horasReales : tarea.getHorasEstimadas());
        }

        return tareaRepository.save(tarea);
    }

    public boolean delete(Long id) {
        Tarea tarea = getById(id);
        if (tarea != null) {
            tareaRepository.delete(tarea);
            return true;
        }
        return false;
    }

    public List<Tarea> findByPrioridad(PrioridadTarea prioridad) {
        return tareaRepository.findByPrioridad(prioridad);
    }

    public List<Tarea> findByProyecto(Long proyectoId) {
        return tareaRepository.findByProyectoId(proyectoId);
    }

    public List<Tarea> findByEmpleado(Long empleadoId) {
        return tareaRepository.findByEmpleadoAsignadoId(empleadoId);
    }

    public List<Tarea> tareasVencidas() {
        return tareaRepository.findByFechaLimiteBeforeAndEstadoNot(LocalDate.now(), EstadoTarea.COMPLETADA);
    }

    public Tarea asignarEmpleado(Long tareaId, Long empleadoId) {
        Tarea tarea = getById(tareaId);
        Empleado empleado = empleadoRepository.findById(empleadoId).orElse(null);

        if (tarea != null && empleado != null && empleado.isActivo()) {
            tarea.setEmpleadoAsignado(empleado);
            return tareaRepository.save(tarea);
        }
        return null;
    }
}
