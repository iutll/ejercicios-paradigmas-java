/*
* october 2019 El parque inc.
* code by:
*
*   Carlos Martínez
*   Edsel Rengifo
*   Siddartha Lisboa
*   
*/

package ochoreinasarreglos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class OchoReinasArreglos {
    // soluciones posibles
    public static int soluciones;
    public static int nCasillas;
    public static int globalIndex;
    

    public static void main(String[] args) throws IOException {
        // iteradores
        int i;
        int j;
        System.out.println("ingresa la cantidad de casillas: ");
        
        nCasillas = pedirdatos();
        
        ArrayList arr;
        arr = new ArrayList<>(nCasillas);
        for(i = 0; i < nCasillas; i++) arr.add(-1);
        
        colocarReinas(arr, 0);
        
        System.out.println("las soluciones posibles son: " + soluciones);
        
    }
    
    // colocar reinas en el tablero
    static void colocarReinas(ArrayList<Integer> positions, int filaActual){
        if(filaActual == nCasillas){
            mostrarTablero(positions);
            soluciones++;

        }
        // tratar de posicionar una torre a la vez
        else{

            for(int i = 0; i < nCasillas; i++){
                if( revisarLugares(positions, filaActual, i) ){
                    positions.set(filaActual, i);
                    colocarReinas(positions, filaActual + 1);
                }
                
            }
        }
    }
    
    // revisar si hay una casilla disponible para la reina
    static boolean revisarLugares(ArrayList<Integer> positions, int filasOcupadas, int columna){
        
        for(int i = 0; i < filasOcupadas; i++){
            if(positions.get(i) == columna){
                return false;
            }
            else if(positions.get(i) - i == ( columna - filasOcupadas ) ){
                return false;
            }
            else if(positions.get(i) + i == columna + filasOcupadas){
                return false;
            }
        }
        return true;
    }
    
    // probar tablero completo
    static void mostrarTablero(ArrayList<Integer> positions){
        
        String linea;
        
        for(int i = 0; i < nCasillas; i++){
            linea = "";
            
            for(int j = 0; j < nCasillas; j++){
                if( positions.get(i) == j) linea += "R ";
                else linea += ". ";
            }
            System.out.println(linea);
            
        }
        System.out.println("\n");
    }
    
    
     static int pedirdatos() throws IOException{
        BufferedReader entradaDatos = new BufferedReader(new InputStreamReader(System.in));
        int cantidadDeCasillas;
        
        while(true){
            try{
                cantidadDeCasillas = Integer.parseInt(entradaDatos.readLine());
                break;// cerrar el ciclo si se introdujo correctamente un numero
                
            }catch(IOException | NumberFormatException e){
                System.out.println("SOLO NUMEROS POR FAVOR");
            }
        }
        
        return cantidadDeCasillas;
    }
     
}
