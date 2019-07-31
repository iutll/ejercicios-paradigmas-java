/*
	Carlos Martinez, Edsel Rengifo
	2019
	github.com/iutll
*/

package hanoipila;
import java.io.*;
//import java.util.Arrays;
public class HanoiPila{

    public static void main(String[] args) throws IOException  {
      System.out.println("Torre de hanoi dinamica con una sola pila ");  
        System.out.println("introdusca el numero de discos");
        int nDiscos; // cantidad de discos
        int tOrigen; // torre de origen
        int tDestino; // torre destino
        int movimientos = 0; // cantidad de movimientos
        boolean puedeMover; // comprobar mover de una torre a otra
        
        nDiscos = pedirdatos();
        while (nDiscos<1 || nDiscos>8) {
            System.out.println("El numero de discos debe ser mayor a 0 y menor a 8:");
    
             nDiscos = pedirdatos();           
        }
        
        // 
        // $$$$$$$$$  creación(instanciación) de una pila  $$$$$$$$$
        pila TORRESPILA = new pila();
        // $$$$$$$$$  introducir  3 nodos a la pila  $$$$$$$$$
        TORRESPILA.empujar(10); // torre 3
        TORRESPILA.empujar(10); // torre 2
        TORRESPILA.empujar(10); // torre 1 // este es el que queda más arriba
        
        //incializar 
        // $$$$$  llenar los datos del ultimo nodo ( primero en salir ) $$$$$
       for(int i = 0; i < nDiscos; i++){
           TORRESPILA.cima.dato[i] = i + 1;
       }    
       // cantidad maxima de cada torre
       int cantidadMaxima = cantidadMaximaEnTorre(TORRESPILA.cima.dato);
       
       // arreglo global con el mismo valor de la torre 3
       // guardada en la pila
       int copiaTorre3[];
       
       nodopila nodoAuxiliar = TORRESPILA.cima;
       while(nodoAuxiliar.siguiente != null ){
           nodoAuxiliar = nodoAuxiliar.siguiente;
       }
       
       
        copiaTorre3 = nodoAuxiliar.dato;
        
        // ######################  empezar juego  ####################
        // cantidadMaximaEnTorre(copiaTorre3)
        while( cantidadMaximaEnTorre(copiaTorre3) < cantidadMaxima ){
            
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
                moverDiscos(TORRESPILA, tOrigen, tDestino, copiaTorre3);
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
    
    // #########################  emular  sacar primero  ##################################
    static int emulOUT(int arr[]){

        int primero = arr[0];
        int aux;
        // iteración para correr un espacio a la izquierda
        for ( int i = 0; i < arr.length -1; i++){
                aux = arr[i + 1];
                arr[i] = aux;
            }

        return primero;
    }
    
    // ##############################  emular introducir de primero  #############################
    static int[] emulIN(int []arr,int numero){
    
    int arrAux[] = new int[arr.length];
    arrAux[0] = numero;
    for (int i = 1; i < arr.length; i++){
    
    arrAux[i] = arr[i - 1];
    }
       return arrAux;
    }
    
    // #########################   dibujar la pila  #######################
    static void dibujar(pila PILA){
        nodopila auxPila = PILA.cima;
        int cont = 0;
        
        while(auxPila != null){
            cont++;
            System.out.println("TORRE " + cont);
            
            for(int i = 0; i < auxPila.dato.length; i++){
                discoSize(auxPila.dato[i]);
            }
            System.out.println("-----------------------------------");
            auxPila = auxPila.siguiente;
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

static boolean comprobarTorres(pila PILA, int origen, int destino){
    int cont = 1;
    int cimaDeOrigen;
    int cimaDestino;
    nodopila nodoAuxTorres = PILA.cima;
    
    // iterar hasta la torre de origen
    while(cont < origen){
        cont++;
        nodoAuxTorres = nodoAuxTorres.siguiente;
    }
    cimaDeOrigen = nodoAuxTorres.dato[0];
    
    // iterar hasta la torre de destino
    cont = 1; // resetear a 1
    nodoAuxTorres = PILA.cima; // resetear nodoPrincipal
    while(cont < destino){
        cont++;
        nodoAuxTorres = nodoAuxTorres.siguiente;
    }
    cimaDestino = nodoAuxTorres.dato[0];
    
    // *************************** comprobar a ver si se puede mover de una torre a la otra
    if(cimaDeOrigen != 0){
        if(cimaDestino == 0 || cimaDestino > cimaDeOrigen){
            return true;
        }
    }
    else{
        return false;
    }
    
    
    return false;// return obigatorio
    
}

// ###################################################################################################
// ###################################################################################################
// ###################################################################################################
// ###################################################################################################

// mover torre
static void moverDiscos(pila PILA, int origen, int destino, int []arrayGlobalTorre3){
    
    int discoExtraido;
    // *************************** sacar disco
    nodopila auxTorreNodo = PILA.cima;
    int cont = 1;
    // iteracion ladillosa
    while(cont < origen){
        cont++;
        auxTorreNodo = auxTorreNodo.siguiente;
    }
    
    // quitar el primer "disco"
    discoExtraido = emulOUT(auxTorreNodo.dato);
    
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
    auxTorreNodo.dato = emulIN( auxTorreNodo.dato , discoExtraido);
    
    // *************************************************  array global
    // asignar valor a el array global de la 3ra torre
    cont = 0;
    
    auxTorreNodo = PILA.cima;
    while(cont < 2){
        cont++;
        auxTorreNodo = auxTorreNodo.siguiente;
    }
    
    // copiar arreglo global de la torre 3
    for(int j = 0 ; j < auxTorreNodo.dato.length ; j++){
        arrayGlobalTorre3[j] = auxTorreNodo.dato[j];
    }
    
}


    // solucion rudimentaria para saber hasta donde se juega
    // es decir, se calcula la cantidad maxima que puede haber en una torre
    // y si la 3ra torre que esta vacia al principio
    // alcanza la cantidad maxima es porque ya se ganó el juego
    static int cantidadMaximaEnTorre(int[] arr){
        int acum = 0;
        for(int i = 0; i < arr.length ; i++ ){
            acum += arr[i];
        }

        return acum;
    }

    
}


