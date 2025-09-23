package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

    @BsonProperty("nombre")
    private String nombre;
    @BsonProperty("apellido")
    private String apellido;
    @BsonProperty("correo")
    private String correo;
    @BsonProperty("edad")
    private int edad;

    public void mostrarDatos(){
        System.out.println("nombre = " + nombre);
        System.out.println("apellido = " + apellido);
        System.out.println("correo = " + correo);
        System.out.println("edad = " + edad);
    }
    /*
    {nombre: "Borja, apellido: "Martin",correo:"borja@gmail.com", edad:40}
     */
}
