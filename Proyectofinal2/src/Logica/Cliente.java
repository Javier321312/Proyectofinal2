package Logica;

import java.util.ArrayList;

public class Cliente {
    
    private String nombre;
    private String id;
    private String direccion;
    private ArrayList<String> proyectosVinculados;

    public Cliente(String nombre, String id, String direccion) {
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
        this.proyectosVinculados = new ArrayList<>();
    }

    public boolean agregarProyecto(String nombreProyecto) {
        if (proyectosVinculados.size() < 5 && !proyectosVinculados.contains(nombreProyecto)) {
            proyectosVinculados.add(nombreProyecto);
            return true;
        }
        return false;
    }

    public int getCantidadProyectos() {
        return proyectosVinculados.size();
    }

    public String getDatosCliente() {
        return String.format("Cliente: %s - Contacto: %s - Dirección: %s", nombre, id, direccion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
