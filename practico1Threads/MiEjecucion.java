package practico1Threads;

public class MiEjecucion extends Thread {
    public void run() {
        ir();
    }

    public void ir() {
        hacerMas();
    }

    public void hacerMas() {
        System.out.println("En la pila");
        System.out.println("En la pila");
        System.out.println("En la pila");
        System.out.println("En la pila");
        System.out.println("En la pila");
        System.out.println("En la pila");
        System.out.println("En la pila");
        System.out.println("En la pila");
        System.out.println("En la pila");

    }
}
