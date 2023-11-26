package parcial2023.ej1PlantaEmbotelladora;

import java.util.concurrent.locks.*;

public class Planta {
    /*
     * Es el recurso compartido que contiene a las cajas y al almacén de estas.
     * Lo comparten los 3 tipos de hilos. Controla la sincronización entre estos.
     */

    /*
     * Actualización: Por lo que estuve probando, conviene bloquear a los hilos en
     * este recurso compartido, porque sino no hay forma de despertarlos bien.
     * Los demás recursos compartidos dentro de la Planta (cajas y almacen) tendrán
     * sus propios locks que sirven como mutex, pero sólo habrá conjuntos de espera
     * en la Planta.
     */

    // Atributos
    private Caja vino; // recurso compartido caja de vino
    private Caja agua; // recurso compartido caja de agua
    private Almacen almacen; // recurso compartido almacen (donde van las cajas)
    private Lock produccion; // Lock para proceso de embotellamiento y empaquedor
    private Condition esperarCajas; // Condición para bloquear al empaquetador si no hay cajas llenas aún
    private Condition esperarVino; // Bloquea al embotellador de vino si la caja está llena
    private Condition esperarAgua; // Bloquea al embotellador de agua si la caja está llena
    private Lock distribucion; // Lock para proceso de almacenamiento y distribución
    private Condition almacenLleno; // Condición para bloquear al empaquetador cuando se llena el almacén
    private Condition transportador; // Condición para bloquear al transportador si aún no se llenó el almacén

    // Constructor
    public Planta(Caja deVino, Caja deAgua, Almacen alm) {
        this.vino = deVino;
        this.agua = deAgua;
        this.almacen = alm;
        this.produccion = new ReentrantLock();
        this.esperarCajas = produccion.newCondition();
        this.esperarAgua = produccion.newCondition();
        this.esperarVino = produccion.newCondition();
        this.distribucion = new ReentrantLock();
        this.almacenLleno = distribucion.newCondition();
        this.transportador = distribucion.newCondition();
    }

    public void embotellar(boolean tipo) {
        try {
            if (tipo) { // si es true: vino
                produccion.lock();
                while (vino.estaLlena()) {
                    esperarVino.await();
                }
                vino.colocarBotella();
                if (vino.estaLlena()) {
                    esperarCajas.signal(); // despierta al empaquetador
                }
            } else { // false: agua saborizada
                produccion.lock();
                while (agua.estaLlena()) {
                    esperarAgua.await();
                }
                agua.colocarBotella();
                if (agua.estaLlena()) {
                    esperarCajas.signal(); // despierta al empaquetador
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            produccion.unlock();
        }
    }

    public void empaquetar() {
        System.out.println(Thread.currentThread().getName() + " ejecuta empaquetar()");
        try {
            produccion.lock();
            while (!vino.estaLlena() && !agua.estaLlena()) {
                System.out.println(Thread.currentThread().getName() + " debe esperar a que se llene una caja");
                esperarCajas.await();
            }
            System.out
                    .println("\n" + Thread.currentThread().getName() + " ha despertado y empaquetará una caja" + "\n");
            // produccion.unlock(); // libero el lock
            // Acá debo verificar que el almacén no esté lleno antes de empaquetar una nueva
            // caja
            distribucion.lock(); // adquiere lock de distribución para verif si está lleno el almacén
            if (vino.estaLlena()) {
                while (almacen.estaLleno()) { // Si el almacén está lleno, espera que lo vacíe el transportador
                    System.out.println();
                    System.out.println("-->Almacén lleno." + "El " + Thread.currentThread().getName() + " deberá" +
                            " esperar a que el transportador lo vacíe");
                    System.out.println("Despertando al transportador...");
                    transportador.signal(); // despierta al transportador
                    almacenLleno.await(); // se bloquea
                }
                System.out.println();
                vino.empaquetar(); // empaqueta caja de vino
                System.out.println("\nLa caja de VINO ya se empaquetó y se está llevando al almacén\n");
                almacen.guardarCaja(); // guarda la caja en el almacen
                esperarVino.signalAll(); // despierta a los embotelladores de vino
            }
            if (agua.estaLlena()) {
                while (almacen.estaLleno()) { // Si el almacén está lleno, espera que lo vacíe el transportador
                    System.out.println();
                    System.out.println("-->Almacén lleno." + "El " + Thread.currentThread().getName() + " deberá" +
                            " esperar a que el transportador lo vacíe");
                    System.out.println("Despertando al transportador...");
                    transportador.signal(); // despierta al transportador
                    almacenLleno.await(); // se bloquea
                }
                System.out.println();
                agua.empaquetar(); // empaqueta caja de agua
                System.out.println("\nLa caja de AGUA ya se empaquetó y se está llevando al almacén\n");
                almacen.guardarCaja(); // guarda la caja en el almacen
                esperarAgua.signalAll(); // despierta a los embotelladores de agua
            }
        } catch (InterruptedException ex) {
        } finally {
            produccion.unlock();
            distribucion.unlock();
        }
    }

    public void transportar() {
        try {
            distribucion.lock();
            System.out.println(Thread.currentThread().getName() + " ejecuta transportar()");
            while (!almacen.estaLleno()) {
                System.out
                        .println("El ALMACEN aún no se llenó " + Thread.currentThread().getName() + " deberá esperar");
                transportador.await();
            }
            System.out.println("\n" + Thread.currentThread().getName()
                    + " listo para transportar las cajas empaquetadas y vaciar el almacén");
            almacen.transportar();
            System.out.println(
                    Thread.currentThread().getName() + " transportó las cajas. Despertando al empaquetador...\n");
            almacenLleno.signal();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            distribucion.unlock();
        }

    }
}
