package practico3Concurrencia;

public class MainEj3 {
    /*
     * Ejercicio 3. En esta solución utilizamos 3 recursos compartidos: Plato, Rueda
     * y Hamaca. Solución 1°.
     */

    public static void main(String[] args) {
        Hamster[] arrayHamsters = new Hamster[5];
        String[] nombres = { "Fabio", "Rodolfo", "Tincho", "Gaspar", "Octavio" };
        Thread[] arrayHilos = new Thread[5];
        PlatoHamster plato = new PlatoHamster();
        RuedaHamster rueda = new RuedaHamster();
        HamacaHamster hamaca = new HamacaHamster();

        // Primera solución con 3 recursos compartidos
        for (int i = 0; i < arrayHamsters.length; i++) {
            // Inicializo los objetos Hámster
            arrayHamsters[i] = new Hamster(plato, rueda, hamaca);

            // Asigno a cada hámster un hilo y le doy un nombre
            arrayHilos[i] = new Thread(arrayHamsters[i], nombres[i]);
        }

        System.out.println("Iniciando los hilos...");

        for (int j = 0; j < arrayHilos.length; j++) {
            arrayHilos[j].start();
        }
    }

}
