
package hanoipila;

/**
 *
 * @author carlos
 */
public class Torre {
    Disco cima;
    int size;
    
    Torre(){
        cima = null;
        size = 0;
    }
    
    void agregarDisco(int discoTamano){
        Disco d = new Disco(discoTamano);
        d.siguiente = cima;
        cima = d;
        size++;
    }
    
    int quitarDisco(){
        Disco aux = cima.siguiente;
        int discoQuitado = cima.valor;
        cima = null;
        cima = aux;
        size--;
        
        return discoQuitado;
    }
    
    int obtenerPrimerDisco(){
        return cima.valor;
    }
    
    int getSize(){
        return size;
    }
    
    
    
    @Override
    public String toString(){
        Disco aux = cima;
        String resultado = "";
        if(this.size == 0){
            resultado = "la pila está vacía";
        }
        else{
            while(aux!= null){
                resultado += aux.valor;
                aux = aux.siguiente;
            }
        }
        return resultado;
    }
}
