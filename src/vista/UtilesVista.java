/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.grafos.Adyacencia;
import controlador.grafos.Grafo;
import controlador.grafos.GrafoEtiquetadoDirigido;
import controlador.grafos.GrafoEtiquetadoNoDirigido;
import controlador.listas.ListaEnlazada;
import controlador.listas.exceptions.VacioException;
import controlador.antenas.AntenaDao;
import java.io.FileWriter;
import javax.swing.ComboBoxEditor;
import modelo.Antena;
import org.edisoncor.gui.comboBox.ComboBoxRect;

/**
 *
 * @author Apolo
 */
public class UtilesVista {
    
    public static void cargarComboAntena(ComboBoxRect combo) throws Exception{
        combo.removeAllItems();
        ListaEnlazada<Antena> lista = new AntenaDao().listAll();
        for (int i = 0; i < lista.getSize(); i++) {
            combo.addItem(lista.get(i));
        }       
    }
    public static Antena getComboAntena(ComboBoxRect combo) throws Exception{
        return (Antena) combo.getSelectedItem();
    }
    
    
    public static void crearMapa(Grafo g) {
        if (g instanceof GrafoEtiquetadoDirigido || g instanceof GrafoEtiquetadoNoDirigido) {
            GrafoEtiquetadoDirigido ge = (GrafoEtiquetadoDirigido) g;
            String mapa = "var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',\n"
                    + "        osmAttrib = '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors',\n"
                    + "        osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});\n"
                    + "\n"
                    + "var map = L.map('map').setView([-4.036, -79.201], 15);\n"
                    + "\n"
                    + "L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {\n"
                    + "    attribution: '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors'\n"
                    + "}).addTo(map);"+"\n";
            try {
                for (int i = 1; i <= ge.nro_vertices(); i++) {
                    Antena ant = (Antena) ge.obtenerEtiquetaE(i);
                    mapa += "\nL.marker([" + ant.getLatitud() + ", " + ant.getLongitud() + "]).addTo(map)\n";
                    mapa += ".bindPopup('" + ant.getAlias() + "')";
                    mapa += ".openPopup();" + "\n";
                }
                FileWriter file = new FileWriter("mapas/mapas.js");
                file.write(mapa);
                file.close();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
