package practico1Threads;

class TesteoHilos {
    public static void main(String[] args) {
        Thread miHilo = new MiEjecucion();
        int i;
        miHilo.start();
        try {
            Thread.sleep(0,5);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("En el main");
    }
}
