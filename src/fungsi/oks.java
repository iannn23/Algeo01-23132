package fungsi;

public class oks {
    package fungsi;

public class gauss {

    public static double[][] ubahkegauss(double[][] m){
        int i,j,k;
        int nRow = m.length;
        int nCol = m[0].length;
        System.out.println("Matrix augmented : ");
        System.out.println(m);
        for (i=0;i<nRow;i++){
            for (j=i+1;j<nCol;j++){
                if (m[j][i]==0){
                    continue;
                }
                double factor=m[i][i]/m[i][j];
                for(k=0;k<nCol;k++ ){
                    m[j][k]=m[i][k]-m[j][k]*factor;
                }
            }
        }
    
        
        return m;
    }
    
}

    
}
