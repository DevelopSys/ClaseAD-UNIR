package com.example.users.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @GetMapping("/usuario")
    public Map<String, String> obtenerUsuario(@AuthenticationPrincipal User user) {
        return Map.of("correo", user.getUsername());
    }
}