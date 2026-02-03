package com.example.tienda.repository;

import com.example.tienda.model.Categoria;
import com.example.tienda.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
