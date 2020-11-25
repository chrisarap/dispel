package codigo;

import funciones.Estadistica;
import funciones.Herramientas;
import funciones.HerramientasArreglo;
import interfaces.Central;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CodigoCentral {

    Central central;
    JTextField txtArea, txtEspesor, txtPorosidad, txtSaturacion, txtFactor;

    double area, espesor, porosidad, saturacion, factor;

    public static double poes, sumaFrecuencia, media, mediana, moda, varianza, desviacion,
            asimetria, curtosis, coeficienteDeVariabilidad, ErrorEstandar;

    public static int iteraciones, TAMAÑO_BASICO = 50;
    public static String seleccionFactor;

    public static double[] areaEstatica = new double[TAMAÑO_BASICO],
            espesorEstatico = new double[TAMAÑO_BASICO],
            porosidadEstatica = new double[TAMAÑO_BASICO],
            saturacionEstatica = new double[TAMAÑO_BASICO],
            factorEstatico = new double[TAMAÑO_BASICO];

    public static double[] areaAcumulada = new double[TAMAÑO_BASICO],
            espesorAcumulado = new double[TAMAÑO_BASICO],
            porosidadAcumulada = new double[TAMAÑO_BASICO],
            saturacionAcumulada = new double[TAMAÑO_BASICO],
            factorAcumulado = new double[TAMAÑO_BASICO];

    public static double[] areaAleatoria = new double[iteraciones],
            espesorAleatorio = new double[iteraciones],
            porosidadAleatoria = new double[iteraciones],
            saturacionAleatoria = new double[iteraciones],
            factorAleatorio = new double[iteraciones];

    public static double[] areaIteraciones = new double[iteraciones],
            espesorIteraciones = new double[iteraciones],
            porosidadIteraciones = new double[iteraciones],
            saturacionIteraciones = new double[iteraciones],
            factorIteraciones = new double[iteraciones],
            poesIteraciones = new double[iteraciones],
            posicionPercentiles,
            percentiles;

    public static double[] limiteInferior = new double[iteraciones],
            limiteSuperior = new double[iteraciones],
            marcaClase = new double[iteraciones],
            frecuenciaAbsoluta = new double[iteraciones],
            frecuenciaAcumulada = new double[iteraciones];

    HerramientasArreglo herramientasArreglo = new HerramientasArreglo();

    // constructor de la ventana central
    public CodigoCentral() {
        Central c = new Central();
        c.setVisible(true);
        c.setResizable(false);
        c.setLocationRelativeTo(null);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // constructor para la seleccion del factor volumetrico del combobox
    public CodigoCentral(String seleccionFactor) {
        this.seleccionFactor = seleccionFactor;
    }

    // constructor recibe los datos de central para el poes volumetrico
    public CodigoCentral(double area, double espesor, double porosidad, double saturacion, double factor) {
        this.area = area;
        this.espesor = espesor;
        this.porosidad = porosidad;
        this.saturacion = saturacion;
        this.factor = factor;
    }

    public CodigoCentral(Central central, int iteraciones) {
        this.central = central;
        this.iteraciones = iteraciones;
    }

    public CodigoCentral(int fantasma) {
    }

    // poes volumetrico
    public void CalcularPoes() {
        poes = 7758 * area * espesor * porosidad * saturacion / factor;
    }

    // llena los arreglos estaticos con las variables iniciales
    public void IgualarArreglosEstaticos() {
        for (int i = 0; i < TAMAÑO_BASICO; i++) {
            areaEstatica[i] = area;
            espesorEstatico[i] = espesor;
            porosidadEstatica[i] = porosidad;
            saturacionEstatica[i] = saturacion;
            factorEstatico[i] = factor;
        }
    }

    public void IgualarArreglosAcumulados() {
        for (int i = 0; i < TAMAÑO_BASICO; i++) {
            areaAcumulada[i] = 1;
            espesorAcumulado[i] = 1;
            porosidadAcumulada[i] = 1;
            saturacionAcumulada[i] = 1;
            factorAcumulado[i] = 1;
        }
    }

    public void CondicionIteracion() {

        // genera arreglos aleatorios entre 0 y 1 segun el numero de iteraciones
        areaAleatoria = herramientasArreglo.GenerarArregloNumerosAleatorios(0, 1, iteraciones);
        espesorAleatorio = herramientasArreglo.GenerarArregloNumerosAleatorios(0, 1, iteraciones);
        porosidadAleatoria = herramientasArreglo.GenerarArregloNumerosAleatorios(0, 1, iteraciones);
        saturacionAleatoria = herramientasArreglo.GenerarArregloNumerosAleatorios(0, 1, iteraciones);
        factorAleatorio = herramientasArreglo.GenerarArregloNumerosAleatorios(0, 1, iteraciones);

        // interpolacion
        areaIteraciones = herramientasArreglo.InterpolacionRelleno(areaEstatica, areaAcumulada, areaAleatoria);
        espesorIteraciones = herramientasArreglo.InterpolacionRelleno(espesorEstatico, espesorAcumulado, espesorAleatorio);
        porosidadIteraciones = herramientasArreglo.InterpolacionRelleno(porosidadEstatica, porosidadAcumulada, porosidadAleatoria);
        saturacionIteraciones = herramientasArreglo.InterpolacionRelleno(saturacionEstatica, saturacionAcumulada, saturacionAleatoria);
        factorIteraciones = herramientasArreglo.InterpolacionRelleno(factorEstatico, factorAcumulado, factorAleatorio);

        // calculo del arreglo del poes
        poesIteraciones = herramientasArreglo.CalcularPoesArreglo(areaIteraciones, espesorIteraciones, porosidadIteraciones, saturacionIteraciones, factorIteraciones);

        arregloFrecuencias();
        ImprimirDos();
    }

    public void arregloFrecuencias() {
        Estadistica estadistica = new Estadistica(poesIteraciones);
        Herramientas h = new Herramientas();

        // limites
        limiteInferior = herramientasArreglo.LimiteInferior(poesIteraciones);
        limiteSuperior = herramientasArreglo.LimiteSuperior(poesIteraciones);

        // tabla estadistica
        marcaClase = herramientasArreglo.MarcaDeClase(poesIteraciones, limiteInferior, limiteSuperior);
        frecuenciaAbsoluta = herramientasArreglo.FrecuenciaAbsoluta(poesIteraciones, limiteInferior, limiteSuperior);
        frecuenciaAcumulada = herramientasArreglo.FrecuenciaAcumulada(frecuenciaAbsoluta);

        // parametros estadisticos
        sumaFrecuencia = herramientasArreglo.suma(frecuenciaAbsoluta);
        posicionPercentiles = estadistica.posicionPercentil(sumaFrecuencia);
        percentiles = estadistica.Percentil(posicionPercentiles, frecuenciaAcumulada, limiteInferior, limiteSuperior);
        media = estadistica.MediaX(marcaClase, frecuenciaAbsoluta);
        mediana = estadistica.Mediana(frecuenciaAbsoluta, frecuenciaAcumulada, limiteInferior, limiteSuperior, sumaFrecuencia);
        moda = estadistica.Modismo(poesIteraciones);
        varianza = estadistica.VarianzaX(marcaClase, frecuenciaAbsoluta, media);
        desviacion = estadistica.DesviacionX(varianza);
        asimetria = estadistica.Asimetria(media, mediana, desviacion);
        curtosis = estadistica.Curtosis(marcaClase, frecuenciaAbsoluta, media, desviacion);
        coeficienteDeVariabilidad = estadistica.Coeficiente(media, desviacion);
        ErrorEstandar = estadistica.ErrorEstandar(frecuenciaAbsoluta, desviacion);
    }

    // imprime los arreglos estatico y acumulado en pantalla
    public void imprimirArreglosEstaticosAcumulados() {
        System.out.println("*****************************************");
        System.out.println("***Llenando arreglos estatico y basico***");
        System.out.println("*****************************************\n");

        // encabezado
        System.out.printf("%2s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\n",
                "#", "area", "acum", "espesor", "acum", "porosidad", "acum", "saturacion", "acum", "factor", "acum");

        // valores dentro de los arreglos estaticos y acumulados
        for (int i = 0; i < TAMAÑO_BASICO; i++) {
            System.out.printf("%2d\t%,10.4f\t %,10.4f\t %,10.4f\t %,10.4f\t %,10.4f\t %,10.4f\t %,10.4f\t %,10.4f\t %,10.4f\t %,10.4f\n",
                    i + 1,
                    areaEstatica[i], areaAcumulada[i],
                    espesorEstatico[i], espesorAcumulado[i],
                    porosidadEstatica[i], porosidadAcumulada[i],
                    saturacionEstatica[i], saturacionAcumulada[i],
                    factorEstatico[i], factorAcumulado[i]);
        }
        System.out.println();
    }

    public void ImprimirDos() {
        /*
        *
        *
        *
         */

        System.out.println("**********************");
        System.out.println("***Arreglos finales***");
        System.out.println("**********************\n");

        System.out.printf("%3s%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\n",
                "#",
                "areaIte", "areaAle",
                "espeIte", "espeAle",
                "poroIte", "poroAle",
                "satuIte", "satuAle",
                "factIte", "factAle");

        for (int i = 0; i < iteraciones; i++) {
            System.out.printf("%3d\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\t%,10.4f\n",
                    i + 1,
                    areaIteraciones[i], areaAleatoria[i],
                    espesorIteraciones[i], espesorAleatorio[i],
                    porosidadIteraciones[i], porosidadAleatoria[i],
                    saturacionIteraciones[i], saturacionAleatoria[i],
                    factorIteraciones[i], factorAleatorio[i]);
        }
        System.out.println();

        /*
        *
        *
        *
         */
        System.out.println("************************");
        System.out.println("***********POES*********");
        System.out.println("************************\n");

        for (int i = 0; i < poesIteraciones.length; i++) {
            System.out.printf("%d\t%,10.0f\n", i + 1, poesIteraciones[i]);
        }

        /*
        *
        *
        *
         */
        System.out.println("************************");
        System.out.println("***Tabla estadisticos***");
        System.out.println("************************\n");

        System.out.printf("%3s%10s\t%10s\t%10s\t%10s\t%10s\n", "#", "limite inferior", "limite superior", "marca de clase", "frecuencia absoluta", "frecuencia acumulada");

        for (int i = 0; i < limiteInferior.length; i++) {
            System.out.printf("%3d\t%,10.0f\t%,10.0f\t%,10.0f\t%,10.0f\t%,10.0f\n",
                    i + 1,
                    limiteInferior[i],
                    limiteSuperior[i],
                    marcaClase[i],
                    frecuenciaAbsoluta[i],
                    frecuenciaAcumulada[i]);
        }
        System.out.println();

        /*
        *
        *
        *
         */
        System.out.println("*****************");
        System.out.println("***Percentiles***");
        System.out.println("*****************\n");

        System.out.printf("%10s\t%10s\n", "posicion", "percentil");
        for (int i = 0; i < posicionPercentiles.length; i++) {
            System.out.printf("%,10.0f\t%,10.0f\n", posicionPercentiles[i], percentiles[i]);
        }
        System.out.println();

        /*
        *
        *
        *
         */
        System.out.println("************************");
        System.out.println("***Datos estadisticos***");
        System.out.println("************************\n");

        System.out.printf("%s\t%,10.4f\n%s\t%,10.4f\n%s\t%,10.4f\n%s\t%,10.4f\n%s\t%,10.4f\n%s\t%,10.4f\n%s\t%,10.4f\n%s\t%,10.4f\n%s\t%,10.4f\n",
                "media", media,
                "mediana", mediana,
                "moda", moda,
                "varianza", varianza,
                "desviacion", desviacion,
                "asimetria", asimetria,
                "curtosis", curtosis,
                "coeficiente de variabilidad", coeficienteDeVariabilidad,
                "Error estandar", ErrorEstandar);
        System.out.println();
    }

    // factor volumetrico condicion alerta
    public void MensajeAlerta(String seleccionFactor, Central central) {
        if ("Seleccionar".equals(seleccionFactor)) {
            JOptionPane.showMessageDialog(central, "Seleccione una correlación");
        } else {
            CodigoFactorVolumetrico cfv = new CodigoFactorVolumetrico();
        }
    }

}
