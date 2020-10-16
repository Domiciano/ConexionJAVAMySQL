package org.example.db;

import org.example.model.Estudiante;

import java.sql.*;
import java.util.ArrayList;

public class MySQLConnection {


    private Connection connection;

    public MySQLConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semana10", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //ORDENES

    public void createTableVehiculo() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE vehiculo(id INT PRIMARY KEY AUTO_INCREMENT, placa VARCHAR (100))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEstudiante(Estudiante estudiante) {
        try {
            Statement statement = connection.createStatement();
            String sql = ("INSERT INTO estudiantes(nombre, apellido, edad) VALUES ('$NOMBRE','$APELLIDO', $EDAD)")
                    .replace("$NOMBRE", estudiante.getNombre())
                    .replace("$APELLIDO", estudiante.getApellido())
                    .replace("$EDAD", "" + estudiante.getEdad());
            statement.execute(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEstudiante(Estudiante estudiante) {
        try {
            Statement statement = connection.createStatement();
            String sql = ("UPDATE estudiantes SET nombre='$NOMBRE', apellido='$APELLIDO', edad=$EDAD WHERE id=$ID")
                    .replace("$ID", "" + estudiante.getId())
                    .replace("$NOMBRE", estudiante.getNombre())
                    .replace("$APELLIDO", estudiante.getApellido())
                    .replace("$EDAD", "" + estudiante.getEdad());
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEstudianteByID(int id) {
        try {
            Statement statement = connection.createStatement();
            String sql = ("DELETE FROM estudiantes WHERE id=$ID")
                    .replace("$ID", "" + id);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Estudiante> getAllEstudiantes() {
        ArrayList<Estudiante> output = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultados = statement.executeQuery("SELECT * FROM estudiantes");

            while (resultados.next()) {
                int id = resultados.getInt(resultados.findColumn("id"));
                String nombre = resultados.getString(resultados.findColumn("nombre"));
                String apellido = resultados.getString(resultados.findColumn("apellido"));
                int edad = resultados.getInt(resultados.findColumn("edad"));
                //System.out.println(id+", "+ nombre + ", " + apellido + ", " + edad);
                output.add(new Estudiante(id, nombre, apellido, edad));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }


    public ArrayList<Estudiante> getEstudiantesByCursoID(int cursoid) {
        ArrayList<Estudiante> output = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultados = statement.executeQuery("SELECT estudiantes.id, estudiantes.nombre, estudiantes.apellido, estudiantes.edad FROM (estudiantes INNER JOIN cursos_estudiantes ON estudiantes.id = cursos_estudiantes.estudianteID) INNER JOIN cursos ON cursos_estudiantes.cursoID = cursos.id WHERE cursos.id = " + cursoid);

            while (resultados.next()) {
                int id = resultados.getInt(1);
                String nombre = resultados.getString(2);
                String apellido = resultados.getString(3);
                int edad = resultados.getInt(4);
                //System.out.println(id+", "+ nombre + ", " + apellido + ", " + edad);
                output.add(new Estudiante(id, nombre, apellido, edad));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }


}
