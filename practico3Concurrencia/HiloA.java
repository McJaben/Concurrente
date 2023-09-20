package practico3Concurrencia;

class HiloA extends Thread {
    private Impresor impresor;
    private int repeticiones;

    public HiloA(Impresor impresor, int repet) {
        this.impresor = impresor;
        this.repeticiones = repet;
    }

    public void run() {
        while (true) {
            impresor.imprimirA(repeticiones);
        }
    }
}
