package MathForGP;
import java.lang.Math;

public class Vector3 {
    public double vector [] = new double[3];
    public double x;
    public double y;
    public double z;

    public static double dotProduct(Vector3 firstVector3, Vector3 secondVector3){
        double resultado = 0;

        for(int i=0;i<3;i++){
            resultado += firstVector3.vector[i]*secondVector3.vector[i];
        }

        return resultado;
    }

    public static Vector3 crossProduct(Vector3 vector1, Vector3 vector2){
        Vector3 resultado = new Vector3();
        resultado.vector[0] = vector1.vector[1] * vector2.vector[2] - vector1.vector[2] * vector2.vector[1];
        resultado.vector[1] = vector1.vector[2] * vector2.vector[0] - vector1.vector[0] * vector2.vector[2];
        resultado.vector[2] = vector1.vector[0] * vector2.vector[1] - vector1.vector[1] * vector2.vector[0];
        return resultado;
    }

    public double Magnitude(){
        double resultado = 0;
        for(int i=0;i<3;i++){
            resultado += Math.pow(this.vector[i], 2);
        }
        resultado = Math.sqrt(resultado);
        return resultado;
    }
    public void Normalize(){
        double magnitud = this.Magnitude();
        for(int i=0;i<3;i++){
            this.vector[i] = this.vector[i]/magnitud ;
        }   
    }
}
