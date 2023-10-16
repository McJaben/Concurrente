package practico3Concurrencia;
// Ejercicio 4

public class Area {
    /*
     * Representa las áreas para actividades y entretenimiento del parque temático
     */

    private String nombre;
    private int espaciosDisponibles;

    public Area(String name, int cantDisponible) {
        this.nombre = name;
        this.espaciosDisponibles = cantDisponible;
    }

    public synchronized boolean reservarEspacios(int cantidad) {
        boolean exito = false;
        if (espaciosDisponibles >= cantidad) {
            espaciosDisponibles -= cantidad;
            exito = true;
        }
        return exito;
    }

    public String getNombre() {
        return this.nombre;
    }

}
