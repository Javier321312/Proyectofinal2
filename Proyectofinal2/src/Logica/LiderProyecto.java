package Logica;

public class LiderProyecto extends ColaboradorTech {
	
private int totalIntegrantes;
    
    public LiderProyecto(String id, String nombre, String apellido, String direccion, char sexo, int edad, double salario, String proyectoAsignado, int totalIntegrantes) {
        super(id, nombre, apellido, direccion, sexo, edad, salario, proyectoAsignado);
        this.totalIntegrantes = totalIntegrantes;
    }
    
    public int getTotalIntegrantes() {
        return totalIntegrantes;
    }
    
    
    public String mostrarResumen() {
        return String.format("LiderProyecto: %s %s - Integrantes: %d - Proyecto: %s - Salario: $%.2f",
                nombre, apellido, totalIntegrantes, proyectoAsignado, salario);
    }
}