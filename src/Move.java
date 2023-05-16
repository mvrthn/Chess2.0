import Pieces.Piece;

import java.awt.*;

public class Move {
    private final Game game;
    private Piece[][] pieces;
    private Point startPos;
    private Point endPos;
    private boolean whiteOnMove;
    private boolean inMove;

    public Move(Game game, Piece[][] pieces, boolean whiteOnMove) {
        this.game = game;
        this.pieces = pieces;
        this.whiteOnMove = whiteOnMove;
        this.inMove = false;
    }

    private boolean validate() {
        return true;
    }

    private void startMove(Point pos) {
        startPos = pos;
        inMove = true;
    }

    private void attemptMove(Point pos) {
        if(startPos.equals(pos)) {
            inMove = false;
            return;
        }
        if(!validate()) {
            return;
        }
        endPos = pos;
        pieces[endPos.x][endPos.y] = pieces[startPos.x][startPos.y];
        pieces[startPos.x][startPos.y] = null;
        inMove = false;
        whiteOnMove = !whiteOnMove;
        game.makeMove(startPos, endPos);
    }

    public void mousePressed(Point pos) {
        if(!inMove) {
            if(pieces[pos.x][pos.y] != null && whiteOnMove == pieces[pos.x][pos.y].isWhite()) {
                startMove(pos);
            }
            return;
            
        }
        attemptMove(pos);
    }  

    public void mouseReleased(Point pos) {
        if(!inMove || startPos.equals(pos)) {
            return;
        }
        attemptMove(pos);
    }
}