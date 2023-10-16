package practico3Concurrencia;

public class ActividadObligatoriaPC {
    // Es el ejercicio 7 del TP3
    public static void main(String[] args) {
        Impresor impresor = new Impresor();

        HiloA hiloA = new HiloA(impresor, 4);
        HiloB hiloB = new HiloB(impresor, 2);
        HiloC hiloC = new HiloC(impresor, 5);

        hiloA.start();
        hiloB.start();
        hiloC.start();
    }
}
