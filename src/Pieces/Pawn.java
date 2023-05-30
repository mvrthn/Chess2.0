package Pieces;

import Chess.Game;

import java.awt.*;

public class Pawn extends Piece {
    private boolean doubleMove;
    public Pawn(boolean isWhite) {
        super("Pawn", 'p', isWhite);
        doubleMove = true;
    }

    @Override
    public boolean move(Piece[][] pieces, Point beg, Point end, Game game) {
        if(Math.abs(beg.x - end.x) + Math.abs(beg.y - end.y) > 2) {
            return false;
        }
        if(Math.signum(beg.y - end.y) != (isWhite() ? 1 : -1 )) {
            return false;
        }
        if(beg.x == end.x) {
            if(pieces[end.x][end.y] != null) {
                return false;
            }
            if(pieces[beg.x][beg.y + (isWhite() ? -1 : 1)] != null) {
                return false;
            }
            if(Math.abs(beg.y - end.y) == 1) {
                game.setEnPassant(null);
                return true;
            }
            if(doubleMove && pieces[end.x][end.y] == null) {
                doubleMove = false;
                game.setEnPassant(new Point(end.x, end.y + (isWhite() ? 1 : -1)));
                return true;
            }
        }
        if(pieces[end.x][end.y] != null) {
            game.setEnPassant(null);
            return true;
        }
        if(end.equals(game.getEnPassant())) {
            pieces[end.x][beg.y] = null;
            game.setEnPassant(null);
            return true;
        }
        return false;
    }
}