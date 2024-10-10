class Matrix {
    double[][] mat;
    int nRow, nCol;
    
    public Matrix(int row, int column) {
        nRow = row;
        nCol = column;
        mat = new double[nRow][nCol];
    }
}