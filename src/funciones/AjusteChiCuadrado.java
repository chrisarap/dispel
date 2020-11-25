package funciones;

import java.text.DecimalFormat;

public class AjusteChiCuadrado {

    FuncionDensidad funcionDensidad = new FuncionDensidad();
    HerramientasArreglo herramientasArreglo = new HerramientasArreglo();
    DecimalFormat sm = new DecimalFormat("###,###.##");

    private double[] marcaDeClase, frecuenciaObservada, probabilidad, normalizado, frecuenciaEsperada, arregloChiCuadrado;
    private double suma, semiChi, finalChi;

    public AjusteChiCuadrado(double[] marcaDeClase, double[] frecuenciaObservada) {
        this.marcaDeClase = marcaDeClase;
        this.frecuenciaObservada = frecuenciaObservada;
        suma = herramientasArreglo.suma(frecuenciaObservada);
    }

    private void CrearFrecuenciaEsperada(double[] probabilidad) {
        frecuenciaEsperada = herramientasArreglo.FrecuenciaEsperada(probabilidad, suma);
    }

    private double[] chiCuadradoFormula() {
        arregloChiCuadrado = new double[frecuenciaObservada.length];

        for (int i = 0; i < arregloChiCuadrado.length; i++) {
            arregloChiCuadrado[i] = Math.pow(frecuenciaObservada[i] - frecuenciaEsperada[i], 2) / frecuenciaEsperada[i];
        }
        return arregloChiCuadrado;
    }

    private double CondicionChi(double numeroClase) {
        double interpolacion = (1.185410072 * numeroClase) + 2.655589928;
        return interpolacion;
    }

    public double Exponencial(double lambda) {
        probabilidad = funcionDensidad.Exponencial(marcaDeClase, lambda);
        normalizado = herramientasArreglo.normalizar(probabilidad, herramientasArreglo.suma(probabilidad));
        CrearFrecuenciaEsperada(normalizado);
        semiChi = herramientasArreglo.suma(chiCuadradoFormula());
        finalChi = CondicionChi(frecuenciaObservada.length) - semiChi;
        return finalChi;
    }

    public double Normal(double media, double desviacion) {
        probabilidad = funcionDensidad.Normal(marcaDeClase, media, desviacion);
        normalizado = herramientasArreglo.normalizar(probabilidad, herramientasArreglo.suma(probabilidad));
        CrearFrecuenciaEsperada(normalizado);
        semiChi = herramientasArreglo.suma(chiCuadradoFormula());
        finalChi = CondicionChi(frecuenciaObservada.length) - semiChi;
        return finalChi;
    }

    public double Uniforme() {
        probabilidad = funcionDensidad.Uniforme(marcaDeClase);
        normalizado = herramientasArreglo.normalizar(probabilidad, herramientasArreglo.suma(probabilidad));
        CrearFrecuenciaEsperada(normalizado);
        semiChi = herramientasArreglo.suma(chiCuadradoFormula());
        finalChi = CondicionChi(frecuenciaObservada.length) - semiChi;
        return finalChi;
    }

    public double Gamma(double alfa, double beta) {
        probabilidad = funcionDensidad.gamma(marcaDeClase, alfa, beta);
        normalizado = herramientasArreglo.normalizar(probabilidad, herramientasArreglo.suma(probabilidad));
        CrearFrecuenciaEsperada(normalizado);
        semiChi = herramientasArreglo.suma(chiCuadradoFormula());
        finalChi = CondicionChi(frecuenciaObservada.length) - semiChi;
        return finalChi;

    }

    public double Beta(double alfa, double beta) {
        probabilidad = funcionDensidad.beta(marcaDeClase, alfa, beta);
        normalizado = herramientasArreglo.normalizar(probabilidad, herramientasArreglo.suma(probabilidad));
        CrearFrecuenciaEsperada(normalizado);
        semiChi = herramientasArreglo.suma(chiCuadradoFormula());
        finalChi = CondicionChi(frecuenciaObservada.length) - semiChi;
        return finalChi;
    }

    public double Poisson(double media) {
        probabilidad = funcionDensidad.Poisson(marcaDeClase, media);
        normalizado = herramientasArreglo.normalizar(probabilidad, herramientasArreglo.suma(probabilidad));
        CrearFrecuenciaEsperada(normalizado);
        semiChi = herramientasArreglo.suma(chiCuadradoFormula());
        finalChi = CondicionChi(frecuenciaObservada.length) - semiChi;
        return finalChi;
    }

    public double Triangular(double minimo, double maximo, double valorProbable) {
        probabilidad = funcionDensidad.Triangular(marcaDeClase, minimo, maximo, valorProbable);
        normalizado = herramientasArreglo.normalizar(probabilidad, herramientasArreglo.suma(probabilidad));
        CrearFrecuenciaEsperada(normalizado);
        semiChi = herramientasArreglo.suma(chiCuadradoFormula());
        finalChi = CondicionChi(frecuenciaObservada.length) - semiChi;
        return finalChi;
    }

    public double Weibbull(double forma, double escala) {
        probabilidad = funcionDensidad.Weibull(marcaDeClase, forma, escala);
        normalizado = herramientasArreglo.normalizar(probabilidad, herramientasArreglo.suma(probabilidad));
        CrearFrecuenciaEsperada(normalizado);
        semiChi = herramientasArreglo.suma(chiCuadradoFormula());
        finalChi = CondicionChi(frecuenciaObservada.length) - semiChi;
        return finalChi;
    }

    public double Lognormal(double mediaLog, double desviacionLog) {
        probabilidad = funcionDensidad.Lognormal(marcaDeClase, mediaLog, desviacionLog);
        normalizado = herramientasArreglo.normalizar(probabilidad, herramientasArreglo.suma(probabilidad));
        CrearFrecuenciaEsperada(normalizado);
        semiChi = herramientasArreglo.suma(chiCuadradoFormula());
        finalChi = CondicionChi(frecuenciaObservada.length) - semiChi;
        return finalChi;
    }
}
