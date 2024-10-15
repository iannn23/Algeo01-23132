package fungsi;
import fungsi.Matrix;
public class gauss {

    public static double[][] ubahkegauss(double[][] m) {
        int nRow = m.length;
        int nCol = m[0].length;
        System.out.println("Matrix augmented : ");
        for (double[] row : m) {
            for (double val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        
        for (int i = 0; i < nRow; i++) {
            for (int j = i + 1; j < nRow; j++) {
                if (m[j][i] == 0) {
                    continue;
                }
                double factor = m[j][i] / m[i][i];
                for (int k = 0; k < nCol; k++) {
                    m[j][k] = m[j][k] - m[i][k] * factor;
                }
            }
        }
        
        return m;
    }
    public double[][] sortMatrixRow(double[][] m){
        // initalize indexRow array
        int[] indexRow = new int[100];
        for(int i=0;i<nRow;i++){
            indexRow[i]=i;


        // sort indexrow array with bubble sort
        for(int i=0;i<nRow;i++){
            for(int j=0;j<nRow-i-1;j++){
                int firstRow=indexRow[j];
                int secRow=indexRow[j+1];
                if(this.notZeroElmt[firstRow]<=this.notZeroElmt[secRow])continue;
                indexRow[j]=secRow;
                indexRow[j+1]=firstRow;
                this.swapCount++;
            }
        }

        // make copy of matrix and notZeroElmt
        double[][] matrixClone =  new double[100][100];
        for(int i=0;i<nRow;i++){
            for(int j=0;j<this.ncol;j++){
                matrixClone[i][j]=this.matrix[i][j];
            }
        }
        int[] notZeroElmtClone = new int[100];
        for(int i=0;i<nRow;i++){
            notZeroElmtClone[i]=this.notZeroElmt[i];
        }

        // update matrix and notZeroElmt array based on sorted indexRow
        for(int i=0;i<nRow;i++){
            this.notZeroElmt[i]=notZeroElmtClone[indexRow[i]];
            for(int j=0;j<nC;j++){
                this.matrix[i][j]=matrixClone[indexRow[i]][j];
            }
        }
    }
    return
    }
}
