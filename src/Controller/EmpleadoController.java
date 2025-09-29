package Controller;

import Model.EmpleadoModel;
import Model.Empleado;
import View.EmpleadoView;

import javax.swing.*;

public class EmpleadoController {
    private Empleado empleado;
    private EmpleadoView view;
    private EmpleadoModel model;

    public EmpleadoController(Empleado model, EmpleadoView view) {
        this.empleado = empleado;
        this.view = view;


        // Eventos
        view.getBtnAgregar().addActionListener(e -> agregarEmpleado());
        view.getBtnDespedir().addActionListener(e -> despedirEmpleado());
    }

    public void agregarEmpleado() {

        String nombre = JOptionPane.showInputDialog(view, "Ingrese el nombre del empleado:");
        if (nombre == null || nombre.isEmpty()) return;

        String apellido = JOptionPane.showInputDialog(view, "Ingrese el apellido del empleado:");
        if (apellido == null || apellido.isEmpty()) return;

        String edadStr = JOptionPane.showInputDialog(view, "Ingrese la edad:");
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Edad inválida.");
            return;
        }

        String TelefonoStr = JOptionPane.showInputDialog(view, "Ingrese el telefono:");
        int telefono;
        try {
            telefono = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Telefono inválida.");
            return;
        }

        Empleado emp = new Empleado(nombre, apellido, edad, telefono);
        model.agregarEmpleado(emp);
        view.getModeloLista().addElement(emp.toString());
    }

    private void despedirEmpleado() {
        int index = view.getListaEmpleados().getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(view, "Seleccione un empleado para despedir.");
            return;
        }

        model.eliminarEmpleado(index);
        view.getModeloLista().remove(index);
    }
}