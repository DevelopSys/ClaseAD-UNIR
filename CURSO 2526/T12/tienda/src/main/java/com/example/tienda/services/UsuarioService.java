package com.example.tienda.services;

import com.example.tienda.model.Usuario;
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


    public List<Usuario> getAllUsers(){
        // SELECT * FROM usuarios
        return repository.findAll();
    }

    public Optional<Usuario> getUserId(long id){
        return repository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario){
        // analizo si el usuario esta en base de datos retorno null
        // si no esta en base de datos lo inserto
        return repository.save(usuario);
    }

}
