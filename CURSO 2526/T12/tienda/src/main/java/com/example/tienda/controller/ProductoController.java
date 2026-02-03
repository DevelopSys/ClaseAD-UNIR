package com.example.tienda.controller;

import com.example.tienda.model.Producto;
import com.example.tienda.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/productos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // localhost:8080/api/productos/getAll
    @GetMapping("getAll")
    public ResponseEntity<?> getAllProductos() {

        Map<String, Object> response = new HashMap<>();
        try {
            List<Producto> lista = productoService.getAll();
            response.put("code", 1);
            response.put("message", "obtenida la lista de productos");
            response.put("total", lista.size());
            response.put("data", lista);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("code", 2);
            response.put("message", "error en el proceso");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("getId/{id}")
    public ResponseEntity<?> getIdProductos(@PathVariable int id) {

        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Producto> producto = productoService.getId(id);
            response.put("code", 1);
            response.put("message", "obtenida la lista de productos");
            response.put("data", producto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("code", 2);
            response.put("message", "error en el proceso");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
