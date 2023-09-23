package geometry;

import math.UVN4x4;
import math.Vector4;

public class Camera {

    Vector4 lookAt;
    Vector4 cameraPosition;
    Vector4 up;
    public double theta;
    public double phi;
    public double radius = 1000;
    public static double DELTA_THETA = 10 * Math.PI / 180;
    public static double DELTA_PHI = 10 * Math.PI / 180;
    public static double DELTA_RADIUS = 25;
    public static boolean DEBUG = true;

    public Camera(Vector4 lookAt, Vector4 cameraPosition, Vector4 up) {
        this.lookAt = lookAt;
        this.cameraPosition = cameraPosition;
        this.up = up;
    }
    
    public Camera() {
        // Asignarle valor al atributo lookAt
        lookAt = new Vector4(0,0,0);
        // Asignarle valor al atributo cameraPosition con base en los atributos theta, phi y radius
        cameraPosition = new Vector4(theta,phi,radius);
        // Asignarle valor al atributo up
        up = new Vector4(0, 1, 0);
    }

    public UVN4x4 createUVN() {
        // Asignarle valor al atributo cameraPosition con base en los atributos theta, phi y radius
        cameraPosition = new Vector4(theta,phi,radius);
        // Crear un objeto de tipo UVN4x4 con los atributos cameraPosition, lookAt y up
        UVN4x4 result = new UVN4x4(cameraPosition, lookAt, up);
        // Retornar el objeto UVN4x4
        return result;
    }
}