import java.util.Scanner;

public class Matriks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get matrix dimensions from user
        System.out.print("baris: ");
        int rows = scanner.nextInt();
        System.out.print("kolom: ");
        int cols = scanner.nextInt();

        // Create the matrix
        int[][] matriks = new int[rows][cols];

        // Input matrix elements
        System.out.println("Masukkan elemen matriks:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("Elemen [%d][%d]: ", i, j);
                matriks[i][j] = scanner.nextInt();
            }
        }

        // Print the matrix
        System.out.println("Matriks : ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
