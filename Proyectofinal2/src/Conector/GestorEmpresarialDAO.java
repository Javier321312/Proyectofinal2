package Conector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import Logica.Cliente;
import Logica.ColaboradorTech;
import Logica.ProyectoSoftware;

public class GestorEmpresarialDAO {

    private Connection conn;

    public GestorEmpresarialDAO(Connection conn) {
        this.conn = conn;
    }

     public boolean registrarCliente(Cliente cliente) {
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

     public boolean agregarProyecto(ProyectoSoftware proyecto) {
        String sql = "INSERT INTO Proyecto (nombre_proyecto) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proyecto.getNombre());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public boolean registrarColaborador(ColaboradorTech colaborador) {
        String sql = "INSERT INTO Trabajador (id_trabajador, nombre, direccion, sexo, edad, salario, nombre_proyecto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, colaborador.getId());
            stmt.setString(2, colaborador.getNombre());
            stmt.setString(3, colaborador.getDireccion());
            stmt.setString(4, String.valueOf(colaborador.getSexo()));
            stmt.setInt(5, colaborador.getEdad());
            stmt.setDouble(6, colaborador.getSalario());
            stmt.setString(7, colaborador.getProyectoAsignado());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public boolean crearContrato(String idCliente, String nombreProyecto, LocalDate fechaInicio, LocalDate fechaEntrega) {
        String sql = "SELECT COUNT(*) FROM Trabaja_en WHERE nombre_proyecto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreProyecto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int colaboradoresCount = rs.getInt(1);
                if (colaboradoresCount < 10) {  
                     String insertContrato = "INSERT INTO Contrato (fecha_inicio, fecha_entrega, nombre_proyecto, id_cliente) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertContrato)) {
                        insertStmt.setDate(1, Date.valueOf(fechaInicio));
                        insertStmt.setDate(2, Date.valueOf(fechaEntrega));
                        insertStmt.setString(3, nombreProyecto);
                        insertStmt.setString(4, idCliente);
                        int rowsAffected = insertStmt.executeUpdate();
                        return rowsAffected > 0;
                    }
                } else {
                    System.out.println("El proyecto está saturado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
