package MathForGP;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ReadTextFile {
    public static Point3[] puntos;
    public static int[][] lineas;


    public static void readFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int numPoints = scanner.nextInt();
            puntos = new Point3[numPoints];
            for(int i = 0; i <= numPoints-1; i++ ) {
                Point3 point = new Point3();
                point.x = scanner.nextDouble();
                point.y = scanner.nextDouble();
                puntos[i] = point;
                System.out.println("Point " + i + ": (" + puntos[i].x + ", " + puntos[i].y + ")");
            }
            int numLines = scanner.nextInt();
            lineas = new int[numLines][2];
            for(int i = 0; i <= numLines-1; i++ ) {
                lineas[i][0] = scanner.nextInt();
                lineas[i][1] = scanner.nextInt();
                System.out.println("Desde: " + lineas[i][0] + " hasta: " + lineas[i][1]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Point3[] getPuntos(){
        return puntos;
    }
    public static int[][] getLineas(){
        return lineas;
    }
}