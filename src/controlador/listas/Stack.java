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
public class Stack<E> {
    private StackOperation<E> stackOperation;

    public Stack(Integer cant)  {
        this.stackOperation = new StackOperation<>(cant);
       
    }
     public void push(E dato) throws LlenoException{
         stackOperation.push(dato);
     }
     public Integer getSIze(){
         return this.stackOperation.getSize();
     }
     public void clear(){
         this.stackOperation.clear();
     }
     public Integer getTop(){
         return this.stackOperation.getTop();
     }
    public void print(){
        System.out.println("Pila");
        System.out.println(stackOperation.print());
        System.out.println("****************");
    }
    public E pop() throws VacioException{
        return stackOperation.pop();
    }
}
