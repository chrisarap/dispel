package codigo;

import funciones.HerramientasArreglo;
import interfaces.Grafica;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CodigoGrafica {

    JTable tablaEstadistica, tablaPercentil;

    DecimalFormat separadorMiles = new DecimalFormat("###,###.##");
    DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
    HerramientasArreglo ha = new HerramientasArreglo();

    // codigo ventana
    public CodigoGrafica() {
        Grafica g = new Grafica();
        g.setVisible(true);
        g.setResizable(false);
        g.setLocationRelativeTo(null);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public CodigoGrafica(JTable tablaEstadistica, JTable tablaPercentil) {
        this.tablaEstadistica = tablaEstadistica;
        this.tablaPercentil = tablaPercentil;
    }

    // parametros estadisticos
    public void Estadistica() {
        String[][] matriz = new String[13][2];

        matriz[0][0] = "Iteraciones";
        matriz[0][1] = separadorMiles.format(CodigoCentral.iteraciones);

        matriz[1][0] = "Caso base";
        matriz[1][1] = separadorMiles.format(CodigoCentral.poes);

        matriz[2][0] = "Media";
        matriz[2][1] = separadorMiles.format(CodigoCentral.media);

        matriz[3][0] = "Mediana";
        matriz[3][1] = separadorMiles.format(CodigoCentral.mediana);

        matriz[4][0] = "Moda";
        matriz[4][1] = separadorMiles.format(CodigoCentral.moda);

        matriz[5][0] = "Desviación estándar";
        matriz[5][1] = separadorMiles.format(CodigoCentral.desviacion);

        matriz[6][0] = "Varianza";
        matriz[6][1] = separadorMiles.format(CodigoCentral.varianza);

        matriz[7][0] = "Asimetría";
        matriz[7][1] = separadorMiles.format(CodigoCentral.asimetria);

        matriz[8][0] = "Curtosis";
        matriz[8][1] = separadorMiles.format(CodigoCentral.curtosis);

        matriz[9][0] = "Coeficiente de variabilidad";
        matriz[9][1] = separadorMiles.format(CodigoCentral.coeficienteDeVariabilidad);

        matriz[10][0] = "Mínimo";
        matriz[10][1] = separadorMiles.format(CodigoCentral.poesIteraciones[0]);

        matriz[11][0] = "Máximo";
        matriz[11][1] = separadorMiles.format(CodigoCentral.poesIteraciones[CodigoCentral.poesIteraciones.length - 1]);

        matriz[12][0] = "Error estándar de la media";
        matriz[12][1] = separadorMiles.format(CodigoCentral.ErrorEstandar);

        tablaEstadistica.setModel(new javax.swing.table.DefaultTableModel(
                matriz, new String[]{"Estadística", "Valores"}));
        cell.setHorizontalAlignment(SwingConstants.RIGHT);
        tablaEstadistica.getColumnModel().getColumn(1).setCellRenderer(cell);
    }

    public void Percentiles() {
        CodigoCentral cc = new CodigoCentral(0);
        cc.arregloFrecuencias();
        String[][] matriz = new String[11][2];

        matriz[0][0] = "P0";
        matriz[0][1] = separadorMiles.format(CodigoCentral.percentiles[0]);

        matriz[1][0] = "P10";
        matriz[1][1] = separadorMiles.format(CodigoCentral.percentiles[1]);

        matriz[2][0] = "P20";
        matriz[2][1] = separadorMiles.format(CodigoCentral.percentiles[2]);

        matriz[3][0] = "P30";
        matriz[3][1] = separadorMiles.format(CodigoCentral.percentiles[3]);

        matriz[4][0] = "P40";
        matriz[4][1] = separadorMiles.format(CodigoCentral.percentiles[4]);

        matriz[5][0] = "P50";
        matriz[5][1] = separadorMiles.format(CodigoCentral.percentiles[5]);

        matriz[6][0] = "P60";
        matriz[6][1] = separadorMiles.format(CodigoCentral.percentiles[6]);

        matriz[7][0] = "P70";
        matriz[7][1] = separadorMiles.format(CodigoCentral.percentiles[7]);

        matriz[8][0] = "P80";
        matriz[8][1] = separadorMiles.format(CodigoCentral.percentiles[8]);

        matriz[9][0] = "P90";
        matriz[9][1] = separadorMiles.format(CodigoCentral.percentiles[9]);

        matriz[10][0] = "P100";
        matriz[10][1] = separadorMiles.format(CodigoCentral.percentiles[10]);

        tablaPercentil.setModel(new javax.swing.table.DefaultTableModel(matriz,
                new String[]{"Percentil", "Valores"}));
        cell.setHorizontalAlignment(SwingConstants.RIGHT);
        tablaPercentil.getColumnModel().getColumn(1).setCellRenderer(cell);
    }

    public double Certeza(double valorUno, double valorDos) {

        System.out.println("");

        for (int i = 0; i < CodigoCentral.marcaClase.length; i++) {
            System.out.printf("%,10.0f\t %,10.0f\n",
                    CodigoCentral.marcaClase[i], CodigoCentral.frecuenciaAcumulada[i]);
        }

        if (valorUno <= CodigoCentral.marcaClase[0]) {
            valorUno = CodigoCentral.marcaClase[0];
        }

        if (valorDos >= CodigoCentral.marcaClase[CodigoCentral.marcaClase.length - 1]) {
            valorDos = CodigoCentral.marcaClase[CodigoCentral.marcaClase.length - 1];
        }

        double xa = 0;
        double xb = 0;
        double ya = 0;
        double yb = 0;

        for (int i = 0; i < CodigoCentral.marcaClase.length; i++) {
            if (valorUno >= CodigoCentral.marcaClase[i]) {
                xa = CodigoCentral.marcaClase[i];
                ya = CodigoCentral.frecuenciaAcumulada[i];
            }

            if (valorDos >= CodigoCentral.marcaClase[i]) {
                xb = CodigoCentral.marcaClase[i];
                yb = CodigoCentral.frecuenciaAcumulada[i];
            }
        }

        double certezaUno = ha.Interpolacion(yb, ya, xb, xa, valorUno);
        double certezaDos = ha.Interpolacion(yb, ya, xb, xa, valorDos);

        System.out.println(certezaUno + " " + certezaDos);
        return ((certezaDos - certezaUno) / CodigoCentral.iteraciones) * 100;
    }
}
