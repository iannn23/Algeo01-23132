import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean state = true;

        while (state) {
            System.out.println("Loading....................");
            System.out.println("Menu");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matrik balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic Spline");
            System.out.println("6. Regresi linier dan kuadratik berganda");
            System.out.println("7. Interpolasi Gambar");
            System.out.println("8. Keluar");
            System.out.println("Masukkan pilihan anda : ");
            int pilihan = sc.nextInt();
            if (pilihan == 1) {

            } else if (pilihan == 2) {

            } else if (pilihan == 3) {

            } else if (pilihan == 4) {

            } else if (pilihan == 5) {

            } else if (pilihan == 6) {

            } else if (pilihan == 7) {

            } else if (pilihan == 8) {
                state = false;
            } else {
                System.out.println("Pilihan tidak valid");
            }
            System.out.println("Pilihan anda : " + pilihan);
        }

    }
}
