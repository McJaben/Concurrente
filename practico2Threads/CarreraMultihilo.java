package practico2Threads;

public class CarreraMultihilo { 
    public static void main(String[] args) {
        Corredor runner1 = new Corredor("Juan", 50);
        Corredor runner2 = new Corredor("Benja", 50);
        Corredor runner3 = new Corredor("Paula", 50);
        Corredor runner4 = new Corredor("Rocio", 50);
        Corredor[] arreglo = {runner1, runner2, runner3, runner4};
        
        Thread run1 = new Thread(runner1);
        Thread run2 = new Thread(runner2);
        Thread run3 = new Thread(runner3);
        Thread run4 = new Thread(runner4);

        run1.start();
        run2.start();
        run3.start();
        run4.start();
    }
}
