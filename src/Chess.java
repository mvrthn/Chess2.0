public class Chess {
    private final Frame frame;
    private Game game;

    public Chess() {
        frame = new Frame();
        game = new Game();

        frame.addBoard(game.getBoard());
    }
}
