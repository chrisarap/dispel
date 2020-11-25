package funciones;

import java.util.ArrayList;

public class Estadistica {

    Herramientas h = new Herramientas();
    private double arreglo[], marcaDeClase[], frecuenciaAbsoluta[], suma, media,
            varianza, desviacion, gammaBeta, gammaAlfa, betaAlfa, betaBeta,
            exponencialLambda, minimo, maximo, mediaLog, varianzaLog,
            desviacionLog;

    public Estadistica(double[] arreglo) {
        this.arreglo = arreglo;
    }

    public double media() {
        suma = h.suma(arreglo);
        return media = suma / arreglo.length;
    }

    public double varianza() {
        media();
        double aux = 0;
        for (int i = 0; i < arreglo.length; i++) {
            aux += Math.pow(arreglo[i] - media, 2);
        }
        return varianza = aux / arreglo.length;
    }

    public double desviacion() {
        varianza();
        return desviacion = Math.sqrt(varianza);
    }

    public double gammaAlfa() {
        gammaBeta();
        return gammaAlfa = media * gammaBeta;
    }

    public double gammaBeta() {
        media();
        varianza();
        return gammaBeta = media / varianza;
    }

    public double betaAlfa() {
        media();
        varianza();
        return betaAlfa = -media / varianza * (Math.pow(media, 2) - media + varianza);
    }

    public double betaBeta() {
        media();
        varianza();
        return betaBeta = (media - 1) / varianza * (Math.pow(media, 2) - media + varianza);
    }

    public double exponencialLambda() {
        media();
        return exponencialLambda = 1 / media;
    }

    public double minimo() {
        double aux = arreglo[0];
        for (int i = 1; i < arreglo.length; i++) {
            if (aux > arreglo[i]) {
                aux = arreglo[i];
            }
        }
        return minimo = aux;
    }

    public double maximo() {
        double aux = arreglo[0];
        for (int i = 1; i < arreglo.length; i++) {
            if (aux < arreglo[i]) {
                aux = arreglo[i];
            }
        }
        return maximo = aux;
    }

    private double[] lnx() {
        double lnx[] = new double[arreglo.length];
        for (int i = 0; i < arreglo.length; i++) {
            lnx[i] = Math.log(arreglo[i]);
        }
        return lnx;
    }

    public double mediaLog() {
        suma = h.suma(lnx());
        return mediaLog = suma / lnx().length;
    }

    public double varianzaLog() {
        mediaLog();
        double aux = 0;
        for (int i = 0; i < arreglo.length; i++) {
            aux += Math.pow(lnx()[i] - mediaLog, 2);
        }
        return varianzaLog = aux / arreglo.length;
    }

    public double desviacionLog() {
        varianzaLog();
        return desviacionLog = Math.sqrt(varianzaLog);
    }

    private void setMarcaDeClase() {
        marcaDeClase = h.marcaDeClase(arreglo);
    }

    private void setFrecuenciaAbsoluta() {
        setMarcaDeClase();
        frecuenciaAbsoluta = h.frecuenciaAbsoluta(arreglo, marcaDeClase);
    }

    public double valorMasProbable() {
        setFrecuenciaAbsoluta();
        double auxiliar = h.mayorQue(frecuenciaAbsoluta);
        int contador = 0;
        for (int i = 0; i < frecuenciaAbsoluta.length; i++) {
            if (auxiliar == frecuenciaAbsoluta[i]) {
                contador = i;
            }
        }
        auxiliar = marcaDeClase[contador];
        return auxiliar;
    }

    private double[] xWeibull() {
        setMarcaDeClase();
        double[] xWeibull = new double[marcaDeClase.length];
        for (int i = 0; i < marcaDeClase.length; i++) {
            if (marcaDeClase[i] <= 0) {
                xWeibull[i] = 0;
            } else {
                xWeibull[i] = Math.log(marcaDeClase[i]);
            }
        }
        return xWeibull;
    }

    private double[] yWeibull() {
        setFrecuenciaAbsoluta();
        double[] yWeibull = new double[frecuenciaAbsoluta.length],
                aux = h.frecuenciaAcumulada(h.frecuenciaRelativa(frecuenciaAbsoluta));

        for (int i = 0; i < aux.length; i++) {
            if (1 - aux[i] <= 0) {
                yWeibull[i] = 0;
            } else {
                yWeibull[i] = Math.log(-Math.log(1 - aux[i]));
            }
        }
        return yWeibull;
    }

    public double formaWeibull() {
        double forma;
        forma = (yWeibull()[yWeibull().length - 1] - yWeibull()[0])
                / (xWeibull()[xWeibull().length - 1] - xWeibull()[0]);
        return forma;
    }

