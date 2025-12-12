import dao.TrabajadorDAO;
import model.Direccion;
import model.Trabajador;

public class MainDAO {

    public static void main(String[] args) {
        TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

        /*trabajadorDAO.insertarUsuario(new Trabajador("MAria", "Lopez", 20, "maria1@gmail.com",
                30000, new Direccion("Madrid", "Madrid", "C/Madrid"), "comentario",
                false));*/

        // trabajadorDAO.eliminarUsuario(5);
        trabajadorDAO.actualizarUsuario(7,60000);
    }
}
