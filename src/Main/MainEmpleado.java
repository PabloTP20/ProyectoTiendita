package Main;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import Model.EmpleadoModel;
import View.EmpleadoView;
import Controller.EmpleadoController;

public class MainEmpleado {
    public static void main(String[] args) {
        EmpleadoModel model = new EmpleadoModel();
        EmpleadoView view = new EmpleadoView();
        new EmpleadoController(model, view);

        view.mostrar();
    }
}
