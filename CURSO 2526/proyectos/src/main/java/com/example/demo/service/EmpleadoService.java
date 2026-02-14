package com.example.demo.service;

import com.example.demo.model.Empleado;
import com.example.demo.model.Proyecto;
import com.example.demo.model.Tarea;
import com.example.demo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> getAll() {
        return empleadoRepository.findAll();
    }

    public Empleado getById(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    public Empleado create(Empleado empleado) {
        if (empleadoRepository.existsByEmail(empleado.getEmail())) {
            throw new RuntimeException("Email ya existe");
        }
        return empleadoRepository.save(empleado);
    }

    public Empleado update(Long id, Empleado datos) {
        Empleado existente = getById(id);
        if (existente == null) return null;

        existente.setNombre(datos.getNombre());
        existente.setApellido(datos.getApellido());
        existente.setEmail(datos.getEmail());
        existente.setCargo(datos.getCargo());
        existente.setDepartamento(datos.getDepartamento());
        existente.setSalario(datos.getSalario());
        existente.setFechaContratacion(datos.getFechaContratacion());
        existente.setActivo(datos.isActivo());

        return empleadoRepository.save(existente);
    }

    public boolean delete(Long id) {
        Empleado empleado = getById(id);
        if (empleado != null) {
            empleado.setActivo(false);
            empleadoRepository.save(empleado);
            return true;
        }
        return false;
    }

    public List<Empleado> findByDepartamento(String departamento) {
        return empleadoRepository.findByDepartamentoIgnoreCase(departamento);
    }

    public List<Tarea> getTareas(Long id) {
        Empleado empleado = getById(id);
        if (empleado == null) return null;
        return empleado.getTareas().stream().toList();
    }

    public List<Proyecto> getProyectos(Long id) {
        Empleado empleado = getById(id);
        if (empleado == null) return null;
        return empleado.getProyectos().stream().toList();
    }

    public int calcularCargaTrabajo(Long id) {
        Empleado empleado = getById(id);
        if (empleado == null) return 0;
        return empleado.getTareas().stream()
                .mapToInt(t -> t.getHorasEstimadas() == null ? 0 : t.getHorasEstimadas())
                .sum();
    }
}
