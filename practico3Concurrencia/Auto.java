package practico3Concurrencia;

import practico2Threads.Vehiculo;

public class Auto extends Vehiculo implements Runnable {
    // Ejercicio 5.
    private float nivelCombustible; // en litros
    private Surtidor surtidor; // el recurso compartido

    public Auto(String patente, String modelo, String marca, float km, float combustible, Surtidor surti) {
        super(patente, modelo, marca, km);
        this.nivelCombustible = combustible;
        this.surtidor = surti;
    }

    public void run() {

    }

}
