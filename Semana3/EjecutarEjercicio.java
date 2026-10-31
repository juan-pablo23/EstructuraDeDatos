public class EjecutarEjercicio {
     public static void main(String[] args) {
        OpercaionesMatrices objM = new OpercaionesMatrices();

        int [][] matriz = objM.llenarMatriz(3, 3, 1, 9);
        System.out.println(objM.imprimirMatriz (matriz));


        System.out.println(objM.sumarFilasColumnas(matriz));
    }
}
