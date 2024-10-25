import controller.Concesionario;
import model.Empleado;
import model.Tipo;

public class Entrada {

    public static void main(String[] args) {

        // TIPO 1,EXT
        // TIPO 2,IND
        // TIPO 3,BEC
        /*DBConnecion dbConnecion = new DBConnecion();
        Connection connection = dbConnecion.getConnection();
        // trabajas con la conexion
        dbConnecion.closeConnecion();
        DBConnecion dbConnecion2 = new DBConnecion();
        Connection connection2 = dbConnecion2.getConnection();
        DBConnecion dbConnecion3 = new DBConnecion();
        Connection connection3 = dbConnecion3.getConnection();*/
        // concesionario.insertarTrabajador(new Empleado("Juan1","Gomez","juan@gmail.com",234, Tipo.BECARIO));
        // concesionario.insertarTrabajador(new Empleado("Juan2","Gomez","juan@gmail.com",234, Tipo.INDEFINIDO));
        // concesionario.insertarTrabajador(new Empleado("Juan3","Gomez","juan@gmail.com",234, Tipo.EXTERNO));
        // System.out.println("Filas afectadas "+concesionario.borrarUsuario(2));
        //concesionario.leerUsuarios(3);
        Concesionario concesionario = new Concesionario();
        concesionario.agregarCoche();
    }
}
