/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.grafos;

//import com.thoughtworks.xstream.core.StringCodec;
import controlador.listas.ListaEnlazada;
import java.util.HashMap;
import java.lang.reflect.Array;
import controlador.grafos.exception.EtiquetaExecption;

/**
 *
 * @author apolo
 */
public class GrafoEtiquetadoDirigido <E> extends GrafoDirigido{
    protected E etiqueta[];
    protected HashMap<E, Integer> dicVertices;
    private Class<E> clazz;

    public GrafoEtiquetadoDirigido(Integer nro_vertices, Class<E> clazz) {
        super(nro_vertices);
        this.clazz = clazz;
        etiqueta = (E[])Array.newInstance(clazz, nro_vertices + 1);
        dicVertices = new HashMap<>(nro_vertices);
    }
    
    public Boolean exsisteAristaE (E o, E d) throws Exception {
        if(estaEtiquetado())
            return existe_arista(obtenerCodigoE(o), obtenerCodigoE(d));
        else
            throw new EtiquetaExecption();
    }
    
    public void insertarAristaE(E o, E d, Double peso) throws Exception {
        if(estaEtiquetado())
            insertar(obtenerCodigoE(o), obtenerCodigoE(d), peso);
        else
            throw new EtiquetaExecption();
    }
    
    public void insertarAristaE(E o, E d) throws Exception {
        if(estaEtiquetado())
            insertar(obtenerCodigoE(o), obtenerCodigoE(d), Double.NaN);
        else
            throw new EtiquetaExecption();
    }
    
    public ListaEnlazada<Adyacencia> adyacentesE(E o) throws Exception {
        if(estaEtiquetado())
            return adyacentes(obtenerCodigoE(o));
        else
            throw new EtiquetaExecption();
                    
    }
    
    public void etiquetarVertice(Integer vertice, E dato) {
        etiqueta[vertice] = dato;
        dicVertices.put(dato, vertice);
    }
    
    public Boolean estaEtiquetado(){
        Boolean band = true;
        for(int i = 1; i < etiqueta.length ; i++ ) {
            E dato = etiqueta[i];
            if(dato == null){
                band = false;
                break;
            }
        }
        return band;
    }
    
    public Integer obtenerCodigoE (E etiqueta) {        
        return dicVertices.get(etiqueta);
    }
    
    public E obtenerEtiquetaE (Integer i) {        
        return etiqueta[i];
    }
  
    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder ("GRAFOS ETIQUETADOS\n");
        try {
            for (int i = 1; i <= nro_vertices() ; i++){
                grafo.append("Vertice").append(" ").append(obtenerEtiquetaE(i)).append("\n");
                if(!adyacentes(i).isEmpty()){
                    Adyacencia[] lista = adyacentes(i).toArray();
                    for (int j = 0; j < lista.length; j++) {
                        Adyacencia a = lista[j];
                        grafo.append("Adyacente ").append(obtenerEtiquetaE(a.getD())).
                                append("--Peso--").append(a.getPeso()).append("\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return grafo.toString();
    }
    
    public static void main(String[] args) {
        GrafoEtiquetadoDirigido <String> gs = new GrafoEtiquetadoDirigido(5, String.class);
        try {
            gs.etiquetarVertice(1, "Andres Tapia");
            gs.etiquetarVertice(2, "Alexis Ludeña");
            gs.etiquetarVertice(3, "Bravo");
            gs.etiquetarVertice(4, "Naim");
            gs.etiquetarVertice(5, "Herrera");
            
            gs.insertarAristaE("Bravo", "Naim", 90.0);
            System.out.println(gs.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
    
    
    }
    
}
