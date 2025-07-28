package Conector;

import java.sql.*;
import java.util.ArrayList;

import Logica.Cliente;

public class ClienteDAO {

    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

     public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (id_cliente, nombre, direccion) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getId());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getDireccion());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public Cliente obtenerCliente(String id) {
        String sql = "SELECT * FROM Cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                return new Cliente(nombre, id, direccion); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre = ?, direccion = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public boolean eliminarCliente(String id) {
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtener todos los clientes (para pruebas)
    public ArrayList<Cliente> obtenerClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id_cliente");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                clientes.add(new Cliente(nombre, id, direccion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
