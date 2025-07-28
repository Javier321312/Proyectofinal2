package Conector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    public static void main(String[] args) {
         String url = "jdbc:mysql://localhost:3306/proyecto1";  
        String usuario = "root"; //  
        String contrasena = "Kataverde321";  

        try {
            // Establece la conexión
            Connection conn = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión exitosa a la base de datos");

            // Aquí puedes ejecutar tus consultas SQL

            conn.close(); // Cerrar la conexión
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
	