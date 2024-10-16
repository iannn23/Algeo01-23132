import java.time.format.TextStyle;
import java.util.Scanner;

public class Matrix {
    double[][] mat;
    int nRow, nCol;

    // Konstruktor Matriks
    public Matrix(int row, int column) {
        nRow = row;
        nCol = column;
        mat = new double[nRow][nCol];
    }

    // Memasukkan nilai ke matriks secara manual (dari input)
    public void readMat() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                mat[i][j] = sc.nextDouble();
            }
        }
        sc.nextLine();
        sc.close();
    }

    // Setter dan Getter
    public void setElmt(int i, int j, double elmt) {
        mat[i][j] = elmt;
    }

    public int getRowLength() {
        return nRow;
    }

    public int getColLength() {
        return nCol;
    }

    public double getElmt(int i, int j) {
        return mat[i][j];
    }

    // Menukar dua baris dalam matriks ini
    public void rowSwap(int rows1, int rows2) {
        double temp;
        for (int i = 0; i < nCol; i++) {
            temp = this.getElmt(rows1, i);
            this.setElmt(rows1, i, this.getElmt(rows2, i));
            this.setElmt(rows2, i, temp);
        }
    }

    // Memasukkan nilai ke matriks dari fungsi
    public void inputELMT(int i, int j, double val) {
        mat[i][j] = val;
    }

    // Mendapatkan nilai indeks matriks
    public double retrieveELMT(int i, int j) {
        return mat[i][j];
    }

    // Men-output/mengeprint matriks
    public void printMat() {
        System.out.println("Matriks : ");
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Transpose matriks
    public Matrix transpose() {
        Matrix mT = new Matrix(nCol, nRow);
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                mT.mat[j][i] = mat[i][j];
            }
        }
        return mT;
    }

    // Copy matriks
    public Matrix copyMat() {
        Matrix mCopied = new Matrix(nRow, nCol);
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                mCopied.mat[i][j] = mat[i][j];
            }
        }
        return mCopied;
    }

    /*** Determinan Matriks (Reduksi Baris) ***/
    // Variabel untuk menghitung swap dan pembagian
    int swapCount = 0;
    double divMult = 1.0;

    // Ganti dua baris
    public void swapRow(int row1, int row2) {
        double[] temp = mat[row1];
        mat[row1] = mat[row2];
        mat[row2] = temp;
        swapCount++;
    }

    // Penyederhanaan/Pembagian 1 baris dengan bilangan
    public void divRowByNum(int row, double div) {
        for (int j = 0; j < nCol; j++) {
            mat[row][j] /= div;
        }
        divMult *= div;
    }

    // Reduksi baris
    public void rowRed() {
        if (nRow == nCol) {
            for (int pivot = 0; pivot < nRow; pivot++) {
                if (mat[pivot][pivot] == 0) {
                    boolean swapped = false;
                    for (int i = pivot + 1; i < nRow; i++) {
                        if (mat[i][pivot] != 0) {
                            swapRow(pivot, i);
                            swapped = true;
                            break;
                        }
                    }
                    if (!swapped) {
                        continue; // Pivot tetap 0, lanjutkan ke pivot berikutnya
                    }
                }

                // Bagi baris pivot dengan elemen pivot
                divRowByNum(pivot, mat[pivot][pivot]);

                // Buat nilai di bawah pivot menjadi 0
                for (int i = pivot + 1; i < nRow; i++) {
                    double factor = mat[i][pivot];
                    for (int j = pivot; j < nCol; j++) {
                        mat[i][j] -= factor * mat[pivot][j];
                    }
                }
            }
        }
    }

    // Menangani banyak solusi
    public void solveManySolution() {
        int nEff = this.getColLength() - 1;
        boolean[] visited = new boolean[nEff];
        char[] parametric = new char[nEff];
        int cur = 17;

        for (int i = 0; i < nEff; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < this.getRowLength(); i++) {
            for (int j = i; j < nEff; j++) {
                if (this.getElmt(i, j) == 1) {
                    visited[j] = true;
                    StringBuilder temp = new StringBuilder();

                    if (Math.abs(this.getElmt(i, this.getColLength() - 1)) > 1e-8) {
                        temp.append(String.format("%.4f", this.getElmt(i, this.getColLength() - 1)));
                    }

                    for (int k = j + 1; k < nEff; k++) {
                        if (Math.abs(this.getElmt(i, k)) > 1e-8) {
                            if (!visited[k]) {
                                visited[k] = true;
                                parametric[k] = (char) ('a' + cur % 26);
                                System.out.printf("X%d = %c%n", k + 1, parametric[k]);
                                cur = (cur + 1) % 26;
                            }

                            if (this.getElmt(i, k) > 0) {
                                if (temp.length() == 0) {
                                    temp.append(String.format("%.4f", Math.abs(this.getElmt(i, k))));
                                } else {
                                    if (Math.abs(this.getElmt(i, k)) == 1) {
                                        temp.append(" - ");
                                    } else {
                                        temp.append(String.format(" - %.4f", Math.abs(this.getElmt(i, k))));
                                    }
                                }
                            } else {
                                if (temp.length() == 0) {
                                    temp.append(String.format("%.4f", Math.abs(this.getElmt(i, k))));
                                } else {
                                    if (Math.abs(this.getElmt(i, k)) == 1) {
                                        temp.append(" + ");
                                    } else {
                                        temp.append(String.format(" + %.4f", Math.abs(this.getElmt(i, k))));
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
                        System.out.printf("X%d = %c%n", j + 1, parametric[j]);
                        cur = (cur + 1) % 26;
                    }
                }
            }
        }
    }

    // Substitusi mundur
    public void backSubstitution(double[] X) {
        int i, j;
        int n = this.getRowLength();
        int m = this.getColLength();

        for (i = n - 1; i >= 0; i--) {
            X[i] = this.getElmt(i, m - 1);
            for (j = i + 1; j < n; j++) {
                X[i] -= this.getElmt(i, j) * X[j];
            }
            X[i] /= this.getElmt(i, i);
        }
    }

    // Determinan Matriks (reduksi baris)
    public double determinantRed() {
        // Reset variabel
        this.swapCount = 0;
        this.divMult = 1.0;

        // Buat salinan matriks agar matriks original tidak berubah
        Matrix mReduced = this.copyMat();
        mReduced.rowRed();

        double detMult = 1.0;
        for (int i = 0; i < mReduced.nRow; i++) {
            detMult *= mReduced.mat[i][i];
        }
        int plusMinus = (mReduced.swapCount % 2 == 1) ? -1 : 1;
        double det = plusMinus * mReduced.divMult * detMult;
        return det;
    }

    // Determinan Matriks (ekspansi kofaktor)
    public double determinantCof() {
        if (nRow == nCol) {
            if (nRow == 1) return retrieveELMT(0, 0);

            double det = 0.0;
            int plusMinus = 1;
            for (int j = 0; j < nCol; j++) {
                if (retrieveELMT(0, j) != 0) {
                    Matrix subMat = new Matrix(nRow - 1, nCol - 1);
                    int iSub = 0;
                    for (int i = 1; i < nRow; i++) {
                        int jSub = 0;
                        for (int k = 0; k < nCol; k++) {
                            if (k != j) {
                                subMat.inputELMT(iSub, jSub, retrieveELMT(i, k));
                                jSub++;
                            }
                        }
                        iSub++;
                    }
                    det += plusMinus * retrieveELMT(0, j) * subMat.determinantCof(); // Rekursif
                }
                plusMinus *= -1;
            }
            return det;
        }
        return 0; // Matriks tidak persegi
    }

    // Aturan Cramer
    public Matrix cramer() {
        // Copy matriks A (kecuali kolom terakhir)
        Matrix A = new Matrix(nRow, nCol - 1);
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol - 1; j++) { // Kecuali kolom terakhir
                A.mat[i][j] = mat[i][j];
            }
        }
        // Mendapat matriks b
        Matrix b = new Matrix(nRow, 1);
        for (int i = 0; i < nRow; i++) {
            b.mat[i][0] = mat[i][nCol - 1];
        }

        Matrix detXi = new Matrix(nRow, 1);
        double detA = A.determinantCof();

        for (int j = 0; j < nCol - 1; j++) {
            Matrix Ai = A.copyMat();
            for (int i = 0; i < nRow; i++) {
                Ai.mat[i][j] = b.mat[i][0];
            }
            double detAi = Ai.determinantCof();
            detXi.mat[j][0] = detAi / detA;
        }
        return detXi;
    }

    // Eliminasi Gauss
    public Matrix gaussElimination() {
        int n = this.getRowLength();
        int m = this.getColLength();
        double[] X = new double[n];

        // Cari pivot non-nol pertama dalam kolom
        for (int i = 0; i < n; i++) {
            if (i >= this.nCol) {
                continue;
            }
            int pivotRow = i;
            while (pivotRow < n && this.getElmt(pivotRow, i) == 0) {
                pivotRow++;
            }

            // Pivot ketemu
            if (pivotRow == n) {
                continue;
            }

            // Tukar baris pivot dengan baris saat ini jika perlu
            if (pivotRow != i) {
                this.rowSwap(i, pivotRow);
            }

            // Buat pivot menjadi 1 jika bukan 1
            double pivotValue = this.getElmt(i, i);
            if (pivotValue != 1 && pivotValue != 0) {
                for (int j = i; j < m; j++) {
                    this.setElmt(i, j, this.getElmt(i, j) / pivotValue);
                }
            }

            // Eliminasi baris di bawah pivot
            for (int j = i + 1; j < n; j++) {
                double factor = this.getElmt(j, i);
                for (int k = i; k < m; k++) {
                    this.setElmt(j, k, this.getElmt(j, k) - factor * this.getElmt(i, k));
                }
            }
        }

        // Memindahkan baris dengan semua elemen nol ke bawah
        for (int i = 0; i < n; i++) {
            boolean allZero = true;
            for (int j = 0; j < m - 1; j++) {
                if (this.getElmt(i, j) != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero) {
                for (int k = i; k < n - 1; k++) {
                    this.rowSwap(k, k + 1);
                }
            }
        }

        // Normalisasi baris pivot
        for (int i = 0; i < n; i++) {
            int pivotCol = 0;
            while (pivotCol < m - 1 && this.getElmt(i, pivotCol) == 0) {
                pivotCol++;
            }

            if (pivotCol == m - 1) {
                continue;
            }

            double pivotVal = this.getElmt(i, pivotCol);
            if (pivotVal != 1 && pivotVal != 0) {
                for (int k = pivotCol; k < m; k++) {
                    this.setElmt(i, k, this.getElmt(i, k) / pivotVal);
                }
            }
        }

        // Eliminasi baris yang sama atau saling menghapus
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean sameAbsValue = true;
                for (int k = 0; k < m - 1; k++) {
                    if (Math.abs(this.getElmt(i, k)) != Math.abs(this.getElmt(j, k))) {
                        sameAbsValue = false;
                        break;
                    }
                }
                if (sameAbsValue) {
                    double lastElemI = this.getElmt(i, m - 2);
                    double lastElemJ = this.getElmt(j, m - 2);
                    if ((lastElemI < 0 && lastElemJ > 0) || (lastElemI > 0 && lastElemJ < 0)) {
                        for (int k = 0; k < m; k++) {
                            this.setElmt(j, k, this.getElmt(j, k) + this.getElmt(i, k));
                        }
                    } else if ((lastElemI > 0 && lastElemJ > 0) || (lastElemI < 0 && lastElemJ < 0)) {
                        for (int k = 0; k < m; k++) {
                            this.setElmt(j, k, this.getElmt(j, k) - this.getElmt(i, k));
                        }
                    }
                }
            }
        }

        // Menentukan solusi
        if (n > m - 1) {
            this.solveManySolution();
        } else {
            backSubstitution(X);
            // Anda mungkin ingin menambahkan kode untuk menampilkan atau mengembalikan solusi X
            // Misalnya:
            System.out.println("Solusi:");
            for (int i = 0; i < X.length; i++) {
                System.out.printf("X%d = %.4f%n", i + 1, X[i]);
            }
        }
        return this;
    }

    // Eliminasi Gauss-Jordan
    public Matrix gaussJordanElimination() {
        int n = this.getRowLength();
        int m = this.getColLength();

        for (int i = 0; i < n; i++) {
            // Cari pivot non-nol pertama dalam kolom
            int pivotRow = i;
            while (pivotRow < n && this.getElmt(pivotRow, i) == 0) {
                pivotRow++;
            }

            // Pivot ketemu
            if (pivotRow == n) {
                continue;
            }

            // Tukar baris pivot dengan baris saat ini
            if (pivotRow != i) {
                this.rowSwap(i, pivotRow);
            }

            // Buat pivot menjadi 1
            double pivot = this.getElmt(i, i);
            if (pivot != 1 && pivot != 0) {
                for (int j = i; j < m; j++) {
                    this.setElmt(i, j, this.getElmt(i, j) / pivot);
                }
            }

            // Eliminasi baris lainnya
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = this.getElmt(j, i);
                    for (int k = i; k < m; k++) {
                        this.setElmt(j, k, this.getElmt(j, k) - factor * this.getElmt(i, k));
                    }
                }
            }
        }

        // Memindahkan baris dengan semua elemen nol ke bawah
        for (int i = 0; i < n; i++) {
            boolean allZero = true;
            for (int j = 0; j < m - 1; j++) {
                if (this.getElmt(i, j) != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero) {
                for (int k = i; k < n - 1; k++) {
                    this.rowSwap(k, k + 1);
                }
            }
        }

        // Normalisasi baris pivot jika diperlukan
        for (int i = 0; i < n; i++) {
            int pivotCol = 0;
            while (pivotCol < m - 1 && this.getElmt(i, pivotCol) == 0) {
                pivotCol++;
            }

            if (pivotCol == m - 1) {
                continue;
            }

            double pivotVal = this.getElmt(i, pivotCol);
            if (pivotVal != 1 && pivotVal != 0) {
                for (int k = pivotCol; k < m; k++) {
                    this.setElmt(i, k, this.getElmt(i, k) / pivotVal);
                }
            }
        }
        return this;
    }

}

    