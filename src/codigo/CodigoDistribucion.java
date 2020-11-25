package codigo;

import static codigo.CodigoCentral.areaAcumulada;
import static codigo.CodigoCentral.areaEstatica;
import static codigo.CodigoCentral.espesorAcumulado;
import static codigo.CodigoCentral.espesorEstatico;
import static codigo.CodigoCentral.factorAcumulado;
import static codigo.CodigoCentral.factorEstatico;
import static codigo.CodigoCentral.porosidadAcumulada;
import static codigo.CodigoCentral.porosidadEstatica;
import static codigo.CodigoCentral.saturacionAcumulada;
import static codigo.CodigoCentral.saturacionEstatica;
import funciones.FuncionDensidad;
import funciones.HerramientasArreglo;
import interfaces.Distribucion;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import funciones.Estadistica;
import static codigo.CodigoCentral.TAMAÑO_BASICO;

public class CodigoDistribucion {

    // declaracion de variables
    JLabel labelTitulo, labelMinimo, labelMaximo, labelAlfa, labelBeta;
    JTextField txtMinimo, txtMaximo, txtAlfa, txtBeta;

    double minimo, maximo, alfa, beta;

    double[] arregloOrdenado = new double[CodigoCentral.TAMAÑO_BASICO],
            arregloProbabilidad = new double[CodigoCentral.TAMAÑO_BASICO],
            arregloFrecuenciaAcumulada = new double[CodigoCentral.TAMAÑO_BASICO];

    HerramientasArreglo herramientasArreglo = new HerramientasArreglo();
    FuncionDensidad funcionDensidad = new FuncionDensidad();
    // fin de declaracion de variables

    // constructor ventana
    public CodigoDistribucion() {
        Distribucion distribucion = new Distribucion();
        distribucion.setVisible(true);
        distribucion.setResizable(false);
        distribucion.setLocationRelativeTo(null);
        distribucion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public CodigoDistribucion(int numFantasma) {
    }

    // constructor de componentes
    public CodigoDistribucion(JLabel labelTitulo, JLabel labelMinimo, JLabel labelMaximo, JLabel labelAlfa, JLabel labelBeta,
            JTextField txtMinimo, JTextField txtMaximo, JTextField txtAlfa, JTextField txtBeta) {
        this.labelTitulo = labelTitulo;
        this.labelMinimo = labelMinimo;
        this.labelMaximo = labelMaximo;
        this.labelAlfa = labelAlfa;
        this.labelBeta = labelBeta;

        this.txtMinimo = txtMinimo;
        this.txtMaximo = txtMaximo;
        this.txtAlfa = txtAlfa;
        this.txtBeta = txtBeta;
    }

    // reciclar etiquetas
    public void Etiquetas() {
        labelTitulo.setText("Distribución " + CodigoSeleccion.distribucion);

        if ("Exponencial".equals(CodigoSeleccion.distribucion)) {
            labelAlfa.setText("Tasa");
            labelBeta.setVisible(false);
            txtBeta.setVisible(false);
            txtBeta.setText("0");
        }

        if ("Gamma".equals(CodigoSeleccion.distribucion)) {
            labelAlfa.setText("Forma");
            labelBeta.setText("Escala");
        }

        if ("Log normal".equals(CodigoSeleccion.distribucion)) {
            labelAlfa.setText("Media");
            labelBeta.setText("Desviación");
        }

        if ("Normal".equals(CodigoSeleccion.distribucion)) {
            labelAlfa.setText("Media");
            labelBeta.setText("Desviación");
            labelMinimo.setVisible(false);
            labelMaximo.setVisible(false);

            txtMinimo.setText("0");
            txtMaximo.setText("0");
            txtMinimo.setVisible(false);
            txtMaximo.setVisible(false);
        }

        if ("Poisson".equals(CodigoSeleccion.distribucion)) {
            labelAlfa.setText("Tasa");
            labelBeta.setVisible(false);
            txtBeta.setVisible(false);
            txtBeta.setText("0");
        }

        if ("Triangular".equals(CodigoSeleccion.distribucion)) {
            labelAlfa.setText("Valor probable");
            labelBeta.setVisible(false);
            txtBeta.setVisible(false);
            txtBeta.setText("0");
        }

        if ("Uniforme".equals(CodigoSeleccion.distribucion)) {
            labelAlfa.setVisible(false);
            labelBeta.setVisible(false);
            txtAlfa.setVisible(false);
            txtBeta.setVisible(false);
            txtAlfa.setText("0");
            txtBeta.setText("0");
        }

        if ("Weibull".equals(CodigoSeleccion.distribucion)) {
            labelAlfa.setText("Forma");
            labelBeta.setText("Escala");
        }
    }

    // entrada de datos en cada distribucion
    public CodigoDistribucion(double minimo, double maximo, double alfa, double beta) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.alfa = alfa;
        this.beta = beta;
    }

