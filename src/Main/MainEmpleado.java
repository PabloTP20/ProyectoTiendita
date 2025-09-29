package Main;

import Controller.EmpleadoController;
import Model.Empleado;
import Model.EmpleadoModel;
import View.EmpleadoView;

public class MainEmpleado {
    public static void main (String[] args) {
        Empleado model = new Empleado();
        EmpleadoModel empleadoModel = new EmpleadoModel();
        EmpleadoView view = new EmpleadoView();
        new EmpleadoController(model, view);

        view.mostrar();
}
}
