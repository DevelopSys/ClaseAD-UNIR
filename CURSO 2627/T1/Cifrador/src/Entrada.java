import controller.FileController;

import java.util.Scanner;

public class Entrada {

    public static void main(String[] args) {
        FileController controller = new FileController();
        Scanner lector = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("Indica que quieres hacer: ");
            System.out.println("1. Cifrar un mensaje");
            System.out.println("2. Descifrar un mensaje");
            System.out.println("3. Descifrar codigos ASCI");
            System.out.println("4. Salir");
            opcion = lector.nextInt();
            switch (opcion) {
                case 1 -> {
                    lector = new Scanner(System.in);
                    System.out.println("Procedemos a cifrar el mensaje");
                    System.out.println("Indica que quieres cifrar");
                    String mensaje = lector.nextLine();
                    System.out.println("Con que fase lo vas a cifrar");
                    int fase = lector.nextInt();
                    controller.cifrarMensaje(mensaje, fase);
                }

                case 2 -> {
                    System.out.println("Procedemos a descifrar el mensaje");
                    System.out.println("Indica cual es la fase de descifrado");
                    int fase = lector.nextInt();
                    controller.descifrarMensaje(fase);
                    System.out.println();
                }

                case 3 ->{
                    controller.descifrarASCI();
                    System.out.println();
                }

                case 4 -> System.out.println("Saliendo");
            }
        } while (opcion != 4);
        lector.close();
    }
}
