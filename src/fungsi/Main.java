package fungsi;
import java.util.Scanner;


public class Polinom {
    
    // baca titik dari keyboard
    public static Matrix readPoint() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Banyak point: ");
        int n = sc.nextInt();
        sc.nextLine();

        Matrix matPoint = new Matrix(n,2);
        for (int i=0; i<=n-1; i++) {
            for (int j=0; j<=1; j++) {
                matPoint.setElmt(i, j, sc.nextDouble());
            }
        }
        sc.close();
        return matPoint;
    }
    // baca nilai x (yang akan ditaksir nilai fungsinya) dari keyboard
    public double readX() {
        Scanner sc = new Scanner(System.in);
        double x = sc.nextDouble();
        sc.close();
        return x;
    }

    // baca dari file
    public static Matrix readFile(Matrix mat) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan nama file teks: ");
        String fileName = sc.nextLine();
        mat.readMatFile(fileName);
        sc.close();
        return mat;
    }
    //pisah titik pada file dari baris terakhir (x, y) yang akan ditaksir
    public static Matrix readPointFile(Matrix matPointAndX) {
        Matrix matPoint = new Matrix(matPointAndX.getRowLength()-1,matPointAndX.getColLength());
        for (int i=0; i<=matPointAndX.getRowLength()-2; i++) { //baris terakhir tidak termasuk
            for (int j=0; j<=matPointAndX.getColLength()-1; j++) {
                matPoint.setElmt(i, j, matPointAndX.retrieveELMT(i, j));
            }
        }
        return matPoint;
    }
    //pisah x yang akan ditaksir pada file dari titik
    public static double readXFile(Matrix matPointAndX) {
        double x = matPointAndX.getElmt(matPointAndX.getRowLength()-1,0);
        return x;
    }

    public static double minX(Matrix mat) {
        double xMin = mat.getElmt(0, 1);
        for (int i=0;i<=mat.getRowLength()-1; i++) {
            if (mat.getElmt(i, 1)<xMin) xMin = mat.getElmt(i, 1);
        }
        return xMin;
    }
    public static double maxX(Matrix mat) {
        double xMax = mat.getElmt(0, 1);
        for (int i=0;i<=mat.getRowLength()-1; i++) {
            if (mat.getElmt(i, 1)>xMax) xMax = mat.getElmt(i, 1);
        }
        return xMax;
    }

    public static boolean allPointsDifferent(Matrix mat) {
        for (int i=0; i<mat.getRowLength()-1; i++) {
            for (int j=i+1; j<=mat.getRowLength()-1; j++) {
                if ((mat.getElmt(i, 0)==mat.getElmt(j, 0)) && (mat.getElmt(i, 1)==mat.getElmt(j, 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void readMtrx(Matrix mat) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < mat.getRowLength(); i++) {
            for (int j = 0; j < mat.getColLength(); j++) {
                if ((i==mat.getRowLength()-1) && (j==mat.getColLength()-1)) mat.setElmt(i,j,0);
                else mat.setElmt(i,j,sc.nextDouble());
            }
        }
        sc.nextLine();
        sc.close();
    }

    // fungsi utama
    public static void polynomialInterpolation() {
        Scanner sc = new Scanner(System.in);
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
        if (pilihan2 == 1) {
            int n = 0;
            System.out.print("Masukkan banyak titik: "); n = sc.nextInt(); sc.nextLine();
            Matrix mat = new Matrix(n+1,2);
            readMtrx(mat);
            double x = readXFile(mat);
            Matrix interpolation = new Matrix(0, 0);
            interpolation = new Matrix(mat.getRowLength()-1, mat.getRowLength());
            for (int i=0; i<mat.getRowLength()-1; i++) {
                double xi = mat.getElmt(i, 0);
                for (int j=0; j<=interpolation.getRowLength()-1; j++) {
                    interpolation.setElmt(i, j, Math.pow(xi,j));
                }
                interpolation.setElmt(i, interpolation.getColLength()-1, mat.getElmt(i, 1));
            }

            if ((x<minX(interpolation)) || (x>maxX(interpolation))) {
                System.out.println("Nilai x harus berada di antara x0 - xn.");
                sc.close(); return;
            }
            if (!allPointsDifferent(mat)) {
                System.out.println("Setiap titik harus unik."); sc.close(); return;
            }

            double result = 0.0;
            double[] a = Matrix.gaussNoText(interpolation);
            String equation = "f(x) = ";
            for (int i=a.length-1; i>0; i--) {
                equation += "("+Double.toString(a[i])+")"+"x^"+Integer.toString(i)+" + ";
                result += a[i] * Math.pow(x, i);
            }
            result += a[0];
            equation += Double.toString(a[0])+", f("+Double.toString(x)+") = "+Double.toString(result);
            System.out.println(equation);
        } else if (pilihan2==2) {
            Matrix mat = Matrix.readFile();
            double x = readXFile(mat);
            //matPoint.printMat();
            Matrix interpolation = new Matrix(0, 0);
            interpolation = new Matrix(mat.getRowLength()-1, mat.getRowLength());
            for (int i=0; i<mat.getRowLength()-1; i++) {
                double xi = mat.getElmt(i, 0);
                for (int j=0; j<=interpolation.getRowLength()-1; j++) {
                    interpolation.setElmt(i, j, Math.pow(xi,j));
                }
                interpolation.setElmt(i, interpolation.getColLength()-1, mat.getElmt(i, 1));
            }

            if ((x<minX(interpolation)) || (x>maxX(interpolation))) {
                System.out.println("Nilai x harus berada di antara x0 - xn.");
                sc.close(); return;
            }
            if (!allPointsDifferent(mat)) {
                System.out.println("Setiap titik harus unik."); sc.close(); return;
            }

            double result = 0.0;
            double[] a = Matrix.gaussNoText(interpolation);
            String equation = "f(x) = ";
            for (int i=a.length-1; i>0; i--) {
                equation += "("+Double.toString(a[i])+")"+"x^"+Integer.toString(i)+" + ";
                result += a[i] * Math.pow(x, i);
            }
            result += a[0];
            equation += Double.toString(a[0])+", f("+Double.toString(x)+") = "+Double.toString(result);
            System.out.println(equation);
        } else System.out.println("Ketik '1' atau '2'.");
        sc.close();
    }
    public static void main(String[] args) {
        polynomialInterpolation();
    }
}
