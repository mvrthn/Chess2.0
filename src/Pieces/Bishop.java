package Pieces;

import Chess.Game;

import java.awt.Point;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super("Bishop", 'b', isWhite);
    }

    @Override
    public boolean move(Piece[][] pieces, Point beg, Point end, Game game) {
        if(end.y - end.x != beg.y - beg.x && end.y + end.x != beg.y + beg.x) {
            return false;
        }
        return checkIfPathIsClear(pieces, beg, end);
    }
}