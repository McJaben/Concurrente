package practico3Concurrencia;

import java.util.concurrent.Semaphore;

class Impresor {
    // Es el ejercicio 7 del TP3
    private Semaphore semaforoA = new Semaphore(1);
    private Semaphore semaforoB = new Semaphore(0);
    private Semaphore semaforoC = new Semaphore(0);

    public void imprimirA(int repeticiones) {
        try {
            semaforoA.acquire();
            for (int i = 0; i < repeticiones; i++) {
                System.out.print("A");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaforoB.release();
        }
    }

    public void imprimirB(int repeticiones) {
        try {
            semaforoB.acquire();
            for (int i = 0; i < repeticiones; i++) {
                System.out.print("B");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaforoC.release();
        }
    }

    public void imprimirC(int repeticiones) {
        try {
            semaforoC.acquire();
            for (int i = 0; i < repeticiones; i++) {
                System.out.print("C");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaforoA.release();
        }
    }

}
