package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection conectarBD() {

        Connection conexion = null;
        /*string de conexion*/
        String url = "jdbc:mysql://localhost:3306/gestorweb";
        /*Usuario de conexion*/
        String usuario = "root";
        /*Contraseña base datos*/
        String contraseña = "2556229";

        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        return conexion;
    }

}
