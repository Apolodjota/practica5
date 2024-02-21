/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.grafos;

import controlador.grafos.exception.VerticeOfSizeExecption;
import controlador.listas.ListaEnlazada;

/**
 *
 * @author apolo
 */
public class GrafoDirigido extends Grafo{
    private Integer nro_vertices;
    private Integer nro_aristas;
    private ListaEnlazada<Adyacencia>[] listaAdyacente;

    public GrafoDirigido(Integer nro_vertices) {
        this.nro_vertices = nro_vertices;
        nro_aristas = 0;
        listaAdyacente = new ListaEnlazada [nro_vertices + 1] ;
        for (int i = 1; i <= nro_vertices; i++) {
            listaAdyacente[i] = new ListaEnlazada<>();
        }
    }
    
    public ListaEnlazada<Adyacencia>[] getListaAdyacente() {
        return listaAdyacente;
    }
    
    public void setNro_aristas(Integer nro_aristas) {
        this.nro_aristas = nro_aristas;
    }

    public void setListaAdyacente(ListaEnlazada<Adyacencia>[] listaAdyacente) {
        this.listaAdyacente = listaAdyacente;
    }


    public void setNro_vertices(Integer nro_vertices) {
        this.nro_vertices = nro_vertices;
    }

    @Override
    public Integer nro_vertices() {
        return this.nro_vertices;   
    }
    
    @Override
    public Integer nro_aristas() {
        return this.nro_aristas;
    }

    @Override
    public Boolean existe_arista(Integer a, Integer b) throws Exception {
        Boolean band = false;
        if(a.intValue() <= nro_vertices.intValue() &&  b.intValue() <= nro_vertices.intValue()) {
             ListaEnlazada<Adyacencia> lista = listaAdyacente[a];
             for(int i = 0; i < lista.getSize(); i++) {
                 Adyacencia aux = lista.get(i);
                 if(aux.getD().intValue() == b.intValue()){
                     band = true;
                     break;
                 }
             }
         } else {
             throw new VerticeOfSizeExecption();
         }
         return band;

        
    
    }

    @Override
    public Double peso_arista(Integer a, Integer b) throws Exception{
        Double peso = Double.POSITIVE_INFINITY;
        if(existe_arista(a, b)) {
            ListaEnlazada<Adyacencia> lista = listaAdyacente[a];
             for(int i = 0; i < lista.getSize(); i++) {
                 Adyacencia aux = lista.get(i);
                 if(aux.getD().intValue() == b.intValue()){
                     peso = aux.getPeso();
                     break;
                 }
             }
        }
        return peso;

    }

    @Override
    public void insertar(Integer a, Integer b) throws Exception {
        insertar(a, b, Double.NaN);
    }

    @Override
    public void insertar(Integer a, Integer b, Double peso) throws Exception{
        if(a.intValue() <= nro_vertices().intValue() && b.intValue() <= nro_vertices()){
            if(!existe_arista(a, b)){
                nro_aristas++;
                 Adyacencia aux = new Adyacencia();
                 aux.setPeso(peso);
                 aux.setD(b);
                 listaAdyacente[a].add(aux);

            }else{
                throw new VerticeOfSizeExecption();
            }
                
        }
    }
    

    @Override
    public ListaEnlazada<Adyacencia> adyacentes(Integer a) {
        return listaAdyacente[a];
    }
    
    public static void main(String[] args) {
        GrafoDirigido gd = new GrafoDirigido(5);
        try {
            gd.insertar(7, 4);
            
            System.out.println(gd.toString());
            System.out.println(gd.adyacentes(7));
            System.out.println("ya");
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        
    }

}
