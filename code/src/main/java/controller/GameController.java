package controller;

import model.Board;
import view.BoardView;

public class GameController {

    private final Board board;

    public GameController() {
        this.board = new Board();
    }

    public void run() {
        BoardView boardView = new BoardView(board);
        boardView.setVisible(true);
    }

}
