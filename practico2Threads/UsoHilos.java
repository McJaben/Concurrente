package practico2Threads;
// Ejercicio 5
class UsoHilos {
    public static void main(String[] args) {
        System.out.println("Hilo principal iniciando.");
        // Primero, construye un objeto unHilo.
        UnHilo mh = new UnHilo("#1");
        UnHilo mh2 = new UnHilo("#2");
        UnHilo mh3 = new UnHilo("#3");
        // Luego, construye un hilo de ese objeto.
        Thread nuevoHilo = new Thread(mh);
        Thread nuevoHilo2 = new Thread(mh2);
        Thread nuevoHilo3 = new Thread(mh3);
        // Finalmente, comienza la ejecución del hilo.
        nuevoHilo.start();
        nuevoHilo2.start();
        nuevoHilo3.start();
        for (int i = 0; i < 400; i++) {
            System.out.print(" .");
        } try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido");
        }

        System.out.println("Hilo principal finalizado.");
    }
}
