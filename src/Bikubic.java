import fungsi.Matrix;
import java.util.Scanner;

public class Bikubic {
    private static Matrix matrixkolom (Matrix masuk){
        int i,j,k;
        Matrix keluar = new Matrix(masuk.getRowLength()*masuk.getColLength(), 1);
        k = 0;
        for(i=0;i<masuk.getRowLength();i++){
            for(j=0;j<masuk.getColLength();j++){
                keluar.setElmt(k,0, masuk.getElmt(i,j));
//				System.out.printf("Elm %d : %f\n",k+1, mIn.getElmt(i,j));
                k++;
            }
        }
        return  keluar;
    }

    public static Matrix bicubicX(){
        int i,j,k,x,y;
        int col,row;
        double ELMTval;
        Matrix mat16 = new Matrix(16,16);
        row = 0;
        for(k=0;k<=3;k++) {
            for (y = 0; y <= 1; y++) {
                for (x = 0; x <= 1; x++) {
                    col = 0;
                    for (j = 0; j <= 3; j++) {
                        for (i = 0; i <= 3; i++) {
                            if (row < 4) {
                                ELMTval = (Math.pow(x, i) * Math.pow(y, j));
                            } else if (row < 8) {
                                if(i==0){
                                    ELMTval = 0;
                                } else {
                                    ELMTval = (i * Math.pow(x, i - 1) * Math.pow(y, j));
                                }
                            } else if (row < 12) {
                                if(j==0){
                                    ELMTval = 0;
                                } else {
                                    ELMTval = (j * Math.pow(x, i) * Math.pow(y, j - 1));
                                }
                            } else {
                                if(i==0 || j==0){
                                    ELMTval = 0;
                                } else {
                                    ELMTval = (i * j * Math.pow(x, i - 1) * Math.pow(y, j - 1));
                                }

                            }
//							System.out.printf("Elmt X(%d,%d) : %.4f\n",row,col,elmtVal);
                            mat16.setElmt(row, col, ELMTval);
                            col++;
                        }
                    }
                    row++;
                }
            }
        }
        return mat16;
    }

    public static Matrix solveBicubic(Matrix mIn){
        Matrix m_SPL = new Matrix(16,1);
        Matrix m_fx = new Matrix(16,1);
        Matrix m_x = new Matrix(16,16);
        m_fx = matrixkolom(mIn);
        m_x = bicubicX();
        Matrix m_augmenteddef = new Matrix(m_x.getRowLength(), (m_x.getColLength()+ m_fx.getColLength()));
        Matrix m_augmented = m_augmenteddef.konkatMatrix(m_x, m_fx);
        m_SPL = m_augmented.inversadj();
        return m_SPL;
    }

    public static double hasilBicubic(Matrix mathasil, double x, double y){
        int i,j,k;
        k = 0;
        double hasil = 0;
        for(j = 0; j<4; j++){
            for(i=0; i<4; i++){
                hasil += mathasil.getElmt(k,0)*Math.pow(x,i)*Math.pow(y,j);
                k++;
            }
        }
        return hasil;
    }
    public static void main(String[] args) {
        System.out.print("Masukkan pilihan : ");
        Scanner sc = new Scanner(System.in);
        int row, col;
        row = 4;
        col = 4;
        Matrix mat = new Matrix(row, col);
        System.out.println("Input matriks berukuran 4x4: ");
        mat.readMat();
        System.out.println("Input Titik (x,y) yang ingin ditaksir nilainya");
        System.out.print("x : ");
        double x = sc.nextDouble();
        sc.nextLine();
        System.out.print("y : ");
        double y = sc.nextDouble();
        sc.nextLine();
        Matrix msolved = new Matrix(16, 1);
        msolved = solveBicubic(mat);
        double taksiran = hasilBicubic(msolved, x, y);
        System.out.printf("f(%f,%f) = %f\n", x, y, taksiran);
        // FileInputOutput.opsiSaveFile(taksiran);
        // break;
    }
}
