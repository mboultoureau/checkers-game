package controller;

import model.Board;
import model.Game;
import model.Player;
import view.BoardView;

public class GameController {
    private final Game game;
    private final Board board;
    private final BoardView boardView;

    public GameController() {
        this.game = new Game();
        this.board = new Board();
        this.boardView = new BoardView(board, this);
    }

    public void run() {
        this.boardView.setVisible(true);
        this.board.setBoardView(boardView);
    }

    /**
     *
     * @param player1 Name of the player 1
     * @param player2 Name of the player 2
     * @return A boolean indicating the success or failure of the name update
     */
    public boolean updatePlayersNames(String player1Name, String player2Name) {
        // Names must not be empty
        if (player1Name.trim().equals("") || player2Name.trim().equals("")) {
            return false;
        }

        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);

        this.game.setPlayer1(player1);
        this.game.setPlayer2(player2);

        return true;
    }

    public void launchGame() {
        this.boardView.setPlaying(true);
        this.boardView.update();
    }

}
