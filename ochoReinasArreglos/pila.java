
package ochoreinasarreglos;

class Pila {
    Nodo cima;
    int tama;
    
    public Pila(Nodo c, int tm){
        this.cima= c;
        this.tama= tm;
    }
    
    public boolean estavacia(){
        return cima == null;
    }
    
    public void empujar(int position){
    
        Nodo nuevo;
        nuevo = new Nodo(position);
        // concatenar nuevo nodo con ya existentes
        nuevo.siguiente = cima;
        // asignar la cima al nuevo nodo
        cima = nuevo;
        tama++;
    }
    
    public int iterar(int index){
        Nodo aux = cima;
        int i = 0;
        while(i < index){
            aux = aux.siguiente;
            i++;
         }
        //System.out.println("el valor retornado es " + aux.valor);
        return aux.valor;
    }
    
    public Nodo regresarNodo(int index){
        Nodo aux = cima;
        int i = 0;
        while(i < index){
            aux = aux.siguiente;
            i++;
         }
        //System.out.println("el valor retornado es " + aux.valor);
        return aux;
    }
    
    
    public void quitarPrimero(){
        if(!this.estavacia()){
            Nodo aux = cima.siguiente;
            cima = null;
            cima = aux;
        }
    }
     
    public void vaciar(){
        cima = null;
    }
    
    
    @Override
    public String toString(){
        Nodo aux = cima;
        String resultado = "";
        if(this.estavacia()){
            resultado = "la pila está vacía";
        }
        else{
            while(aux!= null){
                resultado += aux.valor;
                aux = aux.siguiente;
                System.out.println(resultado);
            }
        }
        return resultado;
    }
  
    
    
    public int tamaniopila(){
        return tama;
    }
    
}

