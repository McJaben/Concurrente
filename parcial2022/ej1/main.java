package parcial2022.ej1;

/**
 *
 * @author benjamin.morales
 */

public class main {
    // Ejercicio 3 del TP5
    public static void main(String[] args) {
        Parque parque = new Parque(3);
        Usuario[] arrUsuarios = new Usuario[10];
        Residente[] arrResidentes = new Residente[4];
        String[] nombresUsuarios = { "Usuario 1", "Usuario 2", "Usuario 3", "Usuario 4", "Usuario 5", "Usuario 6", "Usuario 7",
                "Usuario 8", "Usuario 9" , "Usuario 10"};
        String[] nombresResidentes = { "Residente 1", "Residente 2", "Residente 3", "Residente 4", "Residente 5", "Residente 6", "Residente 7", "Residente 8",
                "Residente 9", "Residente 10"};
        Thread[] arrayHilosUsuarios = new Thread[10];
        Thread[] arrayHilosResidentes = new Thread[4];
        System.out.println("Creando los objetos...");
        for (int i = 0; i < arrUsuarios.length; i++) {
            // Inicializo los objetos Usuario
            arrUsuarios[i] = new Usuario(parque);

            // Asigno a cada Usuario un hilo y le doy un nombre
            arrayHilosUsuarios[i] = new Thread(arrUsuarios[i], nombresUsuarios[i]);
        }

        for (int j = 0; j < arrResidentes.length; j++) {
            // Inicializo los objetos Residente
            arrResidentes[j] = new Residente(parque);

            // Asigno a cada Residente un hilo y le doy un nombre
            arrayHilosResidentes[j] = new Thread(arrResidentes[j], nombresResidentes[j]);
        }
        System.out.println("Inicializando objetos Usuario");
        for (int k = 0; k < arrayHilosUsuarios.length; k++) {
            arrayHilosUsuarios[k].start();
        }
        System.out.println("Inicializando objetos Residente");
        for (int l = 0; l < arrayHilosResidentes.length; l++) {
           arrayHilosResidentes[l].start();
        }
    }
}
