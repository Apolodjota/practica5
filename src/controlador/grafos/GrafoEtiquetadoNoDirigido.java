/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.grafos;

import controlador.listas.ListaEnlazada;
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
        Boolean[] m = visitados();//11 falsos
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(1);
        m[1] = true;
        while (!lista.isEmpty()) {
            Integer actual = lista.deleteLast();
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
        for (int i = 1; i <= nro_vertices(); i++) {
            if (!m[i]) {
                return false;
            }
        }
        return true;
    }

    public Boolean[] visitados() {
        Boolean[] m = new Boolean[nro_vertices() + 1];
        System.out.println("Lista visitados"+nro_vertices());
        for (int i = 1; i <= nro_vertices(); i++) {
            m[i] = false;
            System.out.println("Agregado "+i);
            System.out.println(m[0]);
        }
        System.out.println("Tamanioooo"+m.length);
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

    public void Dijkstrac(Integer origen, Integer destino) {
    Integer n = nro_vertices(); //número de vértices
    Double[][] pesos = new Double [n][n];
    Integer[] ultimo;
    Integer [] D;
    Boolean[] F = visitados();
    
    }
    public HashMap dijkstrac (Integer o, Integer d) throws Exception{
        HashMap sendero = new HashMap();
        if(esta_conectado()) {
                ListaEnlazada<Integer> vertices = new ListaEnlazada<>();
                ListaEnlazada<Double> pesos = new ListaEnlazada<>();
                Boolean finalizar = false;
                Integer inicial = o;
                vertices.add(inicial);
                while(!finalizar){
                    ListaEnlazada<Adyacencia> adyacencias = adyacentes(inicial);
                    Double peso = Double.MAX_VALUE;
                    Integer T = -1;
                    for (int i = 0; i < adyacencias.getSize() ; i++) {
                        Adyacencia ad = adyacencias.get(i);
                        //if(!estaEnCamino(vertices, ad.getD().intValue())){
                           Double pesoArista = ad.getPeso();
                            
                            if(d.intValue() == ad.getD().intValue()) {
                                T = ad.getD();
                                peso = pesoArista;
                                break;
                            } else if (pesoArista < peso){
                                T = ad.getD();
                                peso = pesoArista;
                           // }
                        }
                    }
                    vertices.add(T);
                    pesos.add(peso);
                    inicial = T;
                    if(d.intValue() == inicial.intValue()) break;
                }
                sendero.put("camino", vertices);
                sendero.put("peso", pesos);
            }else{} 
        return sendero;
    }

@Override
public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    public static 

void main(String[] args) {
        //GrafoEtiquetadoNoDirigido <String> gs = new GrafoEtiquetadoNoDirigido(5, String.class);
        try {
            GrafoEtiquetadoNoDirigido <String> gs = new GrafoEtiquetadoNoDirigido(5, String.class  

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
            if(os.equalsIgnoreCase("Windows 11"))
                Utilidades.abrirNavegadorPredeterminadoWindows(dir+File.separatorChar+"d3"+File.separatorChar+"grafo.html");
            else
                System.out.println("No existe sistema");
            System.out.println(gs.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
