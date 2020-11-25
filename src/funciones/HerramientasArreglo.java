package funciones;

import codigo.CodigoCentral;
import java.util.ArrayList;
import java.util.Random;

public class HerramientasArreglo {

    public double suma(double[] arreglo) {
        double suma = 0;
        for (int i = 0; i < arreglo.length; i++) {
            suma += arreglo[i];
        }
        return suma;
    }

    public double[] GenerarArregloNumerosAleatorios(double minimo, double maximo, int numeroIteraciones) {
        Random random = new Random();
        double[] arregloNumerosAleatorios = new double[numeroIteraciones];

        for (int i = 0; i < numeroIteraciones; i++) {
            arregloNumerosAleatorios[i] = minimo + (maximo - minimo) * random.nextDouble();
        }
        return arregloNumerosAleatorios;
    }

    public double[] OrdenarArreglo(double[] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            int cursor = i;
            double auxiliar = arreglo[i];

            while (cursor > 0 && arreglo[cursor - 1] > auxiliar) {
                arreglo[cursor] = arreglo[cursor - 1];
                cursor--;
                arreglo[cursor] = auxiliar;
            }
        }
        return arreglo;
    }

    public double[] ArregloFrecuenciaAcumulada(double[] arregloProbabilidad) {
        double sumaTotal = suma(arregloProbabilidad);
        double[] arregloFrecuenciaAcumulada = new double[arregloProbabilidad.length];

        arregloFrecuenciaAcumulada[0] = arregloProbabilidad[0] / sumaTotal;
        for (int i = 1; i < arregloProbabilidad.length; i++) {
            arregloFrecuenciaAcumulada[i] = arregloProbabilidad[i] / sumaTotal + arregloFrecuenciaAcumulada[i - 1];
        }
        return arregloFrecuenciaAcumulada;
    }

    public double[] InterpolacionRelleno(double[] arregloEstatico, double[] arregloAcumulado, double[] arregloAleatorio) {
        double[] arregloInterpolado = new double[CodigoCentral.iteraciones];
        for (int i = 0; i < CodigoCentral.iteraciones; i++) {
            int cursor = 0;
            while (arregloAleatorio[i] >= arregloAcumulado[cursor]) {
                cursor++;
            }
            arregloInterpolado[i] = arregloEstatico[cursor];
        }
        return arregloInterpolado;
    }

    public double[] CalcularPoesArreglo(double[] area, double[] espesor, double[] porosidad, double[] saturacion, double[] factor) {
        double[] arregloPoes = new double[CodigoCentral.iteraciones];

        for (int i = 0; i < CodigoCentral.iteraciones; i++) {
            arregloPoes[i] = 7758 * area[i] * espesor[i] * porosidad[i] * saturacion[i] / factor[i];
        }

        for (int i = 0; i < CodigoCentral.iteraciones; i++) {
            int cursor = i;
            double auxiliar = arregloPoes[i];

            while (cursor > 0 && arregloPoes[cursor - 1] > auxiliar) {
                arregloPoes[cursor] = arregloPoes[cursor - 1];
                cursor--;
                arregloPoes[cursor] = auxiliar;
            }
        }
        return arregloPoes;
    }

    public double[] LimiteInferior(double[] arregloOrdenado) {
        double rango = arregloOrdenado[arregloOrdenado.length - 1] - arregloOrdenado[0];
        int k = (int) Math.round(1 + (3.322 * Math.log10(arregloOrdenado.length)));
        double intervalo = rango / k;

        double[] limiteInferior = new double[k];

        limiteInferior[0] = arregloOrdenado[0];
        for (int i = 1; i < k; i++) {
            limiteInferior[i] = limiteInferior[i - 1] + intervalo;
        }

        return limiteInferior;
    }

    public double[] LimiteSuperior(double[] arregloOrdenado) {
        double rango = arregloOrdenado[arregloOrdenado.length - 1] - arregloOrdenado[0];
        int k = (int) Math.round(1 + (3.322 * Math.log10(arregloOrdenado.length)));
        double intervalo = rango / k;

        double[] limiteSuperior = new double[k];

        limiteSuperior[0] = arregloOrdenado[0] + intervalo;
        for (int i = 1; i < k; i++) {
            limiteSuperior[i] = limiteSuperior[i - 1] + intervalo;
        }
        return limiteSuperior;
    }

    public double[] MarcaDeClase(double[] ordenado, double[] limiteInferior, double[] limiteSuperior) {
        double[] marcaClase = new double[limiteInferior.length];

        for (int i = 0; i < limiteInferior.length; i++) {
            marcaClase[i] = (limiteInferior[i] + limiteSuperior[i]) / 2;
        }

        return marcaClase;
    }

    public double[] FrecuenciaAbsoluta(double[] arregloOrdenado, double[] limiteInferior, double[] limiteSuperior) {
        double[] frecuenciaAbsoluta = new double[limiteInferior.length];

        int cursor = 0;
        for (int i = 0; i < arregloOrdenado.length; i++) {

            if (arregloOrdenado[i] <= limiteInferior[0]) {
                frecuenciaAbsoluta[0]++;

            } else if (arregloOrdenado[i] >= limiteSuperior[limiteSuperior.length - 1]) {
                frecuenciaAbsoluta[limiteSuperior.length - 1]++;

            } else if (arregloOrdenado[i] > limiteInferior[cursor] && arregloOrdenado[i] <= limiteSuperior[cursor]) {
                frecuenciaAbsoluta[cursor]++;

            } else if (arregloOrdenado[i] > limiteSuperior[cursor]) {
                frecuenciaAbsoluta[cursor + 1]++;
                cursor++;
            }
        }
        return frecuenciaAbsoluta;
    }

    public double[] FrecuenciaAcumulada(double[] frecuenciaAbsoluta) {
        double[] frecuenciaAcumulada = new double[frecuenciaAbsoluta.length];

        frecuenciaAcumulada[0] = frecuenciaAbsoluta[0];

        for (int i = 1; i < frecuenciaAbsoluta.length; i++) {
            frecuenciaAcumulada[i] = frecuenciaAbsoluta[i] + frecuenciaAcumulada[i - 1];
        }
        return frecuenciaAcumulada;
    }

    public double Factorial(double x) {
        double factorial = x;
        int numero = 1;

        if (x <= 0) {
            factorial = 1;
        } else {
            while (numero < x) {
                factorial *= numero;
                numero++;
            }
        }
        return factorial;
    }

    public double MayorQue(double[] frecuenciaAbsoluta) {
        double auxiliar = frecuenciaAbsoluta[0];
        for (int i = 1; i < frecuenciaAbsoluta.length; i++) {
            if (auxiliar <= frecuenciaAbsoluta[i]) {
                auxiliar = frecuenciaAbsoluta[i];
            }
        }
        return auxiliar;
    }

    public double MayorQue(double[] arregloVariable, double[] frecuenciaAbsoluta) {
        double auxiliar = MayorQue(frecuenciaAbsoluta);
        int contador = 0;
        for (int i = 0; i < frecuenciaAbsoluta.length; i++) {
            if (auxiliar == frecuenciaAbsoluta[i]) {
                contador = i;
            }
        }
        auxiliar = arregloVariable[contador];
        return auxiliar;
    }

    public double MayorQue(String[][] matriz) {
        double aux;
        if ("NaN".equals(matriz[0][1])) {
            aux = 0;
        } else {
            aux = Double.parseDouble(matriz[0][1]);
        }
        for (int i = 1; i < matriz.length; i++) {

            if (aux < Double.parseDouble(matriz[i][1])) {
                aux = Double.parseDouble(matriz[i][1]);
            }
        }
        return aux;
    }

    public double[] ValorEsperado(double[] frecuenciaAbsoluta, double[] arregloProbabilidad) {
        double sumaFabs = 0;
        double[] frecuenciaEsperada = new double[frecuenciaAbsoluta.length];

        for (int i = 0; i < frecuenciaAbsoluta.length; i++) {
            sumaFabs += frecuenciaAbsoluta[i];
        }

        for (int i = 0; i < frecuenciaAbsoluta.length; i++) {
            frecuenciaEsperada[i] = sumaFabs * arregloProbabilidad[i];
        }
        return frecuenciaEsperada;
    }

    public double[] FrecuenciaEsperada(double[] arregloProbabilidad, double suma) {
        double[] arregloEsperada = new double[arregloProbabilidad.length];
        for (int i = 0; i < arregloProbabilidad.length; i++) {
            arregloEsperada[i] = arregloProbabilidad[i] * suma;
        }
        return arregloEsperada;
    }

    public double[] diferenciaAbsoluta(double[] observada, double[] esperada) {
        double[] diferencia = new double[observada.length];
        for (int i = 0; i < diferencia.length; i++) {
            diferencia[i] = Math.abs(observada[i] - esperada[i]);
        }
        return diferencia;
    }

    public double[] ArregloMenosUno(double[] arregloRecibido) {
        double[] arreglo = new double[arregloRecibido.length];

        for (int i = 0; i < arregloRecibido.length; i++) {
            arreglo[i] = 1.0001 - arregloRecibido[i];
        }

        return arreglo;
    }

    public double Interpolacion(double yb, double ya, double xb, double xa, double x) {
        double interpolacion = ((yb - ya) / (xb - xa) * (x - xa)) + ya;
        return interpolacion;
    }

    public double[] normalizar(double[] arregloSinNormalizar, double total) {
        double[] arregloNormalizado = new double[arregloSinNormalizar.length];
        for (int i = 0; i < arregloNormalizado.length; i++) {
            arregloNormalizado[i] = arregloSinNormalizar[i] / total;
        }
        return arregloNormalizado;
    }

    private void marcaDeClase(double[] variable, ArrayList<Double> listMarcaDeClase) {
        for (int i = 0; i < variable.length; i++) {
            listMarcaDeClase.add(variable[i]);
        }
        for (int i = 0; i < variable.length; i++) {
            int contador = 0;
            for (int j = 0; j < listMarcaDeClase.size(); j++) {
                if (variable[i] == listMarcaDeClase.get(j)) {
                    contador++;
                }
                if (variable[i] == listMarcaDeClase.get(j) && contador == 2) {
                    listMarcaDeClase.remove(j);
                    contador--;
                }
            }
        }
    }

    private void frecuenciaAbsoluta(double[] variable, ArrayList<Double> listMarcaDeClase) {
        ArrayList<Double> listaFrecuenciaObservada = new ArrayList<>();
        for (int i = 0; i < listMarcaDeClase.size(); i++) {
            int contador = 0;
            for (int j = 0; j < variable.length; j++) {
                if (variable[j] == listMarcaDeClase.get(i)) {
                    contador++;
                }
            }
            listaFrecuenciaObservada.add(Double.parseDouble(String.valueOf(contador)));
        }
    }
}
