/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.tablas;

import controlador.listas.ListaEnlazada;
import controlador.listas.exceptions.VacioException;
import javax.swing.table.AbstractTableModel;
import modelo.Antena;

/**
 *
 * @author Apolo
 */
public class ModeloTablaAntenas extends AbstractTableModel{
    private ListaEnlazada <Antena> antenas;
    
    @Override
    public int getRowCount() {
        return antenas.getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;    
    }

    @Override
    public Object getValueAt(int i, int j) {
        Antena antena = null;
        try {
            antena = antenas.get(i);
        } catch (VacioException ex) {
            System.out.println(ex.getMessage());
        }
        switch (j) {
            case 0:
                return (antena != null) ? antena.getId(): "";
            case 1:
                return (antena != null) ? antena.getAlias(): "";
            case 2:
                return (antena != null) ? antena.getLatitud() : "";
            case 3:
                return (antena != null) ? antena.getLongitud() : "";
            case 4:
                return (antena != null) ? "("+
                        antena.getFrecuenciaMinOperacion()+", "+
                        antena.getFrecuenciaMaxOperacion()+")"
                        : "";
            default:
                return null;
        }
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nro";
            case 1:
                return "Antena";
            case 2:
                return "Latitud";
            case 3:
                return "Longitud";
            case 4:
                return "Frecuencia";
            default:
                return null;
        }
    }

    /**
     * @return the antenas
     */
    public ListaEnlazada<Antena> getAntenas() {
        return antenas;
    }

    /**
     * @param escuelas the antenas to set
     */
    public void setAntenas(ListaEnlazada <Antena> escuelas) {
        this.antenas = escuelas;
    }
}
