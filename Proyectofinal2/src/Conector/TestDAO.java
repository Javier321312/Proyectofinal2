package Conector;

import Logica.ProyectoSoftware;
import java.sql.Connection;
import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        // Obtener la conexión a la base de datos
        Connection connection = ConexionDB.obtenerConexion();

        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
            
            // Crear un ProyectoDAO con la conexión
            ProyectoDAO proyectoDAO = new ProyectoDAO(connection);

            // Intentamos obtener todos los proyectos
            List<ProyectoSoftware> proyectos = proyectoDAO.obtenerTodosProyectos();
            
            if (proyectos != null && !proyectos.isEmpty()) {
                System.out.println("Proyectos obtenidos: ");
                for (ProyectoSoftware proyecto : proyectos) {
                    System.out.println(proyecto.getNombre());  // Mostrar el nombre del proyecto
                }
            } else {
                System.out.println("No se encontraron proyectos.");
            }
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
        }
    }
}
