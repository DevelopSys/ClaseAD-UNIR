package com.example.tienda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO cuidado con el hash y el toString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String nombre;
    @Column
    private String correo;
    @Column
    private String pass;
    // realaciones -> OnetoOne OnetoMany ManyyoMany

    public Usuario(String nombre, String correo, String pass) {
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
    }
}
