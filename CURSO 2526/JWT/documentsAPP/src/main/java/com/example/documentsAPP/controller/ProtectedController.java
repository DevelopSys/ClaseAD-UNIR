package com.example.documentsAPP.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/protected")
public class ProtectedController {
    @GetMapping("/hello")
    public String hello(Authentication auth) {
        return "Hola, " + auth.getName() + "! Acceso autorizado.";
    }
}
