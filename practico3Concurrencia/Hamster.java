package practico3Concurrencia;

public class Hamster implements Runnable {
    // Ejercicio 3. Representa a un hámster dentro de la jaula. Solución 1°
    private PlatoHamster plato;
    private RuedaHamster rueda;
    private HamacaHamster hamaca;

    public Hamster(PlatoHamster platito, RuedaHamster ruedita, HamacaHamster hamaquita) {
        this.plato = platito;
        this.rueda = ruedita;
        this.hamaca = hamaquita;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " comenzando ejecución...");
        System.out.println(Thread.currentThread().getName() + " intenta comer.");
        this.plato.comer();
        System.out.println(Thread.currentThread().getName() + " intenta correr.");
        this.rueda.correr();
        System.out.println(Thread.currentThread().getName() + " intenta descansar.");
        this.hamaca.descansar();
    }
}
