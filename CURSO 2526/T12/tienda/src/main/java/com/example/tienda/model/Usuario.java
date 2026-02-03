package com.example.tienda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
    @JsonIgnore

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="carrito",
            joinColumns={@JoinColumn(name="id_usuario")},
            inverseJoinColumns={@JoinColumn(name="id_producto")})
    private Set<Producto> listaProductos;

    public Usuario(String nombre, String correo, String pass) {
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
    }

    public  void addProductos(Producto producto){
        listaProductos.add(producto);
        producto.getListaUsuario().add(this);

    }
}
