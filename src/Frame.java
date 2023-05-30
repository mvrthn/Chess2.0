package Chess;

import javax.swing.JFrame;

public class Frame extends JFrame {
    public Frame() {
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(100, 100);
        setVisible(true);
    }

    public void addBoard(Board board) {
        add(board);
        pack();
    }
}
