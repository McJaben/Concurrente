package practico5;

import java.util.concurrent.Semaphore;

public class Comedor {
    // Ejercicio 3 del TP5 (y actividad obligatoria del 18/10/2023)
    // En el comedor sólo podran comer simultáneamente animales de la misma especie.
    private Semaphore platos; // maneja la cantidad que puede comer al mismo tiempo
    private Semaphore turnosPerros; // maneja cuándo es el turno de los perros
    private Semaphore turnosGatos; // maneja cuándo es el turno de los gatos
    private int perrosComiendo; // cantidad de perros comiendo en ese momento
    private int gatosComiendo; // cantidad de gatos comiendo en ese momento
    private int perrosEsperando; // cantidad de perros esperando para comer
    private int gatosEsperando; // cantidad de gatos esperando para comer
    // private objSinc; // ¿será que necesito un objeto sincronizado para las
    // variables de tipo entero que uso como contadores?
    private int maxTurno = 9;
    private int cantPerrosComieron;
    private int cantGatosComieron;
    private Semaphore modificacion;

    public Comedor(int maximo) {
        platos = new Semaphore(3); // Suponemos que pueden comer de a 3
        turnosPerros = new Semaphore(0);
        turnosGatos = new Semaphore(0);
        modificacion = new Semaphore(1);
        perrosComiendo = 0;
        gatosComiendo = 0;
        perrosEsperando = 0;
        gatosEsperando = 0;
        cantGatosComieron = 0;
        cantPerrosComieron = 0;
        this.maxTurno = maximo;
    }

    public void comerPerros() throws InterruptedException {
        /*
         * Todos los animales deben poder comer en algún momento de su día. Por tal
         * motivo sólo se permitirá que coman hasta 9 de la misma especie y luego
         * cambia el turno a la otra.
         */
        modificacion.acquire();
        if (perrosComiendo == 0 && gatosComiendo == 0 &&
                perrosEsperando == 0 && gatosEsperando == 0) {
            turnosPerros.release(maxTurno);
        }
        modificacion.release();
        // En este momento habrá al menos un perro esperando (puede ser el que llega
        // primero)
        modificacion.acquire();
        perrosEsperando++;
        modificacion.release();
        /*
         * Si la condición del primer if se cumple, el hilo podrá obtener un permiso del
         * semáforo. De lo contrario, el hilo se bloqueará en este punto hasta que lo
         * liberen (dado que los semáforos de turnos los inicializo en 0 por defecto).
         */
        turnosPerros.acquire();
        System.out.println(Thread.currentThread().getName() + " está esperando su turno");

        /*
         * Acá podrían adquirir los permisos hasta 3 perros consecutivamente
         * Si llega el 4to, debería bloquearse en ese momento hasta que se libere
         * un espacio.
         */
        platos.acquire();

        // Modifico los contadores encerrando con un semáforo binario
        modificacion.acquire();
        perrosComiendo++;
        perrosEsperando--;
        modificacion.release();

        // Simulo que el perro está comiendo
        System.out.println(Thread.currentThread().getName() + " está comiendo.");
        Thread.sleep(500);

        // El perro terminó de comer, modifico contadores encerrando con un semáforo
        // binario y libero 1 permiso del semáforo de platos
        modificacion.acquire();
        perrosComiendo--;
        cantPerrosComieron++;
        modificacion.release();
        platos.release();

        /*
         * El último del turno (múltiplo de 9) debe verificar si hay gatos esperando,
         * para cambiar el turno. Si no hay gatos esperando, libera exactamente la misma
         * cantidad de permisos que la de perros esperando.
         */
        if ((cantPerrosComieron % maxTurno) == 0) {
            if (gatosEsperando > 0) { // hay gatos esperando
                turnosGatos.release(maxTurno);
            } else if (perrosEsperando > 0) {
                turnosPerros.release(perrosEsperando);
            } else {
                // no hay ni perros ni gatos esperando, vuelvo el mundo al estado inicial
                this.resetInicial();
                System.out.println("Ya no hay perros ni gatos esperando por comer.");
            }
        }

        // Verifico si no hay gatos ni perros esperando
        if (perrosEsperando == 0) {
            if (gatosEsperando == 0) {

                // Primero verifico cuántos gatos comieron y, si es necesario, adquiero los
                // permisos que se hayan dado de más al semáforo turnosGatos.
                int permisosDeMas = cantPerrosComieron % maxTurno;
                /*
                 * if (permisosDeMas != 0) {
                 * turnosPerros.acquire(permisosDeMas);
                 * }
                 */
                System.out.println("Ya no hay perros ni gatos esperando por comer.");
                platos.release();
                System.out.println("Fin.");
            } else {
                turnosGatos.release(gatosEsperando);
            }
        }
    }

