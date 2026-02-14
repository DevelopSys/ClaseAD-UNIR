package com.example.demo.repository;
import com.example.demo.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findByDepartamentoIgnoreCase(String departamento);

    Optional<Empleado> findByEmail(String email);

    boolean existsByEmail(String email);
}
