package practico3Concurrencia;

class HiloA extends Thread {
    // Es el ejercicio 7 del TP3
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
