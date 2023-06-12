package Pieces;

import Chess.Game;

import java.awt.Point;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super("Queen", 'q', isWhite);
    }

    @Override
    public boolean move(Piece[][] pieces, Point beg, Point end, Game game) {
        if(beg.x != end.x && beg.y != end.y && end.y - end.x != beg.y - beg.x && end.y + end.x != beg.y + beg.x) {
            return false;
        }
        return checkIfPathIsClear(pieces, beg, end);
    }
}