package practico3Concurrencia;

public class MainEj3_2 {
    /*
     * Ejercicio 3. En esta solución utilizamos 1 recurso compartido Jaula, que va a
     * implementar las 3 actividades: comer del plato, correr en la rueda y
     * descansar en la hamaca. Para ello, utiliza 3 objetos sincronizados.
     * Solución 2°.
     */

    public static void main(String[] args) {
        Hamster2[] arrayHamsters = new Hamster2[5];
        String[] nombres = { "Fabio", "Rodolfo", "Tincho", "Gaspar", "Octavio" };
        Thread[] arrayHilos = new Thread[5];

        // Otra opción es con el recurso compartido Jaula, que utiliza obj sincronizados
        JaulaEj3 jaula = new JaulaEj3();

        // Segunda solución con un recurso compartido y 3 objetos sincronizados

        System.out.println("Iniciando los hilos...");
        for (int i = 0; i < arrayHamsters.length; i++) {
            // Inicializo los objetos Hámster
            arrayHamsters[i] = new Hamster2(jaula);

            // Asigno a cada hámster un hilo y le doy un nombre
            arrayHilos[i] = new Thread(arrayHamsters[i], nombres[i]);
        }
        for (int j = 0; j < arrayHilos.length; j++) {
            arrayHilos[j].start();
        }
    }

}
