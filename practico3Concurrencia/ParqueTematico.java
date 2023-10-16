package practico3Concurrencia;

public class ParqueTematico {
    /*
     * Clase que representa al parque temático. Se encarga de gestionar las áreas y
     * las reservas. Ejercicio 4.
     */

    private Area[] areas; // arreglo de objetos Área.
    private int numAreas; // cantidad actual de áreas.

    public ParqueTematico(int capacidad) {
        this.areas = new Area[capacidad];
        this.numAreas = 0;
    }

    public void agregarArea(Area area) {
        // verifica si hay espacio en el array antes de agregarla
        if (numAreas < areas.length) {
            areas[numAreas] = area;
            numAreas++;
        } else {
            System.out.println("El parque está lleno, no se pueden agregar más áreas.");
        }
    }

    public synchronized boolean reservarEspacios(String nombreArea, int cantReservas) {
        /*
         * Se considera que un visitante puede solicitar una reserva por más de 1
         * espacio, dado que podría ir con más personas (flia, amigos, etc.).
         */
        boolean exito = false;

        for (int i = 0; i < numAreas; i++) { // recorre hasta el número de áreas actual
            if (areas[i].getNombre().equals(nombreArea)) { // si encuentra un área con nombre = nombreArea
                exito = areas[i].reservarEspacios(cantReservas);
            }
        }
        return exito;
    }
}
