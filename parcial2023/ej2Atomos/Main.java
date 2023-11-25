package parcial2023.ej2Atomos;

public class Main {
    /*
     * Método main del ejercicio 2 del parcial 2023 de Programación Concurrente
     */
    public static void main(String[] args) {
        int max = 5;
        Espacio espacio = new Espacio(max); // límite de 5 moléculas de agua
        // Creo la cantidad justa para crear las 5 moléculas de agua para no complejizar
        // más el ejercicio. En el parcial se asumía que había un hilo generador de
        // átomos.
        Hidrogeno[] atomHidrogenos = new Hidrogeno[max * 2];
        Oxigeno[] atomOxigenos = new Oxigeno[max];
        Thread[] hidrogenos = new Thread[max * 2];
        Thread[] oxigenos = new Thread[max];

        // Creando objetos e hilos
        System.out.println("Creando objetos e hilos");
        for (int i = 0; i < atomHidrogenos.length; i++) {
            atomHidrogenos[i] = new Hidrogeno(espacio);
            hidrogenos[i] = new Thread(atomHidrogenos[i], ("hidrógeno número " + (i + 1)));
        }
        for (int j = 0; j < atomOxigenos.length; j++) {
            atomOxigenos[j] = new Oxigeno(espacio);
            oxigenos[j] = new Thread(atomOxigenos[j], ("oxígeno número " + (j + 1)));
        }
        // Inicializando hilos
        System.out.println("Inicializando hilos");
        for (int l = 0; l < atomHidrogenos.length; l++) {
            hidrogenos[l].start();
        }
        for (int m = 0; m < atomOxigenos.length; m++) {
            oxigenos[m].start();
        }
    }
}
