package practico2Threads;

public class Customer {
    // Ejercicio 7
    private String nombre;
    private int[] carroCompra;
    // Constructor y métodos de acceso

    public Customer(String nombre, int[] carroCompra) {
        this.nombre = nombre;
        this.carroCompra = carroCompra;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getCarroCompra() {
        return carroCompra;
    }

}
