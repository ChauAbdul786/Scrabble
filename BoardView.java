import javax.swing.*;
import java.awt.*;

public class BoardView {
    JFrame frame;
    Board board;

    public void startBoardGUI(JFrame f, Board b, int[][] m){
        frame = f;
        board = b;
        
        JPanel buttonPanel = new JPanel();

        JButton gridSpace;
        buttonPanel.setLayout(new GridLayout(15, 15));
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                gridSpace = new JButton("");
                gridSpace.setPreferredSize(new Dimension(50, 50));

                if(i == 7 && j == 7){
                    gridSpace.setText(".");
                    gridSpace.setFont(new Font("Serif", Font.BOLD, 28));
                }

                if(m[i][j] != 1){
                    gridSpace.setText("x" + String.valueOf(m[i][j]));
                    gridSpace.setFont(new Font("#GungSeo", Font.ITALIC, 10));
                }

                buttonPanel.add(gridSpace);
            }
        }

        JPanel boardContainer = new JPanel();
        boardContainer.setLayout(new GridBagLayout());
        boardContainer.add(buttonPanel);

        frame.getContentPane().add(BorderLayout.CENTER, boardContainer);
        frame.setVisible(true);
    }
}
