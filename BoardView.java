import javax.swing.*;
import java.awt.*;

public class BoardView {
    JFrame frame;
    Board board;

    public void startBoardGUI(JFrame f, Board b){
        frame = f;
        board = b;
        
        JPanel buttonPanel = new JPanel();
        JPanel container = new JPanel();

        JButton gridSpace;
        buttonPanel.setLayout(new GridLayout(15, 15));
        for(int i = 0; i < 15 * 15; i++){
                gridSpace = new JButton("");
                gridSpace.setPreferredSize(new Dimension(50, 50));
                buttonPanel.add(gridSpace);
        }

        container.add(buttonPanel);
        frame.getContentPane().add(BorderLayout.CENTER, container);
        frame.setVisible(true);
    }
}
