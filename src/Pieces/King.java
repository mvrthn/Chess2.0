package Pieces;

import Chess.Game;

import java.awt.*;

public class King extends Piece {
    public King(boolean isWhite) {
        super("King", 'k', isWhite);
    }

    @Override
    public boolean move(Piece[][] pieces, Point beg, Point end, Game game) {
        return Math.abs(beg.x - end.x) <= 1 && Math.abs(beg.y - end.y) <= 1;
    }
}