package practico3Concurrencia;

public class MainEj4 {
    // Ejercicio 4.

    public static void main(String[] args) {
        // Creo el parque con la cantidad máxima de áreas
        ParqueTematico parque = new ParqueTematico(10);
        // Creo las áreas con su respectiva capacidad
        Area area1 = new Area("Zona A", 10);
        Area area2 = new Area("Zona B", 6);

        parque.agregarArea(area1);
        parque.agregarArea(area2);

        Thread visitante1 = new Thread(new Visitante("Juan", "Zona A", parque, 2));
        Thread visitante2 = new Thread(new Visitante("Maria", "Zona A", parque, 2));
        Thread visitante3 = new Thread(new Visitante("Pedro", "Zona B", parque, 1));
        Thread visitante4 = new Thread(new Visitante("Fabio", "Zona B", parque, 4)); // no debería poder

        visitante1.start();
        visitante2.start();
        visitante3.start();
        visitante4.start();
    }
}
