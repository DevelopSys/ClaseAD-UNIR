package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coche {
    private int id;
    private String marca, modelo;
    private int cv, precio;
    public Coche(String marca, String modelo, int cv, int precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.cv = cv;
        this.precio = precio;
    }

    public void mostrarDatos(){
        System.out.println("id = " + id);
        System.out.println("marca = " + marca);
        System.out.println("modelo = " + modelo);
        System.out.println("cv = " + cv);
        System.out.println("precio = " + precio);
    }
}
