package Main;
import Controller.InventarioController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventarioController();
        });
    }
}
