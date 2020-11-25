package codigo;

import funciones.AjusteAnderson;
import funciones.AjusteChiCuadrado;
import funciones.AjusteKolmogorov;
import funciones.Estadistica;
import funciones.FuncionDensidad;
import funciones.Herramientas;
import funciones.HerramientasArreglo;
import interfaces.Ajuste;
import interfaces.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CodigoAjuste {

    DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
    HerramientasArreglo herramientasArreglo = new HerramientasArreglo();
    FuncionDensidad funcionDensidad = new FuncionDensidad();
    DecimalFormat separadorMiles = new DecimalFormat("###,###.##");

    private double[] variable, marcaDeClase, frecuenciaObservada, xWeibull, yWeibull, limiteInferior, limiteSuperior, marcaClase,
            frecuenciaAbsoluta, frecuenciaAcumulada, posicionPercentiles, percentiles;
    private ArrayList<Double> listMarcaDeClase = new ArrayList<>(),
            listaFrecuenciaObservada = new ArrayList<>();

    private JTable tablaDistribuciones, tablaEstadistica;

    private double media, varianza, desviacion, minimo, maximo, exponencialLambda,
            gammaAlfa, betaAlfa, gammaBeta, betaBeta, valorProbable,
            formaWeibull, escalaWeibull, mediaLognormal, varianzaLognormal,
            desviacionLognormal;

    private double mediana, moda, asimetria, curtosis, coeficienteDeVariabilidad, ErrorEstandar, sumaFrecuencia;

    // constructor ventana
    public CodigoAjuste() {
        Ajuste ajuste = new Ajuste();
        ajuste.setVisible(true);        
        ajuste.setResizable(false);
        ajuste.setLocationRelativeTo(null);
        ajuste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Principal.ventanaAjuste = true;
    }

    public CodigoAjuste(ArrayList lista, JTable tablaDistribuciones, JTable tablaEstadistica) {
        this.tablaDistribuciones = tablaDistribuciones;
        this.tablaEstadistica = tablaEstadistica;

        variable = new double[lista.size()];
        for (int i = 0; i < variable.length; i++) {
            variable[i] = (double) lista.get(i);
        }
        variable = herramientasArreglo.OrdenarArreglo(variable);
    }

    private void marcaDeClase() {
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

    private void frecuenciaAbsoluta() {
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

    private void setMarcaDeClase() {
        marcaDeClase = new double[listMarcaDeClase.size()];
        for (int i = 0; i < listMarcaDeClase.size(); i++) {
            marcaDeClase[i] = listMarcaDeClase.get(i);
        }
    }

    private void setFrecuenciaObservada() {
        frecuenciaObservada = new double[listaFrecuenciaObservada.size()];
        for (int i = 0; i < listaFrecuenciaObservada.size(); i++) {
            frecuenciaObservada[i] = listaFrecuenciaObservada.get(i);
        }
    }

    private void imprimir() {
        for (int i = 0; i < marcaDeClase.length; i++) {
            System.out.printf("marca %.3f observada %.0f\n", marcaDeClase[i], frecuenciaObservada[i]);
        }
    }

    public void parametrosEstadisticos() {
        Estadistica estadistica = new Estadistica(variable);
        Herramientas h = new Herramientas();

        // limites
        limiteInferior = herramientasArreglo.LimiteInferior(variable);
        limiteSuperior = herramientasArreglo.LimiteSuperior(variable);

        // tabla estadistica
        marcaClase = herramientasArreglo.MarcaDeClase(variable, limiteInferior, limiteSuperior);
        frecuenciaAbsoluta = herramientasArreglo.FrecuenciaAbsoluta(variable, limiteInferior, limiteSuperior);
        frecuenciaAcumulada = herramientasArreglo.FrecuenciaAcumulada(frecuenciaAbsoluta);

        // parametros estadisticos
        sumaFrecuencia = herramientasArreglo.suma(frecuenciaAbsoluta);
        posicionPercentiles = estadistica.posicionPercentil(sumaFrecuencia);
        percentiles = estadistica.Percentil(posicionPercentiles, frecuenciaAcumulada, limiteInferior, limiteSuperior);
        media = estadistica.MediaX(marcaClase, frecuenciaAbsoluta);
        mediana = estadistica.Mediana(frecuenciaAbsoluta, frecuenciaAcumulada, limiteInferior, limiteSuperior, sumaFrecuencia);
        moda = estadistica.Modismo(variable);
        varianza = estadistica.VarianzaX(marcaClase, frecuenciaAbsoluta, media);
        desviacion = estadistica.DesviacionX(varianza);
        asimetria = estadistica.Asimetria(media, mediana, desviacion);
        curtosis = estadistica.Curtosis(marcaClase, frecuenciaAbsoluta, media, desviacion);
        coeficienteDeVariabilidad = estadistica.Coeficiente(media, desviacion);
        ErrorEstandar = estadistica.ErrorEstandar(frecuenciaAbsoluta, desviacion);

        // parametros distribuciones        
        betaAlfa = estadistica.betaAlfa();
        betaBeta = estadistica.betaBeta();

        gammaBeta = estadistica.gammaBeta();
        gammaAlfa = estadistica.gammaAlfa();

        exponencialLambda = estadistica.exponencialLambda();

        minimo = variable[0];
        maximo = variable[variable.length - 1];
        valorProbable = herramientasArreglo.MayorQue(marcaDeClase, frecuenciaObservada);
        xWeibull = estadistica.xWeibull(marcaDeClase);
        yWeibull = estadistica.yWeibull(herramientasArreglo.ArregloFrecuenciaAcumulada(frecuenciaObservada));
        formaWeibull = estadistica.formaWeibull(xWeibull, yWeibull);
        escalaWeibull = estadistica.escalaWeibull(xWeibull, yWeibull);
        mediaLognormal = estadistica.MediaX(estadistica.lnXx(marcaDeClase), frecuenciaObservada);
        varianzaLognormal = estadistica.VarianzaX(marcaDeClase, frecuenciaObservada, mediaLognormal);
        desviacionLognormal = estadistica.DesviacionX(varianzaLognormal);
    }

    public void mostrarTablaEstadistica() {

        String[][] matriz = new String[9][2];

        matriz[0][0] = "Media";
        matriz[0][1] = String.format("%,.2f", media);

        matriz[1][0] = "Mediana";
        matriz[1][1] = String.format("%,.2f", mediana);

        matriz[2][0] = "Moda";
        matriz[2][1] = String.format("%,.2f", moda);

        matriz[3][0] = "Varianza";
        matriz[3][1] = String.format("%,.2f", varianza);

        matriz[4][0] = "Desviacion";
        matriz[4][1] = String.format("%,.2f", desviacion);

        matriz[5][0] = "Curtosis";
        matriz[5][1] = String.format("%,.2f", curtosis);

        matriz[6][0] = "Coeficiente de variabilidad";
        matriz[6][1] = String.format("%,.2f", coeficienteDeVariabilidad);

        matriz[7][0] = "Error estándar";
        matriz[7][1] = String.format("%,.2f", ErrorEstandar);

        matriz[8][0] = "Asimetria";
        matriz[8][1] = String.format("%,.2f", asimetria);

        tablaEstadistica.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                new String[]{
                    "Nombre", "Valor"
                }
        ));
        cell.setHorizontalAlignment(SwingConstants.RIGHT);
        tablaEstadistica.getColumnModel().getColumn(0).setPreferredWidth(200);
        tablaEstadistica.getColumnModel().getColumn(1).setPreferredWidth(200);

    }

    public void mostrarTablaDistribuciones() {
        AjusteAnderson anderson = new AjusteAnderson(variable);
        AjusteChiCuadrado chi = new AjusteChiCuadrado(marcaDeClase, frecuenciaObservada);
        AjusteKolmogorov kolmogorov = new AjusteKolmogorov(marcaDeClase, frecuenciaObservada, variable.length);

        parametrosEstadisticos();

        String[][] matriz = new String[9][5];

        matriz[0][0] = "Beta";
        matriz[0][1] = separadorMiles.format(anderson.Beta());
        matriz[0][2] = separadorMiles.format(chi.Beta(betaAlfa, betaBeta));
        matriz[0][3] = separadorMiles.format(kolmogorov.Beta(betaAlfa, betaBeta));
        matriz[0][4] = "alfa= " + separadorMiles.format(betaAlfa) + ", beta= " + separadorMiles.format(betaBeta);

        matriz[1][0] = "Exponencial";
        matriz[1][1] = separadorMiles.format(anderson.Exponencial());
        matriz[1][2] = separadorMiles.format(chi.Exponencial(exponencialLambda));
        matriz[1][3] = separadorMiles.format(kolmogorov.Exponencial(exponencialLambda));
        matriz[1][4] = "lambda= " + separadorMiles.format(exponencialLambda);

        matriz[2][0] = "Gamma";
        matriz[2][1] = separadorMiles.format(anderson.Gamma());
        matriz[2][2] = separadorMiles.format(chi.Gamma(gammaAlfa, gammaBeta));
        matriz[2][3] = separadorMiles.format(kolmogorov.Gamma(gammaAlfa, gammaBeta));
        matriz[2][4] = "forma= " + separadorMiles.format(gammaAlfa) + ", escala= " + separadorMiles.format(gammaBeta);

        matriz[3][0] = "Log-normal";
        matriz[3][1] = separadorMiles.format(anderson.Lognormal());
        matriz[3][2] = separadorMiles.format(chi.Lognormal(mediaLognormal, desviacionLognormal));
        matriz[3][3] = separadorMiles.format(kolmogorov.Lognormal(mediaLognormal, desviacionLognormal));
        matriz[3][4] = "media= " + separadorMiles.format(mediaLognormal) + ", desviación= " + separadorMiles.format(desviacionLognormal);

        matriz[4][0] = "Normal";
        matriz[4][1] = separadorMiles.format(anderson.Normal());
        matriz[4][2] = separadorMiles.format(chi.Normal(media, desviacion));
        matriz[4][3] = separadorMiles.format(kolmogorov.Normal(media, desviacion));
        matriz[4][4] = "media= " + separadorMiles.format(media) + ", desviación= " + separadorMiles.format(desviacion);

        matriz[5][0] = "Poisson";
        matriz[5][1] = separadorMiles.format(anderson.Poisson());
        matriz[5][2] = separadorMiles.format(chi.Poisson(media));
        matriz[5][3] = separadorMiles.format(kolmogorov.Poisson(media));
        matriz[5][4] = "lambda= " + separadorMiles.format(media);

        matriz[6][0] = "Triangular";
        matriz[6][1] = separadorMiles.format(anderson.Triangular());
        matriz[6][2] = separadorMiles.format(chi.Triangular(minimo, maximo, valorProbable));
        matriz[6][3] = separadorMiles.format(kolmogorov.Triangular(minimo, maximo, valorProbable));
        matriz[6][4] = "mínimo= " + separadorMiles.format(minimo)
                + ", máximo= " + separadorMiles.format(maximo)
                + ", valor probable= " + separadorMiles.format(valorProbable);

        matriz[7][0] = "Uniforme";
        matriz[7][1] = separadorMiles.format(anderson.Uniforme());
        matriz[7][2] = separadorMiles.format(chi.Uniforme());
        matriz[7][3] = separadorMiles.format(kolmogorov.Uniforme());
        matriz[7][4] = "mínimo= " + separadorMiles.format(minimo)
                + ", máximo= " + separadorMiles.format(maximo);

        matriz[8][0] = "Weibull";
        matriz[8][1] = separadorMiles.format(anderson.Weibbull());
        matriz[8][2] = separadorMiles.format(chi.Weibbull(formaWeibull, escalaWeibull));
        matriz[8][3] = separadorMiles.format(kolmogorov.Weibbull(formaWeibull, escalaWeibull));
        matriz[8][4] = "forma= " + separadorMiles.format(formaWeibull)
                + ", escala= " + separadorMiles.format(escalaWeibull);

        tablaDistribuciones.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                new String[]{
                    "Distribución", "A-D", "Chi 2", "K-S", "Parámetros"
                }
        ));
        cell.setHorizontalAlignment(SwingConstants.RIGHT);
        tablaDistribuciones.getColumnModel().getColumn(1).setCellRenderer(cell);
        tablaDistribuciones.getColumnModel().getColumn(2).setCellRenderer(cell);
        tablaDistribuciones.getColumnModel().getColumn(3).setCellRenderer(cell);
        tablaDistribuciones.getColumnModel().getColumn(4).setCellRenderer(cell);
        tablaDistribuciones.getColumnModel().getColumn(4).setPreferredWidth(300);
    }

    public void iniciador() {
        marcaDeClase();
        frecuenciaAbsoluta();
        setMarcaDeClase();
        setFrecuenciaObservada();
        imprimir();
        mostrarTablaDistribuciones();
        mostrarTablaEstadistica();
    }
}
