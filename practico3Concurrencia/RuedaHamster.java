package practico3Concurrencia;

public class RuedaHamster {
    
    public synchronized void correr() {
        System.out.println(Thread.currentThread().getName() + " está corriendo en la rueda.");
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " terminó de correr.");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
