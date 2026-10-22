package model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {

    private String nombre;
    private String apellido;
    private int nota;
    @XmlAttribute
    private String dni;
    @XmlElement(name = "direccion")
    private Direccion direccion;

    public void mostrarDatos(){
        System.out.println("nombre = " + nombre);
        System.out.println("apellido = " + apellido);
        System.out.println("nota = " + nota);
        System.out.println("dni = " + dni);
        System.out.println("direccion localidad= " + direccion.getLocalidad());
        System.out.println("direccion provincia= " + direccion.getProvincia());
    }

}
