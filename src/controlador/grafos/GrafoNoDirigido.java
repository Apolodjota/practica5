/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.grafos;

import controlador.grafos.exception.VerticeOfSizeExecption;
import controlador.listas.ListaEnlazada;
import controlador.util.Utilidades;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apolo
 */
public class GrafoNoDirigido extends GrafoDirigido{

    public GrafoNoDirigido(Integer nro_vertices) {
        super(nro_vertices);
    }

    @Override
    public void insertar(Integer a, Integer b, Double peso) throws Exception {
        if(a.intValue() <= nro_vertices().intValue() &&  b.intValue() <= nro_vertices().intValue()) {
             if(!existe_arista(a, b)) {
                 setNro_aristas(nro_aristas()+1);
                 
                 Adyacencia auxO = new Adyacencia();
                 auxO.setPeso(peso);
                 auxO.setD(b);
                 
                 Adyacencia auxD = new Adyacencia();
                 auxD.setPeso(peso);
                 auxD.setD(a);
                 getListaAdyacente()[a].add(auxO);
                 getListaAdyacente()[b].add(auxD);
                 //listaAdycente[a].add(aux);
             }
         } else
             throw new VerticeOfSizeExecption();
                
        }
    
    
    //public static void main(String[] args) {
//        GrafoNoDirigido gnd = new GrafoNoDirigido(4);
//        try {
//            System.out.println(gnd);
//            gnd.insertar(1, 4, 20.5);
//            System.out.println(gnd.adyacentes(1).print());
//            System.out.println("-----------------");
//            
//            gnd.insertar(1, 3, 8.0);
//            gnd.insertar(3, 2, 2.5);
//            gnd.insertar(2, 3, 5.0);
//            
//            System.out.println(gnd);
//            DibujarGrafo dg = new DibujarGrafo();
//            dg.crearArchivo(gnd);
//            String os = Utilidades.getOs();
//            String dir = Utilidades.getDirProject();
//            System.out.println(dir); 
//            
//            if(os.equalsIgnoreCase("Windows 11"))
//                Utilidades.abrirNavegadorPredeterminadoWindows(dir+File.separatorChar+"d3"+File.separatorChar+"grafo.html");
//            else
//                System.out.println("No existe sistema");
//            
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//    }
    
}
