package Conector;

import java.sql.*;
import java.time.LocalDate;

import Logica.Cliente;
import Logica.Contrato;

public class ContratoDAO {

    private Connection conn;

    public ContratoDAO(Connection conn) {
        this.conn = conn;
    }

     public boolean insertarContrato(Contrato contrato) {
        String sql = "INSERT INTO Contrato (id_contrato, fecha_inicio, fecha_entrega, nombre_proyecto, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contrato.getId());
            stmt.setDate(2, Date.valueOf(contrato.getFechaInicio()));
            stmt.setDate(3, Date.valueOf(contrato.getFechaEntrega()));
            stmt.setString(4, contrato.getNombreProyecto());
            stmt.setString(5, contrato.getCliente().getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public boolean prorrogarContrato(String idContrato, int dias) {
        String sql = "UPDATE Contrato SET fecha_entrega = fecha_entrega + INTERVAL ? DAY WHERE id_contrato = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dias);
            stmt.setString(2, idContrato);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public Contrato obtenerContrato(String idContrato) {
        String sql = "SELECT * FROM Contrato WHERE id_contrato = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idContrato);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id_contrato");
                LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                LocalDate fechaEntrega = rs.getDate("fecha_entrega").toLocalDate();
                String nombreProyecto = rs.getString("nombre_proyecto");
                String idCliente = rs.getString("id_cliente");
                Cliente cliente = new ClienteDAO(conn).obtenerCliente(idCliente); // Obtener cliente asociado
                return new Contrato(id, cliente, nombreProyecto, fechaInicio, fechaEntrega);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     public String obtenerEstadoContrato(String idContrato) {
        Contrato contrato = obtenerContrato(idContrato);
        if (contrato != null) {
            return contrato.getEstadoProyecto();
        }
        return "Contrato no encontrado";
    }

     public double obtenerPenalizacionContrato(String idContrato) {
        Contrato contrato = obtenerContrato(idContrato);
        if (contrato != null) {
            return contrato.calcularPenalizacion();
        }
        return 0.0;
    }

     public boolean eliminarContrato(String idContrato) {
        String sql = "DELETE FROM Contrato WHERE id_contrato = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idContrato);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
