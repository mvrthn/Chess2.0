package Pieces;

import Chess.Game;

import java.awt.*;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super("Rook", 'r', isWhite);
    }

    @Override
    public boolean move(Piece[][] pieces, Point beg, Point end, Game game) {
        if(beg.x != end.x && beg.y != end.y) {
            return false;
        }
        return checkIfPathIsClear(pieces, beg, end);
    }
}