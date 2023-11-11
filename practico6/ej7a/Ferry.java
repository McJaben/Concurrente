/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practico6.ej7a;

/**
 *
 * @author kevin.quintero && benjamin.morales
 */
public class Ferry {

    private final int tamAutos = 5;
    private final int espacios;
    private int espaciosOcupados;
    private boolean sePuede = true;
    private boolean bajando = false;

    public Ferry(int esp) {
        this.espacios = esp;
        this.espaciosOcupados = 0;
    }

    public synchronized void subirAuto() throws InterruptedException {
        if (espaciosOcupados + tamAutos > espacios) {
            sePuede = false;
        }

        while (!sePuede) {
            this.wait();
            if (bajando) {
                this.wait();
            }
            if (espaciosOcupados + tamAutos > espacios) {
                sePuede = false;
            }
        }

        espaciosOcupados = espaciosOcupados + tamAutos;
        System.out.println(Thread.currentThread().getName() + " subio al barco, espacios: " + espaciosOcupados);

        if (espaciosOcupados == espacios) {
            sePuede = false;
            this.enRecorrido();
        } else {
            this.wait();
        }
    }

    public synchronized void subirPasajero() throws InterruptedException {
        while (!sePuede) {
            this.wait();
            if (bajando) {
                this.wait();
            }
            if (espaciosOcupados < espacios) {
                sePuede = true;
            }
        }

        espaciosOcupados++;
        System.out.println(Thread.currentThread().getName() + " subio al barco, espacios: " + espaciosOcupados);
        if (espaciosOcupados == espacios) {
            sePuede = false;
            this.enRecorrido();
        } else {
            this.wait();
        }
    }

    public synchronized void bajarAuto() throws InterruptedException {
        bajando = true;
        espaciosOcupados = espaciosOcupados - tamAutos;
        System.out.println("Cantidad de espacios ocupados: " + espaciosOcupados);
        if (espaciosOcupados == 0) {
            sePuede = true;
            bajando = false;
            this.notifyAll();
        }
    }

    public synchronized void bajarPasajero() throws InterruptedException {
        bajando = true;
        espaciosOcupados--;
        System.out.println("Cantidad de espacios ocupados: " + espaciosOcupados);
        if (espaciosOcupados == 0) {
            sePuede = true;
            bajando = false;
            this.notifyAll();
        }
    }

    public synchronized void enRecorrido() throws InterruptedException {
        System.out.println("Inicia el recorrido");
        Thread.sleep(4000);
        System.out.println("Finaliza el recorrido");

        // System.out.println("Notificando");
        this.notifyAll();
        // System.out.println("Listo la notificacion");

    }
}
