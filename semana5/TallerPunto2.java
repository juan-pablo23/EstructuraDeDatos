import java.util.Deque;
import java.util.LinkedList;

public class TallerPunto2 {

    public static void main(String[] args) {
        Deque<String> S1 = new LinkedList<>();
        Deque<String> S2 = new LinkedList<>();

        S1.addLast("Pan Blanco");
        S1.addLast("Pan lechuga");
        S1.addLast("Pan Blanco");

        S2.addLast("Pan Blanco");
        S2.addLast("Pan tomate");
        S2.addLast("Pan Blanco");
    }
}