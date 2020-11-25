package funciones;

public class AjusteKolmogorov {

    double[] marcaDeClase, frecuenciaAbsoluta, frecuenciaObservadaAcumulada,
            probabilidad, frecuenciaEsperadaAcumulada, diferenciaAbsoluta;
    double valorMaximo, semiKolmo, finalKolmo;
    int cantidadMarcaClase;

    HerramientasArreglo ha = new HerramientasArreglo();
    FuncionDensidad funcionDensidad = new FuncionDensidad();

    public AjusteKolmogorov(double[] marcaDeClase, double[] frecuenciaAbsoluta, int cantidadMarcaClase) {
        this.marcaDeClase = marcaDeClase;
        this.frecuenciaAbsoluta = frecuenciaAbsoluta;
        this.cantidadMarcaClase = cantidadMarcaClase;
    }

    public double kolmogorovMetodo(double[] frecuenciaEsperadaAcumulada) {
        frecuenciaObservadaAcumulada = ha.ArregloFrecuenciaAcumulada(frecuenciaAbsoluta);
        diferenciaAbsoluta = ha.diferenciaAbsoluta(frecuenciaObservadaAcumulada, frecuenciaEsperadaAcumulada);
        valorMaximo = ha.MayorQue(diferenciaAbsoluta);
        semiKolmo = 1.36 / Math.sqrt(cantidadMarcaClase) - valorMaximo;
        return semiKolmo;
    }

    public double Exponencial(double lambda) {
        probabilidad = funcionDensidad.Exponencial(marcaDeClase, lambda);
        frecuenciaEsperadaAcumulada = ha.ArregloFrecuenciaAcumulada(probabilidad);
        finalKolmo = kolmogorovMetodo(frecuenciaEsperadaAcumulada);
        return finalKolmo;
    }

    public double Normal(double media, double desviacion) {
        probabilidad = funcionDensidad.Normal(marcaDeClase, media, desviacion);
        frecuenciaEsperadaAcumulada = ha.ArregloFrecuenciaAcumulada(probabilidad);
        finalKolmo = kolmogorovMetodo(frecuenciaEsperadaAcumulada);
        return finalKolmo;
    }

    public double Uniforme() {
        probabilidad = funcionDensidad.Uniforme(marcaDeClase);
        frecuenciaEsperadaAcumulada = ha.ArregloFrecuenciaAcumulada(probabilidad);
        finalKolmo = kolmogorovMetodo(frecuenciaEsperadaAcumulada);
        return finalKolmo;
    }

    public double Gamma(double forma, double escala) {
        probabilidad = funcionDensidad.gamma(marcaDeClase, forma, escala);
        frecuenciaEsperadaAcumulada = ha.ArregloFrecuenciaAcumulada(probabilidad);
        finalKolmo = kolmogorovMetodo(frecuenciaEsperadaAcumulada);
        return finalKolmo;
    }

    public double Beta(double alfa, double beta) {
        probabilidad = funcionDensidad.beta(marcaDeClase, alfa, beta);
        frecuenciaEsperadaAcumulada = ha.ArregloFrecuenciaAcumulada(probabilidad);
        finalKolmo = kolmogorovMetodo(frecuenciaEsperadaAcumulada);
        return finalKolmo;
    }

    public double Poisson(double media) {
        probabilidad = funcionDensidad.Poisson(marcaDeClase, media);
        frecuenciaEsperadaAcumulada = ha.ArregloFrecuenciaAcumulada(probabilidad);
        finalKolmo = kolmogorovMetodo(frecuenciaEsperadaAcumulada);
        return finalKolmo;
    }

    public double Triangular(double minimo, double maximo, double valorProbable) {
        probabilidad = funcionDensidad.Triangular(marcaDeClase, minimo, maximo, valorProbable);
        frecuenciaEsperadaAcumulada = ha.ArregloFrecuenciaAcumulada(probabilidad);
        finalKolmo = kolmogorovMetodo(frecuenciaEsperadaAcumulada);
        return finalKolmo;
    }

    public double Weibbull(double forma, double escala) {
        probabilidad = funcionDensidad.Weibull(marcaDeClase, forma, escala);
        frecuenciaEsperadaAcumulada = ha.ArregloFrecuenciaAcumulada(probabilidad);
        finalKolmo = kolmogorovMetodo(frecuenciaEsperadaAcumulada);
        return finalKolmo;
    }
    
    public double Lognormal(double mediaLog, double desviacionLog) {
        probabilidad = funcionDensidad.Lognormal(marcaDeClase, mediaLog, desviacionLog);
        frecuenciaEsperadaAcumulada = ha.ArregloFrecuenciaAcumulada(probabilidad);
        finalKolmo = kolmogorovMetodo(frecuenciaEsperadaAcumulada);
        return finalKolmo;
    }
}
