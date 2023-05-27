package Pieces;

import java.awt.*;

public class Pawn extends Piece {
    private boolean doubleMove;
    public Pawn(boolean isWhite) {
        super("Pawn", 'p', isWhite);
        doubleMove = true;
    }

    @Override
    public boolean move(Piece[][] pieces, Point beg, Point end) {
        if(pieces[end.x][end.y] != null) {
            return false;
        }
        if(Math.abs(beg.x - end.x) + Math.abs(beg.y - end.y) > 2) {
            return false;
        }
        if(Math.signum(beg.y - end.y) != (isWhite() ? 1 : -1 )) {
            return false;
        }
        if(beg.x == end.x) {
            if(pieces[beg.x][beg.y + (isWhite() ? -1 : 1)] != null) {
                return false;
            }
            if(Math.abs(beg.y - end.y) == 1) {
                return true;
            }
            if(doubleMove && pieces[end.x][end.y] == null) {
                return true;
            }
        }
        
        return false;
    }
}