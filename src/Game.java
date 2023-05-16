import Pieces.Piece;
import Pieces.PieceFactory;

import java.awt.*;

public class Game {
    private final Board board;
    private Piece[][] pieces;
    private final Mouse mouse;
    private boolean whiteOnMove;
    private boolean[] castleAvailable;
    private Point enPassant;
    private int halfMoveClock;
    private int moveCount;
    private Move move;

    public Game(String fen) {
        int squareSize = 80;

        pieces = new Piece[8][8];
        loadFEN(fen);

        move = new Move(this, pieces, whiteOnMove);

        mouse = new Mouse(move, squareSize);

        board = new Board(squareSize, pieces, mouse);
    }

    public Game() {
        this("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    public Board getBoard() {
        return board;
    }

    public void makeMove(Point startPos, Point endPos) {
        board.repaint();
    }

    public void loadFEN(String fen) {
        PieceFactory factory = new PieceFactory();
        String[] split = fen.split(" ", 6);

        int i = 0, j = 0;
        for(char c : split[0].toCharArray()) {
            if(c >= '1' && c <= '8') {
                i += c - '0';
            }
            else if(c == '/') {
                j++;
                i = 0;
            }
            else {
                pieces[i][j] = factory.createPiece(c);
                i++;
            }
        }

        whiteOnMove = split[1].equals("w");

        castleAvailable = new boolean[4];
        if(!split[2].equals("-")) {
            for(char c : split[2].toCharArray()) {
                if(c == 'K')  castleAvailable[0] = true;
                else if(c == 'Q')  castleAvailable[1] = true;
                else if(c == 'k')  castleAvailable[2] = true;
                else if(c == 'q')  castleAvailable[3] = true;
                else throw new IllegalStateException("Unexpected value: " + c);
            }
        }

        if(!split[3].equals("-")) {
            enPassant = new Point(split[3].charAt(0) - 'a', split[3].charAt(1) - '0');
        }

        halfMoveClock = Integer.parseInt(split[4]);

        moveCount = Integer.parseInt(split[5]);
    }
}
