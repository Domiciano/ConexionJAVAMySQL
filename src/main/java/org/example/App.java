package org.example;

import org.example.db.MySQLConnection;
import org.example.model.Estudiante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

        Connection conex;
        DriverManager manager;


        System.out.println( "Hello World!" );
        MySQLConnection connection = new MySQLConnection();
        //connection.createTableVehiculo();
        //connection.insertEstudiante(new Estudiante(-1,"Fernando","Fernandez",25));
        //connection.updateEstudiante(new Estudiante(2, "Bob","Baron",26));
        //connection.deleteEstudianteByID(5);

        //ArrayList<Estudiante> estudiantes = connection.getAllEstudiantes();
        ArrayList<Estudiante> estudiantes = connection.getEstudiantesByCursoID(3);
        System.out.println(estudiantes.size());


        for( int i=0 ; i<estudiantes.size() ; i++){
            System.out.println(estudiantes.get(i).getNombre());
        }


        connection.closeDB();
    }
}
