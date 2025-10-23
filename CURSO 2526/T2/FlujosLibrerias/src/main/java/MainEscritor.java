import model.Listado;
import model.Usuario;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class MainEscritor {

    public static void main(String[] args) {

        Listado listado = new Listado();
        listado.getListado().add(new Usuario("123A", "Borja1", "Martin1"));
        listado.getListado().add(new Usuario("223A", "Borja2", "Martin2"));
        listado.getListado().add(new Usuario("323A", "Borja3", "Martin3"));
        listado.getListado().add(new Usuario("423A", "Borja4", "Martin4"));
        listado.getListado().add(new Usuario("523A", "Borja5", "Martin5"));

        try {
            JAXBContext context = JAXBContext.newInstance(Listado.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(listado, new File("usuarios2.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }


    }
}
