package practico3Concurrencia;

public class Surtidor {
    // Ejercicio 5.

    private final float MAXIMO;
    private final float combustibleDisponible;

    public Surtidor(float capacidadMaxima) {
        this.MAXIMO = capacidadMaxima;
        this.combustibleDisponible = capacidadMaxima;
    }
}
