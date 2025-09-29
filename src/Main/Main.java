package Main;

import Controller.EmpleadoController;
import Controller.InventarioController;
import Controller.VentasController;
import Model.Empleado;
import Model.EmpleadoModel;
import Model.InventarioModel;
import View.EmpleadoView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            //new InventarioController();
            /*InventarioModel model = new InventarioModel();
            new VentasController(model);*/
            Empleado model = new Empleado();
            EmpleadoView view = new EmpleadoView();
            new EmpleadoController(model, view);
            view.mostrar();
        });

    }
}

