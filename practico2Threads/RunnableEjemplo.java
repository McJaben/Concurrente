package practico2Threads;

public class RunnableEjemplo implements Runnable {

    private String nombre;

    //public RunnableEjemplo(String nom){
    //    this.nombre = nom;
    //}
    public void run() {
        for (int i = 0; i < 10; i++)
            System.out.println(i + " " + Thread.currentThread().getName());
        System.out.println("Termina thread " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        RunnableEjemplo r1 = new RunnableEjemplo();
        RunnableEjemplo r2 = new RunnableEjemplo();
        new Thread(r1, "Jose Maria").start();
        new Thread(r2, "Maria Jose").start();
        System.out.println("Termina thread main");
    }
}
