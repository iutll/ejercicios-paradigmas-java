/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoipila;


/**
 *
 * @author Jhosen
 */
public class pila {
    nodopila cima;
    int tama;
    public pila(){
    
    cima= null;
    tama= 0;
    
    }
    
    public boolean estavacia()
    {
    
    return cima == null;
    }
    
    public void empujar(int numeroTamanio){
    
        nodopila nuevo;
            nuevo = new nodopila(numeroTamanio);


        nuevo.siguiente = cima;

        cima = nuevo;

        tama++;
    }
  
    
    public int tamaniopila(){
    
    
    return tama;
    }
    
   
    }

