package Conector;

import java.sql.*;
import java.time.LocalDate;

import Logica.Cliente;
import Logica.ColaboradorTech;
import Logica.ProyectoSoftware;

public class ProyectoDAO {

    private Connection conn;

    public ProyectoDAO(Connection conn) {
        this.conn = conn;
    }

     public boolean insertarProyecto(ProyectoSoftware proyecto) {
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

     public ProyectoSoftware obtenerProyecto(String nombreProyecto) {
        String sql = "SELECT * FROM Proyecto WHERE nombre_proyecto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreProyecto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre_proyecto");
                 return new ProyectoSoftware(nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     public ResultSet obtenerTodosProyectos() {
        String sql = "SELECT * FROM Proyecto";
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     public boolean actualizarFechaFinalizacion(String nombreProyecto, LocalDate nuevaFechaFinalizacion) {
        String sql = "UPDATE Proyecto SET fecha_finalizacion = ? WHERE nombre_proyecto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(nuevaFechaFinalizacion));
            stmt.setString(2, nombreProyecto);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public boolean agregarColaborador(ColaboradorTech colaborador, String nombreProyecto) {
        String sql = "INSERT INTO Trabaja_en (id_trabajador, nombre_proyecto) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, colaborador.getId());
            stmt.setString(2, nombreProyecto);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public boolean agregarCliente(Cliente cliente, String nombreProyecto) {
        String sql = "INSERT INTO Participa_en (id_cliente, nombre_proyecto) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getId());
            stmt.setString(2, nombreProyecto);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public boolean estaSaturado(String nombreProyecto) {
        String sql = "SELECT COUNT(*) FROM Trabaja_en WHERE nombre_proyecto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreProyecto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int cantidadColaboradores = rs.getInt(1);
                return cantidadColaboradores >= 10;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
