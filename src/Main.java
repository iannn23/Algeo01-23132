import fungsi.Matrix;
import fungsi.SPL;
import fungsi.Gausss;
public class Main {
    public static void main(String[] args) {
       double[][] m = InputMatrix.readMatrixKeyboard1();
                    Matrix m1 = new Matrix(m, m.length, m[0].length);
                    SPL.gaussSPL(m1);
    }   
}
