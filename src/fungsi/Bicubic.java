package fungsi;
import java.util.Scanner;

public class Bicubic {
    // Matriks X (bentuk y = Xa)
    public static Matrix xMat() {
        int x,y,k,l;
        double val;
        Matrix xMat = new Matrix(16,16);
        for (int i=0; i<xMat.getRowLength(); i++) {
            x = i % 2; y = (i/2) % 2;
            k = 0; l = 0;
            for (int j=0; j<xMat.getColLength(); j++) {
                val = 0.0;
                if (i<4) val = Math.pow(x,k) * Math.pow(y,l);
                else if (i<8) {
                    if (k!=0) val = k * Math.pow(x,k-1) * Math.pow(y,l);
                }
                else if (i<12) {
                    if (l!=0) val = l * Math.pow(x, k) * Math.pow(y, l-1);
                }
                else if (i<16) {
                    if ((k!=0) && (l!=0)) val = k*l * Math.pow(x, k-1) * Math.pow(y, l-1);
                }
                
                xMat.setElmt(i, j, val);
                if (k==3) {
                    k=0; l++;
                } else k++;
            }
        }
        return xMat;
    }

    public static Matrix array(Matrix mat) {
        Matrix arr = new Matrix(16,1);
        int iVal=0;
        for (int i=0; i<mat.getRowLength()-1; i++) {
            for (int j=0; j<mat.getColLength(); j++) {
                arr.setElmt(iVal,0,mat.getElmt(i, j));
                iVal++;
            }
        }
        return arr;
    }

    public static double result(Matrix mat, double m, double n) {
        int k = 0; double result = 0.0;
        for (int j=0; j<=3; j++) {
            for (int i=0; i<=3; i++) {
                result += mat.getElmt(k, 0) * Math.pow(m,i) * Math.pow(n,j);
                k++;
            }
        }
        return result;
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
    //pisah x yang akan ditaksir pada file dari titik
    public static double readYFile(Matrix matPointAndY) {
        double y = matPointAndY.getElmt(matPointAndY.getRowLength()-1,1);
        return y;
    }

    public static void bicubicInterpolation() {
        Matrix mat = Matrix.readFile();
        Matrix matPoint = readPointFile(mat);
        matPoint.printMat();
        double x = readXFile(mat); double y = readYFile(mat);
        Matrix arr = array(matPoint);
        // matPoint.printMat();
        // System.out.println("x = "+x);
        // System.out.println("y = "+y);
        Matrix xMatrix = xMat();
        // System.out.print("xMatrix ");
        // xMatrix.printMat();
        Matrix inversedX = xMatrix.inversadj();
        // System.out.print("inversed ");
        // inversedX.printMat();
        Matrix a = Matrix.multiplyMatrix(inversedX, arr);
        //a.printMat();
        double res = result(a,x,y);
        System.out.println("f("+Double.toString(x)+", "+Double.toString(y)+") = "+Double.toString(res));
    }

}
