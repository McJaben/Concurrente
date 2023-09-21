package practico2Threads;

import java.util.Random;

public class Corredor implements Runnable {
    /*
     * Son los hilos, cada corredor conoce la distancia que recorrió.
     * Debe imprimir su nombre y el avance (aleatorio entre 1 y 10) por cada paso
     * realizado.
     * Entre cada paso realizado descansa.
     * Termina a los 100 pasos realizados.
     */

    private String nombre;
    private float distanciaRecorrida;

    public Corredor(String name, float dist) {
        this.nombre = name;
        this.distanciaRecorrida = dist;
    }

    public void run() {
        while (distanciaRecorrida < 100) {
            Random numAleatorio = new Random(System.currentTimeMillis());
            int intAleatorio = 1 + numAleatorio.nextInt(10);
            this.distanciaRecorrida += intAleatorio; 
            System.out.println("[Nombre: " + nombre + "| Distancia recorrida: " + distanciaRecorrida + " pasos]");
        }
    }

}
