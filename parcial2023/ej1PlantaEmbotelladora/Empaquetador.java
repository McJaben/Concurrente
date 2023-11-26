package parcial2023.ej1PlantaEmbotelladora;

public class Empaquetador implements Runnable {
    /*
     * Representa al hilo empaquetador. Empaqueta las cajas cuando se llenan y las
     * guarda en el almacen. Si éste se llena, envía una señal al transportador para
     * que transporte las cajas.
     */

    private Planta planta; // recurso compartido que maneja la sincronización
    
    public Empaquetador(Planta unaPlanta) {
        this.planta = unaPlanta;
    }

    @Override
    public void run() {
        while(true) { // luego lo hago una 'cantidad' de veces
            System.out.println(Thread.currentThread().getName() + " inicia su ejecución");
            planta.empaquetar();        }
    }
}