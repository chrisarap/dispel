package funciones;

public class FuncionDensidad {

    public double[] gamma(double[] arreglo, double alfa, double beta) {
        double[] gamma = new double[arreglo.length];
        HerramientasArreglo arreglo1 = new HerramientasArreglo();
        int a = (int) Math.round(alfa);
        int b = (int) Math.round(beta);
        for (int i = 0; i < arreglo.length; i++) {
            if (a > 0 && b > 0 && arreglo[i] > 0) {
                gamma[i] = beta / arreglo1.Factorial(alfa - 1)
                        * Math.pow(beta * arreglo[i], alfa - 1)
                        * Math.exp(-beta * arreglo[i]);
            } else {
                gamma[i] = 0;
            }
        }
        return gamma;
    }

    public double[] beta(double[] arreglo, double alfa, double beta) {
        double[] betax = new double[arreglo.length];
        HerramientasArreglo arreglo1 = new HerramientasArreglo();
        int a = (int) Math.round(alfa);
        int b = (int) Math.round(beta);
        for (int i = 0; i < arreglo.length; i++) {
            if (a > 0 && b > 0 && arreglo[i] >= 0 && arreglo[i] <= 1) {
                betax[i] = (arreglo1.Factorial(a + b - 1) * Math.pow(arreglo[i], a - 1) * Math.pow(1 - arreglo[i], b - 1))
                        / (arreglo1.Factorial(a - 1) * arreglo1.Factorial(b - 1));
            } else {
                betax[i] = 0;
            }
        }
        return betax;
    }

    public double[] Weibull(double[] arregloOrdenado, double forma, double escala) {
        double[] arregloProbabilidadWeibull = new double[arregloOrdenado.length];

        for (int i = 0; i < arregloOrdenado.length; i++) {
            if (forma > 0 && escala > 0 && arregloOrdenado[i] >= 0) {
                arregloProbabilidadWeibull[i] = forma / escala * Math.pow(arregloOrdenado[i] / escala, forma - 1) * Math.exp(-Math.pow(arregloOrdenado[i] / escala, forma));
            } else {
                arregloProbabilidadWeibull[i] = 0;
            }
        }
        return arregloProbabilidadWeibull;
    }

    public double[] Exponencial(double[] arregloOrdenado, double lambda) {
        double[] arregloProbabilidadExponencial = new double[arregloOrdenado.length];
        for (int i = 0; i < arregloOrdenado.length; i++) {
            if (arregloOrdenado[i] >= 0 && lambda > 0) {
                arregloProbabilidadExponencial[i] = lambda * Math.exp(-lambda * arregloOrdenado[i]);
            } else {
                arregloProbabilidadExponencial[i] = 0;
            }
        }
        return arregloProbabilidadExponencial;
    }

    public double[] Poisson(double[] arregloOrdenado, double lambda) {
        double[] arregloProbabilidadPoisson = new double[arregloOrdenado.length];
        HerramientasArreglo herramientasArreglo = new HerramientasArreglo();
        for (int i = 0; i < arregloOrdenado.length; i++) {
            if (arregloOrdenado[i] > 0) {
                arregloProbabilidadPoisson[i] = (Math.exp(-lambda) * Math.pow(lambda, arregloOrdenado[i])) / herramientasArreglo.Factorial(arregloOrdenado[i]);
            } else {
                arregloProbabilidadPoisson[i] = 0;
            }
        }
        return arregloProbabilidadPoisson;
    }

    public double[] Normal(double[] arregloOrdenado, double media, double desviacion) {
        double[] arregloProbabilidadNormal = new double[arregloOrdenado.length];
        for (int i = 0; i < arregloOrdenado.length; i++) {
            if (desviacion > 0) {
                arregloProbabilidadNormal[i] = Math.exp((-Math.pow(arregloOrdenado[i]
         - media, 2)) / (2 * Math.pow(desviacion, 2))) / (desviacion * Math.sqrt(2 * Math.PI));
            } else {
                arregloProbabilidadNormal[i] = 0;
            }
        }
        return arregloProbabilidadNormal;
    }

    public double[] Lognormal(double[] arregloOrdenado, double media, double desviacion) {
        double[] arregloProbabilidadLogNormal = new double[arregloOrdenado.length];
        for (int i = 0; i < arregloOrdenado.length; i++) {
            if (arregloOrdenado[i] > 0 && desviacion > 0) {
                arregloProbabilidadLogNormal[i] = Math.exp((Math.pow(Math.log(arregloOrdenado[i]) - media, 2)) / (-2 * Math.pow(desviacion, 2))) / (arregloOrdenado[i] * desviacion * Math.sqrt(2 * Math.PI));
            } else {
                arregloProbabilidadLogNormal[i] = 0;
            }
        }
        return arregloProbabilidadLogNormal;
    }

    public double[] Uniforme(double[] arregloOrdenado) {
        double[] arregloProbabilidadUniforme = new double[arregloOrdenado.length];
        for (int i = 0; i < arregloOrdenado.length; i++) {
            arregloProbabilidadUniforme[i] = 1 / (arregloOrdenado[arregloOrdenado.length - 1] - arregloOrdenado[0]);
        }
        return arregloProbabilidadUniforme;
    }

    public double[] Triangular(double[] arregloOrdenado, double minimo, double maximo, double valorProbable) {
        double[] arregloProbabilidadTriangular = new double[arregloOrdenado.length];
        for (int i = 0; i < arregloOrdenado.length; i++) {
            if (arregloOrdenado[i] >= minimo && arregloOrdenado[i] < valorProbable) {
                arregloProbabilidadTriangular[i] = (2 * (arregloOrdenado[i] - minimo)) / ((maximo - minimo) * (valorProbable - minimo));
            } else if (arregloOrdenado[i] > valorProbable && arregloOrdenado[i] <= maximo) {
                arregloProbabilidadTriangular[i] = (2 * (maximo - arregloOrdenado[i])) / ((maximo - minimo) * (maximo - valorProbable));
            } else {
                arregloProbabilidadTriangular[i] = 2 / (maximo - minimo);
            }
        }
        return arregloProbabilidadTriangular;
    }

    public double la_gamma(double x) {
        double[] p = {0.99999999999980993, 676.5203681218851, -1259.1392167224028,
            771.32342877765313, -176.61502916214059, 12.507343278686905,
            -0.13857109526572012, 9.9843695780195716e-6, 1.5056327351493116e-7};
        int g = 7;
        if (x < 0.5) {
            return Math.PI / (Math.sin(Math.PI * x) * la_gamma(1 - x));
        }

        x -= 1;
        double a = p[0];
        double t = x + g + 0.5;
        for (int i = 1; i < p.length; i++) {
            a += p[i] / (x + i);
        }
        return Math.sqrt(2 * Math.PI) * Math.pow(t, x + 0.5) * Math.exp(-t) * a;
    }
}
