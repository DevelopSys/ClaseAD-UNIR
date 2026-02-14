package com.example.tienda.controller;

import com.example.tienda.model.Estado;
import com.example.tienda.model.Producto;
import com.example.tienda.model.Tarea;
import com.example.tienda.model.Usuario;
import com.example.tienda.services.UsuarioService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// localhost:8080/api/usuarios/endpoinr
@RestController
@RequestMapping("api/usuarios")
// TODO configurar las CORS
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController {

    // necesito el acceso al servicio
    @Autowired // inyeccion automatica
    private UsuarioService usuarioService;
    // CRUD
    // getAll
    // localhost:8080/api/usuarios/getAll -> GET


    public void ejemploEnum(){
        Tarea tarea = new Tarea("TareaEjemplo",false,null, Estado.PLANIFICADO);
        tarea.getEstado().getDescipcion();

    }

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

    // localhost:8080/api/usuarios/findCorreo?correo=asdasdasdasdasd
    @GetMapping("/findCorreo")
    public ResponseEntity<?> getUserMail(@RequestParam String correo){
        // servicio -> repositorio
        Usuario usuarioEncontrado = usuarioService.getUsuarioMail(correo);
        Map<String, Object> response = new HashMap<>();
        if(usuarioEncontrado==null){
            response.put("code",1);
            response.put("message", "usuario no encontrado");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(response);
        } else {
            response.put("code",1);
            response.put("message", "usuario encontrado correctamente");
            response.put("data", usuarioEncontrado);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

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

    // localhost:8080/api/usuarios/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        // service -> repository
        Map<String, Object> response = new HashMap<>();
        try{
            Optional<Usuario> usuarioBusqueda = usuarioService.getUserId(id);
            // isPresent -> true
            // -> false
            Usuario usuarioEncontrado = usuarioBusqueda.get(); // estoy suponinedo que el usuario esta si o si
            usuarioEncontrado.setCorreo(usuario.getCorreo());
            usuarioEncontrado.setNombre(usuario.getNombre());
            usuarioEncontrado.setPass(usuario.getPass());
            Usuario usuarioActualizado =  usuarioService.updateUsuario(usuarioEncontrado);
            response.put("code",1);
            response.put("message", "usuario actualizado correctamente");
            response.put("data", usuarioActualizado);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException | DataIntegrityViolationException e){
            response.put("code",2);
            response.put("message", "error de ejecucion");
            response.put("cause", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    // http://localhost:8080/api/usuario/comprar/1?idProducto=1
    // RequestParam -> filtros http://miapi.com/api/endpoint?id=1&arg2=valor
    // PathVariable -> filtros http://miapi.com/api/endpoint/1/32 ->
    @PutMapping("comprar/{id}")
    public ResponseEntity<?> comprarProducto(@PathVariable int id, @RequestParam int idProducto){
        Map<String, Object> response = new HashMap<>();
        Usuario usuario = usuarioService.comprarProducto(id,idProducto);

        double total = usuario.getListaProductos().stream().mapToInt(Producto::getPrecio).sum();
        response.put("code",1);
        response.put("message", "producto agregado correctamente");
        response.put("data", usuario);
        response.put("size", usuario.getListaProductos());
        response.put("total",total);


        return ResponseEntity.status(HttpStatus.OK).body(response);
    }





}
