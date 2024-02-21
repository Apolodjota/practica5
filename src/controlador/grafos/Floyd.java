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
public class Floyd {

    private Double[][] pesos;
    private int[][] mdismin;
    private Double[][] d;
    private int n; // número de vértices

    public Floyd(GrafoEtiquetadoNoDirigido g) {
        n = g.nro_vertices();
        pesos = new Double[n][n];
        matriz(g, pesos);
        d = new Double[n][n];
        mdismin = new int[n][n];
    }

    public void matriz(GrafoEtiquetadoNoDirigido grafo, Double[][] matrizPesos) {
        int numVertices = grafo.nro_vertices();
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                try {
                    double peso = grafo.peso_arista(i, j);
                    matrizPesos[i][j] = Utilidades.redondear(peso);
                } catch (Exception e) {
                    matrizPesos[i][j] = Double.POSITIVE_INFINITY;
                }
            }
            System.out.println(" ");
        }
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(" [" + Utilidades.redondear(matrizPesos[i][j]) + "] ");
            }
            System.out.println("");
        }
    }

    public void todosCaminosMinimo() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = pesos[i][j];
                mdismin[i][j] = -1;
            }
        }
// Camino mínimo de un vértice a si mismo: 0
        for (int i = 0; i < n; i++) {
            d[i][i] = 0.0;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((d[i][k] + d[k][j]) < d[i][j]) // nuevo mínimo
                    {
                        d[i][j] = d[i][k] + d[k][j];
                        mdismin[i][j] = k;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && d[i][j] != Double.POSITIVE_INFINITY) { // Añadimos esta condición para evitar caminos entre nodos no conectados
                    System.out.print("de " + i + " a " + j + ": " + i);
                    int nodoIntermedio = i;
                    while (nodoIntermedio != j && nodoIntermedio != -1) {
                        nodoIntermedio = mdismin[nodoIntermedio][j];
                        if (nodoIntermedio != -1) {
                            System.out.print(" ... " + nodoIntermedio);
                        }
                    }
                    System.out.println(" ... " + j);
                }
            }

        }
    }
}
