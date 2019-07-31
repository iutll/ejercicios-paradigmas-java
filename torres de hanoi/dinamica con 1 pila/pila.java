
package hanoipila;

/**
 * @author Jhosen
 * @modificación Carlos Martínez
 * @param <OBJ>
 */
class Pila<OBJ> {
    Nodo<OBJ> cima;
    int tama;
    
    public Pila(Nodo<OBJ> c, int tm){
        this.cima= c;
        this.tama= tm;
    }
    
    public boolean estavacia(){
        return cima == null;
    }
    
    public void empujar(OBJ element){
    
        Nodo<OBJ> nuevo;
        nuevo = new Nodo<>(element);
        // concatenar nuevo nodo con ya existentes
        nuevo.siguiente = cima;
        // asignar la cima al nuevo nodo
        cima = nuevo;
        tama++;
    }
    
    
    public void quitarPrimero(){
        if(!this.estavacia()){
            Nodo<OBJ> aux = cima.siguiente;
            cima = null;
            cima = aux;
        }
    }
     
    public void vaciar(){
        cima = null;
    }
    
    @Override
    public String toString(){
        Nodo<OBJ> aux = cima;
        String resultado = "";
        if(this.estavacia()){
            resultado = "la pila está vacía";
        }
        else{
            while(aux!= null){
                resultado += aux.dato;
                aux = aux.siguiente;
            }
        }
        return resultado;
    }
  
    
    public int tamaniopila(){
        return tama;
    }
    
}

