package practico3Concurrencia;

public class MainEj2 {
    //Ejercicio 2

    public static void main(String[] args) throws InterruptedException {
        Energia power = new Energia();
        Criatura oscura = new Criatura(power);
        Sanador sanador = new Sanador(power);

        Thread elBicho = new Thread(oscura, "Criatura Oscura");
        Thread elSanador = new Thread(sanador, "El Sanador");
        System.out.println("Iniciando juego...");
        
        elBicho.start();
        elSanador.start();
        //Thread.sleep(4000);
        //elBicho.join();
        //elSanador.join();
        //System.out.println("Juego terminado.");
        //System.out.println("La energía restante fue de: " + power.getEnergia());
    }
}
