package Logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorEmpresarial {
    private HashMap<String, Cliente> clientes;
    private HashMap<String, ProyectoSoftware> proyectos;
    private ArrayList<ColaboradorTech> colaboradores;

    public GestorEmpresarial() {
        clientes = new HashMap<>();
        proyectos = new HashMap<>();
        colaboradores = new ArrayList<>();
    }

    public boolean crearContrato(String idCliente, String nombreProyecto, LocalDate fechaInicio, LocalDate fechaEntrega) {
        Cliente cliente = clientes.get(idCliente);
        ProyectoSoftware proyecto = proyectos.get(nombreProyecto);

        if (cliente != null && proyecto != null && !proyecto.estaSaturado()) {
            Contrato contrato = new Contrato(idCliente, cliente, nombreProyecto, fechaInicio, fechaEntrega);

            return true;
        }
        return false;
    }

    public boolean registrarCliente(String id, String nombre, String direccion) {
        if (!clientes.containsKey(id)) {
            clientes.put(id, new Cliente(id, nombre, direccion));
            return true;
        }
        return false;
    }

        

    public void agregarProyecto(String nombreProyecto) {
        if (!proyectos.containsKey(nombreProyecto)) {
            proyectos.put(nombreProyecto, new ProyectoSoftware(nombreProyecto));
        }
    }

    public void registrarColaborador(ColaboradorTech colaborador) {
        colaboradores.add(colaborador);
    }

    public ArrayList<ColaboradorTech> getColaboradores() {
        return colaboradores;
    }
}
