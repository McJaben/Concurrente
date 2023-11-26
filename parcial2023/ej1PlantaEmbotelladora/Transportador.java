package parcial2023.ej1PlantaEmbotelladora;

public class Transportador implements Runnable {

    /*
     * Representa al hilo transportador. Transporta las cajas cuando el almacén se
     * llena y luego lo vacía.
     */

    private Planta planta; // recurso compartido que maneja la sincronización

    public Transportador(Planta unaPlanta) {
        this.planta = unaPlanta;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " inicia ejecución");
            planta.transportar();
        }
    }

}
