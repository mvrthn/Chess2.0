package Chess;

import Pieces.Piece;

import java.awt.Point;
import java.util.ArrayList;

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

    private void startMove(Point pos) {
        startPos = pos;
        inMove = true;
    }

    private void attemptMove(Point pos) {
        inMove = false;
        Piece piece = pieces[startPos.x][startPos.y];
        Point enPassant = game.getEnPassant() != null ? new Point(game.getEnPassant()) : null;
        ArrayList<Point> changePos = new ArrayList<>();
        ArrayList<Piece> changePieces = new ArrayList<>();
        if(!piece.move(pieces, startPos, pos, game)) {
            return;
        }
        endPos = pos;
        if(endPos.equals(enPassant) && Character.toUpperCase(piece.getId()) == 'P') {
            changePos.add(new Point(endPos.x, startPos.y));
            changePieces.add(pieces[endPos.x][startPos.y]);
            pieces[endPos.x][startPos.y] = null;
        }
        changePos.add(new Point(endPos.x, endPos.y));
        changePieces.add(pieces[endPos.x][endPos.y]);
        pieces[endPos.x][endPos.y] = piece;
        changePos.add(new Point(startPos.x, startPos.y));
        changePieces.add(pieces[startPos.x][startPos.y]);
        pieces[startPos.x][startPos.y] = null;
        if(!piece.kingIsSafe(pieces, whiteOnMove)) {
            for(int i = 0; i < changePos.size(); i++) {
                pieces[changePos.get(i).x][changePos.get(i).y] = changePieces.get(i);
            }
            return;
        }
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
        if(startPos.equals(pos)) {
            inMove = false;
            return;
        }
        if(pieces[pos.x][pos.y] != null && whiteOnMove == pieces[pos.x][pos.y].isWhite()) {
            inMove = false;
            return;
        }
        attemptMove(pos);
    }

    public void mouseReleased(Point pos) {
        if(!inMove) {
            return;
        }
        if(startPos.equals(pos)) {
            inMove = false;
            return;
        }
        if(pieces[pos.x][pos.y] != null && whiteOnMove == pieces[pos.x][pos.y].isWhite()) {
            inMove = false;
            return;
        }
        attemptMove(pos);
    }
}