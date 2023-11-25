package parcial2023.ej2Atomos;

public class Oxigeno implements Runnable {
    /*
     * Representa un átomo de oxígeno que formará una molécula de agua.
     */
    private Espacio space;

    public Oxigeno(Espacio espacio) {
        this.space = espacio;
    }

    public void run() {
        try {
            System.out.println("Átomo de " + Thread.currentThread().getName() +
                    " vagando por el espacio");
            space.oListo();
            System.out.println("Átomo de " + Thread.currentThread().getName() +
                    " terminó su ejecución.");
        } catch (InterruptedException ex) {
        }
    }
}
