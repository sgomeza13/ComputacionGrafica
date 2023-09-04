/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *
 * @author htrefftz
 */

 
public class EventoMouse 
extends JPanel
    
    
    implements MouseListener {
    public ArrayList<Integer> clickedPoints = new ArrayList<Integer>();
      //Codigos de region
    public static final int INSIDE = 0; // 0000
    public static final int LEFT = 1; // 0001
    public static final int RIGHT = 2; // 0010
    public static final int BOTTOM = 4; // 0100
    public static final int TOP = 8; // 1000      
    //Puntos del area de clipping (Rectangulo)
    public static final int x_max = 325;
    public static final int x_min = 175;
    public static final int y_max = 250;
    public static final int y_min = 200;
    
  
    
    Line2D.Double linea1;
    
    public EventoMouse() {
        linea1 = new Line2D.Double();
        this.addMouseListener(this);
    }

    @Override
  public void paintComponent(Graphics g) {

       
                    
         
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      Rectangle2D.Double ClippingWindow = new Rectangle2D.Double(175,200,150,50);      
      g2d.draw(ClippingWindow);

      g2d.setColor(Color.BLUE);
      g2d.draw(linea1);

  }
  public int codigoRegion(double x, double y){
            //Define los codigos de la region de clipping        

            int code = INSIDE;

            if (x < x_min) 
                code |= LEFT;
            else if (x > x_max) 
                code |= RIGHT;
            if (y < y_min)
                code |= BOTTOM;
            else if (y > y_max) 
                code |= TOP;
     
            return code;
  }
  public void cohenSutherland(double x1,double x2,double y1,double y2){
          int code_out;

           int code1 = codigoRegion(x1, y1);
           int code2 = codigoRegion(x2, y2);
    
           boolean accept = false;
    
           while (true) {
               if ((code1 == 0) && (code2 == 0)) {
                   // Aceptacion trivial
                   accept = true;
                   break;
               }
               else if ((code1 & code2) != 0) {
                   // Rechazo trivial, ambos puntos estan por fuera del area de clipping
                   break;
               }
               else {
                   // No es trivial, un segmento de la linea trazada esta adentro y otro(s) afuera 
                   double x = 0, y = 0;
    
                   // elige una arista que esta por fuera
                   if (code1 != 0)
                       code_out = code1;
                   else
                       code_out = code2;
    
                   //El punto por fuera esta arriba de la region de clipping
                   if ((code_out & TOP) != 0) {
                       x = x1 + (x2 - x1) * (y_max - y1) / (y2 - y1);
                       y = y_max;
                   }
                   //El punto por fuera esta abajo de la region de clipping
                   else if ((code_out & BOTTOM) != 0) {
                       x = x1 + (x2 - x1) * (y_min - y1) / (y2 - y1);
                       y = y_min;
                   }
                   //El punto por fuera esta a la derecha de la region de clipping
                   else if ((code_out & RIGHT) != 0) {
                       y = y1 + (y2 - y1) * (x_max - x1) / (x2 - x1);
                       x = x_max;
                   }
                  //El punto por fuera esta a la izquierda de la region de clipping
                   else if ((code_out & LEFT) != 0) {
                       y = y1 + (y2 - y1) * (x_min - x1) / (x2 - x1);
                       x = x_min;
                   }
    
                   // Remplazamos el punto y recalculamos el codigo
                   if (code_out == code1) {
                       x1 = x;
                       y1 = y;
                       code1 = codigoRegion(x1, y1);
                   }
                   else {
                       x2 = x;
                       y2 = y;
                       code2 = codigoRegion(x2, y2);
                   }
               }
           }
           if (accept) {
              linea1.x1 = x1;
              linea1.y1 = y1;
              linea1.x2 = x2;
              linea1.y2 = y2;
              repaint();
           }
           else
               System.out.println("Linea por fuera de la region");
  }
    //Espera a que haga click dos veces para llamar al metodo de line clipping
    @Override 
  public void mouseClicked(MouseEvent e) {
      clickedPoints.add(e.getX()); 
      clickedPoints.add(e.getY());
      if(clickedPoints.size() == 4){
        linea1.x1=clickedPoints.get(0);
        linea1.y1=clickedPoints.get(1);
        linea1.x2=clickedPoints.get(2);
        linea1.y2=clickedPoints.get(3);
        clickedPoints.clear();
        cohenSutherland(linea1.x1,linea1.x2,linea1.y1,linea1.y2);
        
      }
      
      
  }
  
    @Override
  public void mouseEntered(MouseEvent e) {}

    @Override
  public void mouseExited(MouseEvent e) {}

    @Override
  public void mousePressed(MouseEvent e) {

  }

    @Override
  public void mouseReleased(MouseEvent e) {

  }

  public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Eventos del Mouse");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      EventoMouse ev = new EventoMouse();
      frame.add(ev);
      //frame.addMouseListener(ev);
      // Asignarle tamaño
      frame.setSize(500, 500);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
  }

    
}
