import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuView implements ActionListener {
    JFrame frame;
    volatile int playerSelection = -1;

    public void startMainMenuGUI(JFrame f){
        frame = f;
        playerSelection = -1;

        frame.getContentPane().removeAll();

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

        JPanel playerSelectionPanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(playerSelectionPanel, BoxLayout.X_AXIS));

        JButton button;
        for(int i = 1; i < 5; i++){
            button = new JButton(String.valueOf(i) + " player");
            button.addActionListener(this);
            playerSelectionPanel.add(button);
        }

        frame.getContentPane().add(BorderLayout.WEST, playerSelectionPanel);

        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){
        JButton button = (JButton)e.getSource();
        String text = button.getText();
        switch(text){
            case "1 player":
                playerSelection = 1;
                break;
            case "2 player":
                playerSelection = 2;
                break;
            case "3 player":
                playerSelection = 3;
                break;
            case "4 player":
                playerSelection = 4;
                break;
            default:
                playerSelection = -1;
                break;
        }
    }

    public int getPlayerSelection(){
        return playerSelection;
    }
}
