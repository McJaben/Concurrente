/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practico4Sincro;

/**
 *
 * @author Carlitos
 */
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Confiteria {

    //Inicializo y creo los semáforos
    private Semaphore semMozo;
    private Semaphore semConfiteria;
    private Semaphore semSalida;

    public Confiteria() {
        this.semMozo = new Semaphore(0);
        this.semConfiteria = new Semaphore(1);
        this.semSalida = new Semaphore(0);
    }

    //Métodos
    public boolean entrarConfiteria() {
        System.out.println("El empleado " + Thread.currentThread().getName()
                + " intenta ingresar a la confiteria");
        return this.semConfiteria.tryAcquire();
    }

    public void solicitarMozo() {
        semMozo.release();
    }

    public void esperarEmpleado() {
        try {
            System.out.println("El mozo "+ Thread.currentThread().getName()+
                    " está esperando un nuevo empleado");
            semMozo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarAtender() {
        semSalida.release();
    }

    public void siendoAtendido() {
        try {
            System.out.println("Empleado " + Thread.currentThread().getName()
                    + " siendo atendido");
            this.semSalida.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void liberarConfiteria() {
        System.out.println("El empleado " + Thread.currentThread().getName()
                + " se fue");
        semConfiteria.release();
    }
}
