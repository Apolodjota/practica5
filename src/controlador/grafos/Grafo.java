/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.grafos;

import controlador.listas.ListaEnlazada;
import java.util.HashMap;

/**
 *
 * @author apolo
 */
public abstract class Grafo {
    public abstract Integer nro_vertices ();
    public abstract Integer nro_aristas ();
    public abstract Boolean existe_arista (Integer inicio, Integer destino) throws Exception;
    public abstract Double peso_arista (Integer inicio, Integer destino) throws Exception;
    public abstract void insertar (Integer inicio, Integer destino) throws Exception;
    public abstract void insertar (Integer inicio, Integer destino, Double peso) throws Exception;
    public abstract ListaEnlazada<Adyacencia> adyacentes (Integer a);

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder ("GRAFOS \n");
        try {
            for (int i = 1; i <= nro_vertices() ; i++){
                grafo.append("Vertice").append(" - ").append(String.valueOf(i)).append("\n");
                if(!adyacentes(i).isEmpty()){
                    Adyacencia[] lista = adyacentes(i).toArray();
                    for (int j = 0; j < lista.length; j++) {
                        Adyacencia a = lista[j];
                        grafo.append("Adyacente").append(a.getD().toString()).append("\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return grafo.toString();
    }
    
    protected Boolean esta_conectado(){
        Boolean band = true;
        try {
            for (int i = 1; i <= nro_vertices() ; i++){
                if(adyacentes(i).isEmpty()){
                    band = false;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return band;
    }
    public HashMap camino(Integer o, Integer d) throws Exception{
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
                        if(!estaEnCamino(vertices, ad.getD().intValue())){
                           Double pesoArista = ad.getPeso();
                            
                            if(d.intValue() == ad.getD().intValue()) {
                                T = ad.getD();
                                peso = pesoArista;
                                break;
                            } else if (pesoArista < peso){
                                T = ad.getD();
                                peso = pesoArista;
                            }
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
    
    private Boolean estaEnCamino(ListaEnlazada<Integer> lista, Integer vertice) throws Exception{
        Boolean band = false;
        for (int i = 0; i < lista.getSize(); i++) {
            if(lista.get(i).intValue() == vertice.intValue()){
                band = true;
                break;
            }
        }
        return band;
    }
    
    
}
