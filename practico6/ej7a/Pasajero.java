/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practico6.ej7a;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin.quintero && benjamin.morales
 */
public class Pasajero implements Runnable{
    
    private Ferry barco;
    
    public Pasajero(Ferry bar){
        this.barco = bar;
    }
    
    @Override
    public void run() {        
        try {
            System.out.println(Thread.currentThread().getName() + " intentando subir al barco");
            barco.subirPasajero();
            barco.bajarPasajero();
            System.out.println(Thread.currentThread().getName() + " bajó del barco");
        } catch (InterruptedException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
