package practico3Concurrencia;

public class Visitante implements Runnable {
    // Ejercicio 4. Es un hilo que representa a un visitante
    private String nombre;
    private String areaDeseada;
    private ParqueTematico parque;
    private int cantidadReservas;

    public Visitante(String name, String area, ParqueTematico park, int reservas) {
        this.nombre = name;
        this.areaDeseada = area;
        this.parque = park;
        this.cantidadReservas = reservas;
    }

    public void run() {
        boolean reservaExitosa = this.parque.reservarEspacios(this.areaDeseada, this.cantidadReservas);
        System.out.println(this.nombre + " está intentando realizar su reserva");
        if (reservaExitosa) {
            System.out.println("El visitante " + this.nombre + " realizó " + this.cantidadReservas + " reservas" +
                    " en el área: " + this.areaDeseada);
        } else {
            System.out.println("El visitante " + this.nombre + " no pudo realizar las reservas en el área: "
                    + this.areaDeseada);
        }

    }
}
