package com.example.users.controller;
import com.example.users.model.Usuario;
import com.example.users.security.JwtUtil;
import com.example.users.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        usuarioService.registrar(usuario);
        return ResponseEntity.ok(Map.of("mensaje", "Usuario registrado con éxito"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Optional<Usuario> user = usuarioService.buscarPorCorreo(usuario.getCorreo());

        if (user.isPresent() && passwordEncoder.matches(usuario.getPassword(), user.get().getPassword())) {
            String token = jwtUtil.generarToken(usuario.getCorreo());
            //return ResponseEntity.ok(Map.of("token", token));
            return ResponseEntity.ok(Map.of("code", 1));
        } else {
            return ResponseEntity.ok(Map.of("code", 2));
        }

        // return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
    }
}
