package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tienda implements Serializable {

    private static final long serialVersionUID = 1234L;
    private String nombre;
    private int id;
    private int empleados;
    // ATRIBUTO

    public Tienda(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public void mostrarDatos() {
        System.out.println("serialVersionUID = " + serialVersionUID);
        System.out.println("nombre = " + nombre);
        System.out.println("id = " + id);
        System.out.println("empleados = " + empleados);
    }
}
