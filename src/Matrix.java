class Matrix {
    double[][] mat;
    int nRow, nCol;
    
    //Matriks
    public Matrix(int row, int column) {
        nRow = row;
        nCol = column;
        mat = new double[nRow][nCol];
    }

    //Memasukkan nilai ke matriks
    public void inputELMT(int i, int j, double val) {
        this.mat[i][j] = val;
    }
    //Mendapatkan nilai indeks matriks
    public double retrieveELMT(int i, int j) {
        return this.mat[i][j];
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