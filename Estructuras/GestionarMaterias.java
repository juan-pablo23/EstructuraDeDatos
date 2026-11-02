import Modelo.Materia;
import java.util.LinkedList;

public class GestionarMaterias {
    
    private LinkedList<Materia> materias;

    public GestionarMaterias() {
        materias = new LinkedList<>();
    }

    public void agregarMateria(Materia materia) {
        if (buscarMateria(materia.getCodigo()) != null) {
            System.out.println("Error: ya existe una materia con ese codigo.");
            return;
        }

        materias.add(materia);
        System.out.println("Materia agregada correctamente.");
    }

    public Materia buscarMateria(String codigo) {
        for (Materia materia : materias) {

            if (materia.getCodigo().equals(codigo)) {
                return materia;
            }
        }

        return null;
    }

    public void mostrarMaterias() {

        if (materias.isEmpty()) {
            System.out.println("No hay materias registradas.");
            return;
        }

        for (Materia materia : materias) {
            System.out.println("----------------");
            materia.mostrarInformacion();
        }
    }
}
