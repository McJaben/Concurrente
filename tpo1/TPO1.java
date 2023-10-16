/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpo1;

/**
 *
 * @author benjamin.morales, Kevin Quintero, Nicolas Blanco
 */
public class TPO1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int CANT = 5;
        Tren trencito = new Tren(CANT);
        
        ControlTren control = new ControlTren(trencito);
        Thread hilo1 = new Thread(control, "Hilo Control");
        //hilo1.start();
        //Creamos los pasajeros
        Thread[] array = new Thread[10];
        
        
        for (int i = 0; i < array.length; i++) {
            array[i] = new Thread( new Pasajero(trencito), ""+i);
        }
        
        for (int j = 0; j < 10; j++) {
            
            array[j].start();
        }
        hilo1.start();
    }
    
}
