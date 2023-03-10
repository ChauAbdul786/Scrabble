import javax.swing.*;
import java.awt.*;

public class PlayScrabbleView {
    JFrame frame;

    public void startGUI(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DrawPanel northDp = new DrawPanel("Images\\Logos\\scrabblelogo1900.png");
        frame.getContentPane().add(BorderLayout.NORTH, northDp);

        DrawPanel centerDp = new DrawPanel("Images\\Boards\\Boardver1.PNG");
        frame.getContentPane().add(BorderLayout.CENTER, centerDp);
        
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

    public class DrawPanel extends JPanel{
        Image image;

        public DrawPanel(String s){
            image = new ImageIcon(s).getImage();
        }

        public void paintComponent(Graphics g){
            Graphics g2d = (Graphics2D)g;
            g2d.drawImage(image, 0, 0, frame);
        }
    }
}
