package Conector;

import java.sql.*;
import java.util.ArrayList;

import Logica.Cliente;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/proyecto1";  
        String usuario = "root";  
        String contrasena = "Kataverde321";   

        try {
             Connection conn = DriverManager.getConnection(url, usuario, contrasena);
            
             ClienteDAO clienteDAO = new ClienteDAO(conn);

             ArrayList<Cliente> clientes = clienteDAO.obtenerClientes();

             for (Cliente cliente : clientes) {
                System.out.println(cliente.getDatosCliente());
            }

             conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}