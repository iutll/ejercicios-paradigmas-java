/*
    Carlos Martinez
    Torres de Hanoi con 3 pilas
*/


package torrehanoipila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Carlos Martínez
 */
public class TorreHanoiPila {

    public static void main(String[] args) throws IOException{
        int cant_discos[];
        int cant_movs;
        Integer discos;
        int disco_a_mover, discoQuitado, discoPuesto;
        int i;
        int j;
        boolean puede_mover;
        int t1;  // movimiento 1
        int t2;  // movimiento 2
        //****************************************
        //****************************************
        // pilas para las torres
        Stack pilaTorre1 = new Stack();
        Stack pilaTorre2 = new Stack();
        Stack pilaTorre3 = new Stack();
        
        ArrayList<Stack> TORRES = new ArrayList<Stack>();
        TORRES.add(pilaTorre1);
        TORRES.add(pilaTorre2);
        TORRES.add(pilaTorre3);
        
        //****************************************
        //****************************************
        // cantidad de discos en cada torre
        cant_discos = new int[3];
        // pedir y validar cuantos discos colocar en la primer torre
        System.out.println("Ingrese el nro de discos (1-8):");
        discos = exigirEntero();
        while (discos<1 || discos>8) {
                System.out.println("El numero de discos debe ser mayor a 0 y menor a 8:");
                discos = exigirEntero();
        }
        // inicializar los datos
        for(i = discos; i > 0; i--){
            TORRES.get(0).push(i);
        }
        // crear array donde estarán las 3 pilas
        
        // jugar!
        cant_movs = 0;
        // mientras no esten todos los discos en la tercer torre, el juego sigue
        while (TORRES.get(2).size() !=discos) {
            // se dibujan las 3 torres
            dibujar3Torres(TORRES);
                
                // solicitar movimiento
                System.out.println("Mover desde la torre: ");
                t1 = exigirEntero();
                System.out.println("hacia la torre: ");
                t2 = exigirEntero();
                // controlar que el nro de torre sea valido
                if (t1<1 || t1>3 || t2<1 || t2>3) {
                        System.out.println("Movimiento invalido");
                        System.in.read(); // a diferencia del pseudocódigo, espera un Enter, no cualquier tecla
                } else {
                        // controlar que la primera torre que se elige tengo al menos un disco
                        if (TORRES.get(t1-1).empty()) {
                                System.out.println("Movimiento invalido");
                                System.in.read(); // a diferencia del pseudocódigo, espera un Enter, no cualquier tecla
                        } else {
                                // obtener tamanio del disco que se quiere mover
                                disco_a_mover = (int) TORRES.get(t1-1).peek();
                                puede_mover = true;
                                // controlar que la torre dos no tenga discos o tenga solo discos mas grandes
                                if (!TORRES.get(t2-1).empty()) {
                                        if ( (int) TORRES.get(t2-1).peek() < disco_a_mover) {
                                                puede_mover = false;
                                        }
                                }
                                // si paso todos los controles, mover
                                if (puede_mover) {
                                        cant_movs++;
                                        // quitar disco de la torre origen y ponerla en la torre destino
                                        discoQuitado = (int) TORRES.get(t1-1).pop();
                                        TORRES.get(t2-1).push(discoQuitado);
                                } else {
                                        System.out.println("Movimiento invalido");
                                        System.in.read(); // a diferencia del pseudocódigo, espera un Enter, no cualquier tecla
                                }
                        }
                }
        }
        // mostrar resultado
        System.out.println(""); // no hay forma directa de borrar la consola en Java
        // mensaje si gano el juego
        System.out.println("felicidades, has ganado!!!");
        // dibujar la torre completada
        dibujar3Torres(TORRES);
        System.out.println("Juego finalizado en "+cant_movs+" movimientos!");
}

    
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

// metodo para validar y devolver un entero
static int exigirEntero(){
    BufferedReader bufEntrada = new BufferedReader(new InputStreamReader(System.in));
    Integer enteroAretornar;
        while(true){
            try{
                enteroAretornar = Integer.parseInt(bufEntrada.readLine());
                if(enteroAretornar instanceof Integer) {
                    // romper el ciclo
                    break;
                }
            }catch(Exception e){
                System.out.println("SOLO NUMEROS POR FAVOR");
            }
        }
        // retornar el entero
        return enteroAretornar;
    }

    
    // dibujar las 3 torres
    static void dibujar3Torres( ArrayList<Stack> TORRES){
        int i,j;
        for(i = 0; i <= 2 ; i++){
                System.out.println(""); // no hay forma directa de borrar la consola en Java
                // dibujar las tres torres
                System.out.println("Torre " + (i + 1));
                // si la torre esta vacia
                if(TORRES.get(i).empty()){
                    System.out.println("");
                }else{
                    for(j = TORRES.get(i).size() - 1; j >= 0 ; j--){
                        discoSize( (int) TORRES.get(i).get(j) );
                    }
                }
                System.out.println("   ----------------------------------");
                System.out.println("");
            } 
    }

}
    
