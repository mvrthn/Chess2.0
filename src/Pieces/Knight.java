package Pieces;

import Chess.Game;

import java.awt.*;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super("Knight", 'k', isWhite);
    }

    @Override 
    public boolean move(Piece[][] pieces, Point beg, Point end, Game game) {
        if(Math.abs(beg.x - end.x) == 2 && Math.abs(beg.y - end.y) == 1) {
            return true;
        }
        if(Math.abs(beg.x - end.x) == 1 && Math.abs(beg.y - end.y) == 2) {
            return true;
        }
        return false;
    }
}