/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.grafos;

import controlador.listas.ListaEnlazada;
import controlador.listas.Stack;
import controlador.listas.exceptions.VacioException;
import controlador.util.Utilidades;
import java.io.File;
import java.util.HashMap;
import modelo.Antena;

/**
 *
 * @author apolo
 */
public class GrafoEtiquetadoNoDirigido<E> extends GrafoEtiquetadoDirigido<E> {

    public GrafoEtiquetadoNoDirigido(Integer nro_vertice, Class<E> clazz) {
        super(nro_vertice, clazz);
    }

    @Override
    public void insertar(Integer a, Integer b, Double peso) throws Exception {
        if (a.intValue() <= nro_vertices() && b.intValue() <= nro_vertices()) {
            if (!existe_arista(a, b)) {

                Adyacencia aux0 = new Adyacencia();
                aux0.setPeso(peso);
                aux0.setD(b);

                Adyacencia auxD = new Adyacencia();
                auxD.setPeso(peso);
                auxD.setD(a);

                getListaAdyacente()[a].add(aux0);
                getListaAdyacente()[b].add(auxD);
                setNro_aristas(nro_aristas() + 1);
            }
        } else {
            throw new Exception();
        }
    }

    public Boolean recorridoProfundidad() throws Exception {
        Boolean[] m = visitados();
        System.out.println(m.length);
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(1);
        m[1] = true;
        while (!lista.isEmpty()) {
            Integer actual = lista.deleteFirst();
            System.out.println("Vertice actual: " + actual);
            Adyacencia[] adyacentes = this.adyacentes(actual).toArray();
            for (Adyacencia ady : adyacentes) {
                Integer d = ady.getD();
                System.out.println("Destino de la adyacencia: " + d);
                if (m[d] != null) {
                    if (m[d] == false) {
                        lista.add(d);
                        m[d] = true;
                    }
                }
            }
        }
        for (int i = 1; i < nro_vertices(); i++) {
            if (!m[i]) {
                return false;
            }
        }
        return true;
    }

    public Boolean[] visitados() {
        Boolean[] m = new Boolean[nro_vertices() + 1];
        System.out.println("Lista visitados" + (nro_vertices() + 1));
        for (int i = 1; i <= nro_vertices(); i++) {
            m[i] = false;
            System.out.println("Agregado " + i);
            System.out.println(m[0]);
        }
        System.out.println("Tamanioooo" + m.length);
        return m;
    }

    public Boolean recorridoAnchura() throws Exception {
        Boolean[] m = visitados();
        System.out.println(m.length);
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(1);
        m[1] = true;
        while (!lista.isEmpty()) {
            Integer actual = lista.deleteFirst();
            System.out.println("Vertice actual: " + actual);
            Adyacencia[] adyacentes = this.adyacentes(actual).toArray();
            for (Adyacencia ady : adyacentes) {
                Integer d = ady.getD();
                System.out.println("Destino de la adyacencia: " + d);
                if (m[d] != null) {
                    if (m[d] == false) {
                        lista.add(d);
                        m[d] = true;
                    }
                }
            }
        }
        for (int i = 1; i < nro_vertices(); i++) {
            if (!m[i]) {
                return false;
            }
        }
        return true;
    }

    public HashMap<Integer, Integer> dijkstra(Integer origen) throws VacioException {
        Integer n = nro_vertices();
        Double[] distancias = new Double[n + 1];
        HashMap<Integer, Integer> predecesores = new HashMap<>();
        Boolean[] visitados = new Boolean[n + 1];

        // Inicialización
        for (int i = 1; i <= n; i++) {
            distancias[i] = Double.MAX_VALUE;
            visitados[i] = false;
            predecesores.put(i, -1);
        }
        distancias[origen] = 0.0;

        for (int i = 1; i <= n; i++) {
            // Encuentra el vértice con la mínima distancia no visitado
            Integer u = -1;
            Double minDist = Double.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                if (!visitados[j] && distancias[j] < minDist) {
                    minDist = distancias[j];
                    u = j;
                }
            }

            if (u == -1) {
                break; // Si no hay tal vértice, termina el bucle
            }
            visitados[u] = true;

            // Relajación
            ListaEnlazada<Adyacencia> adyacentes = adyacentes(u);
            for (int j = 0; j < adyacentes.getSize(); j++) {
                Adyacencia ady = adyacentes.get(j);
                Integer v = ady.getD();
                Double peso = ady.getPeso();
                if (distancias[u] + peso < distancias[v]) {
                    distancias[v] = distancias[u] + peso;
                    predecesores.put(v, u);
                }
            }
        }

        // Aquí puedes convertir distancias y predecesores en la estructura que necesites devolver
        return predecesores; // Este mapa contiene el vértice predecesor de cada vértice en el camino más corto desde el origen
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public static void main(String[] args) {
        //GrafoEtiquetadoNoDirigido <String> gs = new GrafoEtiquetadoNoDirigido(5, String.class);
        try {
            GrafoEtiquetadoNoDirigido<String> gs = new GrafoEtiquetadoNoDirigido(5, String.class
            );
            gs.etiquetarVertice(1, "Andres Tapia");
            gs.etiquetarVertice(2, "Alexis Ludeña");
            gs.etiquetarVertice(3, "Bravo");
            gs.etiquetarVertice(4, "Naim");
            gs.etiquetarVertice(5, "Herrera");

            gs.insertarAristaE("Bravo", "Naim", 90.0);
            String dir = Utilidades.getDirProject();
            String os = Utilidades.getOs();
            DibujarGrafo dg = new DibujarGrafo();
            dg.crearArchivo(gs);
            if (os.equalsIgnoreCase("Windows 11")) {
                Utilidades.abrirNavegadorPredeterminadoWindows(dir + File.separatorChar + "d3" + File.separatorChar + "grafo.html");
            } else {
                System.out.println("No existe sistema");
            }
            System.out.println(gs.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
