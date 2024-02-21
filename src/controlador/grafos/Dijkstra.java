/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.grafos;

import controlador.util.Utilidades;

/**
 *
 * @author Apolo
 */
public class Dijkstra {

    private Double[][] Pesos;
    private int[] ultimo;
    private Double[] D;
    private boolean[] F;
    private int s, n;
    private GrafoEtiquetadoNoDirigido graforesultante = null;

    public Dijkstra(GrafoEtiquetadoNoDirigido g, Integer origen) {
        n = g.nro_vertices(); // Vertices
        s = origen + 1; //Vertice de origen
        Pesos = new Double[n][n];
        matriz(g, Pesos);
        ultimo = new int[n]; //Sacado de la matriz
        D = new Double[n];
        F = new boolean[n];
    }

    public void matriz(GrafoEtiquetadoNoDirigido grafo, Double[][] matrizPesos) {
        Integer nroV = grafo.nro_vertices();
        for (int i = 0; i < nroV; i++) {
            for (int j = 0; j < nroV; j++) {
                if (i == j) {
                    matrizPesos[i][j] = 0.0;
                } else {
                    try {
                        Double peso = grafo.peso_arista(i, j);
                        matrizPesos[i][j] = (Double)Utilidades.redondear(peso);
                    } catch (Exception e) {
                        matrizPesos[i][j] = Double.POSITIVE_INFINITY;
                    }

                }

            }
        }
        for (int i = 0; i < nroV; i++) {
            for (int j = 0; j < nroV; j++) {
                System.out.print(" [" + matrizPesos[i][j] + "] \t");
            }
            System.out.println("");
        }
    }

    public void caminoMinimos() {
        for (int i = 0; i < n; i++) {
            F[i] = false;
            D[i] = Pesos[s][i];
            ultimo[i] = s;
        }
        F[s] = true;
        D[s] = 0.0;
        for (int i = 1; i < n; i++) { //!
            int v = minimo();
            F[v] = true;
            for (int w = 1; w < n; w++) {
                if (!F[w]) {
                    if ((D[v] + Pesos[v][w]) < D[w]) {
                        D[w] = D[v] + Pesos[v][w];
                        ultimo[w] = v;
                    }
                }
            }
        }
        recuperaCamino(s);
    }

    public int minimo() {
        Double mx = Double.POSITIVE_INFINITY;;
        int v = 1;
        for (int j = 1; j < n; j++) { //1
            if (!F[j] && (D[j] <= mx)) {
                mx = D[j];
                v = j;
            }
        }
        return v;
    }

    public void recuperaCamino(int v) {
        int anterior = ultimo[v];
        if (v != s) {
            recuperaCamino(anterior);
            System.out.print("..V" + v);
        } else {
            System.out.print("V" + s);
        }
    }
}
