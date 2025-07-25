package Logica;

public class DiseñadorCreativo extends ColaboradorTech {

    private String evaluacionAnual;

    public DiseñadorCreativo(String id, String nombre, String apellido, String direccion, char sexo, int edad, double salario, String proyectoAsignado, String evaluacionAnual) {
        super(id, nombre, apellido, direccion, sexo, edad, salario, proyectoAsignado);
        this.evaluacionAnual = evaluacionAnual;
    }

    public String getEvaluacionAnual() {
        return evaluacionAnual;
    }

    public String mostrarResumen() {
        return String.format("DiseñadorCreativo: %s %s - Evaluación: %s - Proyecto: %s - Salario: $%.2f",
                nombre, apellido, evaluacionAnual, proyectoAsignado, salario);
    }
}
