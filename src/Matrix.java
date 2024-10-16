
import java.util.Scanner;

public class Matrix {
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
    public void setElmt(int i, int j, double elmt){//kenan
        mat[i][j] = elmt;
    }
    public int getRowLength(){//kenan
        return nRow;
    }
    public int getColLength(){//kenan
        return nCol;
    }
    public double getElmt(int i, int j){//kenan
        return mat[i][j];
    }
    public void rowSwap(Matrix m, int rows1, int rows2){//kenan
		double temp;
		for (int i = 0; i < m.nCol; i++){
			temp = m.getElmt(rows1, i);
			m.setElmt(rows1, i, m.getElmt(rows2, i));
			m.setElmt(rows2, i, temp);
		}
    }

    //Memasukkan nilai ke matriks dari function
    public void inputELMT(int i, int j, double val) {
        mat[i][j] = val;
    }
    //Mendapatkan nilai indeks matriks
    public double retrieveELMT(int i, int j) {
        return mat[i][j];
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
    public static void solveManySolution(Matrix matrix) {//kenan
        int nEff = matrix.getColLength() - 1;
        boolean[] visited = new boolean[nEff];
        char[] parametric = new char[nEff];
        int cur = 17;

        for (int i = 0; i < nEff; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < matrix.getRowLength(); i++) {
            for (int j = i; j < nEff; j++) {
                if (matrix.getElmt(i, j) == 1) {
                visited[j] = true;
                StringBuilder temp = new StringBuilder();

                if (Math.abs(matrix.getElmt(i, matrix.getColLength() - 1)) > 1e-8) {
                    temp.append(String.format("%.4f", (matrix.getElmt(i, matrix.getColLength() - 1))));
                }

                for (int k = j + 1; k < nEff; k++) {
                    if (Math.abs(matrix.getElmt(i, k)) > 1e-8) {
                        if (!visited[k]) {
                            visited[k] = true;
                            parametric[k] = (char) ('a' + cur % 26);
                            System.out.printf("X%d = %c%n", k + 1, parametric[k]);
                            cur = (cur + 1) % 26;
                        }

                        if (matrix.getElmt(i, k) > 0) {
                            if (temp.length() == 0) {
                                temp.append(String.format("%.4f", Math.abs(matrix.getElmt(i, k))));
                            } else {
                                if (Math.abs(matrix.getElmt(i, k)) == 1) {
                                    temp.append(String.format(" - ", Math.abs(matrix.getElmt(i, k))));
                                }
                                else {
                                    temp.append(String.format(" - %.4f", Math.abs(matrix.getElmt(i, k))));
                                }
                            }
                        } else {
                            if (temp.length() == 0) {
                                temp.append(String.format("%.4f", Math.abs(matrix.getElmt(i, k))));
                            } else {
                                if (Math.abs(matrix.getElmt(i, k)) == 1) {
                                    temp.append(String.format(" + ", Math.abs(matrix.getElmt(i, k))));
                                }
                                else {
                                temp.append(String.format(" + %.4f", Math.abs(matrix.getElmt(i, k))));
                                }
                            }
                        }
                        temp.append(parametric[k]);
                    }
                }
                System.out.printf("X%d = %s%n", j + 1, temp.toString());
                break;
            } else {
                if (!visited[j]) {
                    visited[j] = true;
                    parametric[j] = (char) ('a' + cur % 26);
                    System.out.printf("X%d = %c%n", j + i, parametric[j]);
                    cur = (cur + 1) % 26;
                    }
                }
            }
        }
    }
    public static void backSubstitution(Matrix matrix, double[] X) {//kenan
        int i, j;
        int n, m;
        
        n = matrix.getRowLength();
        m = matrix.getColLength();

        for (i = n - 1; i >= 0; i--) {
            X[i] = matrix.getElmt(i, m - 1);
            for (j = i + 1; j < n; j++) {
                X[i] -= matrix.getElmt(i, j) * X[j];
            }
            X[i] /= matrix.getElmt(i, i);
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
    
    public static Matrix gaussElimination(Matrix matrix) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();
        double X[] = new double[n];
        // Cari pivot non-nol pertama dalam kolom
        for (int i = 0; i < n; i++) {
            int pivotRow = i;
            if (i < matrix.nCol){
            while (pivotRow < n && matrix.getElmt(pivotRow, i) == 0) {
                pivotRow++;
            }
            //Pivot ketermu
            if (pivotRow == n) {
                continue;
            }

            if (matrix.getElmt(pivotRow, i) != 1) {
                double pivotValue = matrix.getElmt(pivotRow, i);
                for (int j = i; j < m; j++) {
                    matrix.setElmt(pivotRow, j, matrix.getElmt(pivotRow, j) / pivotValue);
                }
            }
            
            // Tukar baris pivot dengan baris saat ini
            for (int j = i; j < m; j++) {
                double temp = matrix.getElmt(i, j);
                matrix.setElmt(i, j, matrix.getElmt(pivotRow, j));
                matrix.setElmt(pivotRow, j, temp);
            }

            for (int j = i + 1; j < n; j++) {
                double factor = matrix.getElmt(j, i);
                for (int k = i; k < m; k++) {
                    matrix.setElmt(j, k, matrix.getElmt(j, k) - factor * matrix.getElmt(i, k));
                }
            }
        }
    }

        for (int i = 0 ; i < n ; i++) {
            int allZero = 1 ;
            for (int j = 0; j < m - 1 ; j++) {
                if (matrix.getElmt(i,j) != 0) {
                    allZero = 0;
                    break;
                }
            }
            if (allZero == 1) {
                for (int k = i ; k < n - 1 ; k++) {
                    for (int j = 0 ; j < m ; j++) {
                        matrix.rowSwap(matrix, k, k+1);
                }
            }
        }
    }

        for (int i = 0; i < n; i++) {
            int pivotColl = 0;
            while (pivotColl < m - 1 && matrix.getElmt(i, pivotColl) == 0) {
                pivotColl++;
            }

            if (pivotColl == m - 1) {
                continue;
            }
            
            if (matrix.getElmt(i, pivotColl) != 1) {
                double pivotValue2 = matrix.getElmt(i, pivotColl);
                for (int k = pivotColl; k < m; k++) {
                    matrix.setElmt(i, k, matrix.getElmt(i, k) / pivotValue2);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sameAbsValue = 1;
                for (int k = 0 ; k < m - 1; k++) {
                    if (Math.abs(matrix.getElmt(i, k)) != Math.abs(matrix.getElmt(j, k) )) {
                        sameAbsValue = 0;
                        break;
                    }
                }
                if (sameAbsValue == 1) {
                    if (matrix.getElmt(i, m-2) < 0 && matrix.getElmt(i, m-2) > 0) {
                        for (int k = 0; k < m ; k++) {
                            matrix.setElmt(j, k, matrix.getElmt(j, k) + matrix.getElmt(i, k));
                        }
                    }
                    else if (matrix.getElmt(i, m-2) > 0 && matrix.getElmt(i, m-2) < 0) {
                        for (int k = 0; k < m ; k++) {
                            matrix.setElmt(j, k, matrix.getElmt(j, k) + matrix.getElmt(i, k));
                }
            }
                    else if (matrix.getElmt(i, m-2) > 0 && matrix.getElmt(i, m-2) > 0) {
                        for (int k = 0; k < m ; k++) {
                            matrix.setElmt(j, k, matrix.getElmt(j, k) - matrix.getElmt(i, k));
                }
            }
                    else if (matrix.getElmt(i, m-2) < 0 && matrix.getElmt(i, m-2) < 0) {
                        for (int k = 0; k < m ; k++) {
                            matrix.setElmt(j, k, matrix.getElmt(j, k) - matrix.getElmt(i, k));
                }
            }
        }
            }
        }
        if ( n > m) {
            Matrix.solveManySolution(matrix);
        }
        else {
            backSubstitution(matrix, X);
        }
        return matrix;
    }
    public Matrix gaussJordanElimination() {
        Matrix A = new Matrix(nRow, nCol);
        int n = A.getRowLength();
        int m = A.getColLength();
        
        for (int i = 0; i < n; i++) {
            // Cari pivot non-nol pertama dalam kolom
            int pivotRow = i;
            if (i < A.nCol){
                while (pivotRow < n && A.getElmt(pivotRow, i) == 0) {
                    pivotRow++;
                }

                // Pivot ketemu
                if (pivotRow == n) {
                    continue;
                }

                // Tukar baris pivot dengan baris saat ini
                A.rowSwap(A, i, pivotRow);

                // Buat pivot jadi 1
                double pivot = A.getElmt(i, i);
                for (int j = i; j < m; j++) {
                    A.setElmt(i, j, A.getElmt(i, j) / pivot);
                }

                // Eliminasi baris lainnya
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        double factor = A.getElmt(j, i);
                        for (int k = i; k < m; k++) {
                            A.setElmt(j, k, A.getElmt(j, k) - factor * A.getElmt(i, k));
                        }
                    }
                }
            }
        }
        
        // Jika ada baris dengan semua elemen nol, swap ke paling bawah
        for (int i = 0; i < n; i++) {
            int allZero = 1;
            for (int j = 0; j < m - 1; j++) {
                if (A.getElmt(i, j) != 0) {
                    allZero = 0;
                    break;
                }
            }
            if (allZero == 1) {
                for (int k = i; k < n - 1; k++) {
                    A.rowSwap(A, k, k + 1);
                }
            }
        }

        // Jika ada baris dengan semua elemen nol, swap ke paling bawah
        for (int i = 0; i < n; i++) {
            // Cari pivot non-nol pertama dalam kolom
            int pivotCol = 0;
            while (pivotCol < m - 1 && A.getElmt(i, pivotCol) == 0) {
                pivotCol++;
            }

            // Pivot ketemu
            if (pivotCol == m - 1) {
                continue;
            }

            // Jika elemen pivot bukan 1, bagi seluruh baris oleh elemen pivot
            if (A.getElmt(i, pivotCol) != 1) {
                double pivotValue = A.getElmt(i, pivotCol);
                for (int k = pivotCol; k < m; k++) {
                    A.setElmt(i, k, A.getElmt(i, k) / pivotValue);
                }
            }
        }
        return A;
    }
}
    
