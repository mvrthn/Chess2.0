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
        String path = "../pieces/";
        try {
            String p = path + (isWhite ? "white" : "black") + name + ".png";
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