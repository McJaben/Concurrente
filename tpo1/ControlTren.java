/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpo1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benjamin.morales, Kevin Quintero, Nicolas Blanco
 */
public class ControlTren implements Runnable {

    private Tren trencito;

    public ControlTren(Tren chuchu) {
        this.trencito = chuchu;
    }

    public void run() {
        while (true) {
            try {
                //Iniciar recorrido
                trencito.iniciarRecorrido();
                System.out.println("El tren inició su recorrido");
                Thread.sleep(500);
                System.out.println("El tren terminó su recorrido");
                trencito.terminarRecorrido();
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlTren.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
