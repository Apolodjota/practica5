/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.listas;

import controlador.listas.exceptions.LlenoException;
import controlador.listas.exceptions.VacioException;

/**
 *
 * @author Apolo
 */
public class StackOperation <E> extends ListaEnlazada<E>{
    private Integer top;

    public StackOperation(Integer top) {
        this.top = top;
    }
    public Boolean verify(){
        return getSize().intValue() < getTop().intValue();
    }
    public void push(E dato) throws LlenoException{
        if (verify()){
            addFirst(dato);
        }else{
            throw new LlenoException("Pila llena");
        }
    }
    public E pop () throws VacioException{
        if (isEmpty()){
            throw new VacioException("Pila vacia");
        }else{
            return deleteFirst();
        }
    }
    
  

    /**
     * @return the top
     */
    public Integer getTop() {
        return top;
    }

    /**
     * @param top the top to set
     */
    public void setTop(Integer top) {
        this.top = top;
    }
    
   
}
