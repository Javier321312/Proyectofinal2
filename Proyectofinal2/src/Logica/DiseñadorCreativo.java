package Logica;

public class Dise�adorCreativo extends ColaboradorTech {

    private String evaluacionAnual;

    public Dise�adorCreativo(String id, String nombre, String apellido, String direccion, char sexo, int edad, double salario, String proyectoAsignado, String evaluacionAnual) {
        super(id, nombre, apellido, direccion, sexo, edad, salario, proyectoAsignado);
        this.evaluacionAnual = evaluacionAnual;
    }

    public String getEvaluacionAnual() {
        return evaluacionAnual;
    }

    public String mostrarResumen() {
        return String.format("Dise�adorCreativo: %s %s - Evaluaci�n: %s - Proyecto: %s - Salario: $%.2f",
                nombre, apellido, evaluacionAnual, proyectoAsignado, salario);
    }
}
