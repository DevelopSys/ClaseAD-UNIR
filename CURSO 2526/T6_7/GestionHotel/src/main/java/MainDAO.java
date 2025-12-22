import dao.ClienteDAO;
import dao.PerfilesDAO;
import dao.TrabajadorDAO;
import model.Cliente;
import model.Direccion;
import model.Perfil;
import model.Trabajador;

public class MainDAO {

    public static void main(String[] args) {
        TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
        PerfilesDAO perfilesDAO = new PerfilesDAO();
        ClienteDAO clienteDAO = new ClienteDAO();


        trabajadorDAO.verReservasRealizadas(9);
        // clienteDAO.addCliente(new Cliente("MariaCliente"));
        // clienteDAO.addReserva(2,9);
        // perfilesDAO.getPerfil(1);
        /*trabajadorDAO.insertarUsuario(new Trabajador("MAria", "Lopez", 20, "maria1@gmail.com",
                30000, new Direccion("Madrid", "Madrid", "C/Madrid"), "comentario",
                false));*/

        // trabajadorDAO.eliminarUsuario(5);
        // trabajadorDAO.actualizarUsuario(7,60000);
        // trabajadorDAO.seleccionarTrabajador("maria@gmail.com");
        // trabajadorDAO.seleccionarTodos();
        // Trabajador trabajador = trabajadorDAO.getTrabajador(7);
        // System.out.println("El perfil del trabajador es "+trabajador.getNombre());
        // trabajadorDAO.getTrabajador(8);
        // perfilesDAO.getTipoPerfil(2);


    }
}
