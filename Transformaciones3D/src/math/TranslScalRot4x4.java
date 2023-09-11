package math;

public class TranslScalRot4x4 extends Matrix4x4 {

    double dx = 0;
    double dy = 0;
    double dz = 0;
    double sx = 1;
    double sy = 1;
    double sz = 1;
    double thetaX = 0;
    double thetaY = 0;
    double thetaZ = 0;
    double centerX = 0;
    double centerY = 0;
    double centerZ = 0;
    public static boolean CENTER_TRANFORMS = true;


    public TranslScalRot4x4() {
        super();
    }

    public TranslScalRot4x4(double dx, double dy, double dz,
            double sx, double sy, double sz,
            double thetaX, double thetaY, double thetaZ,
            double centerX, double centerY, double centerZ) {
        super();
         this.dx = dx;  
         this.dy = dy;
         this.dz = dz;
         this.sx = sx;  
         this.sy = sy;  
         this.sz = sz;  
         this.thetaX = thetaX; 
         this.thetaY = thetaY;  
         this.thetaZ = thetaZ;
         this.centerX = centerX;
         this.centerY = centerY;
         this.centerZ = centerZ;
         
         Matrix4x4 traslation = new Translation4x4(dx, dy, dz);
         Matrix4x4 scaling = new Scaling4x4(sx, sy, sz);
         Matrix4x4 rotationX = new RotationX4x4(thetaX);
         Matrix4x4 rotationY = new RotationY4x4(thetaY);
         Matrix4x4 rotationZ = new RotationZ4x4(thetaZ);
 
         if(CENTER_TRANFORMS){
             Matrix4x4 m2 = new Translation4x4(-centerX, -centerY, -centerZ);
             Matrix4x4 m3 = new Scaling4x4(sx, sy, sz);
             Matrix4x4 m4 = new Translation4x4(centerX, centerY, centerZ);
             scaling.matrix = Matrix4x4.times(m4, Matrix4x4.times(m3, m2)).matrix;
             Matrix4x4 m5 = new RotationX4x4(thetaX);
             rotationX.matrix = Matrix4x4.times(m4, Matrix4x4.times(m5, m2)).matrix;
             Matrix4x4 m6 = new RotationY4x4(thetaY);
             rotationY.matrix = Matrix4x4.times(m4, Matrix4x4.times(m6, m2)).matrix;
             Matrix4x4 m7 = new RotationZ4x4(thetaZ);
             rotationZ.matrix = Matrix4x4.times(m4, Matrix4x4.times(m7, m2)).matrix;
         }
 
         Matrix4x4 m1 = Matrix4x4.times(scaling, traslation);
         Matrix4x4 m8 = Matrix4x4.times(rotationZ, m1);
         Matrix4x4 m9 = Matrix4x4.times(rotationY, m8);
         Matrix4x4 finalMatrix = Matrix4x4.times(rotationX, m9);
 
         this.matrix = finalMatrix.matrix;                
    }
    
}           