package codigo;

import interfaces.Seleccion;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CodigoSeleccion {

    public static String variable, distribucion;
    Seleccion seleccion;

    // constructor ventana
    public CodigoSeleccion() {
        Seleccion ventana = new Seleccion();
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public CodigoSeleccion(String variable, String distribucion,
            Seleccion seleccion) {
        CodigoSeleccion.variable = variable;
        CodigoSeleccion.distribucion = distribucion;
        this.seleccion = seleccion;
    }

    public void MensajeAlerta() {
        if ("Seleccionar".equals(variable)
                || "Seleccionar".equals(distribucion)) {
            JOptionPane.showMessageDialog(seleccion,
                    "Seleccione una variable y una distribución.");
        } else {
            CodigoDistribucion cd = new CodigoDistribucion();
            seleccion.setVisible(false);
        }
    }
}
