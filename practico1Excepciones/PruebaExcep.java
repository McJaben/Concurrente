package practico1Excepciones;

import javax.management.RuntimeErrorException;

public class PruebaExcep{
    
    public static void main(String[] args) {
        //try{
        //    esMayor(17);
        //} catch(Exception e) {
        //    System.out.println("Error en el metodo esMayor()");
        //    e.printStackTrace();
       // }
        try{
            ruleta(17);
        } catch(Exception e) {
            System.out.println("Error en el metodo ruleta()");
            e.printStackTrace();
        }
        
    }
    
    public static void esMayor(int edad) {
        if (edad < 18 && edad > 0) {
            throw new RuntimeException("Anda a dormir, pibe. DNI trucho.");
        }
    }

    public static void ruleta(int num) {
        if (num != 5) {
            throw new RuntimeException("Perdiste, pete");
        }
    }
}