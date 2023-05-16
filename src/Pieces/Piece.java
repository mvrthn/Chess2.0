package Pieces;

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
            icon = ImageIO.read(new File(path + (isWhite ? "white" : "black") + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = (isWhite ? Character.toUpperCase(id) : id);
        this.isWhite = isWhite;
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