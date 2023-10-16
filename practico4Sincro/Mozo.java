/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practico4Sincro;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class Mozo implements Runnable {

    private Confiteria confiteria;

    public Mozo(Confiteria confi) {
        this.confiteria = confi;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.confiteria.esperarEmpleado();
                this.atenderEmpleado();
            } catch (InterruptedException ex) {
                Logger.getLogger(Mozo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void atenderEmpleado() throws InterruptedException {
        //Atendiendo
        System.out.println("Atendiendo al empleado");
        Thread.sleep(600); //n tiempo
        this.confiteria.terminarAtender();
    }
}
