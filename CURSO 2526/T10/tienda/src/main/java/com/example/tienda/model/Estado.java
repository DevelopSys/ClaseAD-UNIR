package com.example.tienda.model;

public enum Estado {
    PLANIFICADO,
    EN_PROGRESO("tarea que aun no ha sido completada"),
    COMPLETADO,
    CANCELADO("tarea que ha sido suspendida");
    private String descipcion;

    Estado(String descipcion) {
        this.descipcion = descipcion;
    }

    Estado() {
    }

    public String getDescipcion() {
        return descipcion;
    }
}
