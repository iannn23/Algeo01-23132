import java.util.Scanner;

class Matrix {
    double[][] mat;
    int nRow, nCol;
    
    //Matriks
    public Matrix(int row, int column) {
        nRow = row;
        nCol = column;
        mat = new double[nRow][nCol];
    }

    //Memasukkan nilai ke matriks secara manual (dari input)
    public void readMat() {
        Scanner sc = new Scanner(System.in);
        for (int i=0; i<nRow; i++) {
            for (int j=0; j<nCol; j++) {
                mat[i][j] = sc.nextDouble();
            }
        }
        sc.nextLine();
        sc.close();
    }
    //Memasukkan nilai ke matriks dari function
    public void inputELMT(int i, int j, double val) {
        this.mat[i][j] = val;
    }
    //Mendapatkan nilai indeks matriks
    public double retrieveELMT(int i, int j) {
        return this.mat[i][j];
    }
    //Men-output/mengeprint matriks
    public void printMat() {
        System.out.println("Matriks : ");
        for (int i=0; i<nRow; i++) {
            for (int j=0; j<nCol; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Transpose matriks
    public Matrix transpose() {
        Matrix mT = new Matrix(nCol, nRow);
        for (int i=0; i<nRow; i++) {
            for (int j=0; j<nCol; j++) {
                mT.mat[j][i] = mat[i][j];
            }
        }
        return mT;
    }

    public double determinant() {
        if (nRow==nCol) {
            if (nRow==1) return retrieveELMT(0, 0);
            
            double det = 0.0;
            int plusMinus = 1;
            for (int j=0; j<=nCol-1; j++) {
                //m[0][j] = a_1j
                if (retrieveELMT(0,j)!=0) {
                    Matrix subMat = new Matrix(nRow-1, nCol-1);
                    //submatrix M_ij
                    int iSub = 0;
                    for (int i=1; i<=nRow-1; i++) {
                        int jSub = 0;
                        for (int k=0; k<=nCol-1; k++) {
                            if (k!=j) {
                                subMat.inputELMT(iSub, jSub, retrieveELMT(i, k));
                                jSub++;
                            }
                        }
                        iSub++;
                    }
                    //Determinant = ((-1)^(i+j)) * a_ij * |M_ij|
                    //note: i & j starts from 1; but in code starts from 0
                    det += plusMinus * retrieveELMT(0, j) * subMat.determinant(); //rekursif
                }
                plusMinus *= -1;
            }
            return det;
        }
        return 0; //return nothing (caranya gimana?)
    }
}