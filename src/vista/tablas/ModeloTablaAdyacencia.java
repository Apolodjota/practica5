/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.tablas;

import controlador.grafos.GrafoEtiquetadoNoDirigido;
import controlador.util.Utilidades;
import javax.swing.table.AbstractTableModel;
import modelo.Antena;

/**
 *
 * @author Apolo
 */
public class ModeloTablaAdyacencia extends AbstractTableModel {
    private GrafoEtiquetadoNoDirigido <Antena> grafo;
    
    @Override
    public int getRowCount() {
        return getGrafo().nro_vertices();
    }

    @Override
    public int getColumnCount() {
        return getGrafo().nro_vertices() + 1;  
    }

    @Override
    public Object getValueAt(int i, int j) {
        if (j == 0 ){
            return getGrafo().obtenerEtiquetaE(i + 1).toString();
        }else{
            String valor = "**";
            try {
                
                    Antena antenaOG = getGrafo().obtenerEtiquetaE(i + 1);
                    Antena antenaDES = getGrafo().obtenerEtiquetaE(j);
                    
                    if(getGrafo().exsisteAristaE(antenaOG, antenaDES)){
                      valor = Utilidades.redondear(getGrafo().peso_arista((i + 1), j)).toString();
                    }
                    return valor;                    
                
            } catch (Exception e) {
                System.out.println("Error en modelo table "+e);
                return "";
            }
        }
    }
    
    public String getColumnName(int column) {
        if (column == 0 ){
            return "Antenas";
        }else{
            return getGrafo().obtenerEtiquetaE(column).toString();
        }
    }

    /**
     * @return the grafo
     */
    public GrafoEtiquetadoNoDirigido <Antena> getGrafo() {
        return grafo;
    }

    /**
     * @param grafo the grafo to set
     */
    public void setGrafo(GrafoEtiquetadoNoDirigido <Antena> grafo) {
        this.grafo = grafo;
    }
}
