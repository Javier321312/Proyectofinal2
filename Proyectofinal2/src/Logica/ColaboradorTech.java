package Logica;

public abstract class ColaboradorTech {
	protected String id;
    protected String nombre;
    protected String apellido;
    protected String direccion;
    protected char sexo;
    protected int edad;
    protected double salario;
    protected String proyectoAsignado;
    
    public ColaboradorTech(String id, String nombre, String apellido, String direccion, 
                          char sexo, int edad, double salario, String proyectoAsignado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.sexo = sexo;
        this.edad = edad;
        this.salario = salario;
        this.proyectoAsignado = proyectoAsignado;
    }
    

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellido; }
    public String getDireccion() { return direccion; }
    public char getSexo() { return sexo; }
    public int getEdad() { return edad; }
    public double getSalario() { return salario; }
    public String getProyectoAsignado() { return proyectoAsignado; }
    
    
    public abstract String mostrarResumen();
}
