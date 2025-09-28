package Model;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class InventarioModel {
    private ArrayList<Producto> productos;  // ArrayList para almacenar productos
    private int nextId;
    public InventarioModel() {
        this.productos = new ArrayList<>();
        this.nextId = 1;
    }
    public boolean agregarProducto(String nombre, int cantidad, double precio) {
        Producto producto = new Producto(nextId, nombre, cantidad, precio);
        productos.add(producto);  // Agregar al ArrayList
        nextId++;
        return true;
    }
    public boolean eliminarProductoPorId(int idProducto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == idProducto) {
                productos.remove(i);  // Eliminar del ArrayList
                return true;
            }
        }
        return false;
    }
    public boolean actualizarCantidadPorNombre(String nombre, int nuevaCantidad) {
        for (Producto prod : productos) {
            if (prod.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                prod.setCantidad(nuevaCantidad);
                return true;
            }
        }
        return false;
    }
    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto prod : productos) {
            if (prod.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                return prod;
            }
        }
        return null;
    }
    public List<Producto> obtenerTodosProductos() {
        return new ArrayList<>(productos);  // Retorna una copia del ArrayList
    }
    public List<Producto> buscarProductosPorTermino(String termino) {
        if (termino == null || termino.trim().isEmpty()) {
            return obtenerTodosProductos();
        }
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(termino.toLowerCase())) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }
}
