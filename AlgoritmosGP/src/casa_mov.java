import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import MathForGP.ReadTextFile;
import MathForGP.Matrix3x3;
import MathForGP.Point3;
import java.lang.Math;

public class casa_mov extends JPanel implements KeyListener {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int LINEA1 = 150;
    public static final int LINEA2 = 50;
    public static final double[][] movX =      {{1,0,7.5},{0,1,0},{0,0,1}};
    public static final double[][] movY =      {{1,0,0},{0,1,7.5},{0,0,1}};
    public static final double[][] movminusX = {{1,0,-7.5},{0,1,0},{0,0,1}};
    public static final double[][] movminusY = {{1,0,0},{0,1,-7.5},{0,0,1}};
    public static final double[][] scaleUp = {{2,0,0},{0,2,0},{0,0,1}};
    public static final double[][] scaleDown = {{0.5,0,0},{0,0.5,0},{0,0,1}};
    public static final double[][] rotCounterClockWise = {{Math.cos(0.174533),-Math.sin(0.174533),0},{Math.sin(0.174533),Math.cos(0.174533),0},{0,0,1}};
    public static final double[][] rotClockWise = {{Math.cos(0.174533),Math.sin(0.174533),0},{-Math.sin(0.174533),Math.cos(0.174533),0},{0,0,1}};

    public Point3[] puntos;
    public int[][] lineas;
    public casa_mov(){
      ReadTextFile.readFile("AlgoritmosGP/src/archivo.txt");
      puntos = ReadTextFile.getPuntos();
      lineas = ReadTextFile.getLineas();
      this.setFocusable(true);
      this.requestFocusInWindow();
      this.addKeyListener(this);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAxis(g);
        for(int i=0;i<puntos.length;i++){
          myDrawPoint(g, (int)puntos[i].x, (int)puntos[i].y);
        }
        for(int i=0;i<lineas.length;i++){
          myDrawLine(g, (int)puntos[lineas[i][0]].x, (int)puntos[lineas[i][0]].y, (int)puntos[lineas[i][1]].x, (int)puntos[lineas[i][1]].y);
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
      
    @Override
    public void keyPressed(KeyEvent e) {
      Matrix3x3 matrizmov = new Matrix3x3();
      Point3 center;
      
      int tecla = e.getKeyCode();
      //System.out.println("Key pressed");
      switch(tecla){
        case KeyEvent.VK_D:
          matrizmov.matriz = movX;
          for(int i=0;i<puntos.length;i++){
            puntos[i] = Matrix3x3.timesDiferente(matrizmov, puntos[i]);
          }
          repaint();
          break;
        case KeyEvent.VK_W:
          matrizmov.matriz = movY;
          for(int i=0;i<puntos.length;i++){
            puntos[i] = Matrix3x3.timesDiferente(matrizmov, puntos[i]);
          }
          repaint();
          break;        
        case KeyEvent.VK_S:
          matrizmov.matriz = movminusY;
          for(int i=0;i<puntos.length;i++){
            puntos[i] = Matrix3x3.timesDiferente(matrizmov, puntos[i]);
          }
          repaint();
          break;
        case  KeyEvent.VK_A:
          matrizmov.matriz = movminusX;
          for(int i=0;i<puntos.length;i++){
            puntos[i] = Matrix3x3.timesDiferente(matrizmov, puntos[i]);
          }
          repaint();
          break;
        case KeyEvent.VK_Q:
          matrizmov.matriz = scaleUp;
          center = calcCenter();
          bringPointsToCenter(center);
          for(int i=0;i<puntos.length;i++){
            puntos[i] = Matrix3x3.timesDiferente(matrizmov, puntos[i]);
          }
          returnOriginalPos(center);
          repaint();
          break;
          case KeyEvent.VK_E:
          matrizmov.matriz = scaleDown;
          center = calcCenter();
          bringPointsToCenter(center);
          for(int i=0;i<puntos.length;i++){
            puntos[i] = Matrix3x3.timesDiferente(matrizmov, puntos[i]);
          }
          returnOriginalPos(center);
          repaint();
          break;
          case KeyEvent.VK_LEFT:
            matrizmov.matriz = rotCounterClockWise;
            center = calcCenter();
            bringPointsToCenter(center);
            for(int i=0;i<puntos.length;i++){
              puntos[i] = Matrix3x3.timesDiferente(matrizmov, puntos[i]);
            }
            returnOriginalPos(center);
            repaint();
            break;
            case KeyEvent.VK_RIGHT:
              matrizmov.matriz = rotClockWise;
              center = calcCenter();
              bringPointsToCenter(center);
              for(int i=0;i<puntos.length;i++){
                puntos[i] = Matrix3x3.timesDiferente(matrizmov, puntos[i]);
              }
              returnOriginalPos(center);
              repaint();
              break;
            case KeyEvent.VK_R:
              center = calcCenter();
              bringPointsToCenter(center);
              repaint();
              break;
      }

    }

    public Point3 calcCenter(){
      double centerX = 0;
      double centerY = 0;
      for(int i = 0; i < puntos.length; i++) {
          centerX += puntos[i].x;
          centerY += puntos[i].y;
      }
      centerX /= puntos.length;
      centerY /= puntos.length;
      Point3 resultado = new Point3();
      resultado.x = centerX;
      resultado.y = centerY;
      return resultado;
    }

    public void bringPointsToCenter(Point3 center){
      for (int i = 0; i < puntos.length; i++) {
              puntos[i].x = (puntos[i].x - center.x);
              puntos[i].y = (puntos[i].y - center.y);
      }
  }
      public void returnOriginalPos(Point3 center){
      for (int i = 0; i < puntos.length; i++) {
              puntos[i].x = (puntos[i].x + center.x);
              puntos[i].y = (puntos[i].y + center.y);
      }
  }

    @Override
    public void keyReleased(KeyEvent e) {}
      @Override
    public void keyTyped(KeyEvent e) {}


    public static void main(String [] args) {
        //readFile("Ejemplos/Lineas/archivo.txt");
       // casa_mov casa = new casa_mov();
            
        JFrame frame = new JFrame("Puntos");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new casa_mov());
        // Asignarle tamaño
        frame.setSize(640, 480);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
  
    }

}

