package controller;

import model.Board;
import view.BoardView;

public class GameController {

    public GameController() {
        Board board = new Board();
    }

    public void run() {
        BoardView boardView = new BoardView();
        boardView.display();
    }

}
