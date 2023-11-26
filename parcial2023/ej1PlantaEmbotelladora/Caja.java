package parcial2023.ej1PlantaEmbotelladora;

import java.util.concurrent.locks.*;

public class Caja {
    private String tipo; // para imprimir por pantalla si es de vino o de agua
    private final int limite; // cantidad máxima de botellas por caja
    private int botellas; // cantidad actual de botellas
    private Lock cerrojo; // lock de mutex
    // private Condition condCajaLlena; // condición para sincronizar acceso y modif
    // private boolean cajaLlena;
    // private int embotelladoresEsperando;

    public Caja(int max, String elTipo) {
        this.limite = max;
        this.botellas = 0;
        this.cerrojo = new ReentrantLock();
        // this.condCajaLlena = cerrojo.newCondition();
        this.tipo = elTipo;
        // this.cajaLlena = false;
        // this.embotelladoresEsperando = 0;
    }

    public void colocarBotella() {
        try {
            cerrojo.lock();
            if (botellas < limite) { // si hay lugar para al menos 1 botella
                System.out.println(Thread.currentThread().getName() + " guardando botella en la caja de " + tipo);
                botellas++;
                Thread.sleep(500);
                System.out.println("La cantidad de botellas actual es de: " + botellas + " de " + tipo);
                if (estaLlena()) { // si la caja se llenó con esta última botella guardada
                    System.out.println("\n-------> La caja de " + tipo + " se llenó <-------\n");
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            cerrojo.unlock();
        }
    }

    public void empaquetar() {
        // lógica de empaquetar
        try {
            cerrojo.lock();
            System.out.println("\n" + Thread.currentThread().getName() + " está empaquetando la caja de " + tipo);
            Thread.sleep(500);
            botellas -= limite; // en el parcial el valor era 10 litros, i.e., 10 botellas
            // condCajaLlena.signalAll(); // despierto a los embotelladores que están
            // esperando la caja
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            cerrojo.unlock();
        }
    }

    public boolean estaLlena() {
        return botellas == limite;
    }

    public boolean soyUltimo() {
        return (botellas + 1) == limite;
    }

    // Backup xd
    /*
     * public void colocarBotella() {
     * try {
     * cerrojo.lock();
     * if (botellas < limite) { // si hay lugar para al menos 1 botella
     * System.out.println(Thread.currentThread().getName() +
     * " guardando botella en la caja de " + tipo);
     * botellas++;
     * System.out.println(" La cantidad de botellas actual es de: " + botellas +
     * " de " + tipo);
     * if (botellas == limite) { // si la caja se llenó con esta última botella
     * guardada
     * System.out.println("La caja de " + tipo + " se llenó.");
     * }
     * }
     * /*
     * else {
     * // Esperar mientras la caja esté llena y se espera al Empaquetador para
     * vaciarla
     * while (estaLlena()) {
     * System.out.println("La caja de " + tipo + " está llena.");
     * System.out.println(Thread.currentThread().getName() +
     * " esperando una nueva caja de " + tipo +
     * " para guardar la botella");
     * // embotelladoresEsperando++;
     * condCajaLlena.await(); // esperan acá hasta que el empaquetador vacíe la caja
     * y los despierte
     * // embotelladoresEsperando--;
     * if (botellas < limite) {
     * // si los embotelladores esperando superaban el límite de botellas que se
     * pueden guardar
     * // en la caja, entonces no salen del bucle ni aumentan la cantidad de
     * botellas
     * System.out.println("Ya llegó la nueva caja, guardando botella de " + tipo);
     * botellas++;
     * System.out.println(" La cantidad de botellas actual es de: " + botellas +
     * " de " + tipo);
     * }
     * }
     * }
     * 
     * } catch (InterruptedException ex) {
     * ex.printStackTrace();
     * } finally {
     * cerrojo.unlock();
     * }
     * }
     */
}
