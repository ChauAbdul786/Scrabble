import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScoresView implements ActionListener{
    JFrame frame;
    int numPlayers;
    int[] scoreValues;
    JLabel[] scoreStrings;

    public void startScoresGUI(JFrame f, int players){
        frame = f;
        scoreValues = new int[players];
        scoreStrings = new JLabel[players];
        numPlayers = players;

        JPanel labelContainer = new JPanel();
        labelContainer.setLayout(new BoxLayout(labelContainer, BoxLayout.Y_AXIS));

        JLabel label;
        for(int i = 0; i < players; i++){
            scoreValues[i] = 0;
            label = new JLabel("Player " + String.valueOf(i + 1) + " : " + 0);
            label.setFont(new Font("Serif", Font.PLAIN, 20));
            scoreStrings[i] = label;
            labelContainer.add(label);
        }

        JPanel topPanelContainer = new JPanel();
        topPanelContainer.setLayout(new GridBagLayout());
        
        topPanelContainer.add(labelContainer);

        frame.getContentPane().add(labelContainer, BorderLayout.NORTH);

        frame.setVisible(true);

    }

    public void boldPlayerName(int player){
        switch(player){
            case 1:
                scoreStrings[1].setFont(new Font("Serif", Font.BOLD, 20));
                break;
            case 2:
                scoreStrings[1].setFont(new Font("Serif", Font.BOLD, 20));
                break;
            case 3:
                scoreStrings[1].setFont(new Font("Serif", Font.BOLD, 20));
                break;
            case 4:
                scoreStrings[1].setFont(new Font("Serif", Font.BOLD, 20));
                break;
        }
    }

    public void actionPerformed(ActionEvent e){

    }
}
