package MathForGP;
public class Matrix3x3 {
    public double matriz[][] = new double[3][3];



    public static Point3 times(Matrix3x3 matriz,Point3 punto){
        Point3 resultado = new Point3();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
            resultado.punto[i] += matriz.matriz[i][j]*punto.punto[j];
            }
        }
        return resultado;
    }
    public static Point3 timesDiferente(Matrix3x3 matriz,Point3 punto){
        Point3 resultado = new Point3();
        resultado.x = matriz.matriz[0][0]*punto.x + matriz.matriz[0][1]*punto.y + matriz.matriz[0][2]*punto.w;
        resultado.y = matriz.matriz[1][0]*punto.x + matriz.matriz[1][1]*punto.y + matriz.matriz[1][2]*punto.w;
        resultado.w = matriz.matriz[2][0]*punto.x + matriz.matriz[2][1]*punto.y + matriz.matriz[2][2]*punto.w;
        return resultado;
    }    
    public Matrix3x3 times(Matrix3x3 matriz1,Matrix3x3 matriz2){
        Matrix3x3 resultado = new Matrix3x3();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k=0;k<3;k++) { 
                    resultado.matriz[i][j]+= (matriz1.matriz[i][k] * matriz2.matriz[k][j]);    
                }
            }
    
        }
    
        return resultado;
    }
    public void printMatrix(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.println();
        }


    }
    
}
