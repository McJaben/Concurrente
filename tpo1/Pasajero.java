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
public class Pasajero implements Runnable {

    private Tren trencito;

    public Pasajero(Tren chu) {

        this.trencito = chu;
    }

    @Override
    public void run() {

        while (true) {

            try {
                
                //El pasajero intenta subir
                if (this.trencito.subir()) {
                    System.out.println("El pasajero " + Thread.currentThread().getName() + " subio.");

                    this.trencito.esperarAdentro();
                    System.out.println("El pasajero " + Thread.currentThread().getName() + " se bajo.");
                }else{
                
                    System.out.println("El tren esta lleno");
                    
                    Thread.sleep(200);
                    
                    
                    
                
                }
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Pasajero.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
