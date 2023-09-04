
/**
 * Clase para dibujar algunos puntos.
 * 
 * @author Helmuth Trefftz
 * @version Septiembre 2016
 */

import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;
import javax.swing.JFrame;
    
public class Puntos extends JPanel
{
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int LINEA1 = 150;
    public static final int LINEA2 = 50;

    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);

      //Graphics2D g2d = (Graphics2D) g;



        drawAxis(g);

      brensenham(g, 0,0,LINEA1,LINEA2);
      brensenham(g, 0,0,LINEA2,LINEA1);
      brensenham(g, 0,0,LINEA1,-LINEA2);
      brensenham(g, 0,0,LINEA2,-LINEA1);
      brensenham(g, 0,0,-LINEA1,LINEA2);
      brensenham(g, 0,0,-LINEA2,LINEA1);
      brensenham(g, 0,0,-LINEA1,-LINEA2);
      brensenham(g, 0,0,-LINEA2,-LINEA1);      
      

      



       


  }

  public void drawAxis(Graphics g) {
    g.setColor(Color.red);
    myDrawLine(g, -500, 0, 500, 0);
    g.setColor(Color.blue);
    myDrawLine(g, 0, -500, 0, 500);
  }

  public void myDrawPoint(Graphics g, int x, int y) {
    int xj = x + WIDTH/2;
    int yj = HEIGHT/2 - y;
    g.drawLine(xj, yj, xj, yj);
  }
  private void brensenham(Graphics g, int x1, int y1, int x2, int y2) {
    g.setColor(Color.magenta);

    // delta of exact value and rounded value of the dependent variable
    int d = 0;

    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);

    int dx2 = 2 * dx; // slope scaling factors to
    int dy2 = 2 * dy; // avoid floating point

    int ix = x1 < x2 ? 1 : -1; // increment direction
    int iy = y1 < y2 ? 1 : -1;

    int x = x1;
    int y = y1;

    if (dx >= dy) {
        while (true) {
            myDrawPoint(g, x, y);
            if (x == x2)
                break;
            x += ix;
            d += dy2;
            if (d > dx) {
                y += iy;
                d -= dx2;
            }
        }
    } else {
        while (true) {
            myDrawPoint(g, x, y);
            if (y == y2)
                break;
            y += iy;
            d += dx2;
            if (d > dy) {
                x += ix;
                d -= dy2;
            }
        }
    }
}

  public void myDrawLine(Graphics g, int x1, int y1, int x2, int y2) {
    int xj1 = x1 + WIDTH/2;
    int yj1 = HEIGHT/2 - y1;
    int xj2 = x2 + WIDTH/2;
    int yj2 = HEIGHT/2 - y2;
    g.drawLine(xj1, yj1, xj2, yj2);
  }



  public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Puntos");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      frame.add(new Puntos());
      // Asignarle tamaño
      frame.setSize(640, 480);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
  }
    
}

