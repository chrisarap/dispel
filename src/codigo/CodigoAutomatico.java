package codigo;

import static codigo.CodigoCentral.iteraciones;
import static codigo.CodigoCentral.porosidadAleatoria;
import funciones.AjusteAnderson;
import funciones.Estadistica;
import funciones.Herramientas;
import funciones.HerramientasArreglo;
import interfaces.Automatico;
import javax.swing.JFrame;

public class CodigoAutomatico {

    Herramientas h = new Herramientas();
    private double arrayArea[], arrayEspesor[], arrayPorosidad[], arraySaturacion[], arrayFactor[];

    public CodigoAutomatico() {
        Automatico a = new Automatico();
        a.setVisible(true);
        a.setResizable(false);
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public CodigoAutomatico(double[] area, double[] espesor, double[] porosidad, double[] saturacion, double[] factor) {
        this.arrayArea = h.ordenarArreglo(area);
        this.arrayEspesor = h.ordenarArreglo(espesor);
        this.arrayPorosidad = h.ordenarArreglo(porosidad);
        this.arraySaturacion = h.ordenarArreglo(saturacion);
        this.arrayFactor = h.ordenarArreglo(factor);
    }

    private String mostrarTablaDistribuciones(double[] variable) {
        AjusteAnderson anderson = new AjusteAnderson(variable);

        String[][] matriz = new String[9][2];

        matriz[0][0] = "Beta";
        matriz[0][1] = String.valueOf(anderson.Beta());

        matriz[1][0] = "Exponencial";
        matriz[1][1] = String.valueOf(anderson.Exponencial());

        matriz[2][0] = "Gamma";
        matriz[2][1] = String.valueOf(anderson.Gamma());

        matriz[3][0] = "Log-normal";
        matriz[3][1] = String.valueOf(anderson.Lognormal());

        matriz[4][0] = "Normal";
        matriz[4][1] = String.valueOf(anderson.Normal());

        matriz[5][0] = "Poisson";
        matriz[5][1] = String.valueOf(anderson.Poisson());

        matriz[6][0] = "Triangular";
        matriz[6][1] = String.valueOf(anderson.Triangular());

        matriz[7][0] = "Uniforme";
        matriz[7][1] = String.valueOf(anderson.Uniforme());

        matriz[8][0] = "Weibull";
        matriz[8][1] = String.valueOf(anderson.Weibbull());

        for (int i = 0; i < 9; i++) {
            System.out.printf("%15s %40.4f\n", matriz[i][0], Double.parseDouble(matriz[i][1]));
        }
        System.out.println(h.nombreDistribucion(matriz, h.MayorQue(matriz)) + " " + h.MayorQue(matriz));
        return h.nombreDistribucion(matriz, h.MayorQue(matriz));
    }

    private void iniciadorBasico() {
        // if (arrayArea.length == 1) {
        for (int i = 0; i < CodigoCentral.TAMAÑO_BASICO; i++) {
            CodigoCentral.areaEstatica[i] = h.promedio(arrayArea);
            CodigoCentral.areaAcumulada[i] = 1;

        }
        // if (arrayEspesor.length == 1) {
        for (int i = 0; i < CodigoCentral.TAMAÑO_BASICO; i++) {
            CodigoCentral.espesorEstatico[i] = h.promedio(arrayEspesor);
            CodigoCentral.espesorAcumulado[i] = 1;

        }
        // if (arrayPorosidad.length == 1) {
        for (int i = 0; i < CodigoCentral.TAMAÑO_BASICO; i++) {
            CodigoCentral.porosidadEstatica[i] = h.promedio(arrayPorosidad);
            CodigoCentral.porosidadAcumulada[i] = 1;

        }
        // if (arraySaturacion.length == 1) {
        for (int i = 0; i < CodigoCentral.TAMAÑO_BASICO; i++) {
            CodigoCentral.saturacionEstatica[i] = h.promedio(arraySaturacion);
            CodigoCentral.saturacionAcumulada[i] = 1;

        }
        //  if (arrayFactor.length == 1) {
        for (int i = 0; i < CodigoCentral.TAMAÑO_BASICO; i++) {
            CodigoCentral.factorEstatico[i] = h.promedio(arrayFactor);
            CodigoCentral.factorAcumulado[i] = 1;

        }
    }

    public void iniciador() {
        iniciadorBasico();

        Estadistica estaArea = new Estadistica(arrayArea);
        Estadistica estaEspesor = new Estadistica(arrayEspesor);
        Estadistica estaPorosidad = new Estadistica(arrayPorosidad);
        Estadistica estaSaturacion = new Estadistica(arraySaturacion);
        Estadistica estaFactor = new Estadistica(arrayFactor);

        CodigoDistribucion cd = new CodigoDistribucion(0);

        CodigoCentral.areaEstatica = cd.estatico(estaArea.limitesAleatorios()[0], estaArea.limitesAleatorios()[1], CodigoCentral.areaEstatica);
        CodigoCentral.espesorEstatico = cd.estatico(estaEspesor.limitesAleatorios()[0], estaEspesor.limitesAleatorios()[1], CodigoCentral.espesorEstatico);
        CodigoCentral.porosidadEstatica = cd.estatico(estaPorosidad.limitesAleatorios()[0], estaPorosidad.limitesAleatorios()[1], CodigoCentral.porosidadEstatica);
        CodigoCentral.saturacionEstatica = cd.estatico(estaSaturacion.limitesAleatorios()[0], estaSaturacion.limitesAleatorios()[1], CodigoCentral.saturacionEstatica);
        CodigoCentral.factorEstatico = cd.estatico(estaFactor.limitesAleatorios()[0], estaFactor.limitesAleatorios()[1], CodigoCentral.factorEstatico);

        CodigoCentral.areaAcumulada = cd.acumulado(mostrarTablaDistribuciones(arrayArea), estaArea, CodigoCentral.areaEstatica, CodigoCentral.areaAcumulada);
        CodigoCentral.espesorAcumulado = cd.acumulado(mostrarTablaDistribuciones(arrayEspesor), estaEspesor, CodigoCentral.espesorEstatico, CodigoCentral.espesorAcumulado);
        CodigoCentral.porosidadAcumulada = cd.acumulado(mostrarTablaDistribuciones(arrayPorosidad), estaPorosidad, CodigoCentral.porosidadEstatica, CodigoCentral.porosidadAcumulada);
        CodigoCentral.saturacionAcumulada = cd.acumulado(mostrarTablaDistribuciones(arraySaturacion), estaSaturacion, CodigoCentral.saturacionEstatica, CodigoCentral.saturacionAcumulada);
        CodigoCentral.factorAcumulado = cd.acumulado(mostrarTablaDistribuciones(arrayFactor), estaFactor, CodigoCentral.factorEstatico, CodigoCentral.factorAcumulado);

        CodigoCentral.areaAleatoria = h.GenerarArregloNumerosAleatorios(0, 1, iteraciones);
        CodigoCentral.areaIteraciones = h.InterpolacionRelleno(CodigoCentral.areaEstatica, CodigoCentral.areaAcumulada, CodigoCentral.areaAleatoria);

        CodigoCentral.espesorAleatorio = h.GenerarArregloNumerosAleatorios(0, 1, iteraciones);
        CodigoCentral.espesorIteraciones = h.InterpolacionRelleno(CodigoCentral.espesorEstatico, CodigoCentral.espesorAcumulado, CodigoCentral.espesorAleatorio);

        CodigoCentral.porosidadAleatoria = h.GenerarArregloNumerosAleatorios(0, 1, iteraciones);
        CodigoCentral.porosidadIteraciones = h.InterpolacionRelleno(CodigoCentral.porosidadEstatica, CodigoCentral.porosidadAcumulada, CodigoCentral.porosidadAleatoria);

        CodigoCentral.saturacionAleatoria = h.GenerarArregloNumerosAleatorios(0, 1, iteraciones);
        CodigoCentral.saturacionIteraciones = h.InterpolacionRelleno(CodigoCentral.saturacionEstatica, CodigoCentral.saturacionAcumulada, CodigoCentral.saturacionAleatoria);

        CodigoCentral.factorAleatorio = h.GenerarArregloNumerosAleatorios(0, 1, iteraciones);
        CodigoCentral.factorIteraciones = h.InterpolacionRelleno(CodigoCentral.factorEstatico, CodigoCentral.factorAcumulado, CodigoCentral.factorAleatorio);

        // arreglo 500
        for (int i = 0; i < CodigoCentral.porosidadEstatica.length; i++) {
            System.out.printf("%02d %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f\n",
                    i + 1,
                    CodigoCentral.areaEstatica[i], CodigoCentral.areaAcumulada[i],
                    CodigoCentral.espesorEstatico[i], CodigoCentral.espesorAcumulado[i],
                    CodigoCentral.porosidadEstatica[i], CodigoCentral.porosidadAcumulada[i],
                    CodigoCentral.saturacionEstatica[i], CodigoCentral.saturacionAcumulada[i],
                    CodigoCentral.factorEstatico[i], CodigoCentral.factorAcumulado[i]);
        }

        System.out.println("");

        // arreglo iteraciones
        for (int i = 0; i < porosidadAleatoria.length; i++) {
            System.out.printf("%02d %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f\n",
                    i + 1,
                    CodigoCentral.areaAleatoria[i], CodigoCentral.areaIteraciones[i],
                    CodigoCentral.espesorAleatorio[i], CodigoCentral.espesorIteraciones[i],
                    CodigoCentral.porosidadAleatoria[i], CodigoCentral.porosidadIteraciones[i],
                    CodigoCentral.saturacionAleatoria[i], CodigoCentral.saturacionIteraciones[i],
                    CodigoCentral.factorAleatorio[i], CodigoCentral.factorIteraciones[i]
            );
        }
        iniciadorPoes();
    }

    private void iniciadorPoes() {
        // arreglo poes        
        HerramientasArreglo arreglo = new HerramientasArreglo();
        CodigoCentral.poesIteraciones = arreglo.CalcularPoesArreglo(
                CodigoCentral.areaAleatoria,
                CodigoCentral.espesorAleatorio,
                CodigoCentral.porosidadAleatoria,
                CodigoCentral.saturacionAleatoria,
                CodigoCentral.factorAleatorio);

        for (int i = 0; i < CodigoCentral.poesIteraciones.length; i++) {
            System.out.printf("%02d %10.4f\n", i + 1, CodigoCentral.poesIteraciones[i]);
        }

        CodigoCentral cc = new CodigoCentral(0);
        cc.CondicionIteracion();
        CodigoGrafica cg = new CodigoGrafica();
    }
}
