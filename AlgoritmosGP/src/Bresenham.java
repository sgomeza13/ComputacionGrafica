import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Bresenham {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Bresenham::run);
    }

    private static void run() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setTitle("Bresenham");

        f.getContentPane().add(new BresenhamPanel());
        f.pack();

        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

class BresenhamPanel extends JPanel {

    private final int pixelSize = 1;

    BresenhamPanel() {
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = (getWidth() - 1) / pixelSize;
        int h = (getHeight() - 1) / pixelSize;
        int maxX = (w - 1) / 2;
        int maxY = (h - 1) / 2;
        int x1 = -maxX, x2 = maxX * -2 / 3, x3 = maxX * 2 / 3, x4 = maxX;
        int y1 = -maxY, y2 = maxY * -2 / 3, y3 = maxY * 2 / 3, y4 = maxY;

        drawLine(g, 0, 0, x3, y1); // NNE
        drawLine(g, 0, 0, x4, y2); // ENE
        drawLine(g, 0, 0, x4, y3); // ESE
        drawLine(g, 0, 0, x3, y4); // SSE
        drawLine(g, 0, 0, x2, y4); // SSW
        drawLine(g, 0, 0, x1, y3); // WSW
        drawLine(g, 0, 0, x1, y2); // WNW
        drawLine(g, 0, 0, x2, y1); // NNW
    }

    private void plot(Graphics g, int x, int y) {
        int w = (getWidth() - 1) / pixelSize;
        int h = (getHeight() - 1) / pixelSize;
        int maxX = (w - 1) / 2;
        int maxY = (h - 1) / 2;

        int borderX = getWidth() - ((2 * maxX + 1) * pixelSize + 1);
        int borderY = getHeight() - ((2 * maxY + 1) * pixelSize + 1);
        int left = (x + maxX) * pixelSize + borderX / 2;
        int top = (y + maxY) * pixelSize + borderY / 2;

        g.setColor(Color.black);
        g.drawOval(left, top, pixelSize, pixelSize);
    }

    private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        // delta of exact value and rounded value of the dependent variable
        //plot(g,x1,y1);
        int D = 0;
        int y = 0;
        int x = 0;
        int dx = x2-x1;
        int dy = y2-y1;
        int yi = 1;
        if(dy < 0){
            yi = -1;
            dy = -dy;
        }
        D = (2*dx)-dy;
        y = y1;

        for(int i = x1;i<=x2;i++){
            System.out.println("x: "+ x + " y: " + y);
            plot(g,i,y);
                if(D>0){
                    y = y + yi;
                    D = D + (2*(dy-dx));
                }
                else{
                    D = D + 2*dy;
                }
            
        }
       
    }
}