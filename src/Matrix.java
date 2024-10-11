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
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Transpose matriks
    public Matrix transpose() {
        Matrix mT = new Matrix(nRow, nCol);
        for (int i=0; i<nRow; i++) {
            for (int j=0; j<nCol; j++) {
                mT.mat[j][i] = mat[i][j];
            }
        }
        return mT;
    }
}