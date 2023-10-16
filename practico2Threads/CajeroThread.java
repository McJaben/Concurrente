package practico2Threads;

public class CajeroThread extends Thread {
    // Ejercicio 7, inciso b
    private String nombre;
    private Customer cliente;
    private long initialTime;

    // Constructor, y métodos de acceso

    public CajeroThread(String name, Customer unCliente) {
        this.nombre = name;
        this.cliente = unCliente;
        this.initialTime = System.currentTimeMillis();
    }

    public String getNombre() {
        return nombre;
    }

    public void run() {
        System.out.println("El cajero " + this.nombre + " COMIENZA A PROCESAR " +
                "LA COMPRA DEL CLIENTE " + this.cliente.getNombre() + " EN EL TIEMPO: " +
                ((System.currentTimeMillis() - this.initialTime) / 1000) + " seg");

        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesando el producto: " + (i + 1) + " del cliente: " +
                    this.cliente.getNombre() + " ->Tiempo: " + ((System.currentTimeMillis() - this.initialTime) / 1000)
                    + " seg");
        }

        System.out.println("El cajero " + this.nombre + " HA TERMINADO DE PROCESAR " +
                this.cliente.getNombre() + " EN EL TIEMPO: " + ((System.currentTimeMillis() - this.initialTime) / 1000)
                + "seg");
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
