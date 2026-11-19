package controller;

import database.DBConnection;
import database.SchemaDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class School {

    // private Connection connection;
    private Statement statement;
    // private PreparedStatement preparedStatement;

    public void insertStudent(Student student) throws SQLException {
        // statement
        // 1-> connection
        // prepareStatement X
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?,?,?,?,?)", SchemaDB.TAB_STUDENTS,
                SchemaDB.COL_NAME, SchemaDB.COL_LAST, SchemaDB.COL_EMAIL, SchemaDB.COL_AGE, SchemaDB.COL_PROFILE);
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            // 2-> query -> string + " " -> String.format
        /*String query = "INSERT INTO students (name,last,mail,age,profile) " +
                "VALUES ('" + student.getName() + "','" + student.getLast() + "','"
                + student.getMail() + "'," + student.getAge() + "," + student.getProfile() + ")";*/
            // mensaje, parametros %s -> string %d -> int %.2f -> double
        /*String query = String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES ('%s','%s','%s',%d,%d)",
                SchemaDB.TAB_STUDENTS,
                SchemaDB.COL_NAME, SchemaDB.COL_LAST, SchemaDB.COL_EMAIL, SchemaDB.COL_AGE, SchemaDB.COL_PROFILE,
                student.getName(), student.getLast(), student.getMail(), student.getAge(), student.getProfile());
        // 3 -> crear statement*/

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getLast());
            preparedStatement.setString(3, student.getMail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setLong(5, student.getProfile());
            preparedStatement.execute();

            // dar de alta al alumno en otra tabla
            // mandar un mail al usuario con sus datos
            // mandar un mail al dpt academico para que cree el usuario del ordenador
            // activar el proceso de facturacion

            // conexion esta cerrada
            // connection.close();
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Error en la query, esta mal escrita");
        }
        /*try {
            statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Query ejecutada correctamente");
        } catch (SQLException e) {
            System.out.println("Error en query");
            System.out.println(e.getMessage());
        }*/

    }

    public int deleteStudent(String name) {
        String query = String.format("DELETE FROM %s WHERE %s=?", SchemaDB.TAB_STUDENTS, SchemaDB.COL_NAME);
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, name);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int updateUsers(int profile) {

        String query = String.format("UPDATE %s SET %s=? WHERE %s=?",
                SchemaDB.TAB_STUDENTS, SchemaDB.COL_LAST, SchemaDB.COL_PROFILE);

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, profile == 1 ? "grade" : "fp");
            preparedStatement.setInt(2, profile);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public Student getUserByMail(String mail) {

        String query = String.format("SELECT * FROM %s WHERE %s=?",
                SchemaDB.TAB_STUDENTS, SchemaDB.COL_EMAIL);
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            //                  v -> moveToNext next
            //  r1 r2 r3 r4 r5 r6
            while (resultSet.next()) {
                String uname = resultSet.getString(SchemaDB.COL_NAME);
                String ulast = resultSet.getString(SchemaDB.COL_LAST);
                String umail = resultSet.getString(SchemaDB.COL_EMAIL);
                Long uprofile = resultSet.getLong(SchemaDB.COL_PROFILE);
                int age = resultSet.getInt(SchemaDB.COL_AGE);
                return new Student(uname, ulast, umail, age, uprofile);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> studentsList = new ArrayList<>();
        String query = "SELECT * FROM " + SchemaDB.TAB_STUDENTS;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(SchemaDB.COL_ID);
                String uname = resultSet.getString(SchemaDB.COL_NAME);
                String ulast = resultSet.getString(SchemaDB.COL_LAST);
                String umail = resultSet.getString(SchemaDB.COL_EMAIL);
                Long uprofile = resultSet.getLong(SchemaDB.COL_PROFILE);
                int age = resultSet.getInt(SchemaDB.COL_AGE);
                studentsList.add(new Student(id, uname, ulast, umail, age, uprofile));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return studentsList;
    }

}
