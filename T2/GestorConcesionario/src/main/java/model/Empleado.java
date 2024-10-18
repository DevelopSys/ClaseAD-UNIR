package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    private int id;
    private String nombre, apellido, correo;
    private int telefono;
    private Tipo tipo;

    public Empleado(String nombre, String apellido, String correo, int telefono, Tipo tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.tipo = tipo;
    }
}
