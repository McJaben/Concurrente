package practico3Concurrencia;

public class HamacaHamster {
    
    public synchronized void descansar() {
        System.out.println(Thread.currentThread().getName() + " está descansando en la hamaca.");
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " ya descansó.");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
