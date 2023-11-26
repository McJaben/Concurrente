package parcial2023.ej1PlantaEmbotelladora;

public class Embotellador implements Runnable {
    /*
     * Representa al hilo embotellador. Sólo puede hacer botellas de un tipo,
     * ya sea vino o agua saborizada.
     */

    private Planta planta; // recurso compartido que maneja la sincronización
    private boolean tipo; // true: vino, false: agua saborizada.

    public Embotellador(Planta unaPlanta, boolean type) {
        this.planta = unaPlanta;
        this.tipo = type;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println(Thread.currentThread().getName() + " inicia ejecución");
            planta.embotellar(tipo);  // true: vino, false: agua saborizada
        }
    }
}
