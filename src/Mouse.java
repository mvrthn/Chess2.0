import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
    private final Move move;
    private final int squareSize;
    private final int boardSize;

    public Mouse(Move move, int squareSize) {
        this.move = move;
        this.squareSize = squareSize;
        boardSize = 8 * squareSize;
    }

    public Point getPos() {
        return new Point(this.getPos());
    }

    private boolean eventInBounds(MouseEvent e) {
        return e.getX() >= 0 && e.getX() <= boardSize && e.getY() >= 0 && e.getY() <= boardSize;
    }

    @Override
    public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1 && eventInBounds(e)) {
            move.mousePressed(new Point(e.getX() / squareSize, e.getY() / squareSize));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 && eventInBounds(e)) {
            move.mouseReleased(new Point(e.getX() / squareSize, e.getY() / squareSize));
        }
    }
}