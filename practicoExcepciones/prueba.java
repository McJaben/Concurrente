import java.io.IOException;

public class prueba {
    public static void main(String[] args) {
        // double[] v = new double[15];
        // acceso_por_indice(v, 16);
        // acceso_por_indice2(v, 16);
        try {
            System.out.println(metodo());
        } catch (Exception e) {
            System.err.println("Excepción en el metodo()");
            e.printStackTrace();
        }
    }

    /**
     * public static double acceso_por_indice(double[] v, int j) throws
     * RuntimeException{
     * try{
     * if(0<= j && j <= v.length){
     * return v[j];
     * } else {
     * throw new RuntimeException("El indice: " + j + " no existe en el vector");
     * }
     * }
     * catch(RuntimeException exc){
     * throw exc;
     * }
     * }
     */

    public static double acceso_por_indice(double[] v, int j) {
        // if (0 <= j && j < v.length) {
        //     return v[j];
        // } else {
        //     throw new IndexOutOfBoundsException("El índice: " + j + " está fuera del rango del vector");
        // }

        if (0 > j || j >= v.length) {
            throw new IndexOutOfBoundsException("El índice: " + j + " está fuera del rango del vector");
        } 
        return v[j];
    }

    public static double acceso_por_indice2(double[] v, int j) {
        try {
            return v[j];
        } catch (ArrayIndexOutOfBoundsException exc) {
            throw new RuntimeException("El índice: " + j + " está fuera del rango del vector");
        }
    }

    private static int metodo() {
        int valor = 0;
        try {
            valor += 1;
            valor = valor + Integer.parseInt("W");
            valor += 1;
            System.out.println("Valor al final del try: " + valor);
            throw new IOException();
        } catch (IOException e) {
            valor = valor + Integer.parseInt("42");
            System.out.println("Valor al final del catch: " + valor);
        } finally {
            valor += 1;
            System.out.println("Valor al final del finally: " + valor);

        }
        valor += 1;
        System.out.println("Valor antes del return: " + valor);
        return valor;
    }
}
