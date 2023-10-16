package practico3Concurrencia;

public class PlatoHamster {
    
    public synchronized void comer() {
        System.out.println(Thread.currentThread().getName() + " está comiendo del plato.");
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " terminó de comer.");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
