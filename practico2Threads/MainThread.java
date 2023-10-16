package practico2Threads;

public class MainThread {
    // Ejercicio 7, inciso b
    /*
     * ¿Y si en vez de procesar primero un cliente y después otro, procesamos los
     * dos a la vez?, ¿Cuánto tardaría el programa en ejecutarse?. Si en vez de
     * haber solo una Cajera (es decir un solo hilo), hubiese dos Cajeras (es decir
     * dos hilos o threads) podríamos procesar los dos clientes a la vez y tardar
     * menos tiempo en ejecutar el programa. Complete el siguiente código a fin de
     * representar el escenario descrito.
     */

    public static void main(String[] args) {
        Customer cliente1 = new Customer("Cliente 1", new int[] { 2, 2, 1, 5, 2, 3 });
        Customer cliente2 = new Customer("Cliente 2", new int[] { 1, 3, 5, 1, 1 });
        CajeroThread cajero1 = new CajeroThread("Cajero 1", cliente1);
        CajeroThread cajero2 = new CajeroThread("Cajero2", cliente2);
        // Tiempo inicial de referencia
        /* long initialTime = System.currentTimeMillis();
        cajero1.procesarCompra(cliente1, initialTime);
        cajero2.procesarCompra(cliente2, initialTime); */
        cajero1.start();
        cajero2.start();
    }


}
