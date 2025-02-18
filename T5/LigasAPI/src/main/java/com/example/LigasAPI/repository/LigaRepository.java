package com.example.LigasAPI.repository;

import com.example.LigasAPI.model.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// metodos que van contra base de datos
// tantos metodos "adicionales" como necesitemos
// los metodos por "defecto" me los de el JPA repository
// persist
// save
// merge
// list
// get -> id
public interface LigaRepository extends JpaRepository<Liga, Integer> {
}
