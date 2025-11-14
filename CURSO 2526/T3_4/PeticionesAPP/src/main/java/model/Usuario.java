package model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {

    @XmlAttribute
    private int id;
    private String nombre, mail;
    private int telefono;
    @XmlAttribute
    private int idPerfil;

    public Usuario(String nombre, String mail, int telefono, int idPerfil) {
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.idPerfil = idPerfil;
    }

    public void mostrarDatos(){
        System.out.println("nombre = " + nombre);
        System.out.println("mail = " + mail);
        System.out.println("telefono = " + telefono);
        System.out.println("idPerfil = " + idPerfil);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", mail='" + mail + '\'' +
                ", telefono=" + telefono +
                ", idPerfil=" + idPerfil +
                '}';
    }
}
