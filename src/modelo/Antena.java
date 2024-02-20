/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Apolo
 */
public class Antena {
    private Integer id;
    private String alias;
    private String foto;
    private Double latitud;
    private Double longitud;
    private Double frecuenciaMaxOperacion;
    private Double frecuenciaMinOperacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getFrecuenciaMaxOperacion() {
        return frecuenciaMaxOperacion;
    }

    public void setFrecuenciaMaxOperacion(Double frecuenciaMaxOperacion) {
        this.frecuenciaMaxOperacion = frecuenciaMaxOperacion;
    }

    public Double getFrecuenciaMinOperacion() {
        return frecuenciaMinOperacion;
    }

    public void setFrecuenciaMinOperacion(Double frecuenciaMinOperacion) {
        this.frecuenciaMinOperacion = frecuenciaMinOperacion;
    }


    @Override
    public String toString() {
        return id+". "+alias;
    }
}
