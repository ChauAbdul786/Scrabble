import javax.swing.*;
import java.awt.*;

public class PlayScrabbleView {
    JFrame frame;

    public void startGUI(JFrame f){
        frame = f;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //DrawPanel northDp = new DrawPanel("Images\\Logos\\scrabblelogo1900.png");
        //frame.getContentPane().add(BorderLayout.CENTER, northDp);

        //JLabel handLabel = new JLabel("HAND GOES HERE");
        //handLabel.setFont(new Font("Serif", Font.PLAIN, 48));
        //frame.getContentPane().add(BorderLayout.SOUTH, handLabel);

        JLabel scoreLabel = new JLabel("SCORES GO HERE");
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        frame.getContentPane().add(BorderLayout.EAST, scoreLabel);

        DrawPanel centerDp = new DrawPanel("Images\\Boards\\Boardver1.PNG");
        frame.getContentPane().add(BorderLayout.CENTER, centerDp);
        
        frame.setSize(750, 750);
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
