package com.example.demo.repository;



import com.example.demo.model.EstadoProyecto;
import com.example.demo.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    List<Proyecto> findByEstado(EstadoProyecto estado);

    List<Proyecto> findByClienteContainingIgnoreCase(String cliente);
}
