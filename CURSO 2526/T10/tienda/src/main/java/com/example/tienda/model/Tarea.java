package com.example.tienda.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Tarea {

    private String nombre;
    private boolean completado;
    private LocalDate fecha;
    @Column
    private Estado estado;

    public Tarea(String nombre, boolean completado, LocalDate fecha, Estado estado) {
        this.nombre = nombre;
        this.completado = completado;
        this.fecha = fecha;
        this.estado = estado;
    }

    public void mostrarDatos(){
        // CANCELADO
        System.out.println("estado = " + estado.name());
    }


}
