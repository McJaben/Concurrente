package parcial2023.ej1PlantaEmbotelladora;

import java.util.concurrent.locks.*;

public class Almacen {
    /*
     * Representa el almacén donde se guardan las cajas de vino y de agua.
     * El almacén tiene un límite. Cuando se llena, el transportador se lleva
     * las cajas y el almacén se vacía.
     */

    private final int limite;
    private int cajas;
    private Lock cerrojo;
    // private Condition transportador;
    // private Condition empaquetador;

    public Almacen(int max) {
        this.limite = max;
        this.cajas = 0;
        this.cerrojo = new ReentrantLock();
        // this.transportador = cerrojo.newCondition();
        // this.empaquetador = cerrojo.newCondition();
    }

    public void guardarCaja() {
        try {
            cerrojo.lock();
            if (cajas < limite) { // si hay lugar para al menos 1 botella
                System.out.println(Thread.currentThread().getName() + " guardando una caja");
                cajas++;
                Thread.sleep(500);
                System.out.println("La cantidad de cajas actual dentro del almacén es de: " + cajas);
                if (estaLleno()) { // si la caja se llenó con esta última botella guardada
                    System.out.println();
                    System.out.println("-----> El ALMACEN se llenó <-----");
                    System.out.println();
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            cerrojo.unlock();
        }
    }

    public void transportar() {
        try {
            cerrojo.lock();
            System.out.println();
            System.out.println(Thread.currentThread().getName() + " está transportando las cajas");
            Thread.sleep(500);
            cajas -= limite;
            System.out.println("----------> Almacén vaciado <----------");
            System.out.println();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            cerrojo.unlock();
        }

    }

    public boolean estaLleno() {
        return limite == cajas;
    }
}
