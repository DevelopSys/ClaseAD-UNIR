package com.example.tienda.repository;

import com.example.tienda.model.Producto;
import com.example.tienda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
