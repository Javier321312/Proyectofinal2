package Logica;

public class Cliente {
	
	private String nombre;
    private String id;
    private String direccion;
    
    	
	
public Cliente(String nombre, String id, String direccion) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.direccion = direccion;
	}


	    public String agregarProyecto(String nombreProyecto) {
	        return "Proyecto '" + nombreProyecto + "' agregado para cliente: " + nombre;
	    }
	    
	    public String getCalidadProyectos() {
	        return "Evaluando calidad de proyectos para cliente: " + nombre;
	    }
	    
	    public String getDatosCliente() {
	        return String.format("Cliente: %s - Contacto: %s", nombre, id);
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
