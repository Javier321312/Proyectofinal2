package Logica;

public class PlanificarAgil extends ColaboradorTech {
	
private int frecuenciaDias;
    
    public PlanificarAgil(String id, String nombre, String apellido, String direccion, char sexo, int edad, double salario, String proyectoAsignado, int frecuenciaDias) {
        super(id, nombre, apellido, direccion, sexo, edad, salario, proyectoAsignado);
        this.frecuenciaDias = frecuenciaDias;
    }
    
    public int getFrecuenciaDias() {
        return frecuenciaDias;
    }
    
   
    public String mostrarResumen() {
        return String.format("PlanificarAgil: %s %s - Frecuencia: %d días - Proyecto: %s - Salario: $%.2f",
                nombre, apellido, frecuenciaDias, proyectoAsignado, salario);
    }
}