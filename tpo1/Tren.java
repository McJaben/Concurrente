/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpo1;

import java.util.concurrent.Semaphore;

/**
 *
 * @author benjamin.morales, Kevin Quintero, Nicolas Blanco
 */
public class Tren {

    private int espacios;
    private int pasajeros;

    private Semaphore iniciarRecorrido;
    private Semaphore esperaAdentro;
    private Semaphore esperAfuera;
    private Semaphore sem1;
    private Semaphore sem2;

    public Tren(int lugares) {

        this.espacios = lugares;
        this.pasajeros = 0;
        this.esperaAdentro = new Semaphore(0); //Controla la duración del viaje hasta ejecutar método terminarRecorrido()
        this.esperAfuera = new Semaphore(1); // Controla que no se suban mientras está recorriendo y no se hayan bajado todos los pasajeros
        this.iniciarRecorrido = new Semaphore(0);
        this.sem1 = new Semaphore(1); // protege variable pasajeros
    }

    public boolean subir() throws InterruptedException {
        // Simula la acción de subir al tren por parte del pasajero
        // Retorna true si el pasajero se pudo subir, falso en caso contrario.
        boolean rta = false;

        esperAfuera.acquire();
        sem1.acquire();
        if (pasajeros < espacios) {
            pasajeros++;

            if (pasajeros == espacios) {
                sem1.release();
                // Avisa al control tren que está lleno y que puede iniciar el viaje
                iniciarRecorrido.release();
            } else {
                sem1.release();
            }
            rta = true;

        } else {
            sem1.release();
        }
        esperAfuera.release();
        return rta;
    }

    public void esperarAdentro() throws InterruptedException {
        //Controla la duración del viaje hasta ejecutar método terminarRecorrido()
        this.esperaAdentro.acquire();

    }

    public void iniciarRecorrido() throws InterruptedException {
        // Inicia el recorrido una vez está lleno el tren
        iniciarRecorrido.acquire();
        // "Cierra las puertas del tren" para que no suban más pasajeros.
        this.esperAfuera.acquire();
    }

    public void terminarRecorrido() throws InterruptedException {
        // Se ejecuta al finalizar el recorrido. Baja a los pasajeros y libera
        // al primero en la fila de espera para subir.
        sem1.acquire();
        for (int i = 0; i < espacios; i++) {
            pasajeros--;
            sem1.release();
            this.esperaAdentro.release();
        }
        
        esperAfuera.release();

    }
}
