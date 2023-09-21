package practico3Concurrencia;

class HiloB extends Thread {
    private Impresor impresor;
    private int repeticiones;

    public HiloB(Impresor impresor, int repet) {
        this.impresor = impresor;
        this.repeticiones = repet;
    }

    public void run() {
        while (true) {
            impresor.imprimirB(repeticiones);
        }
    }
}
