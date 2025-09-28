package Controller;
import Model.InventarioModel;
import Model.Producto;
import View.InventarioView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
public class InventarioController {
    private InventarioModel model;
    private InventarioView view;
    public InventarioController() {
        this.model = new InventarioModel();
        this.view = new InventarioView(this);

        // Configurar todos los eventos aquí (ActionListeners, DocumentListener, MouseListener)
        configurarEventos();

        actualizarTabla();  // Cargar tabla inicial
        view.setVisible(true);
    }
    private void configurarEventos() {
        // ActionListener para botón agregar
        view.getBtnAgregar().addActionListener(e -> agregarProducto());
        // ActionListener para botón llenar inventario
        view.getBtnLlenar().addActionListener(e -> llenarInventario());
        // ActionListener para botón limpiar
        view.getBtnLimpiar().addActionListener(e -> limpiarBusqueda());
        // DocumentListener para búsqueda en tiempo real
        view.getTxtBusqueda().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { buscarProductos(); }
            @Override
            public void removeUpdate(DocumentEvent e) { buscarProductos(); }
            @Override
            public void changedUpdate(DocumentEvent e) { buscarProductos(); }
        });
        // Configurar menú contextual y MouseListener para click derecho en tabla
        view.getItemEliminar().addActionListener(e -> mostrarMenuEliminar());
        view.getTablaProductos().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = view.getTablaProductos().rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        view.getTablaProductos().setRowSelectionInterval(row, row);
                        view.getMenuContextual().show(view.getTablaProductos(), e.getX(), e.getY());
                    }
                }
            }
        });
    }
    // Evento para agregar producto: solo llama al View para mostrar diálogo
    public void agregarProducto() {
        view.mostrarDialogoAgregarEliminar();
    }
    // Evento para llenar inventario: lógica de búsqueda y llamada a View para UI
    public void llenarInventario() {
        String nombreBuscar = view.pedirNombreProducto();
        if (nombreBuscar == null || nombreBuscar.trim().isEmpty()) {
            return;
        }
        Producto productoEncontrado = model.buscarProductoPorNombre(nombreBuscar);
        if (productoEncontrado == null) {
            view.mostrarMensaje("Error", "Producto no encontrado.", JOptionPane.ERROR_MESSAGE);
            return;
        }
        view.mostrarDialogoActualizarCantidad(productoEncontrado);
    }
    // Procesar agregar: lógica de negocio (llama Model y actualiza View)
    public void procesarAgregar(String nombre, int cantidad, double precio) {
        if (model.agregarProducto(nombre, cantidad, precio)) {
            view.mostrarMensaje("Éxito", "Producto agregado.", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
        } else {
            view.mostrarMensaje("Error", "Error al agregar producto.", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Procesar eliminar: lógica de negocio
    public void procesarEliminar(int id) {
        if (model.eliminarProductoPorId(id)) {
            view.mostrarMensaje("Éxito", "Producto eliminado.", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
        } else {
            view.mostrarMensaje("Error", "ID no encontrado.", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Procesar actualizar: lógica de negocio
    public void procesarActualizar(String nombre, int nuevaCantidad) {
        if (model.actualizarCantidadPorNombre(nombre, nuevaCantidad)) {
            view.mostrarMensaje("Éxito", "Inventario actualizado.", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
        } else {
            view.mostrarMensaje("Error", "Error al actualizar.", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Evento de búsqueda: obtiene datos de View, procesa en Model, actualiza View
    public void buscarProductos() {
        String termino = view.obtenerTerminoBusqueda().toLowerCase();
        List<Producto> filtrados = model.buscarProductosPorTermino(termino);
        view.actualizarTabla(filtrados);
    }
    // Evento limpiar búsqueda
    public void limpiarBusqueda() {
        view.limpiarCampoBusqueda();
        actualizarTabla();
    }
    // Evento menú eliminar: obtiene selección de View, confirma (via View), procesa
    public void mostrarMenuEliminar() {
        int filaSeleccionada = view.getTablaProductos().getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idProd = (int) view.getModeloTabla().getValueAt(filaSeleccionada, 0);
            if (view.mostrarConfirmacion("¿Eliminar el producto con ID " + idProd + "?", "Eliminar")) {
                procesarEliminar(idProd);
            }
        }
    }
    // Método interno para actualizar tabla
    private void actualizarTabla() {
        List<Producto> productos = model.obtenerTodosProductos();
        view.actualizarTabla(productos);
    }
}
