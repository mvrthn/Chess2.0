package Pieces;

import Chess.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Piece {
    private Image icon;
    private final char id;
    private final boolean isWhite;

    public Piece(String name, char id, boolean isWhite) {
        String path = "pieces/";
        try {
            icon = ImageIO.read(new File(path + (isWhite ? "white" : "black") + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = (isWhite ? Character.toUpperCase(id) : id);
        this.isWhite = isWhite;
    }

    public boolean move(Piece[][] pieces, Point beg, Point end, Game game) {
        return true;
    }

    public boolean kingInDanger(Piece[][] pieces, boolean whiteOnMove, Game game) {
        final Point kingPos = game.getKingPos(whiteOnMove);
        for(int a = 1, b = 2, i = 0; i < 8; i++, b *= -1) {
            if(i % 2 == 1) {
                a *= -1;
            }
            else if(i == 4) {
                a = 2;
                b = 1;
            }
            if(kingPos.x + a < 0 || kingPos.x + a >= 8 || kingPos.y + b < 0 || kingPos.y + b >= 8) {
                continue;
            }
            if(pieces[kingPos.x + a][kingPos.y + b] == null) {
                continue;
            }
            if(pieces[kingPos.x + a][kingPos.y + b].getId() == (whiteOnMove ? 'n' : 'N')) {
                return true;
            }
        }
        for(int j = 0, a = 1; j < 2; j++, a = -1) {
            for(int i = kingPos.x + a; i < 8 && i >= 0; i += a) {
                if(pieces[i][kingPos.y] == null) {
                    continue;
                }
                if(pieces[i][kingPos.y].isWhite() == whiteOnMove) {
                    break;
                }
                if(pieces[i][kingPos.y].getId() == (whiteOnMove ? 'q' : 'Q')) {
                    return true;
                }
                if(pieces[i][kingPos.y].getId() == (whiteOnMove ? 'r' : 'R')) {
                    return true;
                }
                if(pieces[i][kingPos.y].getId() == (whiteOnMove ? 'k' : 'K') && Math.abs(i - kingPos.x) == 1) {
                    return true;
                }
                break;
            }
        }
        for(int j = 0, a = 1; j < 2; j++, a = -1) {
            for(int i = kingPos.y + a; i < 8 && i >= 0; i += a) {
                if(pieces[kingPos.x][i] == null) {
                    continue;
                }
                if(pieces[kingPos.x][i].isWhite() == whiteOnMove) {
                    break;
                }
                if(pieces[kingPos.x][i].getId() == (whiteOnMove ? 'q' : 'Q')) {
                    return true;
                }
                if(pieces[kingPos.x][i].getId() == (whiteOnMove ? 'r' : 'R')) {
                    return true;
                }
                if(pieces[kingPos.x][i].getId() == (whiteOnMove ? 'k' : 'K') && Math.abs(i - kingPos.y) == 1) {
                    return true;
                }
                break;
            }
        }
        for(int i = 0, a = 1, b = 1; i < 4; i++) {
            if(i % 2 == 1) {
                b *= -1;
            }
            if(i == 2) {
                a = -1;
            }
            for(int x = kingPos.x + a, y = kingPos.y + b; x >= 0 && x < 8 && y >= 0 && y < 8; x += a, y += b) {
                if(pieces[x][y] == null) {
                    continue;
                }
                if(pieces[x][y].isWhite() == whiteOnMove) {
                    break;
                }
                if(pieces[x][y].getId() == (whiteOnMove ? 'q' : 'Q')) {
                    return true;
                }
                if(pieces[x][y].getId() == (whiteOnMove ? 'b' : 'B')) {
                    return true;
                }
                if(pieces[x][y].getId() == (whiteOnMove ? 'p' : 'P') && Math.abs(x) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIfPathIsClear(Piece[][] pieces, Point beg, Point end) {
        int a = (int) Math.signum(end.x - beg.x);
        int b = (int) Math.signum(end.y - beg.y);
        int x = beg.x + a;
        int y = beg.y + b;
        while(x != end.x || y != end.y) {
            if(pieces[x][y] != null) {
                return false;
            }
            x += a;
            y += b;
        }
        return true;
    }

    public Image getIcon() {
        return icon;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public char getId() {
        return id;
    }
}