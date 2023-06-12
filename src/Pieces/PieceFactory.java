package Pieces;

public class PieceFactory {
    public static Piece createPiece(char c) {
        boolean isWhite = false;
        if(c <= 'R') {
            isWhite = true;
            c = Character.toLowerCase(c);
        }
        if(c == 'p') return new Pawn(isWhite); 
        if(c == 'n') return new Knight(isWhite);
        if(c == 'b') return new Bishop(isWhite);
        if(c == 'r') return new Rook(isWhite);
        if(c == 'q') return new Queen(isWhite);
        if(c == 'k') return new King(isWhite);
        else return null;
    }
}