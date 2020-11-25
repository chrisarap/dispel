package funciones;

public class AjusteAnderson {

    double semiAnder, finalAnder;
    double[] arreglo, probabilidad, frecuenciaRelativaAcumulada,
            arregloMenosUno, OrdenadoNegativo;

    FuncionDensidad funcionDensidad = new FuncionDensidad();
    Herramientas h = new Herramientas();
    Estadistica e;

    public AjusteAnderson(double[] arreglo) {
        this.arreglo = arreglo;
        e = new Estadistica(arreglo);
    }

    public double AndersonMetodo(double[] arregloProbabilidad) {
        frecuenciaRelativaAcumulada = h.frecuenciaAcumulada(h.frecuenciaRelativa(arregloProbabilidad));
        arregloMenosUno = h.arregloMenosUno(frecuenciaRelativaAcumulada);
        OrdenadoNegativo = h.ordenarArreglo(arregloMenosUno);
        semiAnder = e.Anderson(frecuenciaRelativaAcumulada, OrdenadoNegativo);
        return semiAnder;
    }

    public double Exponencial() {
        probabilidad = funcionDensidad.Exponencial(arreglo, e.exponencialLambda());
        finalAnder = AndersonMetodo(probabilidad);
        return finalAnder;
    }

    public double Normal() {
        probabilidad = funcionDensidad.Normal(arreglo, e.media(), e.desviacion());
        finalAnder = AndersonMetodo(probabilidad);
        return finalAnder;
    }

    public double Uniforme() {
        probabilidad = funcionDensidad.Uniforme(arreglo);
        finalAnder = AndersonMetodo(probabilidad);
        return finalAnder;
    }

    public double Gamma() {
        probabilidad = funcionDensidad.gamma(arreglo, e.gammaAlfa(), e.gammaBeta());
        finalAnder = AndersonMetodo(probabilidad);
        return finalAnder;
    }

    public double Beta() {
        probabilidad = funcionDensidad.beta(arreglo, e.betaAlfa(), e.betaBeta());
        finalAnder = AndersonMetodo(probabilidad);
        return finalAnder;
    }

    public double Poisson() {
        probabilidad = funcionDensidad.Poisson(arreglo, e.media());
        finalAnder = AndersonMetodo(probabilidad);
        return finalAnder;
    }

    public double Triangular() {
        probabilidad = funcionDensidad.Triangular(arreglo, e.minimo(), e.maximo(), e.valorMasProbable());
        finalAnder = AndersonMetodo(probabilidad);
        return finalAnder;
    }

    public double Weibbull() {
        probabilidad = funcionDensidad.Weibull(arreglo, e.formaWeibull(), e.escalaWeibull());
        finalAnder = AndersonMetodo(probabilidad);
        return finalAnder;
    }

    public double Lognormal() {
        probabilidad = funcionDensidad.Lognormal(arreglo, e.mediaLog(), e.desviacionLog());
        finalAnder = AndersonMetodo(probabilidad);
        return finalAnder;
    }
}
