package practico5;

public class Gato implements Runnable {
    // Ejercicio 3 del TP5
    private Comedor comedor;

    public Gato(Comedor unComedor) {
        this.comedor = unComedor;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " está intentando comer.");
        try {
            comedor.comerGatos();
            System.out.println(Thread.currentThread().getName() + " terminó de comer.");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
