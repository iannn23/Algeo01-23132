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
                mat[i][j] = Double.valueOf(sc.next());
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

    //Copy matriks
    public Matrix copyMat() {
        Matrix mCopied = new Matrix(nRow, nCol);
        for (int i=0; i<=nRow-1; i++) {
            for (int j=0; j<=nCol-1; j++) {
                mCopied.mat[i][j] = mat[i][j];
            }
        }
        return mCopied;
    }
    
    /***Determinan Matriks (Reduksi Baris)***/
    //Ganti dua baris
    int swapCount = 0; //(yang saya ragu di sini)
    public void swapRow(int row1, int row2) {
        double[] temp = mat[row1];
        mat[row1] = mat[row2];
        mat[row2] = temp;
        swapCount++;
    }
    //Penyederhanaan/Pembagian 1 baris dengan bilangan
    int divMult = 1; //(yang saya ragu di sini)
    public void divRowByNum(int row, double div) {
        for (int j=0; j<=nCol-1; j++) {
            mat[row][j] /= div;
        }
        divMult *= div;
    }
    //Reduksi baris
    public void rowRed() {
        if (nRow==nCol) {
            Matrix mReduced = this.copyMat();
            for (int pivot=0; pivot<=nRow-1; pivot++) {
                if (mat[pivot][pivot]==0) {
                    for (int i=pivot+1; i<=nRow-1; i++) {
                        if (mat[i][pivot] != 0) {
                            swapRow(pivot, i);
                            break;
                        }
                    }
                }
                //(bagaimana jika setelah swapRow, mat[pivot][pivot] tetap 0?)

                divRowByNum(pivot, mat[pivot][pivot]);

                //buat nilai di bawah pivot = 0
                for (int i=pivot+1; i<=nRow-1; i++) {
                    for (int j=pivot; j<=nCol-1; j++) {
                        mReduced.mat[i][j] = mReduced.mat[i][j] - (mReduced.mat[i][pivot]/mReduced.mat[pivot][pivot]) * mReduced.mat[pivot][j];
                    }
                }         
            }
        }
    }
    //Determinan Matriks (reduksi baris)
    public double determinantRed() {
        int detMult = 1;
        int plusMinus = 1;
        int swapCount = 0; //reset variabel tiap kali fungsi det dipakai (yang saya ragu di sini)
        double divMult = 1.0; //reset variabel tiap kali fungsi det dipakai (yang saya ragu di sini)
        
        rowRed(); //(masalahnya matriks originalnya jadi berubah)
        for (int i=0; i<=nRow-1; i++) detMult *= mat[i][i];
        if (swapCount%2 == 1) plusMinus *= -1;
        double det = plusMinus * divMult * detMult;
        return det;
    }

    //Determinan Matriks (ekspansi kofaktor)
    public double determinantCof() {
        if (nRow==nCol) {
            if (nRow==1) return retrieveELMT(0, 0);
            
            double det = 0.0;
            int plusMinus = 1;
            for (int j=0; j<=nCol-1; j++) {
                //m[0][j] = a_1j
                if (retrieveELMT(0,j)!=0) {
                    Matrix subMat = new Matrix(nRow-1, nCol-1);
                    //submatriks M_ij
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
                    //Determinan = ((-1)^(i+j)) * a_ij * |M_ij|
                    //note: i & j di rumus dimulai dari 1; kalau di kode dari 0
                    det += plusMinus * retrieveELMT(0, j) * subMat.determinantCof(); //rekursif
                }
                plusMinus *= -1;
            }
            return det;
        }
        return 0; //return nothing (caranya gimana?)
    }
    
    //Aturan Cramer
    public Matrix cramer() {
        //Copy matriks keseluruhan ke A kecuali kolom terakhir
        Matrix A = new Matrix(nRow, nCol-1);
        for (int i=0; i<=nRow-1; i++) {
            for (int j=0; j<=nCol-2; j++) { //kecuali kolom terakhir
                A.mat[i][j] = mat[i][j];
            }
        }
        //mendapat matriks b
        Matrix b = new Matrix(nRow,1);
        for (int i=0; i<=nRow; i++) {
            b.mat[i][0] = mat[i][nCol-1];
        }

        Matrix detXi = new Matrix(nRow, 1);
        //mengganti kolom matriks A menjadi matriks b,
        //menyimpan det(A_i) ke matriks baru
        for (int j=0; j<=nCol; j++) {
            Matrix Ai = A.copyMat();
            for (int i=0; i<=nRow-1; i++) {
                Ai.mat[i][j] = b.mat[i][0];
            }
            detXi.mat[j][0] = Ai.determinantCof() / A.determinantCof();
        }
        return detXi;
    }
}