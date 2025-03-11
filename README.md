# Eliminação de Gauss para Resolução de Sistemas Lineares

Este projeto implementa o **método de eliminação de Gauss** para resolver sistemas lineares de equações do tipo \( Ax = b \), onde:

- \( A \) é uma matriz \( n \times n \) de coeficientes,
- \( x \) é o vetor das incógnitas, e
- \( b \) é o vetor dos termos constantes.

## Como Funciona

O método de eliminação de Gauss é uma técnica usada para resolver sistemas lineares de equações. A ideia principal é transformar a matriz \( A \) em uma forma triangular superior utilizando operações de linha, o que permite a resolução do sistema por substituição reversa.

### Passos do Algoritmo

1. **Eliminação de Gauss (Transformação para Matriz Escalonada):**
    - Para cada coluna, encontramos o maior valor absoluto (pivotamento) para evitar erros numéricos.
    - Eliminamos as variáveis abaixo da diagonal principal, utilizando a substituição para zerar os elementos abaixo do pivô.

2. **Substituição Reversa:**
    - Depois de transformar a matriz \( A \) em uma matriz escalonada superior, resolvemos o sistema de forma retroativa, começando da última linha até a primeira.

### Pivotamento

O algoritmo também utiliza **pivotamento parcial**, o que significa que, para cada coluna, a linha com o maior valor absoluto na coluna é trocada com a linha atual. Isso ajuda a evitar erros de precisão, especialmente quando os elementos da matriz possuem grande variação de valores.

## Como Usar

### Requisitos

Este código foi implementado em Java e pode ser executado em qualquer ambiente que suporte a linguagem Java. Para isso, basta ter o **Java Development Kit (JDK)** instalado na sua máquina.

### Exemplo de Uso

Aqui está um exemplo de como usar o método de eliminação de Gauss para resolver um sistema de equações:

```java
public class Main {
    public static void main(String[] args) {
        // Definição da matriz de coeficientes A
        double[][] A = {
            {2, -1, 1},
            {3, 3, 9},
            {3, 3, 5}
        };

        // Definição do vetor de termos constantes b
        double[] b = {2, -1, 1};

        // Chamando o método para resolver o sistema de equações
        double[] solution = gaussElimination(A, b);

        // Exibindo a solução
        System.out.println("Solução:");
        for (double v : solution) {
            System.out.println(v);
        }
    }

    // Método para resolver o sistema de equações Ax = b usando eliminação de Gauss
    public static double[] gaussElimination(double[][] A, double[] b) {
        int n = b.length;

        // Eliminação de Gauss (transformação para matriz escalonada)
        for (int i = 0; i < n; i++) {
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
}
