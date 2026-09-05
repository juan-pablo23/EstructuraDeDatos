public class Ejecutar {

public static void main (String[] args) {
    
    //declaracion del arreglo (forma 1)
    int[] a = {5, 16, 8, 9, 10, 25, 19, 3, 6, 22, 2};

    for (int i = 0; i < a.length; i++) {
        System.out.println("a[" + i + "]=" +a[i]);
    }

    //sumar todos los elementos del arreglo a
    int suma = 0;
    for (int i = 0; i < a.length; i++) {
        suma += a[i];
    }

    System.out.println("la suma de los elementos del arreglo a: "+ suma);


    //Declaracion del arreglo de la (forma 2)
    int[] b = new int[11];
    int min = 1, max = 30;
     for (int i = 0; i < b.length; i++) {
        b[i] = (int)(Math.random() * (max - min - 1) + min);
     }
     
     for (int i = 0; i < b.length; i++) {
        System.out.println("b[" + i + "]=" +b[i]);
     }

    }
}