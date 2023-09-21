package practico3Concurrencia;

class HiloC extends Thread {
    private Impresor impresor;
    private int repeticiones;

    public HiloC(Impresor impresor, int repet) {
        this.impresor = impresor;
        this.repeticiones = repet;
    }

    public void run() {
        while (true) {
            impresor.imprimirC(repeticiones);
        }
    }
}
