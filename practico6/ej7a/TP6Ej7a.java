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
public class TP6Ej7a {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Ferry barco = new Ferry(20);
        Auto[] autos = new Auto[10];
        Pasajero[] pasajeros = new Pasajero[10];
        Thread[] arrayAutos = new Thread[10];
        Thread[] arrayPasajeros = new Thread[10];
        
        for(int i = 0; i < autos.length; i++){
            autos[i] = new Auto(barco);
        }
        
        for(int i = 0; i < pasajeros.length; i++){
            pasajeros[i] = new Pasajero(barco);
        }
        
        for(int i = 0; i < arrayAutos.length; i++){
           arrayAutos[i] = new Thread(autos[i], "Auto " + i);
        }
        
        for(int i = 0; i < arrayPasajeros.length; i++){
           arrayPasajeros[i] = new Thread(pasajeros[i], "Pasajero " + i);
        }
        
        for(int i = 0; i < arrayAutos.length; i++){
           arrayAutos[i].start();
        }
        
        for(int i = 0; i < arrayPasajeros.length; i++){
           arrayPasajeros[i].start();
        }
        
    }
    
}
