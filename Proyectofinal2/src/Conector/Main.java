package Conector;

import java.sql.*;
import java.util.ArrayList;

import Logica.Cliente;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/proyecto1";  // URL de la base de datos
        String usuario = "root";  // Tu usuario de MySQL
        String contrasena = "Kataverde321";  // Tu contraseña de MySQL

        try {
            // Establece la conexión con la base de datos
            Connection conn = DriverManager.getConnection(url, usuario, contrasena);
            
            // Creamos el DAO de Cliente
            ClienteDAO clienteDAO = new ClienteDAO(conn);

            // Obtener todos los clientes desde la base de datos
            ArrayList<Cliente> clientes = clienteDAO.obtenerClientes();

            // Mostrar los detalles de cada cliente
            for (Cliente cliente : clientes) {
                System.out.println(cliente.getDatosCliente());
            }

            // Cerrar la conexión
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}