    public void comerGatos() throws InterruptedException {
        /*
         * Todos los animales deben poder comer en algún momento de su día. Por tal
         * motivo sólo se permitirá que coman hasta 9 de la misma especie y luego
         * cambia el turno a la otra.
         */
        modificacion.acquire();
        if (perrosComiendo == 0 && gatosComiendo == 0 &&
                perrosEsperando == 0 && gatosEsperando == 0) {
            turnosGatos.release(maxTurno);
        }
        modificacion.release();
        // En este momento habrá al menos un perro esperando (puede ser el que llega
        // primero)
        modificacion.acquire();
        gatosEsperando++;
        modificacion.release();
        /*
         * Si la condición del primer if se cumple, el hilo podrá obtener un permiso del
         * semáforo. De lo contrario, el hilo se bloqueará en este punto hasta que lo
         * liberen (dado que los semáforos de turnos los inicializo en 0 por defecto).
         */
        turnosGatos.acquire();
        System.out.println(Thread.currentThread().getName() + " está esperando su turno");

        /*
         * Acá podrían adquirir los permisos hasta 3 gatos consecutivamente
         * Si llega el 4to, debería bloquearse en ese momento hasta que se libere
         * un espacio.
         */
        platos.acquire();

        // Modifico los contadores encerrando con un semáforo binario
        modificacion.acquire();
        gatosComiendo++;
        gatosEsperando--;
        modificacion.release();

        // Simulo que el perro está comiendo
        System.out.println(Thread.currentThread().getName() + " está comiendo.");
        Thread.sleep(500);

        // El perro terminó de comer, modifico contadores encerrando con un semáforo
        // binario y libero 1 permiso del semáforo de platos
        modificacion.acquire();
        gatosComiendo--;
        cantGatosComieron++;
        modificacion.release();
        platos.release();

        /*
         * El último del turno (múltiplo de 9) debe verificar si hay perros esperando,
         * para cambiar el turno. Si no hay perros esperando, libera exactamente la
         * misma
         * cantidad de permisos que la de gatos esperando.
         */
        if ((cantGatosComieron % maxTurno) == 0) {
            if (perrosEsperando > 0) { // hay gatos esperando
                turnosPerros.release(maxTurno);
            } else if (gatosEsperando > 0) {
                turnosGatos.release(gatosEsperando);
            }
        }

        // Verifico si no hay gatos ni perros esperando
        if (gatosEsperando == 0) {
            if (perrosEsperando == 0) {
                // Primero verifico cuántos gatos comieron y, si es necesario, adquiero los
                // permisos que se hayan dado de más al semáforo turnosGatos.
                int permisosDeMas = cantGatosComieron % maxTurno;
                if (permisosDeMas != 0) {
                    turnosGatos.acquire(permisosDeMas);
                }
                System.out.println("Ya no hay perros ni gatos esperando por comer.");
                platos.release();
                System.out.println("Fin.");
            } else {
                turnosPerros.release(perrosEsperando);
            }
        }
    }

    // Voy a ver si necesito esto o no
    private void resetInicial() {
        platos = new Semaphore(3);
        turnosPerros = new Semaphore(0);
        turnosGatos = new Semaphore(0);
        modificacion = new Semaphore(1);
        perrosComiendo = 0;
        gatosComiendo = 0;
        perrosEsperando = 0;
        gatosEsperando = 0;
    }
}