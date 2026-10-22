package controller;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.Agenda;
import model.Direccion;
import model.Usuario;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileController<T> {

    private String basePath = "src/main/java/files/";

    public void escritorXML(String path, T data) {
        // escribir en un XML datos
        Agenda agenda = new Agenda();

        agenda.getData().add(new Usuario("Alumno 1", "Apellido 1", 4, "1234A", new Direccion("Madrid", "Madrid")));
        agenda.getData().add(new Usuario("Alumno 2", "Apellido 2", 5, "2234A", new Direccion("Barcelona", "Madrid")));
        agenda.getData().add(new Usuario("Alumno 3", "Apellido 3", 6, "3234A", new Direccion("Bilbao", "Madrid")));
        agenda.getData().add(new Usuario("Alumno 4", "Apellido 4", 7, "4234A", new Direccion("Valencia", "Madrid")));
        agenda.getData().add(new Usuario("Alumno 5", "Apellido 5", 7, "5234A", new Direccion("Sevilla", "Madrid")));
        agenda.getData().add(new Usuario("Alumno 6", "Apellido 6", 9, "6234A", new Direccion("Zaragoza", "Madrid")));
        agenda.getData().add(new Usuario("Alumno 7", "Apellido 7", 10, "7234A", new Direccion("Malaga", "Madrid")));
        try {
            JAXBContext context = JAXBContext.newInstance(Agenda.class);
            // Java -> XML
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(agenda, new File(basePath + path));
        } catch (JAXBException e) {
            System.out.println("Error en la traduccion de XML");
            System.out.println(e.getMessage());
        }

    }

    public void lectorXML(String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(Agenda.class);
            Unmarshaller unMarshaller = context.createUnmarshaller();
            Agenda agenda = (Agenda) unMarshaller.unmarshal(new File(basePath + path));
            List aprobados = new ArrayList();
            /*for (Usuario item : agenda.getData()) {
                if (item.getNota() >= 5) {
                    aprobados.add(item)
                }
            }*/
            agenda.getData().forEach( Usuario::mostrarDatos );
            // List<Usuario> filtrados = agenda.getData().stream().filter(item-> item.getNota()>=5).toList();
        } catch (JAXBException e) {
            System.out.println("Error en la lectora");
            System.out.println(e.getMessage());
        }

    }
    public void transformarXML(String path){
        File fileXML = new File(basePath+path); // fichero de donde salen los datos
        File fileXSL = new File(basePath+"alumnos.xsl"); // fichero de donde salen las ordenes a transformar
        File fileHTML = new File(basePath+"alumnos.html"); // fichero de salida
        // todos los ficheros indicados existen en el sistema
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(fileXSL));
            StreamSource source = new StreamSource(fileXML);
            StreamResult result = new StreamResult(fileHTML);
            transformer.transform(source,result);
        } catch (TransformerConfigurationException e) {
            System.out.println("Error en la transformacion XSL");
        } catch (TransformerException e) {
            System.out.println("Ordenes XSL no comprensibles");
        }
    }
}
