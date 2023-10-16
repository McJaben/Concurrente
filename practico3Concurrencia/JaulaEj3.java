package practico3Concurrencia;

public class JaulaEj3 {
    /*
     * Pruebo otra manera de implementar la solución del ejercicio 3, utilizando
     * objetos sincronizadores
     */

    private Object objPlato;
    private Object objRueda;
    private Object objHamaca;

    public JaulaEj3() {
        this.objPlato = new Object();
        this.objRueda = new Object();
        this.objHamaca = new Object();

    }

    public void comer() {
        synchronized (objPlato) {
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

    public void correr() {
        synchronized (objRueda) {
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

    public void descansar() {
        synchronized (objHamaca) {
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
}
