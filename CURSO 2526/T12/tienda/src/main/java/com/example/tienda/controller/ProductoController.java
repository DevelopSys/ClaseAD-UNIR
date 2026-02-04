package com.example.tienda.controller;

import com.example.tienda.model.Producto;
import com.example.tienda.services.ProductoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.BiFunction;

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

    public void funcionesLamda() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        //    private long id;
        //    private String nombre;
        //    private int precio;
        // param -> cuerpo -> Lamda que ya estan hechas
        // item -> cuerpo
        /*listaProductos.forEach(item->{
            // sumatorio
            // sout
        });*/
        boolean caro = listaProductos.stream().anyMatch(item -> item.getPrecio() > 10);
        List<Producto> caros = listaProductos.stream().filter(item -> item.getPrecio() > 10).toList();
        int total = listaProductos.stream().mapToInt(Producto::getPrecio).sum();

        BiFunction<Integer, Integer, Integer> funcionBI = (p1, p2) -> p1 + p2;
        funcionBI.apply(1, 7);

        realizarOperacion((p1, p2) -> p1 + p2,1,3);
        realizarOperacion((p1, p2) -> p1 * p2,1,3);
        realizarOperacion((p1, p2) -> p1 / p2,1,3);
        realizarOperacion((p1, p2) -> p1 - p2,1,3);

    }

    public void realizarOperacion(BiFunction<Integer, Integer, Integer> funcion, int op1, int op2) {
        funcion.apply(op1,op2);
    }

}
