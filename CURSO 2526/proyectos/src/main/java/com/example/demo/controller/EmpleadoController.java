package com.example.demo.controller;
import com.example.demo.model.Empleado;
import com.example.demo.model.Proyecto;
import com.example.demo.model.Tarea;
import com.example.demo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // GET /api/empleados
    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> response = new HashMap<>();
        List<Empleado> lista = empleadoService.getAll();

        response.put("code", 1);
        response.put("message", "Lista de empleados obtenida");
        response.put("total", lista.size());
        response.put("data", lista);
        return ResponseEntity.ok(response);
    }

    // GET /api/empleados/1
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Empleado empleado = empleadoService.getById(id);

        if (empleado != null) {
            response.put("code", 1);
            response.put("message", "Empleado obtenido correctamente");
            response.put("total", 1);
            response.put("data", empleado);
        } else {
            response.put("code", 0);
            response.put("message", "Empleado no encontrado");
            response.put("total", 0);
            response.put("data", null);
        }
        return ResponseEntity.ok(response);
    }

    // POST /api/empleados
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Empleado empleado) {
        Map<String, Object> response = new HashMap<>();
        try {
            Empleado creado = empleadoService.create(empleado);
            response.put("code", 1);
            response.put("message", "Empleado creado correctamente");
            response.put("total", 1);
            response.put("data", creado);
        } catch (Exception e) {
            response.put("code", 0);
            response.put("message", "Error: " + e.getMessage());
            response.put("data", null);
        }
        return ResponseEntity.ok(response);
    }

    // PUT /api/empleados/1
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Empleado empleado) {
        Map<String, Object> response = new HashMap<>();
        Empleado actualizado = empleadoService.update(id, empleado);

        if (actualizado != null) {
            response.put("code", 1);
            response.put("message", "Empleado actualizado correctamente");
            response.put("data", actualizado);
        } else {
            response.put("code", 0);
            response.put("message", "Error actualizando empleado");
            response.put("data", null);
        }
        return ResponseEntity.ok(response);
    }

    // DELETE /api/empleados/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        boolean eliminado = empleadoService.delete(id);

        if (eliminado) {
            response.put("code", 1);
            response.put("message", "Empleado marcado como inactivo");
        } else {
            response.put("code", 0);
            response.put("message", "Error eliminando empleado");
        }
        return ResponseEntity.ok(response);
    }

    // GET /api/empleados/departamento/IT
    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<?> getByDepartamento(@PathVariable String departamento) {
        Map<String, Object> response = new HashMap<>();
        List<Empleado> lista = empleadoService.findByDepartamento(departamento);

        response.put("code", 1);
        response.put("message", "Empleados por departamento");
        response.put("total", lista.size());
        response.put("data", lista);
        return ResponseEntity.ok(response);
    }

    // GET /api/empleados/1/tareas
    @GetMapping("/{id}/tareas")
    public ResponseEntity<?> getTareas(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        List<Tarea> tareas = empleadoService.getTareas(id);

        if (tareas != null) {
            response.put("code", 1);
            response.put("message", "Tareas del empleado obtenidas");
            response.put("total", tareas.size());
            response.put("data", tareas);
        } else {
            response.put("code", 0);
            response.put("message", "Empleado no encontrado");
            response.put("data", null);
        }
        return ResponseEntity.ok(response);
    }

    // GET /api/empleados/1/proyectos
    @GetMapping("/{id}/proyectos")
    public ResponseEntity<?> getProyectos(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        List<Proyecto> proyectos = empleadoService.getProyectos(id);

        if (proyectos != null) {
            response.put("code", 1);
            response.put("message", "Proyectos del empleado obtenidos");
            response.put("total", proyectos.size());
            response.put("data", proyectos);
        } else {
            response.put("code", 0);
            response.put("message", "Empleado no encontrado");
            response.put("data", null);
        }
        return ResponseEntity.ok(response);
    }

    // GET /api/empleados/1/carga-trabajo
    @GetMapping("/{id}/carga-trabajo")
    public ResponseEntity<?> getCargaTrabajo(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        int carga = empleadoService.calcularCargaTrabajo(id);

        response.put("code", 1);
        response.put("message", "Carga de trabajo calculada");
        response.put("data", carga);
        response.put("unidad", "horas estimadas");
        return ResponseEntity.ok(response);
    }
}
