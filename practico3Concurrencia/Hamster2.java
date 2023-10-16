package practico3Concurrencia;

public class Hamster2 implements Runnable {
    // Ejercicio 3. Representa a un hámster dentro de la jaula. Solución 2°
    private JaulaEj3 jaula;

    public Hamster2(JaulaEj3 jaulita) {
        this.jaula = jaulita;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " comenzando ejecución...");
        System.out.println(Thread.currentThread().getName() + " intenta comer.");
        jaula.comer();
        System.out.println(Thread.currentThread().getName() + " intenta correr.");
        jaula.correr();
        System.out.println(Thread.currentThread().getName() + " intenta descansar.");
        jaula.descansar();
    }
}