    // minimo y maximo para distribucion normal
    public double minimo(double media, double desviacion) {
        double minimo;
        return minimo = media - (3 * desviacion);
    }

    public double maximo(double media, double desviacion) {
        double maximo;
        return maximo = media + (3 * desviacion);
    }

    public void GenerarArreglosBasicos() {
        if ("Normal".equals(CodigoSeleccion.distribucion)) {
            minimo = minimo(alfa, beta);
            maximo = maximo(alfa, beta);
        }
        arregloOrdenado = herramientasArreglo.OrdenarArreglo(herramientasArreglo.GenerarArregloNumerosAleatorios(minimo, maximo, CodigoCentral.TAMAÑO_BASICO));
        CondicionesDistribuciones();
        arregloFrecuenciaAcumulada = herramientasArreglo.ArregloFrecuenciaAcumulada(arregloProbabilidad);
        IgualarCentrales();
        Imprimir();
    }

    // generar arregoProbabilidad
    public void CondicionesDistribuciones() {
        if ("Beta".equals(CodigoSeleccion.distribucion)) {
            arregloProbabilidad = funcionDensidad.beta(arregloOrdenado, alfa, beta);
        }

        if ("Exponencial".equals(CodigoSeleccion.distribucion)) {
            arregloProbabilidad = funcionDensidad.Exponencial(arregloOrdenado, alfa);
        }

        if ("Gamma".equals(CodigoSeleccion.distribucion)) {
            arregloProbabilidad = funcionDensidad.gamma(arregloOrdenado, alfa, beta);
        }

        if ("Log normal".equals(CodigoSeleccion.distribucion)) {
            arregloProbabilidad = funcionDensidad.Lognormal(arregloOrdenado, alfa, beta);
        }

        if ("Normal".equals(CodigoSeleccion.distribucion)) {
            arregloProbabilidad = funcionDensidad.Normal(arregloOrdenado, alfa, beta);
        }

        if ("Poisson".equals(CodigoSeleccion.distribucion)) {
            arregloProbabilidad = funcionDensidad.Poisson(arregloOrdenado, alfa);
        }

        if ("Triangular".equals(CodigoSeleccion.distribucion)) {
            arregloProbabilidad = funcionDensidad.Triangular(arregloOrdenado, minimo, maximo, alfa);
        }

        if ("Uniforme".equals(CodigoSeleccion.distribucion)) {
            arregloProbabilidad = funcionDensidad.Uniforme(arregloOrdenado);
        }

        if ("Weibull".equals(CodigoSeleccion.distribucion)) {
            arregloProbabilidad = funcionDensidad.Weibull(arregloOrdenado, alfa, beta);
        }
    }

