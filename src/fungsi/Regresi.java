package fungsi;
import java.util.Scanner;

public class Regresi {
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
    // //pisah x yang akan ditaksir pada file dari titik
    // public static double readYFile(Matrix matPointAndX) {
    //     double y = matPointAndX.getElmt(matPointAndX.getRowLength()-1,1);
    //     return y;
    // }
    // //pisah x yang akan ditaksir pada file dari titik
    // public static double readZFile(Matrix matPointAndX) {
    //     double z = matPointAndX.getElmt(matPointAndX.getRowLength()-1,2);
    //     return z;
    // }
    
    /***Regresi Linear Berganda***/
    private static Matrix MultiLinearReg(Matrix mIn) {
        Matrix m_normalEstimation = new Matrix(mIn.getColLength(), (mIn.getColLength() + 1));
        int i,j,k;
        double multiplier, sum;
        for(k=0;k< m_normalEstimation.getRowLength();k++){
            for(j=0;j< m_normalEstimation.getColLength();j++){
                if((k==0) && (j==0)){
                    m_normalEstimation.setElmt(k,j,mIn.getRowLength());
                } else {
                    sum = 0;
                    for(i=0;i< mIn.getRowLength();i++){
                        if(k==0) {
                            multiplier = 1;
                            sum += (mIn.getElmt(i,(j-1)) * multiplier);
                        } else if(j==0){
                            multiplier = 1;
                            sum += (mIn.getElmt(i,(k-1)) * multiplier);
                        } else {
                            multiplier = mIn.getElmt(i,(k-1));
                            sum += (mIn.getElmt(i,(j-1)) * multiplier);
                        }
                    }
                    m_normalEstimation.setElmt(k,j,sum);
                }
            }
        }
        return m_normalEstimation;
    }
    // melakukan regresi (masih dalam bentuk matriks)
    public static Matrix solveReg(Matrix mat) {
        Matrix matNormalEst = new Matrix(mat.getColLength(), mat.getColLength()+1);
        
        matNormalEst = MultiLinearReg(mat);
        // System.out.print("MatNormalEst ");
        // matNormalEst.printMat();
        
        Matrix hasil= new Matrix (matNormalEst.getColLength()-1, 1);
        hasil=Matrix.inverseGab(matNormalEst);
        // System.out.print("MatNormalEst inv ");
        // hasil.printMat();
        
        return hasil;
    }
    // mendapat persamaan regresi
    public static String getRegEq(Matrix mat) {
        String regression = "f(x) = ";
        for (int i=0; i<=mat.getRowLength()-1; i++) {
            if ((i==0) && (mat.getElmt(i, mat.getColLength()-1)<0)) {
                regression += "-" + String.format("%.3f", (-1)*mat.getElmt(i, mat.getColLength()-1));
            } else if (i==0) {
                regression += String.format("%.3f", mat.getElmt(i, mat.getColLength()-1));
            } else {
                if (mat.getElmt(i, mat.getColLength()-1) < 0) {
                    regression += "- " + String.format("%.3f", (-1)*mat.getElmt(i, mat.getColLength()-1)) + "x^" + String.valueOf(i);
                } else {
                    regression += "+ " + String.format("%.3f", mat.getElmt(i, mat.getColLength()-1)) + "x^" + String.valueOf(i);
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
            else if (i!=mat.getRowLength()-1) taksiranStr += String.valueOf(val[i]) + ", ";
        }
        taksiranStr += String.valueOf(taksiran);
        return taksiranStr;
    }

    public static void readMtrx(Matrix mat) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < mat.getRowLength(); i++) {
            for (int j = 0; j < mat.getColLength(); j++) {
                if ((i==mat.getRowLength()-1) && (j==mat.getColLength()-1)) mat.setElmt(1,j,0);
                else mat.setElmt(i,j,sc.nextDouble());
            }
        }
        sc.nextLine();
        sc.close();
    }
    // fungsi utama
    public static void linearRegression() {
        int choice;
        System.out.println("Apakah ingin ketik manual atau baca dari file?");
        System.out.println("1. Ketik manual");
        System.out.println("2. Baca dari file");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt(); sc.nextLine();
        if (choice==1) {
            int n = 0; int m = 0;
            System.out.print("Masukkan jumlah variabel x: "); n = sc.nextInt(); sc.nextLine();
            System.out.print("Masukkan jumlah data: "); m = sc.nextInt(); sc.nextLine();
            Matrix mat = new Matrix(m+1, n+1);
            readMtrx(mat);
            
            Matrix matPoint = readPointFile(mat);
            //matPoint.printMat();

            double[] variableVal = new double[matPoint.getColLength()-1];
            for (int i=0; i<mat.getColLength()-1; i++) {
                variableVal[i] = mat.getElmt(mat.getRowLength()-1, i);
            }
            Matrix matSolved = new Matrix(matPoint.getRowLength(),1);
            matSolved = solveReg(matPoint);
            //matSolved.printMat();
            String equation = getRegEq(matSolved);
            String taks = taksiranReg(matSolved, variableVal);
            //Persamaan regresi linear berganda dan taksiran
            System.out.println("Regresi Linear Berganda: "+equation+", "+taks);
            sc.close();
        } else if (choice==2) {
            Matrix matEmpty = new Matrix();
            Matrix mat = readFile(matEmpty);
            Matrix matPoint = readPointFile(mat);

            double[] variableVal = new double[matPoint.getColLength()-1];
            for (int i=0; i<mat.getColLength()-1; i++) {
                variableVal[i] = mat.getElmt(mat.getRowLength()-1, i);
            }
            Matrix matSolved = new Matrix(matPoint.getRowLength(),1);
            matSolved = solveReg(matPoint);
            //matSolved.printMat();
            String equation = getRegEq(matSolved);
            String taks = taksiranReg(matSolved, variableVal);
            //Persamaan regresi linear berganda dan taksiran
            System.out.println("Regresi Linear Berganda: "+equation+", "+taks);
        } else {
            System.out.println("Ketik '1' atau '2'.");
        }
        sc.close();
    }
    public static void main(String[] args) {
        linearRegression();
    }

    /***Regresi Kuadratik Berganda***/
}
