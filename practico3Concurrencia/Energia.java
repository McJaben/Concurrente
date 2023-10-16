package practico3Concurrencia;

public class Energia {
    // Ejercicio 2.
    private int energy;
    private final int MAX = 30;

    public Energia() {
        this.energy = 20;
    }

    public void sumarEnergia(int recuperacion) {
        if ((this.energy + recuperacion) < MAX) { 
            System.out.println(Thread.currentThread().getName() + " está" +
                    " restaurando la energía en " + recuperacion + " puntos.");
            this.energy += recuperacion;
        } else {
            this.energy = MAX;
        }
        System.out.println("La energía actual es: " + this.energy);

    }

    public void quitarEnergia(int daño) {
        if ((this.energy - daño) > 0) {
            System.out.println(Thread.currentThread().getName() + " está" +
                    " drenando la energía en " + daño + " puntos.");
            this.energy -= daño;
        } else {
            this.energy = 0;
        }
        System.out.println("La energía actual es: " + this.energy);
    }

    public int getEnergia() {
        return this.energy;
    }
}
