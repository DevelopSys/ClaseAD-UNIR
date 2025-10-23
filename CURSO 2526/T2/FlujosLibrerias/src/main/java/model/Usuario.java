package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {
    @XmlAttribute
    private String dni;
    @XmlAttribute
    private String nombre;
    private String apellido;
    // @XmlElement(name = "direccion")
    // private Direccion direccion;

    public void mostrarDatos(){
        System.out.println("dni = " + dni);
        System.out.println("nombre = " + nombre);
        System.out.println("apellido = " + apellido);
    }
}
