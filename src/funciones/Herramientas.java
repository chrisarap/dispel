package funciones;

import codigo.CodigoCentral;
import java.util.ArrayList;
import java.util.Random;

public class Herramientas {

    public double suma(double[] arreglo) {
        double suma = 0;
        for (int i = 0; i < arreglo.length; i++) {
            suma += arreglo[i];
        }
        return suma;
    }

    public double[] parseoListaEnArreglo(ArrayList<Double> lista) {
        double[] arreglo = new double[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            arreglo[i] = lista.get(i);
        }
        return arreglo;
    }

    public ArrayList parseoArregloEnLista(double[] arreglo) {
        ArrayList<Double> lista = new ArrayList<>();
        for (int i = 0; i < arreglo.length; i++) {
            lista.add(arreglo[i]);
        }
        return lista;
    }

    public double[] ordenarArreglo(double[] arreglo) {
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

    public double[] marcaDeClase(double[] arreglo) {
        ArrayList<Double> lista = parseoArregloEnLista(arreglo);
        for (int i = 0; i < arreglo.length; i++) {
            int contador = 0;
            for (int j = 0; j < lista.size(); j++) {
                if (arreglo[i] == lista.get(j)) {
                    contador++;
                }
                if (arreglo[i] == lista.get(j) && contador == 2) {
                    lista.remove(j);
                    contador--;
                }
            }
        }
        return parseoListaEnArreglo(lista);
    }

    public double[] frecuenciaAbsoluta(double[] arreglo, double[] marcaDeClase) {
        double[] frecuenciaObservada = new double[marcaDeClase.length];
        for (int i = 0; i < marcaDeClase.length; i++) {
            int contador = 0;
            for (int j = 0; j < arreglo.length; j++) {
                if (marcaDeClase[i] == arreglo[j]) {
                    contador++;
                }
            }
            frecuenciaObservada[i] = contador;
        }
        return frecuenciaObservada;
    }

    public double[] frecuenciaRelativa(double[] arreglo) {
        double sumaTotal = suma(arreglo), arregloNormalizado[] = new double[arreglo.length];
        for (int i = 0; i < arregloNormalizado.length; i++) {
            arregloNormalizado[i] = arreglo[i] / sumaTotal;
        }
        return arregloNormalizado;
    }

    public double[] frecuenciaAcumulada(double[] arreglo) {
        double[] frecuencia = new double[arreglo.length];
        frecuencia[0] = arreglo[0];
        for (int i = 1; i < arreglo.length; i++) {
            frecuencia[i] = arreglo[i] + frecuencia[i - 1];
        }
        return frecuencia;
    }

    public double[] frecuenciaEsperada(double[] observada, double[] arreglo) {
        double suma = suma(observada), esperada[] = new double[observada.length];
        for (int i = 0; i < arreglo.length; i++) {
            esperada[i] = arreglo[i] * suma;
        }
        return esperada;
    }

    public double chiCuadrado(double[] observada, double[] esperada) {
        double chi = 0;
        for (int i = 0; i < observada.length; i++) {
            chi += Math.pow(observada[i] - esperada[i], 2) / esperada[i];
        }
        return chi;
    }

    public double interpolacionChiCuadrado(double[] esperada) {
        return 1.185410072 * esperada.length + 2.655589928;
    }

    public double restaChiError(double chi, double error) {
        return error - chi;
    }

    public double mayorQue(double[] frecuenciaAbsoluta) {
        double auxiliar = frecuenciaAbsoluta[0];
        for (int i = 1; i < frecuenciaAbsoluta.length; i++) {
            if (auxiliar <= frecuenciaAbsoluta[i]) {
                auxiliar = frecuenciaAbsoluta[i];
            }
        }
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

    public double[] arregloMenosUno(double[] arregloRecibido) {
        double[] arreglo = new double[arregloRecibido.length];

        for (int i = 0; i < arregloRecibido.length; i++) {
            arreglo[i] = 1.0001 - arregloRecibido[i];
        }

        return arreglo;
    }

    public double promedio(double[] arreglo) {
        double suma = 0;
        for (int i = 0; i < arreglo.length; i++) {
            suma += arreglo[i];
        }
        return suma / arreglo.length;
    }

    public double[] GenerarArregloNumerosAleatorios(double minimo, double maximo, int numeroIteraciones) {
        Random random = new Random();
        double[] arregloNumerosAleatorios = new double[numeroIteraciones];

        for (int i = 0; i < numeroIteraciones; i++) {
            arregloNumerosAleatorios[i] = minimo + (maximo - minimo) * random.nextDouble();
        }
        return arregloNumerosAleatorios;
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

    public String nombreDistribucion(String[][] matriz, double mayorQue) {
        int contador = 0;
        for (int i = 0; i < matriz.length; i++) {
            if (mayorQue == Double.parseDouble(matriz[i][1])) {
                contador = i;
            }
        }
        return matriz[contador][0];
    }

}
