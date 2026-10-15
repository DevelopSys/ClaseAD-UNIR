package controller;

import model.Usuario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLController {

    public void escrituraXML(String path) throws ParserConfigurationException, TransformerException {
        List<Usuario> lista = new ArrayList<>();
        lista.add(new Usuario("Nombre1", "Apellido1", "Correo1", "DNI1", 123));
        lista.add(new Usuario("Nombre2", "Apellido2", "Correo2", "DNI2", 123));
        lista.add(new Usuario("Nombre3", "Apellido3", "Correo3", "DNI3", 123));
        lista.add(new Usuario("Nombre4", "Apellido4", "Correo4", "DNI4", 123));
        lista.add(new Usuario("Nombre5", "Apellido5", "Correo5", "DNI5", 123));
        lista.add(new Usuario("Nombre6", "Apellido6", "Correo6", "DNI6", 123));
        File file = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 1. Creas un documento
        Document document = builder.newDocument();
        // 2. Creas el root ( si quieres pones atrobutos
        Element root = document.createElement("usuarios");
        root.setAttribute("version", "1");
        root.setAttribute("idioma", "es");
        root.setAttribute("numero", String.valueOf(lista.size()));
        document.appendChild(root);
        // 3. Creas un nodo usuario -> y le pones valores y atributos
        // TODO
        for (Usuario usuario : lista) {
            Element user = document.createElement("usuario");
            user.setAttribute("dni", usuario.getDni());
            user.setAttribute("telefono", String.valueOf(usuario.getTelefono()));

            Element name = document.createElement("nombre");
            name.setTextContent(usuario.getNombre());
            Element surname = document.createElement("apellido");
            surname.setTextContent(usuario.getApellido());
            Element mail = document.createElement("correo");
            mail.setTextContent(usuario.getCorreo());

            user.appendChild(name);
            user.appendChild(surname);
            user.appendChild(mail);
            // 4. El nodo usuario lo pones dentro del root
            root.appendChild(user);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(document);
        StreamResult finalStream = new StreamResult(file);

        transformer.transform(source,finalStream);

    }
    public void lectorXML(String path) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        // List<Usuario> lista = new ArrayList<>();
        File file = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        // root
        Element root = document.getDocumentElement();
        NodeList nodosUser = root.getElementsByTagName("usuario");
        for (int i = 0; i < nodosUser.getLength(); i++) {
            Element item = (Element) nodosUser.item(i);
            item.getAttribute("dni");
            item.getAttribute("telefono");
            Element itemName = (Element) item.getElementsByTagName("nombre").item(0);
            itemName.getTextContent();
            Element itemApellido = (Element) item.getElementsByTagName("apellido").item(0);
            itemApellido.getTextContent();
            Element itemCorreo = (Element) item.getElementsByTagName("correo").item(0);
            itemCorreo.getTextContent();
        }
        /*root.getAttribute("idioma");
        root.getAttribute("version");
        root.getAttribute("numero");*/


        /*
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(document);
        StreamResult finalStream = new StreamResult(file);

        transformer.transform(source,finalStream);*/

    }
}