    public double escalaWeibull() {
        double intercepto[] = new double[xWeibull().length],
                aux = 0;
        for (int i = 0; i < xWeibull().length; i++) {
            intercepto[i] = yWeibull()[i] - (formaWeibull() * xWeibull()[i]);
            aux += intercepto[i];
        }
        return Math.exp((aux / intercepto.length) / -formaWeibull());
    }

    public double[] limitesAleatorios() {
        media();
        desviacion();
        double[] aleatorios = new double[2];
        aleatorios[0] = media - 3 * desviacion;
        aleatorios[1] = media + 3 * desviacion;
        return aleatorios;
    }

    /*
    *
    *
    *
    *
    *
    *
    *
    *
    *
     */
    public double[] posicionPercentil(double sumaFrecuencia) {
        double[] posicion = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        double[] posicionPercentil = new double[11];

        for (int i = 0; i < posicion.length; i++) {
            posicionPercentil[i] = posicion[i] * sumaFrecuencia / 100;
        }

        return posicionPercentil;
    }

    public double[] Percentil(double[] posicionperce, double[] F, double[] li, double[] ls) {
        double[] percentil = new double[11];

        for (int i = 0; i < 11; i++) {
            if (posicionperce[i] <= F[0]) {

                percentil[i] = ((posicionperce[i] / F[0]) * (ls[0] - li[0])) + li[0];

            } else if (posicionperce[i] >= F[F.length - 1]) {

                percentil[i] = (((posicionperce[i] - F[F.length - 2]) / (F[F.length - 1] - F[F.length - 2])) * (ls[F.length - 1] - li[F.length - 1])) + li[F.length - 1];

            } else {
                int cursor = 1;
                while (posicionperce[i] >= F[cursor]) {
                    cursor++;
                }
                percentil[i] = (((posicionperce[i] - F[cursor - 1]) / (F[cursor] - F[cursor - 1])) * (ls[cursor] - li[cursor])) + li[cursor];
            }
        }
        return percentil;
    }

    public double MediaX(double[] marcaClase, double[] frecuenciaAbsoluta) {
        double sumaxifi = 0, sumafi = 0, media;
        double[] marcaPorAbsoluta = new double[marcaClase.length];

        for (int i = 0; i < marcaClase.length; i++) {
            marcaPorAbsoluta[i] = marcaClase[i] * frecuenciaAbsoluta[i];
        }

        for (int i = 0; i < marcaClase.length; i++) {
            sumaxifi += marcaPorAbsoluta[i];
            sumafi += frecuenciaAbsoluta[i];
        }

        media = sumaxifi / sumafi;

        return media;
    }

    public double Mediana(double[] frecuenciaAbsoluta, double[] frecuenciaAcumulada, double[] limiteInferior, double[] limiteSuperior, double sumaFrecuencia) {
        double mediana;

        double identificador = sumaFrecuencia / 2;
        int cursorIdentificador = 0;

        if (identificador <= frecuenciaAcumulada[0]) {
            mediana = 0;
        } else {
            while (identificador > frecuenciaAcumulada[cursorIdentificador]) {
                cursorIdentificador++;
            }
            mediana = ((identificador - frecuenciaAcumulada[cursorIdentificador - 1]) / frecuenciaAbsoluta[cursorIdentificador]
                    * (limiteSuperior[cursorIdentificador] - limiteInferior[cursorIdentificador])) + limiteInferior[cursorIdentificador];
        }
        return mediana;
    }

    public double Moda(double[] frecuenciaAbsoluta, double[] limiteInferior, double[] limiteSuperior) {

        double auxiliar = frecuenciaAbsoluta[0], moda;

        for (int i = 1; i < frecuenciaAbsoluta.length; i++) {
            if (auxiliar <= frecuenciaAbsoluta[i]) {
                auxiliar = frecuenciaAbsoluta[i];
            }
        }

        int cursor = 0;
        while (auxiliar != frecuenciaAbsoluta[cursor]) {
            cursor++;
        }
        if (auxiliar == frecuenciaAbsoluta[0] || auxiliar == frecuenciaAbsoluta[frecuenciaAbsoluta.length - 1]) {
            moda = 0;
        } else {
            moda = ((frecuenciaAbsoluta[cursor] - frecuenciaAbsoluta[cursor - 1])
                    / ((frecuenciaAbsoluta[cursor] - frecuenciaAbsoluta[cursor - 1]) + (frecuenciaAbsoluta[cursor] - frecuenciaAbsoluta[cursor + 1]))
                    * (limiteSuperior[cursor] - limiteInferior[cursor])) + limiteInferior[cursor];
        }
        return moda;
    }

