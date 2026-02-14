package com.example.tienda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private int precio;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_categoria")
    // @JsonIgnore
    // TODO gestion de la relacion
    @JsonBackReference("producto-categoria")
    private Categoria categoria;

    @JsonManagedReference("usuario-producto")
    @ManyToMany(mappedBy = "listaProductos")
    public Set<Usuario> listaUsuario;

    public Producto(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
}
