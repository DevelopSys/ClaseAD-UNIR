import controller.PeticionesController;
import model.Usuario;

public class MainPeticiones {

    public static void main(String[] args) {
        // VIEW -> la interaccion con el usuario
        PeticionesController peticionesController = new PeticionesController();
        peticionesController.insertarUsuario(new Usuario("BorjaM","borjam@gmail.com",123,1));
    }
}
