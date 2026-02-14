package com.example.tienda.services;

import com.example.tienda.model.Producto;
import com.example.tienda.model.Usuario;
import com.example.tienda.repository.ProductoRepository;
import com.example.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    // metodos con logica que llaman al repositorio
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ProductoRepository productoRepository;


    public List<Usuario> getAllUsers(){
        // SELECT * FROM usuarios
        return repository.findAll();
    }

    public Optional<Usuario> getUserId(long id){
        return repository.findById(id);
    }

    public Usuario getUsuarioMail(String correo){

        return repository.findByCorreo(correo);
    }

    public Usuario createUsuario(Usuario usuario){
        // analizo si el usuario esta en base de datos retorno null
        // si no esta en base de datos lo inserto
        return repository.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario){
        // si llego a este punto, no tengo dudas que el usuario esta en la base de datos
        return repository.save(usuario);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
        // primero busco por id
            // borro
        // retorno el encontrado
        // returno un null
    }

    // quiero agregar un producto al carrito del usuario
    // id usuario id producto
    // idUsuario -> busco por id -> repositorio usuario
    // idProducto -> busco por id -> repositorio producto
    // gestion de error
    // Usuario.getListaProductos.addProcuto(Producto)
    // save -> actualizas

    public Usuario  comprarProducto(long idUsuario, long idProducto){
        Usuario usuarioEncontrado = repository.findById(idUsuario).get();
        Producto productoEncontrado = productoRepository.findById(idProducto).get();
        usuarioEncontrado.getListaProductos().add(productoEncontrado);
        return usuarioEncontrado;
    }



    // quiero obtener el total de productos que tiene el usuario
    // coste total
    // id -> usuario
    // usuario -> productos -> map Producto::getPrecio -> total del carrito

}
