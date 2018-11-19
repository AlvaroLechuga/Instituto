package dam.instituto.test;

import dam.instituto.DAOimpl.Conexion;
import dam.instituto.vista.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MVC_Instituto {

    public static void main(String[] args) {

        FormPrincipal window = new FormPrincipal();
        window.setVisible(true);

        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(window,
                        "¿Estas seguro que desea salir?", "Confirmacion Salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    Conexion.cerrarSesion();
                    System.exit(0);
                }
            }
        });
    }
}
