package practico5;

/**
 *
 * @author benjamin.morales
 */

public class ControlComedor {
    // Ejercicio 3 del TP5
    public static void main(String[] args) {
        Comedor comedor = new Comedor(9);
        Perro[] arrPerros = new Perro[10];
        Gato[] arrGatos = new Gato[10];
        String[] nombresPerros = { "Perro 1", "Perro 2", "Perro 3", "Perro 4", "Perro 5", "Perro 6", "Perro 7",
                "Perro 8", "Perro 9" , "Perro 10"};
        String[] nombresGatos = { "Gato 1", "Gato 2", "Gato 3", "Gato 4", "Gato 5", "Gato 6", "Gato 7", "Gato 8",
                "Gato 9", "Gato 10"};
        Thread[] arrayHilosPerros = new Thread[10];
        Thread[] arrayHilosGatos = new Thread[10];
        System.out.println("Creando los objetos...");
        for (int i = 0; i < arrPerros.length; i++) {
            // Inicializo los objetos Perro
            arrPerros[i] = new Perro(comedor);

            // Asigno a cada perro un hilo y le doy un nombre
            arrayHilosPerros[i] = new Thread(arrPerros[i], nombresPerros[i]);
        }

        for (int j = 0; j < arrGatos.length; j++) {
            // Inicializo los objetos Gato
            arrGatos[j] = new Gato(comedor);

            // Asigno a cada gato un hilo y le doy un nombre
            arrayHilosGatos[j] = new Thread(arrGatos[j], nombresGatos[j]);
        }
        System.out.println("Inicializando objetos Perro");
        for (int k = 0; k < arrayHilosPerros.length; k++) {
            arrayHilosPerros[k].start();
        }
        System.out.println("Inicializando objetos Gato");
        for (int l = 0; l < arrayHilosGatos.length; l++) {
           arrayHilosGatos[l].start();
        }
    }
}
