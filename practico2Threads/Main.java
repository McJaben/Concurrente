package practico2Threads;

public class Main {
    // Ejercicio 7, inciso a
    public static void main(String[] args) {
        Customer cliente1 = new Customer("Cliente 1", new int[] { 2, 2, 1, 5,
                2, 3 });
        Customer cliente2 = new Customer("Cliente 2", new int[] { 1, 3, 5, 1,
                1 });
        Cajero cajero1 = new Cajero("Cajero 1");
        // Tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();
        cajero1.procesarCompra(cliente1, initialTime);
        cajero1.procesarCompra(cliente2, initialTime);
    }
}
