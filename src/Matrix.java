public class Matrix {
    private double[][] data;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
    }

    public void setValue(int row, int col, double value) {
        data[row][col] = value;
    }

    public double getValue(int row, int col) {
        return data[row][col];
    }

    private double determinant(double[][] matrix, int n) {
        double det = 0;
        if (n == 1) {
            return matrix[0][0];
        }
        double[][] temp = new double[n][n];
        int sign = 1;
        for (int f = 0; f < n; f++) {
            getCofactor(matrix, temp, 0, f, n);
            det += sign * matrix[0][f] * determinant(temp, n - 1);
            sign = -sign;
        }
        return det;
    }

    private void getCofactor(double[][] matrix, double[][] temp, int p, int q, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = matrix[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    private void adjoint(double[][] matrix, double[][] adj) {
        if (rows == 1) {
            adj[0][0] = 1;
            return;
        }
        int sign = 1;
        double[][] temp = new double[rows][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                getCofactor(matrix, temp, i, j, rows);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adj[j][i] = (sign) * (determinant(temp, rows - 1));
            }
        }
    }

    public void Invers() {
        if (rows != cols) {
            throw new IllegalArgumentException("Matrix must be square");
        }
        double det = determinant(data, rows);
        if (det == 0) {
            throw new ArithmeticException("Matrix is singular and cannot have an inverse");
        }
        double[][] adj = new double[rows][rows];
        adjoint(data, adj);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                data[i][j] = adj[i][j] / det;
            }
        }
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix(3, 3);
        matrix.setValue(0, 0, 1);
        matrix.setValue(0, 1, 2);
        matrix.setValue(0, 2, 3);
        matrix.setValue(1, 0, 0);
        matrix.setValue(1, 1, 1);
        matrix.setValue(1, 2, 4);
        matrix.setValue(2, 0, 5);
        matrix.setValue(2, 1, 6);
        matrix.setValue(2, 2, 0);

        System.out.println("Original Matrix:");
        matrix.display();

        try {
            matrix.Invers();
            System.out.println("Inverted Matrix:");
            matrix.display();
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}