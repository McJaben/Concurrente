package parcial2023.ej1PlantaEmbotelladora;

public class Main {
    public static void main(String[] args) {
        // Creo e inicializo los objetos
        Caja cajaVino = new Caja(10, "vino");
        Caja cajaAgua = new Caja(10, "agua");
        Almacen almacen = new Almacen(10);
        Planta planta = new Planta(cajaVino, cajaAgua, almacen);
        Embotellador[] embotes = new Embotellador[10];
        Thread[] hiloEmbotelladores = new Thread[10];
        Empaquetador empaquetador = new Empaquetador(planta);
        Thread hiloEmpaqueta = new Thread(empaquetador, "Empaquetador");
        Transportador transportador = new Transportador(planta);
        Thread hiloTransportador = new Thread(transportador, "Transportador");

        for (int i = 0; i < 3; i++) {
            embotes[i] = new Embotellador(planta, true);
            hiloEmbotelladores[i] = new Thread(embotes[i], "EmbotelladorVino n:" + (i+1));
        }
        for (int i = 3; i < 10; i++) {
            embotes[i] = new Embotellador(planta, false);
            hiloEmbotelladores[i] = new Thread(embotes[i], "EmbotelladorAgua n:" + (i-2));
        }
        // Inicio la ejecución de los hilos
        for (int j = 0; j < hiloEmbotelladores.length; j++) {
            hiloEmbotelladores[j].start();
        }
        hiloEmpaqueta.start();
        hiloTransportador.start();

    }
}
