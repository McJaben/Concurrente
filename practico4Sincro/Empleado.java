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
public class Empleado implements Runnable {

    private Confiteria confiteria;

    public Empleado(Confiteria unaConfi) {
        this.confiteria = unaConfi;
    }

    @Override
    public void run() {
        //while(true){
        boolean exito = confiteria.entrarConfiteria();
        while (!exito) {
            try {
                System.out.println(Thread.currentThread().getName() + " vuelve a la confitería");
                Thread.sleep(500);
                exito = confiteria.entrarConfiteria();
            } catch (InterruptedException ex) {
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (exito) {
            confiteria.solicitarMozo();
            confiteria.siendoAtendido();
            confiteria.liberarConfiteria();
        }
    }

}