    public double VarianzaX(double[] marcaClase, double[] frecuenciaAbsoluta, double media) {
        double varianza, sumaDif = 0, sumafi = 0;
        double[] dif = new double[marcaClase.length];

        for (int i = 0; i < marcaClase.length; i++) {
            dif[i] = Math.pow(marcaClase[i] - media, 2) * frecuenciaAbsoluta[i];
        }

        for (int i = 0; i < marcaClase.length; i++) {
            sumaDif += dif[i];
            sumafi += frecuenciaAbsoluta[i];
        }

        varianza = sumaDif / sumafi;

        return varianza;
    }

    public double DesviacionX(double varianza) {
        double desviacion = Math.sqrt(varianza);
        return desviacion;
    }

    public double Asimetria(double media, double mediana, double desviacion) {
        double asimetria = 3 * (media - mediana) / desviacion;
        return asimetria;
    }

    public double Curtosis(double[] marcaClase, double[] frecuenciaAbsoluta, double media, double desviacion) {
        double curtosis, sumaDif = 0, sumaf = 0;

        double[] dif = new double[marcaClase.length];

        for (int i = 0; i < marcaClase.length; i++) {
            dif[i] = Math.pow(marcaClase[i] - media, 4);
            sumaDif += dif[i];
            sumaf += frecuenciaAbsoluta[i];
        }

        curtosis = sumaDif / (sumaf * Math.pow(desviacion, 4));

        return curtosis;
    }

    public double Coeficiente(double media, double desviacion) {
        double coeficiente = desviacion / media;
        return coeficiente;
    }

    public double ErrorEstandar(double[] frecuenciaAbsoluta, double desviacion) {
        double sumaf = 0;

        for (int i = 0; i < frecuenciaAbsoluta.length; i++) {
            sumaf += frecuenciaAbsoluta[i];
        }

        double error = desviacion / Math.sqrt(sumaf);

        return error;
    }

    public double ChiCuadrado(double[] frecuenciaObservada, double[] frecuenciaEsperada) {
        double[] arregloChiCuadrado = new double[frecuenciaEsperada.length];
        double chiCuadrado = 0;
        for (int i = 0; i < arregloChiCuadrado.length; i++) {
            arregloChiCuadrado[i] = Math.pow(frecuenciaObservada[i] - frecuenciaEsperada[i], 2) / frecuenciaEsperada[i];
            chiCuadrado += arregloChiCuadrado[i];
        }
        return chiCuadrado;
    }

    public double Anderson(double[] frecuenciaRelativaOrdenada, double[] arregloOrdenado) {
        double[] arregloAnderson = new double[frecuenciaRelativaOrdenada.length];
        HerramientasArreglo ha = new HerramientasArreglo();
        for (int i = 0; i < arregloOrdenado.length; i++) {
            if (frecuenciaRelativaOrdenada[i] <= 0 || arregloOrdenado[i] <= 0) {
                arregloAnderson[i] = 0;
            } else {
                arregloAnderson[i] = (((2 * i) + 1)) * (Math.log(frecuenciaRelativaOrdenada[i]) + Math.log(arregloOrdenado[i]));
            }
        }
        double sumaAnderson = ha.suma(arregloAnderson);
        double A2 = 2.492 - ((-sumaAnderson / arregloOrdenado.length) - arregloOrdenado.length);
        return A2;
    }

    public double lambdaExponencial(double media) {
        double lambda = 1 / media;
        return lambda;
    }

    public double betaGamma(double media, double varianza) {
        double beta = media / varianza;
        return beta;
    }

    public double alfaGamma(double media, double varianza) {
        double forma = Math.pow(media, 2) / varianza;
        return forma; // alfa es la forma
    }

    public double alfaBetaX(double media, double varianza) {
        double c = (1 - media) / media;
        double alfa = (-media * (Math.pow(media, 2) - media + varianza)) / varianza;
        return alfa;
    }

    public double betaBetaX(double media, double varianza) {
        double beta = ((media - 1) * (Math.pow(media, 2) - media + varianza)) / varianza;
        return beta;
    }

    public double[] xWeibull(double[] xi) {
        double[] xWeibull = new double[xi.length];

        for (int i = 0; i < xi.length; i++) {
            if (xi[i] <= 0) {
                xWeibull[i] = 0;
            } else {
                xWeibull[i] = Math.log(xi[i]);
            }
        }
        return xWeibull;
    }

    public double[] yWeibull(double[] acumulado) {
        double[] yWeibull = new double[acumulado.length];
        for (int i = 0; i < acumulado.length; i++) {
            if (1 - acumulado[i] <= 0) {
                yWeibull[i] = 0;
            } else {
                yWeibull[i] = Math.log(-Math.log(1 - acumulado[i]));
            }
        }
        return yWeibull;
    }

    public double formaWeibull(double[] x, double[] y) {
        double forma;
        // m, pendiente = y2 - y1 / x2 - x1
        forma = (y[y.length - 1] - y[0]) / (x[x.length - 1] - x[0]);
        return forma;
    }

