package com.example.LigasAPI.repository;

import com.example.LigasAPI.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> getByCorreo(String correo);

    List<Usuario> getByPass(String pass);

    @Query("FROM Usuario u WHERE u.correo = :correo AND u.pass=:pass")
    Usuario getByCorreoPass(@Param("correo") String correo, @Param("pass") String pass);
}
