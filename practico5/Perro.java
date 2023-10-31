package practico5;

public class Perro implements Runnable {
    // Ejercicio 3 del TP5
    private Comedor comedor;

    public Perro(Comedor unComedor) {
        this.comedor = unComedor;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " está intentando comer.");
        try {
            comedor.comerPerros();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
