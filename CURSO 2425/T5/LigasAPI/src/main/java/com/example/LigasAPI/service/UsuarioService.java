package com.example.LigasAPI.service;

import com.example.LigasAPI.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> getAllUsuarios();
    List<Usuario> getUsuarioCorreo(String correo);
    Usuario getLogin(String correo, String pass);

    Usuario insertarUsuario(Usuario usuario);

}
