package MathForGP;
public class Matrix4x4 {
    public double matriz[][] = new double[4][4];

    public static Point4 times(Matrix4x4 matriz, Point4 punto){
        Point4 resultado = new Point4();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
            resultado.punto[i] += matriz.matriz[i][j]*punto.punto[j];
            }
        }
        return resultado;
    }
    public static Point4 timesDiferente(Matrix4x4 matriz,Point4 punto){
        Point4 resultado = new Point4();
         resultado.x = matriz.matriz[0][0] * punto.x + matriz.matriz[0][1] * punto.y + matriz.matriz[0][2] * punto.z + matriz.matriz[0][3] * punto.w;
         resultado.y = matriz.matriz[1][0] * punto.x + matriz.matriz[1][1] * punto.y + matriz.matriz[1][2] * punto.z + matriz.matriz[1][3] * punto.w;
         resultado.z = matriz.matriz[2][0] * punto.x + matriz.matriz[2][1] * punto.y + matriz.matriz[2][2] * punto.z + matriz.matriz[2][3] * punto.w;
         resultado.w = matriz.matriz[3][0] * punto.x + matriz.matriz[3][1] * punto.y + matriz.matriz[3][2] * punto.z + matriz.matriz[3][3] * punto.w;
        return resultado;
    }
    public static Matrix4x4 times(Matrix4x4 matriz1,Matrix4x4 matriz2){
        Matrix4x4 resultado = new Matrix4x4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k=0;k<4;k++) { 
                    resultado.matriz[i][j]+= (matriz1.matriz[i][k] * matriz2.matriz[k][j]);    
                }
            }
    
        }
    
        return resultado;
    }
    public void printMatrix(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.println();
        }


    }
     
    
    
}
