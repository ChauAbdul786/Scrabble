import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HandView {
    JFrame frame;

    public void startHandGUI(JFrame f, Hand h){
        frame = f;
        //JLabel testLabel = new JLabel("HAND GOES HERE");
        //testLabel.setFont(new Font("Serif", Font.PLAIN, 48));
        //frame.getContentPane().add(BorderLayout.SOUTH, testLabel);

        JPanel handPanel = new JPanel();
        handPanel.setLayout(new BoxLayout(handPanel, BoxLayout.X_AXIS));
        frame.add(handPanel);

        for(int i = 0; i < h.getSize(); i++){
            JButton button = new JButton(String.valueOf(h.getLetter(i).getLetter()));
            handPanel.add(button);
        }

        frame.getContentPane().add(BorderLayout.SOUTH, handPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public class DrawPanel extends JPanel{
        public DrawPanel(){

        }

        public void paintComponent(){

        }
    }
}
