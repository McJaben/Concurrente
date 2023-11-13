package parcial2022.ej1;

public class Usuario implements Runnable {
    private Parque parque;

    public Usuario(Parque park) {
        this.parque = park;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " intentando entrar al parque.");
            parque.entrar();
            parque.salirParque();
            System.out.println(Thread.currentThread().getName() + " salió del parque.");
        } catch (InterruptedException e) {
        }
    }
}
