package parcial2023.ej2Atomos;

import java.util.concurrent.Semaphore;

public class Espacio {
    // Atributos
    private int maxAgua; // límite de moléculas de agua que caben en el recipiente
    private int cantAgua; // cantidad de moléculas de agua actual
    private Semaphore olistos; // sem general, controla ejecución de átomos listos
    private Semaphore hlistos; // sem general, controla ejecución de átomos listos
    private Semaphore mutex; // sem binario para exclusión mutua
    private Semaphore formandoAgua; // sincroniza formación de agua y salida de los 3 átomos participantes
    private int cantOlistos; // cantidad de átomos de oxígeno listos actualmente
    private int cantHlistos; // cantidad de átomos de hidrógeno listos actualmente

    // Constructor
    public Espacio(int max) {
        this.maxAgua = max; // el límite se pasa por parámetro
        this.cantAgua = 0;
        this.olistos = new Semaphore(1); // inicializo en 1 para que el primero en llegar pueda pasar
        this.hlistos = new Semaphore(2); // inizializo en 2 para que los dos primeros en llegar pasen
        this.mutex = new Semaphore(1);
        this.formandoAgua = new Semaphore(0);
        this.cantOlistos = 0;
        this.cantHlistos = 0;
    }

    // Métodos hListo, oListo y hacerAgua - explicación lógica de sincronización
    /*
     * -----------------------------------------------------------------------------
     * La idea es que los primeros átomos necesarios para formar una molécula de
     * agua puedan pasar sin complicaciones. Para ello, van a adquirir los permisos
     * de los semáforos olistos y hlistos. Los que lleguen luego de eso, quedarán
     * dormidos en esos semáforos hasta ser liberados por aquel que ejecuta
     * hacerAgua(). Los contadores no se incrementarán hasta que puedan adquirir
     * dichos permisos.
     * 
     * De los primeros 3 átomos en pasar, sólo uno ejecutará el método hacerAgua().
     * Para ello, antes de verificar si están dadas las condiciones para ejecutar
     * dicho mensaje, adquirirán los permisos del mutex, así sólo aquel que
     * verifique esas condiciones pueda llamar a ese mensaje. Los demás deberán
     * quedar dormidos en el semáforo formandoAgua para ser liberados una vez se
     * forme la molécula de agua. De esa manera, los 3 átomos participantes
     * terminarán su ejecución en forma sincronizada y prolija.
     * 
     * En el mensaje hacerAgua() se deberá aumentar la cantidad de moléculas de agua
     * generadas. Al finalizar, se debe disminuir la cantidad de átomos listos y
     * liberar tanto a los átomos que están participando en la formación de esta
     * molécula como los que esperan su turno para poder luego ejecutar este
     * mensaje.
     * -----------------------------------------------------------------------------
     */

    // Método oListo()
    public void oListo() throws InterruptedException {
        // El primero pasará y adquirirá el permiso, luego verificará si se dan las
        // condiciones para ejecutar hacerAgua().
        System.out.println("Átomo de " + Thread.currentThread().getName() + " llegando");
        olistos.acquire();
        // Una vez pasa este semáforo, incrementa el contador de cantOlistos
        mutex.acquire();
        System.out.println("Átomo de " + Thread.currentThread().getName() + " listo");
        cantOlistos++;
        System.out.println("Cantidad átomos oxigeno listos: " + cantOlistos);
        mutex.release();
        // Ahora verifica si están dadas las condiciones para hacer agua
        mutex.acquire();
        if (cantOlistos == 1 && cantHlistos == 2) {
            System.out.println("Átomo de " + Thread.currentThread().getName() +
                    " se prepara para iniciar proceso de creación de molécula de agua");
            System.out.println();
            this.hacerAgua();
            formandoAgua.release(2); // libero a los otros dos átomos que formaron parte del proceso
            mutex.release(); // libera el mutex sólo cuando ya se formó la molécula de agua
        } else {
            System.out.println("Átomo de " + Thread.currentThread().getName() +
                    " esperando que se den las condiciones para formar la molécula de agua");
            mutex.release(); // libera el mutex para que puedan llegar los átomos que faltan
            formandoAgua.acquire(); // se libera al terminar método hacerAgua(). Logra una salida sincronizada.
        }
    }

    // Método hListo()
    public void hListo() throws InterruptedException {
        // El primero pasará y adquirirá el permiso, luego verificará si se dan las
        // condiciones para ejecutar hacerAgua().
        System.out.println("Átomo de " + Thread.currentThread().getName() + " llegando");
        hlistos.acquire();
        // Una vez pasa este semáforo, incrementa el contador de cantHlistos
        mutex.acquire();
        System.out.println("Átomo de " + Thread.currentThread().getName() + " listo");
        cantHlistos++;
        System.out.println("Cantidad átomos hidrógeno listos: " + cantHlistos);
        mutex.release();
        // Ahora verifica si están dadas las condiciones para hacer agua
        mutex.acquire();
        if (cantOlistos == 1 && cantHlistos == 2) {
            System.out.println("Átomo de " + Thread.currentThread().getName() +
                    " se prepara para iniciar proceso de creación de molécula de agua");
            System.out.println();
            this.hacerAgua();
            formandoAgua.release(2); // libero a los otros dos átomos que formaron parte del proceso
            mutex.release(); // libera el mutex sólo cuando ya se formó la molécula de agua
        } else {
            System.out.println("Átomo de " + Thread.currentThread().getName() +
                    " esperando que se den las condiciones para formar la molécula de agua");
            mutex.release(); // libera el mutex para que puedan llegar los átomos que faltan
            formandoAgua.acquire(); // se libera al terminar método hacerAgua(). Logra una salida sincronizada.
        }
    }

    /*
     * En el mensaje hacerAgua() se deberá aumentar la cantidad de moléculas de agua
     * generadas. Al finalizar, se debe disminuir la cantidad de átomos listos y
     * liberar tanto a los átomos que están participando en la formación de esta
     * molécula como los que esperan su turno para poder luego ejecutar este
     * mensaje.
     */
    public void hacerAgua() throws InterruptedException {
        // El semáforo mutex ya fue adquirido antes de ejecutar este método, por lo que
        // se liberará desde el mismo método que lo adquirió. Se considera que este
        // mensaje ya está protegido con exclusión mutua.
        System.out.println("El átomo de " + Thread.currentThread().getName() + " está iniciando" +
                " el proceso de formación de la molécula de agua");
        // Se forma la molécula de agua, por lo que incremento el contador
        cantAgua++;
        System.out.println("Molécula de agua formada, depositando en recipiente");
        cantOlistos--;
        cantHlistos -= 2;
        if (cantAgua >= maxAgua) {
            System.out.println("Recipiento de agua lleno.");
            System.out.println("Envasando agua para su distribución y vaciando recipiente.");
            cantAgua -= maxAgua;
        }
        System.out.println();
        // Finalizado el proceso, libero los permisos para formar la siguiente molécula
        hlistos.release(2);
        olistos.release();
    }
}
