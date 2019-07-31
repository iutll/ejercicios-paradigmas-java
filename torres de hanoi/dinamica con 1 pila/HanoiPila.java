/*
	Carlos Martinez
	Torres de hanoi con 1 pila

	2019
*/


package hanoipila;
import java.io.*;
//import java.util.Arrays;
public class HanoiPila{

    // variable global con el mismo valor de la torre 3
    // guardada en la pila
    static Torre copiaTorre3;
    
    public static void main(String[] args) throws IOException {
      System.out.println("Torre de hanoi dinamica con una sola pila ");  
        System.out.println("introdusca el numero de discos");
        int nDiscos; // cantidad de discos
        
        nDiscos = pedirdatos();
        while (nDiscos<1 || nDiscos>8) {
            System.out.println("El numero de discos debe ser mayor a 0 y menor a 8:");
    
             nDiscos = pedirdatos();           
        }
        
        // 
        // $$$$$$$$$  creación(instanciación) de una pila  $$$$$$$$$
        Pila<Torre> TORRESPILA = new Pila<>(null, 0);
        // $$$$$$$$$  introducir  3 nodos a la pila  $$$$$$$$$
        TORRESPILA.empujar(new Torre()); // torre 3
        TORRESPILA.empujar(new Torre()); // torre 2
        TORRESPILA.empujar(new Torre()); // torre 1 // este es el que queda más arriba
        
        //incializar 
        // $$$$$  llenar los datos del ultimo nodo ( primero en salir ) $$$$$
       for(int i = nDiscos; i > 0; i--){
           TORRESPILA.cima.dato.agregarDisco(i);
       }
       
       Nodo<Torre> auxTorre = TORRESPILA.cima ;
       while(auxTorre.siguiente != null ){
           auxTorre = auxTorre.siguiente;
       }
       copiaTorre3 = auxTorre.dato;
        
       // ######################  empezar juego  ####################
        Play(TORRESPILA, nDiscos);
        
    }
    
    static void Play(Pila<Torre> TORRESPILA, int nDiscos) throws IOException{
        int tDestino; // torre que se elige primero
        int tOrigen; // torre de destino (hacia donde se desea mover)
        int movimientos = 0; // movimientos jugados
        boolean puedeMover;
        
        while( copiaTorre3.size < nDiscos ){
            
            // dibujar la torre en cada ronda
            dibujar(TORRESPILA);
            // pedir movimiento de origen y de llegada
            System.out.println("torre de origen: ");
            tOrigen = pedirdatos();
            System.out.println("torre destino: ");
            tDestino = pedirdatos();
            // pequeña validacion
            while( (tOrigen<1 || tOrigen>3) || (tDestino<1 || tDestino>3) ){
                System.out.println("\n\n\t rango no valido".toUpperCase());
                System.out.println("torre de origen: ");
                tOrigen = pedirdatos();
                System.out.println("torre destino: ");
                tDestino = pedirdatos();
            }
            
            // comprobar si puede mover de una torre a otra
            puedeMover = comprobarTorres(TORRESPILA, tOrigen, tDestino);
            if(puedeMover){
                movimientos++;
                moverDiscos(TORRESPILA, tOrigen, tDestino);
            }
            else{
                System.out.println("\n\n\t movimiento invalido".toUpperCase());
            }
            
        }
              
        // cuando se termina de jugar
        
        dibujar(TORRESPILA);
        System.out.println(" \n GANASTE!!! en " + movimientos + " movimientos" );
        
    }
    
    
    // ############################################################################
    static int pedirdatos() throws IOException{
        BufferedReader entradaDatos = new BufferedReader(new InputStreamReader(System.in));
        int cantidadDiscos;
        
        while(true){
            try{
                cantidadDiscos = Integer.parseInt(entradaDatos.readLine());
                break;// cerrar el ciclo si se introdujo correctamente un numero
                
            }catch(IOException | NumberFormatException e){
                System.out.println("SOLO NUMEROS POR FAVOR");
            }
        }
        
        return cantidadDiscos;
    }
    
    
    // #########################   dibujar la pila  #######################
    static void dibujar(Pila<Torre> PILA){
        Nodo<Torre> n = PILA.cima;
        int contador = 0;
        while(n != null){
            contador++;
            System.out.println("\t TORRE " + contador);
            System.out.println("");
            
            dibujar2(n.dato.cima);
            n = n.siguiente;
            System.out.println("-------------------------------------");
        }
        
    }
   
