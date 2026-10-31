public class OpercaionesMatrices {

    public int[][] llenarMatriz(int f, int c, int min, int max) {
        int[][] m = new int[f][c];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = (int) (Math.random() * (max - min - 1) + min);
            }
        }
        return m;
    }

    public String imprimirMatriz (int[][] m) {
        String cad = "";
        for (int i = 0; i < m.length; i++){
            cad+= "|";
            for (int j = 0; j < m[0].length; j++) {
                cad+= m [i][j] + "|";
            }
            cad+= "\n";
        }

        return cad;
    }
}