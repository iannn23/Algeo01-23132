import fungsi.Matrix;

public class Regresi {
    /***Regresi Linear Berganda***/
    private static Matrix MultiLinearReg(Matrix mat) {
        double mult = 0.0; //multiplier
        Matrix matNormalEst = new Matrix(mat.getColLength(), mat.getColLength()+1);
        for (int i=0; i<=matNormalEst.getRowLength()-1; i++) {
            for (int j=0; j<=matNormalEst.getColLength()-1; j++) {
                if ((i==0)&&(j==0)) matNormalEst.setElmt(i, j, mat.getRowLength());
                else {
                    double sum=0;
                    for(int k=0; i<mat.getRowLength(); i++) {
                        if (i==0) {
                            mult=1;
                            sum = sum + mat.getElmt(k,j-1)*mult;
                        } else if (j==0) {
                            mult=1;
                            sum = sum + mat.getElmt(k, i-1)*mult;
                        } else {
                            mult = mat.getElmt(i, k-1);
                            sum = sum + mat.getElmt(i, j-1)*mult;
                        }
                    }
                    matNormalEst.setElmt(i, j, mult);
                }
            }
        }
        return matNormalEst;
    }
    // melakukan regresi (masih dalam bentuk matriks)
    public static Matrix solveReg(Matrix mat) {
        Matrix matNormalEst = new Matrix(mat.getColLength(), mat.getColLength()+1);
        matNormalEst = MultiLinearReg(mat);
        return matNormalEst.Invers();
    }
    // mendapat persamaan regresi
    public static String getRegEq(Matrix mat) {
        String regression = "f(x) = ";
        for (int i=0; i<=mat.getRowLength()-1; i++) {
            if ((i==0) && (mat.getElmt(i, mat.getColLength()-1)<0)) {
                regression += "-" + String.valueOf((-1)*mat.getElmt(i, mat.getColLength()-1));
            } else if (i==0) {
                regression += String.valueOf(mat.getElmt(i, mat.getColLength()-1));
            } else {
                if (mat.getElmt(i, mat.getColLength()-1) < 0) {
                    regression += "- " + String.valueOf((-1)*mat.getElmt(i, mat.getColLength()-1)) + String.valueOf(i);
                } else {
                    regression += "+ " + String.valueOf(mat.getElmt(i, mat.getColLength()-1)) + String.valueOf(i);
                }
            }
            if (i!=mat.getRowLength()-1) regression += " ";
        }
        return regression;
    }
    // menghitung taksiran regresi
    public static String taksiranReg(Matrix mat, double[]val) {
        double taksiran = 0.0;
        String taksiranStr = "f(";
        for (int i=0; i<=mat.getRowLength()-1; i++) {
            if (i==0) taksiran += mat.getElmt(i, 0);
            else taksiran += mat.getElmt(i, 0)*val[i-1];
            
            if (i==mat.getRowLength()-2) taksiranStr += String.valueOf(val[i]) + ") = ";
            else if (i!=mat.getRowLength()-1) taksiranStr += String.valueOf(val[i]) + ",";
        }
        taksiranStr += String.valueOf(taksiran);
        return taksiranStr;
    }

    /***Regresi Kuadratik Berganda***/
}