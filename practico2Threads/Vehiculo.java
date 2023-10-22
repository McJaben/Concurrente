package practico2Threads;

public class Vehiculo  {
    private String patente;
    private String modelo;
    private String marca;
    private float km;

    public Vehiculo(String patente, String modelo, String marca, float km) {
        this.patente = patente;
        this.modelo = modelo;
        this.marca = marca;
        this.km = km;
    }

    public void recorrer(double distancia) {
        km += distancia;
    }

}