    // iguala los arreglos centraless
    public void IgualarCentrales() {
        if ("Area".equals(CodigoSeleccion.variable)) {
            CodigoCentral.areaEstatica = arregloOrdenado;
            CodigoCentral.areaAcumulada = arregloFrecuenciaAcumulada;
        }

        if ("Espesor".equals(CodigoSeleccion.variable)) {
            CodigoCentral.espesorEstatico = arregloOrdenado;
            CodigoCentral.espesorAcumulado = arregloFrecuenciaAcumulada;
        }

        if ("Porosidad".equals(CodigoSeleccion.variable)) {
            CodigoCentral.porosidadEstatica = arregloOrdenado;
            CodigoCentral.porosidadAcumulada = arregloFrecuenciaAcumulada;
        }

        if ("Saturación".equals(CodigoSeleccion.variable)) {
            CodigoCentral.saturacionEstatica = arregloOrdenado;
            CodigoCentral.saturacionAcumulada = arregloFrecuenciaAcumulada;
        }

        if ("Factor volumétrico".equals(CodigoSeleccion.variable)) {
            CodigoCentral.factorEstatico = arregloOrdenado;
            CodigoCentral.factorAcumulado = arregloFrecuenciaAcumulada;
        }
    }

    public void Imprimir() {

        System.out.println("******************************************");
        System.out.println("***Llenando arreglos con distribuciones***");
        System.out.println("******************************************\n");

        // encabezado
        System.out.printf("%3s\t%10s\t%10s\t%10s\n", "#", "arreglo", "probabilidad", "frecuencia acumulada");

        // mostrar arreglo generado de la variable seleccionada
        for (int i = 0; i < CodigoCentral.TAMAÑO_BASICO; i++) {
            System.out.printf("%3d\t%,10.4f\t%,10.4f\t%,10.4f\n",
                    i + 1, arregloOrdenado[i], arregloProbabilidad[i], arregloFrecuenciaAcumulada[i]);
        }

        System.out.println();

        System.out.println("*********************");
        System.out.println("***arreglo general***");
        System.out.println("*********************\n");

        // segundo encabezado
        System.out.printf("%3s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\n",
                "#", "area", "acumulado", "espesor", "acumulado", "porosidad", "acumulado", "saturacion", "acumulado", "factor", "acumulado");

        // mostrar arreglo general
        for (int i = 0; i < TAMAÑO_BASICO; i++) {
            System.out.printf("%3d\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\n", i + 1,
                    areaEstatica[i], areaAcumulada[i], espesorEstatico[i], espesorAcumulado[i], porosidadEstatica[i],
                    porosidadAcumulada[i], saturacionEstatica[i], saturacionAcumulada[i], factorEstatico[i], factorAcumulado[i]);
        }
        System.out.println();
    }

    public double[] estatico(double minimo, double maximo, double[] estatico) {
        estatico = herramientasArreglo.OrdenarArreglo(herramientasArreglo.GenerarArregloNumerosAleatorios(minimo, maximo, CodigoCentral.TAMAÑO_BASICO));
        return estatico;
    }

    public double[] acumulado(String nombreDistribucion, Estadistica e, double[] estatico, double[] acumulado) {
        arregloProbabilidad = pruebaCondicion(nombreDistribucion, e, estatico);
        acumulado = herramientasArreglo.ArregloFrecuenciaAcumulada(arregloProbabilidad);
        return acumulado;
    }

    public double[] pruebaCondicion(String nombreDistribucion, Estadistica e, double[] arregloOrdenado) {
        switch (nombreDistribucion) {
            case "Beta":
                return funcionDensidad.beta(arregloOrdenado, e.betaAlfa(), e.betaBeta());
            case "Exponencial":
                return funcionDensidad.Exponencial(arregloOrdenado, e.exponencialLambda());
            case "Gamma":
                return funcionDensidad.gamma(arregloOrdenado, e.gammaAlfa(), e.gammaBeta());
            case "Log-normal":
                return funcionDensidad.Lognormal(arregloOrdenado, e.mediaLog(), e.desviacionLog());
            case "Normal":
                return funcionDensidad.Normal(arregloOrdenado, e.media(), e.desviacion());
            case "Poisson":
                return funcionDensidad.Poisson(arregloOrdenado, e.media());
            case "Triangular":
                return funcionDensidad.Triangular(arregloOrdenado, e.minimo(), e.maximo(), e.valorMasProbable());
            case "Uniforme":
                return funcionDensidad.Uniforme(arregloOrdenado);
            default:
                return funcionDensidad.Weibull(arregloOrdenado, e.formaWeibull(), e.escalaWeibull());
        }
    }
}
