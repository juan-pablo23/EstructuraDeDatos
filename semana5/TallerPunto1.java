import java.util.LinkedList;
import java.util.Queue;

public class TallerPunto1 {
    
    public static void main(String[] args) {
        Queue<Integer> Votos = new LinkedList<>();

        int upvotes = 132;
        int downvotes = 132;

        for (int i = 0; i < upvotes; i++) {
           Votos.add(+1); 
        }
        for (int i = 0; i < downvotes; i++) {
            Votos.add(-1);
        }

        int resultado = 0;

        while (!Votos.isEmpty()) {
            int votos = Votos.poll();
            resultado += votos;
        }

        System.out.println("La cantidad de votos es: " + resultado);
}
}




