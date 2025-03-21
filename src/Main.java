public class Main {

    // Método para resolver o sistema de equações Ax = b
    public static double[] gaussElimination(double[][] A, double[] b) {
        int n = b.length;

        // Eliminação de Gauss (transformação para matriz escalonada)
        for (int i = 0; i < n; i++) {
            // Encontrar o maior valor na coluna (para troca de linha, evitando erros de precisão)
            double max = Math.abs(A[i][i]);
            int row = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(A[k][i]) > max) {
                    max = Math.abs(A[k][i]);
                    row = k;
                }
            }

            // Trocar a linha atual com a linha com o maior valor na coluna
            if (i != row) {
                for (int j = 0; j < n; j++) {
                    double temp = A[i][j];
                    A[i][j] = A[row][j];
                    A[row][j] = temp;
                }
                double temp = b[i];
                b[i] = b[row];
                b[row] = temp;
            }

            // Eliminar as variáveis abaixo da diagonal principal
            for (int j = i + 1; j < n; j++) {
                double ratio = A[j][i] / A[i][i];
                for (int k = i; k < n; k++) {
                    A[j][k] -= ratio * A[i][k];
                }
                b[j] -= ratio * b[i];
            }
        }

        // Substituição reversa para resolver o sistema
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = b[i];
            for (int j = i + 1; j < n; j++) {
                x[i] -= A[i][j] * x[j];
            }
            x[i] /= A[i][i];
        }

        return x;
    }

    public static void main(String[] args) {
        // Exemplo de um sistema de equações
        double[][] A = {
                {5, 2, 1},
                {-1, 4, 2},
                {2, -3, 10}
        };
        double[] b = {-12, 20, 3};

        double[] solution = gaussElimination(A, b);

        // Exibir a solução
        System.out.println("Solução:");
        for (double v : solution) {
            System.out.println(v);
        }
    }
}
