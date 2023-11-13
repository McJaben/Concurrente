package parcial2022.ej1;

import java.util.concurrent.Semaphore;

/*
 * @author benjamin.morales
 */

public class Parque {
    /*
     * El parque tiene un número máximo de usuarios por razones de seguridad.
     * Cuando se llena, se debe esperar en la entrada hasta que alguien salga.
     * Los residentes de la zona tienen preferencia sobre otros usuarios para
     * acceder.
     */

    private Semaphore turnoNormal; // Controla turnos usuarios normales
    private Semaphore turnoResidentes; // Controla turnos usuarios residentes con preferencia
    private Semaphore mutex; // para garantizar exclusión mutua al modificar contadores
    private Semaphore waiting; // mutex para el contador de esperando
    private final int maximo; // aforo máximo de usuarios
    private int ocupados; // usuarios dentro del parque
    private int residentesEsperando; // residentes de la zona esperando para entrar
    private int normalEsperando; // usuarios normales esperando para entrar
    private boolean sePuede; // Indica si es posible entrar o no al parque

    // Constructor
    public Parque(int max) {
        this.turnoNormal = new Semaphore(0);
        this.turnoResidentes = new Semaphore(0);
        this.mutex = new Semaphore(1);
        this.waiting = new Semaphore(1);
        this.maximo = max;
        this.ocupados = 0;
        this.residentesEsperando = 0;
        this.normalEsperando = 0;
        this.sePuede = true;
    }

    public void entrar() throws InterruptedException {
        // Primero llega a la entrada y espera para poder ingresar
        waiting.acquire();
        System.out.println("Lugares disponibles: " + (maximo - ocupados));
        normalEsperando++;
        System.out.println("Usuarios normales esperando: " + normalEsperando);
        waiting.release();

        mutex.acquire();
        if (sePuede && residentesEsperando == 0) {
            // libera un turno si el parque no está lleno y si no hay residentes esperando
            turnoNormal.release();
        }
        mutex.release();
        // Adquiere el turno. Si no tenía liberado el turno, se bloquea en este momento
        turnoNormal.acquire();
        // Ya pudo adquirir el turno, por lo que en este momento entró al parque y no
        // espera más
        mutex.acquire();
        System.out.println();
        System.out.println(Thread.currentThread().getName() + " entró al parque.");
        System.out.println();
        ocupados++;
        normalEsperando--;
        System.out.println("Lugares disponibles: " + (maximo - ocupados));
        System.out.println("Usuarios normales esperando: " + normalEsperando);
        Thread.sleep(500);
        if (ocupados == maximo) {
            sePuede = false;
        }
        mutex.release();
    }

    public void entrarResidente() throws InterruptedException {
        // Primero llega a la entrada y espera para poder ingresar
        waiting.acquire();
        System.out.println("Lugares disponibles: " + (maximo - ocupados));
        residentesEsperando++;
        System.out.println("Residentes esperando: " + residentesEsperando);
        waiting.release();
        // Verifica que haya lugar para entrar al parque
        mutex.acquire();
        if (sePuede) {
            turnoResidentes.release(); // se libera un turno si el parque no está lleno
        }
        mutex.release();
        // Adquiere el turno. Si no tenía liberado el turno, se bloquea en este momento
        turnoResidentes.acquire();

        // Adquirió el turno, por lo que entró al parque y deja de esperar
        mutex.acquire();
        System.out.println();
        System.out.println(Thread.currentThread().getName() + " entró al parque.");
        System.out.println();
        ocupados++;
        residentesEsperando--;
        System.out.println("Lugares disponibles: " + (maximo - ocupados));
        System.out.println("Residentes esperando: " + residentesEsperando);
        Thread.sleep(500);
        if (ocupados == maximo) {
            sePuede = false;
        }
        mutex.release();
    }

    public void salirParque() throws InterruptedException {
        mutex.acquire();
        System.out.println();
        System.out.println(Thread.currentThread().getName() + " está saliendo del parque");
        System.out.println();
        ocupados--;
        System.out.println("Lugares disponibles: " + (maximo - ocupados));
        if (residentesEsperando > 0) {
            System.out.println("Hay residentes de la zona esperando, libera un turno preferencial");
            turnoResidentes.release();
        } else if (normalEsperando > 0) {
            System.out.println("Ningún usuario en espera es residente de la zona, libera un turno normal.");
            turnoNormal.release();
        }
        sePuede = true;
        mutex.release();
    }

}
