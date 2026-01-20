package com.example.tienda.controller;

import com.example.tienda.model.Usuario;
import com.example.tienda.services.UsuarioService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// localhost:8080/api/usuarios/endpoinr
@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    // necesito el acceso al servicio
    @Autowired // inyeccion automatica
    private UsuarioService usuarioService;
    // CRUD
    // getAll
    // localhost:8080/api/usuarios/getAll -> GET
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        // HashMap = clave,valor
        List<Usuario> listaResultante = usuarioService.getAllUsers();
        Map<String, Object> response = new HashMap<>();
        response.put("code",1);
        response.put("message", "obtenida la lista de usuarios");
        response.put("total", listaResultante.size());
        response.put("data", listaResultante);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // localhost:8080/api/usuarios/1
    @GetMapping("/findId/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        // servicio
        Map<String, Object> response = new HashMap<>();
        Optional<Usuario> usuario = usuarioService.getUserId(id);
        if (usuario.isPresent()){
            response.put("code",1);
            response.put("message", "usuario obtenido correctamente");
            response.put("total", 1);
            response.put("data", usuario.get());
        } else {
            response.put("code",1);
            response.put("message", "el usuario no esta en la base de datos");
            response.put("total", 0);
            response.put("data", null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody Usuario usuario){
        // servicio
        Map<String, Object> response = new HashMap<>();
        Usuario usuarioCreado = usuarioService.createUsuario(usuario);
        response.put("code",1);
        response.put("message", "usuario creado correctamente");
        response.put("total", 1);
        response.put("data", usuarioCreado);
        return ResponseEntity.status(HttpStatus.OK).body(response);
        // intentar retornar elementos diferentes dependiendo
        // de si el usuario ya tiene un mail que exista
    }


}
