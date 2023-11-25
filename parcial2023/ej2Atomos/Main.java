package parcial2023.ej2Atomos;

public class Main {
    public static void main(String[] args) {
        int max = 5;
        Espacio espacio = new Espacio(max); // límite de 5 moléculas de agua
        // Creo la cantidad justa para crear las 5 moléculas de agua para no complejizar
        // más el ejercicio
        Hidrogeno[] atomHidrogenos = new Hidrogeno[max * 2];
        Oxigeno[] atomOxigenos = new Oxigeno[max];
        Thread[] hidrogenos = new Thread[max * 2];
        Thread[] oxigenos = new Thread[max];

        // Creando objetos e hilos
        System.out.println("Creando objetos e hilos");
        for (int i = 0; i < atomHidrogenos.length; i++) {
            atomHidrogenos[i] = new Hidrogeno(espacio);
            hidrogenos[i] = new Thread(atomHidrogenos[i], ("hidrógeno número " + i));
        }
        for (int j = 0; j < atomOxigenos.length; j++) {
            atomOxigenos[j] = new Oxigeno(espacio);
            oxigenos[j] = new Thread(atomOxigenos[j], ("oxígeno número " + j));
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
