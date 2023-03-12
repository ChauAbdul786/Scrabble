import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HandView implements ActionListener{
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
            button.addActionListener(this);
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

    public void actionPerformed(ActionEvent e){
        JButton button = (JButton)e.getSource();
        if(button.getFont().isBold()){
            button.setFont(new Font("Serif", Font.PLAIN, 24));
        }else{
            button.setFont(new Font("Serif", Font.BOLD, 24));
        }
    }

    public class DrawPanel extends JPanel{
        public DrawPanel(){

        }

        public void paintComponent(){

        }
    }
}
