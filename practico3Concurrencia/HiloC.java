package practico3Concurrencia;

class HiloC extends Thread {
    // Es el ejercicio 7 del TP3
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
