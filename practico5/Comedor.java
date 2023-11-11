package practico5;

import java.util.concurrent.Semaphore;

/**
 *
 * @author benjamin.morales
 */
public class Comedor {

    // Ejercicio 3 del TP5 (y actividad obligatoria del 18/10/2023)
    // En el comedor sólo podran comer simultáneamente animales de la misma especie.
    private Semaphore platos; // maneja la cantidad que puede comer al mismo tiempo
    private Semaphore turnosPerros; // maneja cuándo es el turno de los perros
    private Semaphore turnosGatos; // maneja cuándo es el turno de los gatos
    private Semaphore modificacion; // mutex para contadores
    private Semaphore waiting; // mutex para el contador de esperando
    private int perrosComiendo; // cantidad de perros comiendo en ese momento
    private int gatosComiendo; // cantidad de gatos comiendo en ese momento
    private int perrosEsperando; // cantidad de perros esperando para comer
    private int gatosEsperando; // cantidad de gatos esperando para comer
    private final int maxTurno;
    private int cantPerrosComieron; // cant perros que comieron en el turno
    private int cantGatosComieron; // cant gatos que comieron en el turno
    // Valores iniciales para los semáforos
    private int initialPlatos = 3;
    private int initialTurnosPerros = 0;
    private int initialTurnosGatos = 0;

    public Comedor(int maximo) {
        platos = new Semaphore(initialPlatos); // Suponemos que pueden comer de a 3
        turnosPerros = new Semaphore(initialTurnosPerros);
        turnosGatos = new Semaphore(initialTurnosGatos);
        modificacion = new Semaphore(1);
        waiting = new Semaphore(1);
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
         * motivo sólo se permitirá que coman hasta maxTurno de la misma especie y luego
         * cambia el turno a la otra.
         */
        modificacion.acquire();
        if (perrosComiendo == 0 && gatosComiendo == 0
                && perrosEsperando == 0 && gatosEsperando == 0) {
            turnosPerros.release(maxTurno);
        }
        modificacion.release();

        // En este momento habrá al menos un perro esperando (puede ser el que llega
        // primero)
        // modificacion.acquire();
        waiting.acquire();
        perrosEsperando++;
        System.out.println(Thread.currentThread().getName() + " está esperando su turno");
        // modificacion.release();
        waiting.release();
        /*
         * Si la condición del primer if se cumple, el hilo podrá obtener un permiso del
         * semáforo. De lo contrario, el hilo se bloqueará en este punto hasta que lo
         * liberen (dado que los semáforos de turnos los inicializo en 0 por defecto).
         */
        turnosPerros.acquire();

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
        // Simulo que el perro está comiendo
        System.out.println(Thread.currentThread().getName() + " está comiendo.");
        Thread.sleep(1000);
        modificacion.release();

        // El perro terminó de comer, modifico contadores encerrando con un semáforo
        // binario y libero 1 permiso del semáforo de platos
        modificacion.acquire();
        perrosComiendo--;
        cantPerrosComieron++;
        modificacion.release();
        platos.release();

        // Debo intentar unir estos dos ifs. Debería manejar todo junto
        /*
         * El último del turno debe verificar si hay gatos esperando para cambiar
         * el turno. Si no hay gatos esperando: si perrosEsperando < maxTurno,
         * libera exactamente la misma cantidad de permisos que la de perros esperando,
         * sino libera maxTurno.
         * Si no hay nadie esperando y soy el último, reinicio el mundo
         */
        // Objetivo: tener alguna manera de determinar si soy o no el último
        modificacion.acquire();
        Thread.sleep(500); // Como para darle un tiempo a que llegue algún otro animal
        if (cantPerrosComieron >= maxTurno) {
            if (gatosEsperando > 0) { // hay gatos esperando
                turnosGatos.release(maxTurno);
            } else if (perrosEsperando > 0) {
                if (perrosEsperando < maxTurno) {
                    // hack: si perrosEsperando < maxTurno, libero perrosEsperando, sino dar hasta
                    // maxTurno
                    turnosPerros.release(perrosEsperando);
                } else {
                    turnosPerros.release(maxTurno);
                }
            } else { // mismo caso de this.soyElUltimo() pero lo ejecuta el último del turno
                // no hay ni perros ni gatos esperando, vuelvo el mundo al estado inicial
                // Primero verifico cuántos perros comieron y, si es necesario, adquiero los
                // permisos que se hayan dado de más al semáforo turnosPerros.
                int permisosDeMas = maxTurno - cantPerrosComieron;

                if (permisosDeMas != 0) {
                    turnosPerros.acquire(permisosDeMas);
                }
                this.resetInicial();
                System.out.println("Ya no hay perros ni gatos esperando por comer.");
                System.out.println("Fin.");
            }
            cantPerrosComieron = 0; // Terminó la primer tanda
        } else {
            if (this.soyElUltimo()) { // Si soy el último de ambas especies
                this.resetInicial();
                System.out.println("Ya no hay perros ni gatos esperando por comer.");
                System.out.println("Fin.");
            } else if (perrosEsperando == 0) {
                // si soy el último de mi especie pero hay animales de otra especie esperando su
                // turno
                int permisosDeMas = maxTurno - cantPerrosComieron;

                if (permisosDeMas != 0) {
                    turnosPerros.acquire(permisosDeMas);
                }
                if (gatosEsperando > 0) {
                    if (gatosEsperando < maxTurno) {
                        // hack: si gatosEsperando < maxTurno, libero gatosEsperando, sino dar hasta
                        // maxTurno
                        turnosGatos.release(gatosEsperando);
                    } else {
                        turnosGatos.release(maxTurno);
                    }
                }
            }
        }
        modificacion.release();

    }

