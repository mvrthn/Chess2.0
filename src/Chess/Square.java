package Chess;

import javax.swing.JPanel;
import java.awt.*;

public class Square extends JPanel {
    public Square(Color color, int size) {
        setBackground(color);
        setPreferredSize(new Dimension(size, size));
    }
}