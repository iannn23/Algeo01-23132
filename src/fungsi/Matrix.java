package fungsi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    //jika jumlah baris dan kolom bervariasi
    public Matrix() {
        this(0,0);
    }

    public void inputRow(){
        System.out.println("Masukkan jumlah baris: ");
        Scanner sc = new Scanner(System.in);
        nRow = sc.nextInt();
    }

    public void inputCol(){
        System.out.println("Masukkan jumlah kolom: ");
        Scanner sc = new Scanner(System.in);
        nCol = sc.nextInt();
    }
    
    // Memasukkan nilai ke matriks secara manual (dari input)
    public static Matrix readMat() {
        int i, j;
        int nRow, nCol;
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah baris: ");
        nRow = sc.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        nCol = sc.nextInt();

        Matrix matriks = new Matrix(nRow, nCol);
        System.out.println("Masukkan elemen matriks: ");
        for (i = 0; i < nRow; i++) {
            for (j = 0; j < nCol; j++) {
                matriks.mat[i][j] = sc.nextDouble();
            }
        }
        return matriks;
    }
    public void readMat2() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                mat[i][j] = sc.nextDouble();
            }
        }
        sc.nextLine();
        sc.close();
    }
    public static Matrix readMatSPL() {
        int i, j;
        int nRow, nCol;
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah baris: ");
        nRow = sc.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        nCol = sc.nextInt();

        Matrix matriksA = new Matrix(nRow, nCol);
        System.out.println("Masukkan elemen matriks A: ");
        for (i = 0; i < nRow; i++) {
            for (j = 0; j < nCol; j++) {
                matriksA.mat[i][j] = sc.nextDouble();
            }
        }

        Matrix matriksB = new Matrix(nRow, 1);
        System.out.println("Masukkan elemen matriks B: ");
        for (i = 0; i < nRow; i++) {
            for (j = 0; j < 1; j++) {
            matriksB.mat[i][0] = sc.nextDouble();
            }
        }

        Matrix m = new Matrix (nRow, nCol+1);
        for (i = 0; i < nRow; i++) {
            for (j = 0; j < nCol; j++) {
                m.mat[i][j] = matriksA.mat[i][j];
            }
            m.mat[i][nCol] = matriksB.mat[i][0];
        }
        return m;
    }

    public static Matrix readFile() {
        Matrix mat = new Matrix();
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan nama file teks: ");
        String fileName = sc.nextLine();
        mat.readMatFile(fileName);
        sc.close();
        return mat;
    } 
    // Memasukkan nilai ke matriks dari file
    public void readMatFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader("test/"+fileName+".txt"))) {
            String line;
            int colCount = 0;
            int rowCount = 0;

            while ((line=br.readLine()) != null) {
                String[] values = line.trim().split("\\s+");
                colCount = Math.max(colCount, values.length);
                rowCount++;
            }

            nRow = rowCount;
            nCol = colCount;
            mat = new double[nRow][nCol];

            try (BufferedReader lines = new BufferedReader(new FileReader("test/"+fileName+".txt"))) {
                int currRow = 0;
                while ((line=lines.readLine()) != null) {
                    String[] values = line.trim().split("\\s+");
                    for (int j=0; j<values.length; j++) {
                        mat[currRow][j] = Double.parseDouble(values[j]);
                    }
                    currRow++;
                }
            } catch (IOException e) {
                System.out.println("Terdapat error saat membuka file: "+e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Terdapat error saat membuka file: "+e.getMessage());
            
        }
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
    public int getLastIdxCol(){
        return nCol-1;
    }
    public double getElmt(int i, int j) {
        return mat[i][j];
    }
    public static Matrix multiplyMatrix(Matrix m1, Matrix m2){
		Matrix mHasil = new Matrix(m1.getRowLength(), m2.getColLength());
		int row,col,i;	
		for(row = 0; row< m1.getRowLength(); row++){
			for(col = 0; col< m2.getColLength(); col++){
				double hasil = 0;
				for(i = 0; i< m2.getRowLength(); i++){
					hasil += m1.getElmt(row,i)*m2.getElmt(i, col);
				}
				mHasil.setElmt(row, col, hasil);
			}
		}
		return mHasil;
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

    // Mendapat matriks A dari matriks augmented
    public Matrix matrixA() {    
        Matrix A = new Matrix(nRow, nCol - 1);
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol - 1; j++) { // Kecuali kolom terakhir
                A.mat[i][j] = mat[i][j];
            }
        }
        return A;
    }
    // Mendapat matriks b dari matriks augmented
    public Matrix matrixb() {
        Matrix b = new Matrix(nRow, 1);
        for (int i = 0; i < nRow; i++) {
            b.mat[i][0] = mat[i][nCol - 1];
        }
        return b;
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
    public static Matrix konkatMatrix(Matrix m1, Matrix m2) {
		Matrix mHasil = new Matrix(m1.getRowLength(),m1.getColLength()+m2.getColLength());
		int i,j;
		for(i = 0; i<m1.getRowLength();i++) {
			for(j=0; j<m1.getColLength();j++) {
				mHasil.setElmt(i, j, m1.getElmt(i, j));
			}
		}
		
		for(i = 0; i<m1.getRowLength();i++) {
			int currCol = 0;
			for(j= m1.getColLength(); j<mHasil.getColLength();j++) {
				mHasil.setElmt(i, j, m2.getElmt(i, currCol));
				currCol += 1;
			}
		}
		return mHasil;
	}
    public Matrix getMatrixConstant() {
		Matrix mOut = new Matrix (this.getRowLength(),1);
		int row;
		for (row = 0 ; row < mOut.getRowLength() ; ++row) {
			mOut.setElmt(row, 0, this.getElmt(row, this.getLastIdxCol()));
		}
		return mOut;
		
	}
    public Matrix getMatrixCoefficient() {
		Matrix mOut = new Matrix(this.getRowLength(),this.getColLength() - 1);
		int row,col;
		for (row = 0 ; row < this.getRowLength() ; ++row) {
			for (col = 0 ; col < this.getColLength(); ++col) {
				if (col != this.getLastIdxCol()) {
					mOut.setElmt(row, col, this.getElmt(row, col));
				}
			}
		}
		return mOut;
	}
    public Matrix inverseGab() {
		Matrix mInvers = this.getMatrixCoefficient();
		Matrix mConstant = this.getMatrixConstant();
		mInvers = mInvers.inversOBE();
		Matrix mOut = multiplyMatrix(mInvers, mConstant);
		return mOut;
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
    public void banyakSolusi() {
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

    public int bentukSolusi() {
        int i; 
        int j;
        int n; 
        int m;
        int x = 0;
        int kolomaug;
        boolean Baristidaknol = false;
        
        n = this.getRowLength();
        m = this.getColLength();
        kolomaug = m - 1;

        for (i = 0; i < n; i++) {
            boolean Barisnol= true;
            for (j = 0; j < kolomaug; j++) {
                if (this.getElmt(i, j) != 0) {
                    Barisnol = false;
                    break;
                }
            }
            if (!Barisnol) {
                x++;
                Baristidaknol = true;
            } else if (this.getElmt(i, kolomaug) != 0) {
                return 0;
            }
        }
        if (!Baristidaknol || x < kolomaug) {
            return 2;
        } else {
            return 1;
        }
    }
    // Substitusi mundur
    public void bentuksegitiga(double[] X) {
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
        if (this.nRow == this.nCol) {
            if (this.nRow == 1) return this.retrieveELMT(0, 0);

            double det = 0.0;
            int plusMinus = 1;
            for (int j = 0; j < nCol; j++) {
                if (this.retrieveELMT(0, j) != 0) {
                    Matrix subMat = new Matrix(nRow - 1, nCol - 1);
                    this.getCofactor(subMat, 0, j, nRow);
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

    // Aturan Cramer (NOTE: PAKAI MATRIKS AUGMENTED, jangan matriks A langsung)
    public Matrix cramer() {
        Matrix A = this.matrixA();
        Matrix b = this.matrixb();

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
    public Matrix gaussEliminasi() {
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
        if (n > m ) {
            this.banyakSolusi();
        } else {
            bentuksegitiga(X);
        }
        return this;
    }
    // Eliminasi Gauss-Jordan
    public Matrix gaussJordanEliminasi() {
        int n = this.getRowLength();
        int m = this.getColLength();

        for (int i = 0; i < n; i++) {
            // Cari pivot non-nol pertama dalam kolom
            int pivot_baris = i;
            while (pivot_baris < n && this.getElmt(pivot_baris, i) == 0) {
                System.out.println(pivot_baris);
                pivot_baris++;
            }

            // Pivot ketemu
            if (pivot_baris == n) {
                continue;
            }

            // Tukar baris pivot dengan baris saat ini
            if (pivot_baris != i) {
                this.rowSwap(i, pivot_baris);
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
            boolean kosong = true;
            for (int j = 0; j < m - 1; j++) {
                if (this.getElmt(i, j) != 0) {
                    kosong = false;
                    break;
                }
            }
            if (kosong) {
                for (int k = i; k < n - 1; k++) {
                    this.rowSwap(k, k + 1);
                }
            }
        }

        // Normalisasi baris pivot jika diperlukan
        for (int i = 0; i < n; i++) {
            int pivot_kolom = 0;
            while (pivot_kolom < m - 1 && this.getElmt(i, pivot_kolom) == 0) {
                pivot_kolom++;
            }

            if (pivot_kolom == m - 1) {
                continue;
            }

            double pivotVal = this.getElmt(i, pivot_kolom);
            if (pivotVal != 1 && pivotVal != 0) {
                for (int k = pivot_kolom; k < m; k++) {
                    this.setElmt(i, k, this.getElmt(i, k) / pivotVal);
                }
            }
        }
        return this;
    }


    public void getCofactor (Matrix temp, int p, int q, int n){
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp.setElmt(i, j++, getElmt(row, col));
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }


    }
    public Matrix cofMatrix(){
        Matrix cofactor = new Matrix(nRow, nCol);
        Matrix temp = new Matrix(nRow-1, nCol-1);
        for (int i=0; i<nRow; i++){
            for (int j=0; j<nCol; j++){
               this.getCofactor(temp, i, j, nRow);
               double cofactorVal = Math.pow(-1, i+j) * temp.determinantCof();
               cofactor.setElmt(i, j, cofactorVal);
            }
        }
        return cofactor;
    }
    public Matrix inversOBE (){
        Matrix mat_inv = new Matrix(this.getRowLength(), this.getColLength()*2);
        int i;
        int j;
        int k;
		// mengisi matriks dengan matriks identitas dan matriks input
		for (i = 0; i < this.getRowLength(); i++){
			for (j = 0; j < this.getColLength(); j++){
				if (i == j)
                {
					mat_inv.setElmt(i, j+this.getColLength(), 1);
				}
				else
                {
					mat_inv.setElmt(i, j+this.getColLength(), 0);
				}
				mat_inv.setElmt(i, j, this.getElmt(i,j));
			}
		}
		// mengeloop sehingga mendapatkan invers
		for (i = 0; i < mat_inv.getRowLength(); i++){
			for (j = 0; j < mat_inv.getRowLength(); j++){
				if (i != j){
					k = (i + 1);
					if (mat_inv.getElmt(i,i) == 0){
						while (k < mat_inv.getRowLength()){
							if (mat_inv.getElmt(k,i) != 0){
								// menukar baris
								mat_inv.rowSwap(i, k);
							}
							k++;
						}
					}

					double sub = -1 * mat_inv.getElmt(j, i) / mat_inv.getElmt(i, i);
					for (k = i; k < mat_inv.getColLength(); k++){
						mat_inv.setElmt(j, k, (mat_inv.getElmt(j,k)+sub*mat_inv.getElmt(i,k)));
					}
				}
			}
		}

		for (i = 0; i < mat_inv.getRowLength(); i++){
			double buatbagi = mat_inv.getElmt(i,i);
			for (j = 0; j < mat_inv.getColLength(); j++){
				if (mat_inv.getElmt(i,j) != 0){
					mat_inv.setElmt(i, j, mat_inv.getElmt(i,j) / buatbagi);
				}
			}
		}

		for (i = 0; i < this.getRowLength(); i++){
			for (j = 0; j < this.getColLength(); j++){
				this.setElmt(i, j, (mat_inv.getElmt(i, j+this.getColLength())));
			}
		}
		return this;
	}
    public Matrix adjoint(){
        Matrix cofactor = cofMatrix();
        Matrix adjugate = new Matrix(nRow, nCol);
        for (int i=0; i<nRow; i++){
            for (int j=0; j<nCol; j++){
                adjugate.setElmt(i, j, cofactor.getElmt(j, i));
            }
        }
        return adjugate;
    }
    public Matrix inversadj(){
        //kalau determinannya == 0  error
        double det = determinantCof();
        if  (det == 0){
            System.out.println("Determinan = 0 ");
            return null;
        }
        Matrix mat_inv = new Matrix(nRow, nCol);
        Matrix adj = adjoint();

        for (int i=0; i<nRow; i++){
            for (int j=0; j<nCol; j++){
                mat_inv.setElmt(i, j, adj.getElmt(i, j) / det);
            }
        }
        return mat_inv;
    }
    
    public static double[] gaussNoText(Matrix mat){
        double[] failRet = {4, 20};
        if(mat.getRowLength() < mat.getColLength()-1) return failRet;
        else {
            mat = mat.gaussEliminasi();
            int rowNonZero = mat.getRowLength();
            boolean rowZero; boolean rowAnomaly = false;

            for(int i=0;i<=mat.getRowLength()-1;i++){
                rowZero = true;
                for(int j=0;j<=mat.getColLength()-1;j++){
                    if((j == mat.getColLength()-1) && (mat.getElmt(i, j) != 0)) rowAnomaly = true;
                    if(mat.getElmt(i, j) != 0) rowZero = false; break;
                }
                if(rowZero) rowNonZero--;
                if(rowAnomaly) break;
            }

            double[] x,y;
            x = new double[rowNonZero];
            y = new double[rowNonZero];

            if((rowAnomaly) || (rowNonZero<mat.getColLength()-1)) return failRet;
            else {
                x[0] = mat.getElmt(rowNonZero-1, mat.getColLength()-1);
                for(int i=1;i<rowNonZero;i++){
                    x[i] = mat.getElmt(rowNonZero-1-i, mat.getColLength()-1);
                    for(int j=0;j<i;j++){
                        x[i] -= mat.getElmt(rowNonZero-1-i, mat.getColLength()-2-j)*x[j];
                    }
                }
                for(int i=0;i<rowNonZero;i++){
                    y[i] = x[rowNonZero-i-1];
                }
                return y;
            }
        }
    }

    public static void main(String[] args) {
        Matrix m = new Matrix(3,3);
        m.readMat2();
        m.cramer();
        m.printMat();

    }
}
