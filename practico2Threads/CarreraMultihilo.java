package practico2Threads;

import java.util.Scanner;

public class CarreraMultihilo {
    // Ejercicio 6 del TP de Threads
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        int mayorDistancia;
        Corredor[] arreglo = new Corredor[4];
        cargarArreglo(arreglo);
        Thread run1 = new Thread(arreglo[0]);
        Thread run2 = new Thread(arreglo[1]);
        Thread run3 = new Thread(arreglo[2]);
        Thread run4 = new Thread(arreglo[3]);

        run1.start();
        run2.start();
        run3.start();
        run4.start();
        run1.join();
        run2.join();    
        run3.join();    
        run4.join();        

        mayorDistancia = distanciaMaxima(arreglo);
        System.out.println("El corredor que recorrió la mayor distancia fue " + arreglo[mayorDistancia].getNombre() +
        " y la distancia recorrida fue de "+ arreglo[mayorDistancia].getDistancia() + " pasos.");
    }

    private static void cargarArreglo(Corredor[] array) {
        System.out.println("Se crearán 4 objetos corredor.");
        for (int i = 0; i < array.length; i++) {
            System.out.print("Ingresar el nombre del corredor " + (i+1) + ": ");
            String nombre = sc.nextLine();
            Corredor runner = new Corredor(nombre, 0);
            array[i] = runner;
        }
    }

    private static int distanciaMaxima(Corredor[] array) {
        // Devuelve la posición del corredor que recorrió la mayor distancia.
        int mayor = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].getDistancia() > array[mayor].getDistancia()) {
                mayor = i;
            }
        }
        return mayor;
    }

}
