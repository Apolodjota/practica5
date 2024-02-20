/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.grafos;

import controlador.listas.ListaEnlazada;
import controlador.listas.exceptions.VacioException;
import controlador.util.Utilidades;
import java.io.FileWriter;

public class DibujarGrafo {
    String URL = "d3/grafo.js";
    public void crearArchivo(GrafoEtiquetadoNoDirigido g) throws VacioException {
        
        StringBuilder vertices = new StringBuilder();
        StringBuilder aristas = new StringBuilder();
        vertices.append("var nodes = new vis.DataSet([\n");
        aristas.append("var edges = new vis.DataSet([\n");
        for (int i = 1; i <= g.nro_vertices(); i++) {
            vertices.append("\t{ id: ").append(i).append(", label: \" ")
                    .append(g.obtenerEtiquetaE(i)).append("\" },\n");
            ListaEnlazada<Adyacencia> listaAdyacencia = g.adyacentes(i);
            
            for (int j = 0; j < listaAdyacencia.getSize(); j++) {
                Adyacencia a = listaAdyacencia.get(j);
                aristas.append("\t{ from: ").append(i)
                        .append(", to: ").append(a.getD())
                        .append(", label: \"").append(Utilidades.redondear(a.getPeso())).append("\" },\n");
            }
        }
        vertices.append("]);");
        aristas.append("]);");
        System.out.println(vertices);
        System.out.println(aristas);

        String finalArchivo = "var container = document.getElementById(\"mynetwork\");\n" +
"      var data = {\n" +
"        nodes: nodes,\n" +
"        edges: edges,\n" +
"      };\n" +
"      var options = {};\n" +
"      var network = new vis.Network(container, data, options);";
        
        try {
            FileWriter file = new FileWriter(URL);
            file.write(vertices+"\n"+aristas+"\n"+finalArchivo);
            file.close();
        } catch (Exception e) {
        }
    }
}
