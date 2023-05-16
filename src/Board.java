import Pieces.Piece;

import javax.swing.JPanel;
import java.awt.*;

public class Board extends JPanel {
    private final Color whiteSquareColor, blackSquareColor;
    private final Color whiteHighlighted, blackHighlighted;
    private final int squareSize;
    private final Square[][] squares;
    private Piece[][] pieces;

    public Board(int squareSize, Piece[][] pieces, Mouse mouse) {
        whiteSquareColor = new Color(0xf0d9b5);
        blackSquareColor = new Color(0xb58863);
        whiteHighlighted = new Color(0x829769);
        blackHighlighted = new Color(0x646f40);

        this.pieces = pieces;

        this.squareSize = squareSize;

        setLayout(new GridLayout(8, 8));
        addMouseListener(mouse);

        squares = new Square[8][8];
        for(int j = 0; j < 8; j++) {
            for(int i = 0; i < 8; i++) {
                Square square = new Square((i + j) % 2 == 0 ? whiteSquareColor : blackSquareColor, squareSize);
                add(square);
                squares[i][j] = square;
            }
        }
    }

    public void touchPiece(Piece piece) {
        //do nothing yet
    }

    public void refresh(Point[] highlighted, int mode) {
        Color colorA, colorB;
        if(mode == 0) {
            colorA = whiteHighlighted;
            colorB = blackHighlighted;
        }
        else {
            colorA = whiteSquareColor;
            colorB = blackSquareColor;
        }
        for(Point point : highlighted) {
            squares[point.x][point.y].setBackground((point.x + point.y) % 2 == 0 ? colorA : colorB);
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(pieces[i][j] != null) {
                    g2d.drawImage(pieces[i][j].getIcon(), i * squareSize, j * squareSize, squareSize, squareSize, null);
                }
            }
        }
    }
}
