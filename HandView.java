import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HandView {
    JFrame frame;
    Hand hand;

    public void startHandGUI(JFrame f, Hand h){
        frame = f;
        hand = h;
        //JLabel testLabel = new JLabel("HAND GOES HERE");
        //testLabel.setFont(new Font("Serif", Font.PLAIN, 48));
        //frame.getContentPane().add(BorderLayout.SOUTH, testLabel);

        JPanel handPanel = new JPanel();
        handPanel.setLayout(new BoxLayout(handPanel, BoxLayout.X_AXIS));
        frame.add(handPanel);

        for(int i = 0; i < h.getSize(); i++){
            JButton button = new JButton(String.valueOf(hand.getLetter(i).getLetter()));
            button.setFont(new Font("Serif", Font.PLAIN, 24));
            handPanel.add(button);
        }

        JPanel handContainer = new JPanel();
        handContainer.setLayout(new GridBagLayout());
        handContainer.add(handPanel);

        frame.getContentPane().add(BorderLayout.SOUTH, handContainer);
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
