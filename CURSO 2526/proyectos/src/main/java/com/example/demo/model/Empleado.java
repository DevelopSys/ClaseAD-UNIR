package com.example.demo.model;

package com.example.gestion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "empleados", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String nombre;


    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    private String cargo;


    private String departamento;


    private BigDecimal salario;

    private LocalDate fechaContratacion;

    private boolean activo = true;

    @OneToMany(mappedBy = "empleadoAsignado")
    @JsonIgnore
    private Set<Tarea> tareas = new HashSet<>();

    @ManyToMany(mappedBy = "empleados")
    @JsonIgnore
    private Set<Proyecto> proyectos = new HashSet<>();
}
