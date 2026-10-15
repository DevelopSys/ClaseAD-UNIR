import controller.XMLController;
import model.Usuario;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        XMLController xmlController = new XMLController();

        try {
            xmlController.escrituraXML("src/main/java/resources/usuarios.xml");
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }

}