    public double escalaWeibull(double[] x, double[] y) {
        double forma, formaSuma = 0, escala;

        double[] b = new double[x.length];
        double[] lambda = new double[x.length];

        // m = y2 - y1 / x2 - x1
        forma = (y[x.length - 1] - y[0]) / (x[x.length - 1] - x[0]);

        for (int i = 0; i < x.length; i++) {

            // b = ( -m * x1 ) + y1
            b[i] = (-forma * x[i]) + y[i];

            // lambda = e ^ ( b / -k )
            lambda[i] = Math.exp(b[i] / -forma);
        }

        for (int i = 0; i < x.length; i++) {
            formaSuma += lambda[i];
        }
        escala = formaSuma / x.length;
        return escala;
    }

    public double[] lnXx(double xi[]) {
        double[] lnx = new double[xi.length];

        for (int i = 0; i < xi.length; i++) {
            lnx[i] = Math.log(xi[i]);
        }
        return lnx;
    }

    public double Modismo(double[] arregloPrincipal) {
        double auxiliar = 0, moda;
        ArrayList<Double> marcaClase = new ArrayList<>(),
                frecuenciaAbsoluta = new ArrayList<>();

        for (int i = 0; i < arregloPrincipal.length; i++) {
            marcaClase.add(arregloPrincipal[i]);
        }

        for (int i = 0; i < arregloPrincipal.length; i++) {
            int contador = 0;
            for (int j = 0; j < marcaClase.size(); j++) {
                if (arregloPrincipal[i] == marcaClase.get(j)) {
                    contador++;
                }

                if (arregloPrincipal[i] == marcaClase.get(j) && contador == 2) {
                    contador--;
                    marcaClase.remove(j);
                }
            }
        }

        for (int i = 0; i < marcaClase.size(); i++) {
            int contador = 0;
            for (int j = 0; j < arregloPrincipal.length; j++) {
                if (marcaClase.get(i) == arregloPrincipal[j]) {
                    contador++;
                }
            }
            frecuenciaAbsoluta.add(Double.parseDouble(String.valueOf(contador)));
        }

        auxiliar = frecuenciaAbsoluta.get(0);
        for (int i = 1; i < frecuenciaAbsoluta.size(); i++) {
            if (frecuenciaAbsoluta.get(i) > auxiliar) {
                auxiliar = frecuenciaAbsoluta.get(i);
            }
        }

        int contador = 0, libra = 0;
        for (int i = 0; i < frecuenciaAbsoluta.size(); i++) {
            if (auxiliar == frecuenciaAbsoluta.get(i)) {
                contador++;
                libra = i;
            }
        }

        if (contador != 1) {
            moda = 0;
        } else {

            moda = marcaClase.get(libra);
        }

        return moda;
    }

    public double OrdenarChiLoco(double[] frecuenciaObservada, double[] frecuenciaEsperada) {
        int contador = 0;
        double sumaChi = 0;
        double[] chi = new double[frecuenciaObservada.length];

        double X, F;

        // 1ra vuelta
        for (int i = frecuenciaObservada.length; i > 1; i--) {

            F = frecuenciaEsperada[i - 1] + frecuenciaEsperada[i - 2];
            X = frecuenciaObservada[i - 1] + frecuenciaObservada[i - 2];

            if (frecuenciaEsperada[i - 1] < 5) {

                frecuenciaObservada[i - 1] = 0;
                frecuenciaEsperada[i - 1] = 0;

                frecuenciaObservada[i - 2] = X;
                frecuenciaEsperada[i - 2] = F;
            }
        }

        // 2da vuelta
        for (int i = 0; i < frecuenciaObservada.length - 1; i++) {

            F = frecuenciaEsperada[i] + frecuenciaEsperada[i + 1];
            X = frecuenciaObservada[i] + frecuenciaObservada[i + 1];
            if (frecuenciaEsperada[i] < 5) {

                frecuenciaObservada[i] = 0;
                frecuenciaEsperada[i] = 0;

                frecuenciaObservada[i + 1] = X;
                frecuenciaEsperada[i + 1] = F;
            }
        }

        for (int i = 0; i < frecuenciaObservada.length; i++) {

            if (frecuenciaObservada[i] == 0 && frecuenciaEsperada[i] == 0) {

                frecuenciaObservada[i] = 1;
                frecuenciaEsperada[i] = 1;

                contador++;
            }
            chi[i] = Math.pow(frecuenciaObservada[i] - frecuenciaEsperada[i], 2) / frecuenciaEsperada[i];
        }

        for (int i = 0; i < frecuenciaObservada.length; i++) {
            sumaChi += chi[i];
        }

        return sumaChi;
    }
}
