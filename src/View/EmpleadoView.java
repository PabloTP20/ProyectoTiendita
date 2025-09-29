package View;

import Controller.EmpleadoController;
import Model.Empleado;
import Model.EmpleadoModel;

import javax.swing.*;
import java.awt.*;

public class EmpleadoView extends JFrame {
    private DefaultListModel<String> modeloLista;
    private JList<String> listaEmpleados;
    private JButton btnAgregar;
    private JButton btnDespedir;

    public EmpleadoView() {
        super("Gestión de Empleados");

        modeloLista = new DefaultListModel<>();
        listaEmpleados = new JList<>(modeloLista);
        btnAgregar = new JButton("Agregar empleado");
        btnDespedir = new JButton("Despedir empleado");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnDespedir);

        setLayout(new BorderLayout());
        add(new JScrollPane(listaEmpleados), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public void mostrar() {
        setVisible(true);
    }

    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnDespedir() { return btnDespedir; }
    public JList<String> getListaEmpleados() { return listaEmpleados; }
    public DefaultListModel<String> getModeloLista() { return modeloLista; }
}
