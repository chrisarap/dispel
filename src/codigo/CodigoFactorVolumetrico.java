package codigo;

import interfaces.Central;
import interfaces.FactorVolumetrico;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CodigoFactorVolumetrico {

    JLabel lblUno, lblDos, lblTres, lblCuatro;
    JTextField txtUno, txtDos, txtTres, txtCuatro;
    double uno, dos, tres, cuatro;
    String resultado;

    // constructor ventana
    public CodigoFactorVolumetrico() {
        FactorVolumetrico fv = new FactorVolumetrico();
        fv.setVisible(true);
        fv.setResizable(false);
        fv.setLocationRelativeTo(null);
        fv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public CodigoFactorVolumetrico(JLabel lblUno, JLabel lblDos,
            JLabel lblTres, JLabel lblCuatro) {
        this.lblUno = lblUno;
        this.lblDos = lblDos;
        this.lblTres = lblTres;
        this.lblCuatro = lblCuatro;
    }

    public CodigoFactorVolumetrico(double uno, double dos, double tres,
            double cuatro) {
        this.uno = uno;
        this.dos = dos;
        this.tres = tres;
        this.cuatro = cuatro;
    }

    public void CondicionEtiqueta() {
        if ("Glaso".equals(CodigoCentral.seleccionFactor)) {
            lblUno.setText("Razón gas disuelto-petróleo a p ≥ pb, PCN/BN");
            lblDos.setText("Temperatura del yacimiento, ºF");
            lblTres.setText("Gravedad específica del gas, (aire = 1)");
            lblCuatro.setText("Gravedad específica del petróleo, (agua = 1)");
        }

        if ("Standing".equals(CodigoCentral.seleccionFactor)) {
            lblUno.setText("Razón gas disuelto-petróleo a p ≥ pb, PCN/BN");
            lblDos.setText("Temperatura del yacimiento, ºF");
            lblTres.setText("Gravedad específica del gas, (aire = 1)");
            lblCuatro.setText("Gravedad específica del petróleo, (agua = 1)");
        }

        if ("Vázquez y Beggs".equals(CodigoCentral.seleccionFactor)) {
            lblUno.setText("Razón gas disuelto-petróleo a p ≥ pb, PCN/BN");
            lblDos.setText("Temperatura del yacimiento, ºF");
            lblTres.setText("Gravedad específica del gas corregida a 100 lpcm., (aire = 1)");
            lblCuatro.setText("Gravedad específica del petróleo, ºAPI");
        }
    }

    // condicion para seleccionar las correlaciones
    public void CondicionCalculo() {
        if ("Glaso".equals(CodigoCentral.seleccionFactor)) {
            Glaso(uno, dos, tres, cuatro);
        }
        if ("Standing".equals(CodigoCentral.seleccionFactor)) {
            Standing(uno, dos, tres, cuatro);
        }
        if ("Vázquez y Beggs".equals(CodigoCentral.seleccionFactor)) {
            VazquezBeggs(uno, dos, tres, cuatro);
        }

        Central.txtFactor.setText(resultado);
    }

    /*
     * correlaciones para el calculo del factor volumetrico
     */
    public void Glaso(double razon, double temperatura,
            double GravedadEspecificaGas, double GravedadEspecificaPetroleo) {

        double constante = (razon * Math.pow(GravedadEspecificaGas
                / GravedadEspecificaPetroleo, 0.526)) + (0.968 * temperatura);

        double beta = -6.58511
                + (2.91329 * Math.log10(constante))
                - (0.27683 * Math.pow(Math.log10(constante), 2));

        double glaso = 1 + Math.pow(10, beta);

        resultado = String.valueOf(Math.round(glaso * 10000f) / 10000f);

    }

    public void Standing(double razon, double temperatura,
            double GravedadEspecificaGas, double GravedadEspecificaPetroleo) {
        double constante = Math.pow((1.25 * temperatura)
                + (Math.sqrt(GravedadEspecificaGas / GravedadEspecificaPetroleo)
                * razon), 1.2);
        double standing = (0.00012 * constante) + 0.9759;
        resultado = String.valueOf(Math.round(standing * 10000f) / 10000f);
    }

    public void VazquezBeggs(double razon, double temperatura,
            double gravedadGas, double gravedadApi) {
        double[] menor30 = {0.0004677, 0.00001751, -0.000000018106};
        double[] mayor30 = {0.0004670, 0.00001100, 0.0000000013370};
        double vazquezBeggs = 0;

        if (gravedadApi <= 30) {
            vazquezBeggs = 1
                    + (menor30[0] * razon)
                    + (menor30[1] * (temperatura - 60) * (gravedadApi
                    / gravedadGas))
                    + (menor30[2] * razon * (temperatura - 60) * (gravedadApi
                    / gravedadGas));
        }

        if (gravedadApi > 30) {
            vazquezBeggs = 1
                    + (mayor30[0] * razon)
                    + (mayor30[1] * (temperatura - 60) * (gravedadApi
                    / gravedadGas))
                    + (mayor30[2] * razon * (temperatura - 60) * (gravedadApi
                    / gravedadGas));
        }

        resultado = String.valueOf(Math.round(vazquezBeggs * 10000f) / 10000f);
    }

}
