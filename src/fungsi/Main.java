package fungsi;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static void writeToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(content + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean state = true;

        while (state) {
           
            System.out.println("Loading....................");
            System.out.println("---------------------------------------------");
            System.out.println("         __________  _____                            ");
            System.out.println("        //\\\\___   \\\\//\\\\ \\\\                   ");
            System.out.println("        \\\\//__//\\\\ \\\\  \\\\ \\\\                ");
            System.out.println("              ___\\\\ \\\\  \\\\ \\\\                 ");
            System.out.println("             //\\\\ \\\\_\\\\  \\\\ \\\\_____         ");
            System.out.println("             \\\\ \\\\____/\\\\ \\\\_______\\\\       ");
            System.out.println("              \\\\//___// \\\\//_______//             ");
            System.out.println("---------------------------------------------");
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
                        double X[] = new double[matriks.getRowLength()];
                        int jenisSolusi = matriks.bentukSolusi();
                        matriks.bentuksegitiga(X);
                        if (jenisSolusi == 0){
                            System.out.println("Solusi tidak ada.");
                            matriks.printMat();
                        } else if (jenisSolusi == 1){
                            System.out.println("Solusi tunggal:");
                            for (int i = 0; i < matriks.getRowLength(); i++) {
                                System.out.printf("X%d = %.4f%n", i + 1, X[i]);
                            }
                            matriks.printMat();
                         }else {
                            System.out.println("Solusi banyak (parametrik):");
                            matriks.banyakSolusi();
                            matriks.printMat();

                }
                        
                    }
                    else if (pilihan2 == 2){
                        Matrix matriks = Matrix.readFile();
                        matriks.gaussEliminasi();
                        double X[] = new double[matriks.getRowLength()];
                        int jenisSolusi = matriks.bentukSolusi();
                        matriks.bentuksegitiga(X);
                        if (jenisSolusi == 0){
                            System.out.println("Solusi tidak ada.");
                            matriks.printMat();
                            writeToFile("gauss eliminasi.txt", "Solusi tidak ada.\n" + matriks.toString());
                        } else if (jenisSolusi == 1){
                            StringBuilder result = new StringBuilder("Solusi tunggal:\n");
                            for (int i = 0; i < matriks.getRowLength(); i++) {
                                String solution = String.format("X%d = %.4f%n", i + 1, X[i]);
                                System.out.printf("X%d = %.4f%n", i + 1, X[i]);
                                result.append(solution);
                            }
                            matriks.printMat();
                            result.append(matriks.toString());
                            writeToFile("gauss eliminasi.txt", result.toString());
                        }else {
                            System.out.println("Solusi banyak (parametrik):");
                            matriks.banyakSolusi();
                            matriks.printMat();
                            writeToFile("gauss eliminasi.txt", "Solusi banyak (parametrik):\n" + matriks.toString());
                        }
                    }
                }
                else if (pilihan1 == 2){
                    if (pilihan2 == 1){
                        Matrix matriks = Matrix.readMatSPL();
                        matriks.gaussJordanEliminasi();
                        int jenisSolusi = matriks.bentukSolusi();
                        if (jenisSolusi == 0){
                            System.out.println("Solusi tidak ada.");
                            matriks.printMat();
                        } else if (jenisSolusi == 1){
                            System.out.println("Solusi tunggal:");
                            for (int i = 0; i < matriks.getRowLength(); i++) {
                                System.out.printf("X[%d] = %.4f%n", i + 1, matriks.mat[i][matriks.getColLength() - 1]);
                            }
                            matriks.printMat();
                         }else {
                            System.out.println("Solusi banyak (parametrik):");
                            matriks.banyakSolusi();
                            matriks.printMat();
                        }
                    }
                    else if (pilihan2 == 2){
                        Matrix matriks = Matrix.readFile(); 
                        matriks.gaussJordanEliminasi();
                        int jenisSolusi = matriks.bentukSolusi();
                        if (jenisSolusi == 0){
                            System.out.println("Solusi tidak ada.");
                            writeToFile("gauss eliminasi jordan.txt", "Solusi tidak ada.\n" + matriks.toString());
                        } else if (jenisSolusi == 1){
                            StringBuilder result = new StringBuilder("Solusi tunggal:\n");
                            for (int i = 0; i < matriks.getRowLength(); i++) {
                                System.out.printf("X[%d] = %.4f%n", i + 1, matriks.mat[i][matriks.getColLength() - 1]);
                                String solution = String.format("X[%d] = %.4f%n", i + 1, matriks.mat[i][matriks.getColLength() - 1]);
                                result.append(solution);
                            }
                            result.append(matriks.toString());
                            writeToFile("gauss eliminasi jordan.txt", result.toString());
                         }else {
                            System.out.println("Solusi banyak (parametrik):");
                            matriks.banyakSolusi();
                            writeToFile("gauss eliminasi jordan.txt", "Solusi banyak (parametrik):\n" + matriks.toString());
                        }

                    }
                }
                else if (pilihan1 == 3){
                    if (pilihan2 == 1){
                        Matrix m = Matrix.readMatSPL();
                        String[] solusi = m.SPLInvers(m, m.getRowLength(), m.getColLength());
                        for (int i = 0; i < solusi.length; i++){
                            System.out.println(solusi[i]);
                        }
                    }
                    else if (pilihan2 == 2){
                        Matrix matriks = Matrix.readFile();
                        String[] solusi = matriks.SPLInvers(matriks, matriks.getRowLength(), matriks.getColLength());
                        StringBuilder result = new StringBuilder("Solusi Invers:\n");
                        for (int i = 0; i < solusi.length; i++){
                            System.out.println(solusi[i]);
                            result.append(solusi[i]).append("\n");
                        }
                        writeToFile("invers.txt", result.toString());
                        
                    }
                }
                else if (pilihan1 == 4){
                    if (pilihan2 == 1){
                        Matrix matriks = Matrix.readMatSPL();
                        matriks.cramer();

                    }
                    else if (pilihan2 == 2){
                        Matrix matriks = Matrix.readFile();
                        writeToFile("cramer.txt", "Cramer : " + matriks.cramer());
                    }
                }

            } else if (pilihan == 2) {
                System.out.println("Loading....................");
                System.out.println("--------------------------------------------");
                System.out.println("                   Metode                   ");
                System.out.println("--------------------------------------------");
                System.out.println("1. Metode kofaktor");
                System.out.println("2. Metode Reduksi");
                System.out.println("Masukkan pilihan anda : ");
                int pilihan1 = sc.nextInt();
                while (pilihan1 < 1 || pilihan1 > 2) {
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
                        Matrix matriks = Matrix.readMat();
                        System.out.println("Determinant : " + matriks.determinantCof());
                    }
                    else if (pilihan2 == 2){
                        Matrix matriks = Matrix.readFile();
                        System.out.println("Determinant : " + matriks.determinantCof());
                        writeToFile("determinan kofaktor.txt", "Determinant : " + matriks.determinantCof());
                    }
                }
                else if (pilihan1 == 2){
                    if (pilihan2 == 1){
                        Matrix matriks = Matrix.readMat();
                        System.out.println("Determinant : " + matriks.determinantRed());
                       
                    }
                    else if (pilihan2 == 2){
                        Matrix matriks = Matrix.readFile();
                        System.out.println("Determinant : " + matriks.determinantRed());
                        writeToFile("determinan reduksi.txt", "Determinant : " + matriks.determinantCof());
                        
                    }
                }   
            
                

            } else if (pilihan == 3) {
                System.out.println("Loading....................");
                System.out.println("--------------------------------------------");
                System.out.println("                   Metode                   ");
                System.out.println("--------------------------------------------");
                System.out.println("1. Metode Adjoin");
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
                if (pilihan1 == 1){
                    if (pilihan2 == 1){
                        Matrix matriks = Matrix.readMat();
                        Matrix matriks_adjoint = matriks.inversadj();
                        matriks_adjoint.printMat();
                    }
                    else if (pilihan2 == 2){
                        Matrix matriks = Matrix.readFile();
                        Matrix matriks_adjoint = matriks.inversadj();
                        writeToFile("invers adjoint.txt", "invers adjoint : " + matriks_adjoint);
                    }
                }else if (pilihan1 == 2){
                    if (pilihan2 == 1){
                        Matrix matriks = Matrix.readMat();
                        matriks.inversOBE();
                        matriks.printMat();
                        }
                    else if (pilihan2 == 2){
                        Matrix matriks = Matrix.readFile();
                        writeToFile("invers OBE.txt", "invers OBE : " + matriks.inversOBE());
                        }
                }
                

                

            } else if (pilihan == 4) {
                Polinom.polynomialInterpolation();
                
            } else if (pilihan == 5) {
                Bicubic.bicubicInterpolation();
                
            } else if (pilihan == 6) {
                Regresi.linearRegression();
                

            } else if (pilihan == 7) {
                System.out.println("Loading....................");
                System.out.println("Masih dalam tahap pengembangan");

            } else if (pilihan == 8) {
                System.out.println("---------------------------------------------");
                System.out.println("        Terima kasih telah menggunakan       ");
                System.out.println("                Kalkulator JL                ");
                System.out.println("---------------------------------------------");    
                state = false;
            } else {
                state = false;
            }
            
        }

    }
}
