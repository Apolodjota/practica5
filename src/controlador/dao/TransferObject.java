/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.dao;

import controlador.listas.ListaEnlazada;

/**
 *
 * @author Apolo
 */
public interface TransferObject <T> {
    public Boolean save(T data);
    public Boolean update(T data, Integer i);
    public ListaEnlazada<T> listAll();
    public T find(Integer id);
    
    
}
