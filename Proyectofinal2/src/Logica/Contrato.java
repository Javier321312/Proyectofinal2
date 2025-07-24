package Logica;

import java.time.LocalDate;

public class Contrato {
	
	 private String id;
	    private Cliente cliente;
	    private String nombreProyecto;
	    private LocalDate fechaInicio;
	    private LocalDate fechaEntrega;
	    
	    public Contrato(String id, Cliente cliente, String nombreProyecto, 
	                   LocalDate fechaInicio, LocalDate fechaEntrega) {
	        this.id = id;
	        this.cliente = cliente;
	        this.nombreProyecto = nombreProyecto;
	        this.fechaInicio = fechaInicio;
	        this.fechaEntrega = fechaEntrega;
	    }
	    
	    public String prorrogarEntregas(int dias) {
	        fechaEntrega = fechaEntrega.plusDays(dias);
	        return String.format("Contrato %s prorrogado por %d días. Nueva fecha: %s", 
	                id, dias, fechaEntrega);
	    }
	    
	    public long calcularDiasRestantes() {
	        LocalDate hoy = LocalDate.now();
	        if (hoy.isBefore(fechaEntrega)) {
	            return java.time.temporal.ChronoUnit.DAYS.between(hoy, fechaEntrega);
	        } else if (hoy.isEqual(fechaEntrega)) {
	            return 0; 
	        } else {
	            return java.time.temporal.ChronoUnit.DAYS.between(fechaEntrega, hoy) * -1; 
	        }
	    }
	    
	    public String getEstadoProyecto() {
	        long diasRestantes = calcularDiasRestantes();
	        if (diasRestantes > 0) {
	            return String.format("EN PROGRESO - %d días restantes", diasRestantes);
	        } else if (diasRestantes == 0) {
	            return "ENTREGA HOY";
	        } else {
	            return String.format("RETRASADO - %d días de retraso", Math.abs(diasRestantes));
	        }
	    }
	    
	    public double calcularPenalizacion() {
	        long diasRestantes = calcularDiasRestantes();
	        if (diasRestantes < 0) {
	            return Math.abs(diasRestantes) * 100.0; 
	        }
	        return 0.0;
	    }
	    
	    public String mostrarDetallesContrato() {
	        return String.format("Contrato ID: %s\nCliente: %s\nProyecto: %s\nInicio: %s\nEntrega: %s\nEstado: %s\nPenalización: $%.2f",
	                id, cliente.getNombre(), nombreProyecto, fechaInicio, fechaEntrega, 
	                getEstadoProyecto(), calcularPenalizacion());
	    }
	    
	    
	    public String getId() { return id; }
	    public Cliente getCliente() { return cliente; }
	    public String getNombreProyecto() { return nombreProyecto; }
	    public LocalDate getFechaInicio() { return fechaInicio; }
	    public LocalDate getFechaEntrega() { return fechaEntrega; }
	    
}