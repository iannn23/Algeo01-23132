import fungsi.Matrix;
import java.util.Scanner;
import java.lang.Math;

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

    //baca nilai x (yang akan ditaksir nilai fungsinya) dari keyboard
    public double readX() {
        Scanner sc = new Scanner(System.in);
        double x = sc.nextDouble();
        sc.close();
        return x;
    }

    // persamaan linear
    public static Matrix createLinearEq(Matrix point) {
        int n = point.getRowLength();
        Matrix linearEq = new Matrix(n, n+1);
        for (int i=0; i<=linearEq.getRowLength()-1; i++) {
            for (int j=0; j<=linearEq.getColLength()-1; j++) {
                if (j==linearEq.getColLength()-1) {
                    linearEq.setElmt(i, j, Math.pow(point.getElmt(i, 0), j));
                }
            }
        }
        return linearEq;
    }

    //mendapat polinomial (belum persamaan)
    public static Matrix getPolynomial(Matrix mLinEq) {
        Matrix solTemp = mLinEq.gaussJordanElimination();
        Matrix sol = new Matrix(solTemp.getRowLength(),1);
        for (int i=0; i<sol.getRowLength(); i++) {
            sol.setElmt(i, 0, solTemp.getElmt(i, solTemp.getRowLength()-1));
        }
        return sol;
    }

    //mendapat polinomial (bentuk persamaan)
    public static String getPolynomialEq(Matrix sol) {
        String polynomial = "f(x) = ";
        for (int i=0; i<=sol.getRowLength()-1; i++) {
            polynomial += String.valueOf(sol.getElmt(i, sol.getColLength()-1));
            if (i==1) polynomial += "x";
            else if (i>1) polynomial += "x^" + String.valueOf(i);
            if (i!=sol.getRowLength()-1) polynomial += " + ";
        }
        return polynomial;
    }

    //menghitung taksiran (pakai matriks solusi)
    public static double taksiran(Matrix sol, double x) {
        double taksiran = 0;
        for (int i=0; i<sol.getRowLength(); i++ ) {
            for (int j=0; j<sol.getColLength(); j++) {
                taksiran += sol.getElmt(i, j)*Math.pow(x,i);
            }
        }
        return taksiran;
    }
}