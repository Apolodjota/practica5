/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.grafos;

/**
 *
 * @author apolo
 */
public class Adyacencia {
    private Double peso;
    private Integer d;

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }


    public Integer getD() {
        return d;
    }


    public void setD(Integer d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "Adyacencia -"+ d.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
}
