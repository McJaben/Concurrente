package parcial2022.ej1;

public class Residente implements Runnable {
    private Parque parque;

    public Residente(Parque park) {
        this.parque = park;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " intentando entrar al parque.");
            parque.entrarResidente();
            parque.salirParque();
            System.out.println(Thread.currentThread().getName() + " salió del parque.");
        } catch (InterruptedException e) {
        }
    }
}