    public void comerGatos() throws InterruptedException {
        /*
         * Todos los animales deben poder comer en algún momento de su día. Por tal
         * motivo sólo se permitirá que coman hasta 9 de la misma especie y luego
         * cambia el turno a la otra.
         */
        modificacion.acquire();
        if (perrosComiendo == 0 && gatosComiendo == 0
                && perrosEsperando == 0 && gatosEsperando == 0) {
            turnosGatos.release(maxTurno);
        }
        modificacion.release();

        // En este momento habrá al menos un gato esperando (puede ser el que llega
        // primero)
        waiting.acquire();
        // modificacion.acquire();
        gatosEsperando++;
        System.out.println(Thread.currentThread().getName() + " está esperando su turno");
        // modificacion.release();
        waiting.release();

        /*
         * Si la condición del primer if se cumple, el hilo podrá obtener un permiso del
         * semáforo. De lo contrario, el hilo se bloqueará en este punto hasta que lo
         * liberen (dado que los semáforos de turnos los inicializo en 0 por defecto).
         */
        turnosGatos.acquire();

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
        // Simulo que el perro está comiendo
        System.out.println(Thread.currentThread().getName() + " está comiendo.");
        Thread.sleep(1000);
        modificacion.release();

        // El perro terminó de comer, modifico contadores encerrando con un semáforo
        // binario y libero 1 permiso del semáforo de platos
        modificacion.acquire();
        gatosComiendo--;
        cantGatosComieron++;
        modificacion.release();
        platos.release();

        // Debo intentar unir estos dos ifs. Debería manejar todo junto
        /*
         * El último del turno debe verificar si hay perros esperando para cambiar
         * el turno. Si no hay perros esperando: si gatosEsperando < maxTurno,
         * libera exactamente la misma cantidad de permisos que la de gatos esperando,
         * sino libera maxTurno.
         * Si no hay nadie esperando y soy el último, reinicio el mundo
         */
        // Objetivo: tener alguna manera de determinar si soy o no el último
        modificacion.acquire();
        Thread.sleep(500); // Como para darle un tiempo a que llegue algún otro animal
        if (cantGatosComieron >= maxTurno) {
            if (perrosEsperando > 0) { // hay gatos esperando
                turnosPerros.release(maxTurno);
            } else if (gatosEsperando > 0) {
                if (gatosEsperando < maxTurno) {
                    // hack: si gatosEsperando < maxTurno, libero gatosEsperando, sino dar hasta
                    // maxTurno
                    turnosGatos.release(gatosEsperando);
                } else {
                    turnosGatos.release(maxTurno);
                }
            } else { // mismo caso de this.soyElUltimo() pero lo ejecuta el último del turno
                // no hay ni perros ni gatos esperando, vuelvo el mundo al estado inicial
                // Primero verifico cuántos gatos comieron y, si es necesario, adquiero los
                // permisos que se hayan dado de más al semáforo turnosGatos.
                int permisosDeMas = maxTurno - cantGatosComieron;

                if (permisosDeMas != 0) {
                    turnosGatos.acquire(permisosDeMas);
                }
                this.resetInicial();
                System.out.println("Ya no hay perros ni gatos esperando por comer.");
                System.out.println("Fin.");
            }
            cantGatosComieron = 0; // Terminó la primer tanda
        } else {
            if (this.soyElUltimo()) { // Si soy el último de ambas especies
                this.resetInicial();
                System.out.println("Ya no hay perros ni gatos esperando por comer.");
                System.out.println("Fin.");
            } else if (gatosEsperando == 0) {
                // si soy el último de mi especie pero hay animales de otra especie esperando su
                // turno
                int permisosDeMas = maxTurno - cantGatosComieron;

                if (permisosDeMas != 0) {
                    turnosGatos.acquire(permisosDeMas);
                }
                if (perrosEsperando > 0) {
                    if (perrosEsperando < maxTurno) {
                        // hack: si perrosEsperando < maxTurno, libero perrosEsperando, sino dar hasta
                        // maxTurno
                        turnosPerros.release(perrosEsperando);
                    } else {
                        turnosPerros.release(maxTurno);
                    }
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " terminó de comer.");
        modificacion.release();
    }

    // Voy a ver si necesito esto o no
    private void resetInicial() {
        // Dreno los permisos de los semáforos de turnos y platos
        platos = new Semaphore(3);
        turnosPerros = new Semaphore(0);
        turnosGatos = new Semaphore(0);

        // Reinicio los contadores
        perrosComiendo = 0;
        gatosComiendo = 0;
        perrosEsperando = 0;
        gatosEsperando = 0;
        cantPerrosComieron = 0;
        cantGatosComieron = 0;

        // Libero los permisos iniciales de los semáforos
        platos.release(initialPlatos);
        turnosPerros.release(initialTurnosPerros);
        turnosGatos.release(initialTurnosGatos);
    }

    private boolean soyElUltimo() {
        return (perrosEsperando == 0 && gatosEsperando == 0
                && perrosComiendo == 0 && gatosComiendo == 0);
    }
}
