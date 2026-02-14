package com.example.demo.controller;


import com.example.demo.model.PrioridadTarea;
import com.example.demo.model.Tarea;
import com.example.demo.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> response = new HashMap<>();
        Page<Tarea> lista = tareaService.getAll(page, size);

        response.put("code", 1);
        response.put("message", "Lista de tareas obtenida");
        response.put("totalElements", lista.getTotalElements());
        response.put("totalPages", lista.getTotalPages());
        response.put("data", lista.getContent());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Tarea tarea = tareaService.getById(id);

        if (tarea != null) {
            response.put("code", 1);
            response.put("message", "Tarea obtenida correctamente");
            response.put("data", tarea);
        } else {
            response.put("code", 0);
            response.put("message", "Tarea no encontrada");
            response.put("data", null);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Tarea tarea, @RequestParam Long proyectoId) {
        Map<String, Object> response = new HashMap<>();
        Tarea creada = tareaService.create(tarea, proyectoId);

        if (creada != null) {
            response.put("code", 1);
            response.put("message", "Tarea creada correctamente");
            response.put("data", creada);
        } else {
            response.put("code", 0);
            response.put("message", "Error creando tarea");
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Tarea tarea) {
        Map<String, Object> response = new HashMap<>();
        Tarea actualizada = tareaService.update(id, tarea);

        if (actualizada != null) {
            response.put("code", 1);
            response.put("message", "Tarea actualizada correctamente");
            response.put("data", actualizada);
        } else {
            response.put("code", 0);
            response.put("message", "Error actualizando tarea");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/prioridad/{prioridad}")
    public ResponseEntity<?> getByPrioridad(@PathVariable PrioridadTarea prioridad) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 1);
        response.put("data", tareaService.findByPrioridad(prioridad));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<?> getByProyecto(@PathVariable Long proyectoId) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 1);
        response.put("data", tareaService.findByProyecto(proyectoId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<?> getByEmpleado(@PathVariable Long empleadoId) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 1);
        response.put("data", tareaService.findByEmpleado(empleadoId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vencidas")
    public ResponseEntity<?> getVencidas() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 1);
        response.put("data", tareaService.tareasVencidas());
        return ResponseEntity.ok(response);
    }
}