    // dibujar 2
    // este metodo se encarga de dibujar de disco en disco
    static void dibujar2(Disco d){
        Disco disck = d;
        while(disck != null){
            discoSize(disck.valor);
            disck = disck.siguiente;
        }
    }
    
    // ###################################################################
    // metodo que dibuja cada disco dependiendo de su tamaño    
static void discoSize(int size){
    switch (size) {
    case 1:
            System.out.println("                   XX");
            break;
    case 2:
            System.out.println("                 XXXXXX");
            break;
    case 3:
            System.out.println("               XXXXXXXXXX");
            break;
    case 4:
            System.out.println("             XXXXXXXXXXXXXX");
            break;
    case 5:
            System.out.println("           XXXXXXXXXXXXXXXXXX");
            break;
    case 6:
            System.out.println("         XXXXXXXXXXXXXXXXXXXXXX");
            break;
    case 7:
            System.out.println("       XXXXXXXXXXXXXXXXXXXXXXXXXX");
            break;
    case 8:
            System.out.println("     XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            break;
    }
}
    

// ####################################################################
// ####################################################################
// ####################################################################
// ####################################################################

static boolean comprobarTorres(Pila<Torre> PILA, int origen, int destino){
    int cont = 1;
    int cimaDeOrigen;
    int cimaDestino;
    Nodo<Torre> nodoAuxTorres = PILA.cima;
    
    // iterar hasta la torre de origen
    while(cont < origen){
        cont++;
        nodoAuxTorres = nodoAuxTorres.siguiente;
    }
    // validación para ver si tiene discos esa torre, ya que si no tiene discos no se 
    // puede meter ese valor en una variable tipo int
    try{
        cimaDeOrigen = nodoAuxTorres.dato.obtenerPrimerDisco();
    }catch(Exception e){
        cimaDeOrigen = 0;
    }
    
    // iterar hasta la torre de destino
    cont = 1; // resetear a 1
    nodoAuxTorres = PILA.cima; // resetear nodoPrincipal
    while(cont < destino){
        cont++;
        nodoAuxTorres = nodoAuxTorres.siguiente;
    }
    try{
        cimaDestino = nodoAuxTorres.dato.obtenerPrimerDisco();
    }catch(Exception e){
        cimaDestino = 0;
    }
    
    // *************************** comprobar a ver si se puede mover de una torre a la otra
    if(cimaDeOrigen != 0){
        if(cimaDestino == 0 || cimaDestino > cimaDeOrigen){
            return true;
        }
    }
    
    return false;// return obigatorio
    
}

// ###################################################################################################
// ###################################################################################################
// ###################################################################################################

// mover torre
static void moverDiscos(Pila PILA, int origen, int destino){
    
    // *************************** sacar disco
    Nodo<Torre> auxTorreNodo = PILA.cima;
    int discoExtraido;
    int cont = 1;
    // iteracion ladillosa
    while(cont < origen){
        cont++;
        auxTorreNodo = auxTorreNodo.siguiente;
    }
    
    // quitar el primer "disco"
    discoExtraido = auxTorreNodo.dato.quitarDisco();
    
    // ********************************** meter disco
    auxTorreNodo = PILA.cima;
    cont = 1;
    // iteracion ladillosa
    while(cont < destino){
        cont++;
        auxTorreNodo = auxTorreNodo.siguiente;
    }
    
    // meter disco extraido de torre de origen
    // en torre de destino
    auxTorreNodo.dato.agregarDisco(discoExtraido);
    
    
    }
}


