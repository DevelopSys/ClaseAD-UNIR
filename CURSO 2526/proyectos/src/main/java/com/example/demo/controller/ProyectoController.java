package com.example.demo.controller;


import com.example.demo.model.EstadoProyecto;
import com.example.demo.model.Proyecto;
import com.example.demo.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    // GET /api/proyectos?page=0&size=10
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> response = new HashMap<>();
        Page<Proyecto> lista = proyectoService.getAll(page, size);

        response.put("code", 1);
        response.put("message", "Lista de proyectos obtenida");
        response.put("totalElements", lista.getTotalElements());
        response.put("totalPages", lista.getTotalPages());
        response.put("data", lista.getContent());
        return ResponseEntity.ok(response);
    }

    // GET /api/proyectos/1
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Proyecto proyecto = proyectoService.getById(id);

        if (proyecto != null) {
            response.put("code", 1);
            response.put("message", "Proyecto obtenido correctamente");
            response.put("total", 1);
            response.put("data", proyecto);
        } else {
            response.put("code", 0);
            response.put("message", "Proyecto no encontrado");
            response.put("total", 0);
            response.put("data", null);
        }
        return ResponseEntity.ok(response);
    }

    // POST /api/proyectos
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Proyecto proyecto) {
        Map<String, Object> response = new HashMap<>();
        Proyecto creado = proyectoService.create(proyecto);

        response.put("code", 1);
        response.put("message", "Proyecto creado correctamente");
        response.put("total", 1);
        response.put("data", creado);
        return ResponseEntity.ok(response);
    }

    // PUT /api/proyectos/1
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        Map<String, Object> response = new HashMap<>();
        Proyecto actualizado = proyectoService.update(id, proyecto);

        if (actualizado != null) {
            response.put("code", 1);
            response.put("message", "Proyecto actualizado correctamente");
            response.put("data", actualizado);
        } else {
            response.put("code", 0);
            response.put("message", "Error actualizando proyecto");
            response.put("data", null);
        }
        return ResponseEntity.ok(response);
    }

    // DELETE /api/proyectos/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        boolean eliminado = proyectoService.delete(id);

        if (eliminado) {
            response.put("code", 1);
            response.put("message", "Proyecto eliminado correctamente");
        } else {
            response.put("code", 0);
            response.put("message", "Error eliminando proyecto");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> getByEstado(@PathVariable EstadoProyecto estado) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 1);
        response.put("message", "Proyectos por estado");
        response.put("data", proyectoService.findByEstado(estado));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cliente/{cliente}")
    public ResponseEntity<?> getByCliente(@PathVariable String cliente) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 1);
        response.put("message", "Proyectos por cliente");
        response.put("data", proyectoService.findByCliente(cliente));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/estadisticas")
    public ResponseEntity<?> estadisticas(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> stats = proyectoService.getEstadisticas(id);

        if (stats != null) {
            response.put("code", 1);
            response.put("data", stats);
        } else {
            response.put("code", 0);
            response.put("data", null);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/empleados/{empleadoId}")
    public ResponseEntity<?> asignarEmpleado(@PathVariable Long id, @PathVariable Long empleadoId) {
        Map<String, Object> response = new HashMap<>();
        Proyecto proyecto = proyectoService.asignarEmpleado(id, empleadoId);

        if (proyecto != null) {
            response.put("code", 1);
            response.put("message", "Empleado asignado correctamente");
            response.put("data", proyecto);
        } else {
            response.put("code", 0);
            response.put("message", "Error asignando empleado");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/empleados/{empleadoId}")
    public ResponseEntity<?> removerEmpleado(@PathVariable Long id, @PathVariable Long empleadoId) {
        Map<String, Object> response = new HashMap<>();
        Proyecto proyecto = proyectoService.removerEmpleado(id, empleadoId);

        if (proyecto != null) {
            response.put("code", 1);
            response.put("message", "Empleado removido correctamente");
            response.put("data", proyecto);
        } else {
            response.put("code", 0);
            response.put("message", "Error removiendo empleado");
        }
        return ResponseEntity.ok(response);
    }
}
