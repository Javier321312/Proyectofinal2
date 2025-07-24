package Logica;

public class CodeMaster extends ColaboradorTech {
	
private String lenguajeDominante;
    
    public CodeMaster(String id, String nombre, String apellido, String direccion, char sexo, int edad, double salario, String proyectoAsignado, String lenguajeDominante) {
        super(id, nombre, apellido, direccion, sexo, edad, salario, proyectoAsignado);
        this.lenguajeDominante = lenguajeDominante;
    }
    
    public String getLenguajeDominante() {
        return lenguajeDominante;
    }
    
    
    public String mostrarResumen() {
        return String.format("CodeMaster: %s %s - Lenguaje: %s - Proyecto: %s - Salario: $%.2f",
                nombre, apellido, lenguajeDominante, proyectoAsignado, salario);
    }
}