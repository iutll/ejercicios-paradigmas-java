/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoipila;
 
/**
 *
 * @author carlos
 * @param <OBJ>
 */
class Nodo<OBJ>{
   
    OBJ dato;
    Nodo<OBJ> siguiente;
    
    public Nodo(OBJ d){
        this.dato = d;    
        this.siguiente = null;
    }
}
