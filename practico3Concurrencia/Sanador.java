package practico3Concurrencia;

public class Sanador implements Runnable {
    // Ejercicio 2.
    private Energia power;

    public Sanador(Energia energy) {
        this.power = energy;
    }

    public void run() {
        for (int i = 0; i < 6; i++) {
            System.out.println(Thread.currentThread().getName() +
                    " intentando restaurar energía.");
            this.power.sumarEnergia(3);
        }
    }

}
