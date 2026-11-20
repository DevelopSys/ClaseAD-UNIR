import controller.School;
import database.SchemaDB;
import model.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // UI
        School school = new School();
        try (Scanner scanner = new Scanner(System.in)) {

            int opcion = 0;
            do {
                System.out.println("1: Añadir usuario");
                System.out.println("2: Borrar usuario (por nombre)");
                System.out.println("3: Modificar usuario (por profile)");
                System.out.println("4: Buscar usuario (por correo)");
                System.out.println("5: Lista usuarios ");
                System.out.println("6: Importar desde JSON");
                System.out.println("7: Salir");
                System.out.println("Indica que quires hacer");
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Indica el nombre");
                        String name = scanner.next();
                        System.out.println("Indica el apellido");
                        String last = scanner.next();
                        System.out.println("Indica el mail");
                        String mail = scanner.next();
                        System.out.println("Indica la edad");
                        int age = scanner.nextInt();
                        long profile = 1;
                        Student student = new Student(name, last, mail, age, profile);
                        boolean error = false;
                        do {
                            try {
                                school.insertStudent(student);
                                error = false;
                            } catch (SQLException e) {
                                System.out.println("Error en la query, clave duplicada");
                                System.out.println("Por favor indica un nuevo correo");
                                mail = scanner.next();
                                student.setMail(mail);
                                error = true;
                            }
                        } while (error);
                        System.out.println("Estudiante insertado correctamente");
                    }
                    case 2 -> {
                        System.out.println("Indica el nombre que quieres borrar");
                        String name = scanner.next();
                        System.out.println("El numero de elementos borrados es de " + school.deleteStudent(name));
                    }
                    case 3 -> {
                        System.out.println("Que perfil quieres actualizar");
                        int profile = scanner.nextInt();
                        System.out.println("Actualizados: " + school.updateUsers(profile));
                    }
                    case 4 -> {
                        System.out.println("Indica el correo que quieres buscar");
                        String mail = scanner.next();
                        Student find = school.getUserByMail(mail);
                        if (find != null) {
                            find.showData();
                        } else {
                            System.out.println("No hay datos del usuario, mail no reconocido");
                        }
                    }
                    case 5 -> {
                        // school.getAllStudents().forEach(Student::showData);
                        for (Student item : school.getAllStudents()) {
                            item.showData();
                        }
                    }
                    case 6 -> {
                        System.out.println("Cuantos usuarios quieres importar");
                        int userNumber = scanner.nextInt();
                        school.importUsers(userNumber);
                    }
                }
            } while (opcion != 7);

        } catch (InputMismatchException e) {
            System.out.println("Error de IO");
        }

    }
}
