import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Arrays;

public class Gancho extends JPanel {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int LINEA1 = 150;
    public static final int LINEA2 = 50;



    public static void readFile(String fileName) {


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<int[]> puntos = new ArrayList<int[]>();

        drawAxis(g);

                        try {
            Scanner scanner = new Scanner(new File("Ejemplos/Lineas/archivo.txt"));
            int numPoints = scanner.nextInt();
            for(int i = 1; i <= numPoints; i++ ) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                myDrawPoint(g, x, y);
                int point[] = {x,y};
                puntos.add(point);
                System.out.println("Point " + i + ": (" + x + ", " + y + ")");
            }
            int numLines = scanner.nextInt();
            for(int i = 1; i <= numLines; i++ ) {
                int indice1 = scanner.nextInt();
                int indice2 = scanner.nextInt();
                int x1 = puntos.get(indice1)[0];
                int y1 = puntos.get(indice1)[1];
                int x2 = puntos.get(indice2)[0];
                int y2 = puntos.get(indice2)[1];
                myDrawLine(g, x1,y1,x2,y2);
                System.out.println("Desde: " + indice1 + " hasta: " + indice2);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void drawAxis(Graphics g) {
        g.setColor(Color.red);
        myDrawLine(g, -500, 0, 500, 0);
        g.setColor(Color.blue);
        myDrawLine(g, 0, -500, 0, 500);
      }

      public void myDrawPoint(Graphics g, int x, int y) {
        g.setColor(Color.red);
        int xj = x + WIDTH/2;
        int yj = HEIGHT/2 - y;
        g.drawLine(xj, yj, xj, yj);
      }

      public void myDrawLine(Graphics g, int x1, int y1, int x2, int y2) {
        int xj1 = x1 + WIDTH/2;
        int yj1 = HEIGHT/2 - y1;
        int xj2 = x2 + WIDTH/2;
        int yj2 = HEIGHT/2 - y2;
        g.drawLine(xj1, yj1, xj2, yj2);
      }

    public static void main(String [] args) {
        //readFile("Ejemplos/Lineas/archivo.txt");
        JFrame frame = new JFrame("Puntos");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new Gancho());
        // Asignarle tamaño
        frame.setSize(640, 480);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
  
    }

}