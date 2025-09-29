package Main;

import Controller.InventarioController;
import Controller.VentasController;
import Model.InventarioModel;
import Model.VentasModel;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Menú Principal");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnInventario= new JButton("Gestion de Inventario");
        JButton btnVentas = new JButton("Gestión de Ventas");
        JButton btnEmpleado = new JButton("Gestion Empleado");


        setLayout(new FlowLayout());
        add(btnVentas);
    }
}

