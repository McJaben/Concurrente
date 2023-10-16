package practico3Concurrencia;

public class Criatura implements Runnable {
    // Ejercicio 2.
    private Energia power;

    public Criatura(Energia energy) {
        this.power = energy;
    }

    public void run() {
        for (int i = 0; i < 6; i++) {
            System.out.println(Thread.currentThread().getName() +
                    " intentando quitar energía.");
            this.power.quitarEnergia(3);
        }
    }
}
