import java.util.Scanner;

public class Matriks {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //Dimensi Matriks dari user
        System.out.print("Masukkan baris :");
        int rows = scanner.nextInt();
        System.out.print("Masukkan kolom :");
        int cols = scanner.nextInt();

        //Deklarasi matriks
        int [][] matriks = new int[rows][cols];

        //Input elemen matriks
        System.out.println("Masukkan elemen matriks :");
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                System.out.printf("Elemen [%d][%d]: ", i, j);
                matriks [i][j] = scanner.nextInt();
            }
        }
        //print matriks
        System.out.println("Matriks : ");
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println();
        }

    }

}