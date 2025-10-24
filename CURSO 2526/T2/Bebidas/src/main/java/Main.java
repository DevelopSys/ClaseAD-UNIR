import controller.DrinkController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DrinkController controller = new DrinkController();
        int opcion = 0;
        do{
            System.out.println("1. Buscar por nombre");
            System.out.println("2. Bucar por letra");
            System.out.println("3. Sacar aleatorio");
            System.out.println("4. Salir");
            System.out.println("Que quieres hacer");
            opcion = scanner.nextInt();
            switch (opcion){
                case 1->{
                    System.out.println("Busqueda por nombre");
                    System.out.println("Por que nombre quieres buscar");
                    String nombre = scanner.next();
                    //controller.consultarNombre(nombre.toLowerCase());
                    controller.consultarLetra(nombre.toLowerCase(),"s");
                }
                case 2->{
                    System.out.println("Busqueda por letra");
                    System.out.println("Por que letra quieres hacer la busqueda");
                    String letra = scanner.next();
                    // controller.consultarLetra(letra);
                    controller.consultarLetra(letra.toLowerCase(),"f");
                }
                case 3->{
                    System.out.println("Busqueda aleatoria");
                    controller.obtenerAleatorio();
                }
                case 4->{
                    System.out.println("Saliendo");
                }
            }
        } while (opcion!=4);

    }
}
