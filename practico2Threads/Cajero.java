package practico2Threads;

public class Cajero {
    // Ejercicio 7, inciso a
    // debe procesar la compra cliente a cliente, es decir que primero le cobra al
    // cliente 1, luego al cliente 2 y así sucesivamente.
    private String nombre;

    public Cajero(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void procesarCompra(Customer cliente, long timeStamp) {
        // Procesa la compra del cliente pasado por parámetro, a partir del tiempo
        // inicial timeStamp pasado por parámetro (en milisegundos).
        // La fórmula (System.currentTimeMillis() - timeStamp) / 1000 permite comparar
        // el tiempo actual del inicial para indicar cuánto tiempo ha transcurrido.
        System.out.println("El cajero " + this.nombre + " COMIENZA A" +
                "PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre() + " EN EL" +
                "TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 +
                "seg");
        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1) + "->Tiempo: "
                    + (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
        }
        System.out.println("El cajero " + this.nombre + " HA TERMINADO DE" +
                "PROCESAR " + cliente.getNombre() + " EN EL TIEMPO: " +
                (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
    }

    public void esperarXsegundos(int tiempo) {
        // Multiplica por 1000 el tiempo (en segundos) pasado por parámetro
        // para obtener el equivalente en milisegundos.
        try {
            Thread.sleep(tiempo * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
