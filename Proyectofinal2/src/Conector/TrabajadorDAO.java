package Conector;

import java.sql.*;

import Logica.ColaboradorTech;

public class TrabajadorDAO {

    private Connection conn;

    public TrabajadorDAO(Connection conn) {
        this.conn = conn;
    }

     public boolean insertarTrabajador(ColaboradorTech trabajador) {
        String sql = "INSERT INTO Trabajador (id_trabajador, nombre, direccion, sexo, edad, salario, nombre_proyecto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trabajador.getId());
            stmt.setString(2, trabajador.getNombre());
            stmt.setString(3, trabajador.getDireccion());
            stmt.setString(4, String.valueOf(trabajador.getSexo()));
            stmt.setInt(5, trabajador.getEdad());
            stmt.setDouble(6, trabajador.getSalario());
            stmt.setString(7, trabajador.getProyectoAsignado());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public ColaboradorTech obtenerTrabajador(String id) {
        String sql = "SELECT * FROM Trabajador WHERE id_trabajador = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String idTrabajador = rs.getString("id_trabajador");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                char sexo = rs.getString("sexo").charAt(0);
                int edad = rs.getInt("edad");
                double salario = rs.getDouble("salario");
                String nombreProyecto = rs.getString("nombre_proyecto");
                 return new ColaboradorTech(idTrabajador, nombre, direccion, nombreProyecto, sexo, edad, salario, nombreProyecto) {
                    @Override
                    public String mostrarResumen() {
                        return "Resumen del Trabajador: " + nombre + " - Proyecto: " + nombreProyecto;
                    }
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     public ResultSet obtenerTodosTrabajadores() {
        String sql = "SELECT * FROM Trabajador";
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     public boolean eliminarTrabajador(String id) {
        String sql = "DELETE FROM Trabajador WHERE id_trabajador = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     
}
