package Pieces;

import Chess.Game;

import java.awt.*;

public class King extends Piece {
    public King(boolean isWhite) {
        super("King", 'k', isWhite);
    }

    @Override
    public boolean move(Piece[][] pieces, Point beg, Point end, Game game) {
        if(Math.abs(beg.x - end.x) > 1 || Math.abs(beg.y - end.y) > 1) {
            if(beg.y != end.y || Math.abs(beg.x - end.x) != 2) {
                return false;
            }
            if(!game.getCastle(isWhite(), beg.x < end.x)) {
                return false;
            }
            if(!checkIfPathIsClear(pieces, beg, new Point(beg.x > end.x ? 0 : 7, end.y))) {
                return false;
            }
            if(kingInDanger(pieces, isWhite(), game)) {
                return false;
            }

            int shift = (end.x - beg.x) / 2;
            Point kingPos = new Point(beg.x + shift, beg.y);
            for(int i = 0; i < 2; i++) {
                if(i == 1) {
                    kingPos.x += shift;
                }
                for (int a = 1, b = (isWhite() ? -2 : 2), j = 0; j < 4; j++) {
                    if (j % 2 == 1) {
                        a *= -1;
                    } else if (j == 2) {
                        a = 2;
                        b /= 2;
                    }
                    if (kingPos.x + a < 0 || kingPos.x + a >= 8 || kingPos.y + b < 0 || kingPos.y + b >= 8) {
                        continue;
                    }
                    if (pieces[kingPos.x + a][kingPos.y + b] == null) {
                        continue;
                    }
                    if (pieces[kingPos.x + a][kingPos.y + b].getId() == (isWhite() ? 'n' : 'N')) {
                        return false;
                    }
                }
                for(int a = (isWhite() ? -1 : 1), j = kingPos.x + a; j < 8 && j >= 0; j += a) {
                    if(pieces[j][kingPos.y] == null) {
                        continue;
                    }
                    if(pieces[j][kingPos.y].isWhite() == isWhite()) {
                        break;
                    }
                    if(pieces[j][kingPos.y].getId() == (isWhite() ? 'q' : 'Q')) {
                        return false;
                    }
                    if(pieces[j][kingPos.y].getId() == (isWhite() ? 'r' : 'R')) {
                        return false;
                    }
                    if(pieces[j][kingPos.y].getId() == (isWhite() ? 'k' : 'K') && Math.abs(j - kingPos.x) == 1) {
                        return false;
                    }
                    break;
                }
                for(int j = 0, a = 1, b = (isWhite() ? -1 : 1); j < 2; j++) {
                    if(j == 1) {
                        a = -1;
                    }
                    for(int x = kingPos.x + a, y = kingPos.y + b; x >= 0 && x < 8 && y >= 0 && y < 8; x += a, y += b) {
                        if(pieces[x][y] == null) {
                            continue;
                        }
                        if(pieces[x][y].isWhite() == isWhite()) {
                            break;
                        }
                        if(pieces[x][y].getId() == (isWhite() ? 'q' : 'Q')) {
                            return false;
                        }
                        if(pieces[x][y].getId() == (isWhite() ? 'b' : 'B')) {
                            return false;
                        }
                        if(pieces[x][y].getId() == (isWhite() ? 'p' : 'P') && Math.abs(x) == 1) {
                            return false;
                        }
                    }
                }
            }
            game.useCastle(isWhite());
            pieces[beg.x + shift][beg.y] = pieces[beg.x > end.x ? 0 : 7][end.y];
            pieces[beg.x > end.x ? 0 : 7][end.y] = null;
        }
        game.changeKingPos(isWhite(), end);
        return true;
    }
}