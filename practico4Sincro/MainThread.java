/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practico4Sincro;

/**
 *
 * @author alumno
 */
public class MainThread {

    public static void main(String[] args) {
        Confiteria confi = new Confiteria();

        Empleado emp1 = new Empleado(confi);
        Empleado emp2 = new Empleado(confi);
        Empleado emp3 = new Empleado(confi);

        Mozo mozo = new Mozo(confi);

        Thread trabajador1 = new Thread(emp1, "Juan");
        Thread trabajador2 = new Thread(emp2, "Kevin");
        Thread trabajador3 = new Thread(emp3, "Benja");

        Thread elMozo = new Thread(mozo, "Carlitos");

        //Iniciamos los hilos
        elMozo.start();
        trabajador1.start();
        trabajador2.start();
        trabajador3.start();
    }
}
