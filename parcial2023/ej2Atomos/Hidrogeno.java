package parcial2023.ej2Atomos;

public class Hidrogeno implements Runnable {
    /*
     * Representa un átomo de hidrógeno que formará una molécula de agua.
     */
    private Espacio space;

    public Hidrogeno(Espacio espacio) {
        this.space = espacio;
    }

    public void run() {
        try {
            System.out.println("Átomo de " + Thread.currentThread().getName() +
                    " vagando por el espacio");
            space.hListo();
            System.out.println("Átomo de " + Thread.currentThread().getName() +
                    " terminó su ejecución.");
        } catch (InterruptedException ex) {
        }
    }
}
