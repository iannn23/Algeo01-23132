package fungsi;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean state = true;

        while (state) {
            System.out.println("Loading....................");
            System.out.println("--------------------------------------------");
            System.out.println("                    Menu                    ");
            System.out.println("--------------------------------------------");
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

            while (pilihan < 1 || pilihan > 8) {
                System.out.println("Pilihan yang anda masukkan tidak valid, silahkan input ulang :");
                pilihan = sc.nextInt();
            }
            if (pilihan == 1) {
                System.out.println("Loading....................");
                System.out.println("--------------------------------------------");
                System.out.println("                   Metode                   ");
                System.out.println("--------------------------------------------");
                System.out.println("1. Metode eliminasi Gauss");
                System.out.println("2. Metode eliminasi Gauss-Jordan");
                System.out.println("3. Metode matriks balikan");
                System.out.println("4. Kaidah Cramer");
                System.out.println("Masukkan pilihan anda : ");
                int pilihan1 = sc.nextInt();
                while (pilihan1 < 1 || pilihan1 > 4) {
                    System.out.println("Pilihan yang anda masukkan tidak valid, silahkan input ulang");
                    pilihan1 = sc.nextInt();
                }
                System.out.println("Loading....................");
                System.out.println("--------------------------------------------");
                System.out.println("                Pilihan Input               ");
                System.out.println("--------------------------------------------");
                System.out.println("1. Input dari Keyboard");
                System.out.println("2. Input dari File");
                System.out.println("Masukkan pilihan anda : ");
                int pilihan2 = sc.nextInt();
                while (pilihan2 < 1 || pilihan2 > 2){
                    System.out.println("Pilihan yang anda masukkan tidak valid, silahkan input ulang");
                    pilihan2 = sc.nextInt();
                }
                if (pilihan1 == 1){
                    if (pilihan2 == 1){
                        Matrix matriks = Matrix.readMatSPL();
                        matriks.gaussEliminasi();
                        matriks.printMat();
                        
                    }
                    else if (pilihan2 == 2){
                        Matrix matriks = Matrix.readFile();
                        matriks.gaussEliminasi();
                        matriks.printMat();
                        

                    }
                }
                else if (pilihan1 == 2){
                    if (pilihan2 == 1){
                        Matrix matriks = Matrix.readMatSPL();
                        matriks.gaussJordanEliminasi();
                        matriks.printMat();

                    }
                    else if (pilihan2 == 2){
                        Matrix matriks = Matrix.readFile(); 
                        matriks.gaussJordanEliminasi();
                        matriks.printMat();

                    }
                }
                else if (pilihan1 == 3){
                    if (pilihan2 == 1){
                        Matrix matriks = Matrix.readMatSPL();
                        matriks.inverseGab();
                        matriks.printMat();

                    }
                    else if (pilihan2 == 2){

                    }
                }
                else if (pilihan1 == 4){
                    if (pilihan2 == 1){

                    }
                    else if (pilihan2 == 2){

                    }
                }

            } else if (pilihan == 2) {
                System.out.println("Loading....................");
                System.out.println("--------------------------------------------");
                System.out.println("                   Metode                   ");
                System.out.println("--------------------------------------------");
                System.out.println("1. Metode kofaktor");
                System.out.println("2. Metode OBE");
                System.out.println("Masukkan pilihan anda : ");
                int pilihan1 = sc.nextInt();
                while (pilihan1 < 1 || pilihan1 > 4) {
                    System.out.println("Pilihan yang anda masukkan tidak valid, silahkan input ulang");
                    pilihan1 = sc.nextInt();
                }
                System.out.println("Loading....................");
                System.out.println("--------------------------------------------");
                System.out.println("                Pilihan Input               ");
                System.out.println("--------------------------------------------");
                System.out.println("1. Input dari Keyboard");
                System.out.println("2. Input dari File");
                System.out.println("Masukkan pilihan anda : ");
                int pilihan2 = sc.nextInt();
                while (pilihan2 < 1 || pilihan2 > 2){
                    System.out.println("Pilihan yang anda masukkan tidak valid, silahkan input ulang");
                    pilihan2 = sc.nextInt();
                }

            } else if (pilihan == 3) {
                System.out.println("Loading....................");
                System.out.println("--------------------------------------------");
                System.out.println("                   Metode                   ");
                System.out.println("--------------------------------------------");
                System.out.println("1. Metode Adjoin");
                System.out.println("2. Metode Matriks Identitas");
                System.out.println("Masukkan pilihan anda : ");
                int pilihan1 = sc.nextInt();
                while (pilihan1 < 1 || pilihan1 > 4) {
                    System.out.println("Pilihan yang anda masukkan tidak valid, silahkan input ulang");
                    pilihan1 = sc.nextInt();
                }
                System.out.println("Loading....................");
                System.out.println("--------------------------------------------");
                System.out.println("                Pilihan Input               ");
                System.out.println("--------------------------------------------");
                System.out.println("1. Input dari Keyboard");
                System.out.println("2. Input dari File");
                System.out.println("Masukkan pilihan anda : ");
                int pilihan2 = sc.nextInt();
                while (pilihan2 < 1 || pilihan2 > 2){
                    System.out.println("Pilihan yang anda masukkan tidak valid, silahkan input ulang");
                    pilihan2 = sc.nextInt();
                }

            } else if (pilihan == 4) {
                System.out.println("Loading....................");
                System.out.println("--------------------------------------------");
                System.out.println("                Pilihan Input               ");
                System.out.println("--------------------------------------------");
                System.out.println("1. Input dari Keyboard");
                System.out.println("2. Input dari File");
                System.out.println("Masukkan pilihan anda : ");
                int pilihan2 = sc.nextInt();
                while (pilihan2 < 1 || pilihan2 > 2){
                    System.out.println("Pilihan yang anda masukkan tidak valid, silahkan input ulang");
                    pilihan2 = sc.nextInt();
                }
                
            } else if (pilihan == 5) {
                Bicubic.bicubicInterpolation();

            } else if (pilihan == 6) {
                System.out.println("Loading....................");
                System.out.println("--------------------------------------------");
                System.out.println("                Pilihan Input               ");
                System.out.println("--------------------------------------------");
                System.out.println("1. Input dari Keyboard");
                System.out.println("2. Input dari File");
                System.out.println("Masukkan pilihan anda : ");
                int pilihan2 = sc.nextInt();
                while (pilihan2 < 1 || pilihan2 > 2){
                    System.out.println("Pilihan yang anda masukkan tidak valid, silahkan input ulang");
                    pilihan2 = sc.nextInt();
                }
                

            } else if (pilihan == 7) {

            } else if (pilihan == 8) {
                System.out.println("---------------------------------------------");
                System.out.println("        Terima kasih telah menggunakan       ");
                System.out.println("                Kalkulator JL                ");
                System.out.println("---------------------------------------------");
                

                
            } else {
                state = false;
            }
            
        }

    }
}
