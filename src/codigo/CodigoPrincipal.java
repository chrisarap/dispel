package codigo;

import interfaces.Principal;
import javax.swing.JFrame;

public class CodigoPrincipal {

    // constructor
    public CodigoPrincipal() {
        Principal ventana = new Principal();
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // metodo main
    public static void main(String[] args) {
        CodigoPrincipal cp = new CodigoPrincipal();
    }
}
