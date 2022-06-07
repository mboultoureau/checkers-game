package model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static int COLUMNS = 10;
    public static int ROWS = 10;

    private List<List<Pawn>> board;

    public Board() {
        this.board = new ArrayList<List<Pawn>>(ROWS);
        this.initBoard();
    }

    public void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            this.board.get(row).clear();
        }
        this.board.clear();
    }

    public void initBoard() {
        // Initial creation of the game board
        for (int row = 0; row < ROWS; row++) {
            ArrayList<Pawn> newRow = new ArrayList<Pawn>(COLUMNS);

            for (int column = 0; column < COLUMNS; column++) {
                // 4 first rows
                if (row < 4) {
                    if ((column % 2 != 0 && row % 2 == 0) || (column % 2 == 0 && row % 2 != 0)) {
                        newRow.add(new Pawn(Pawn.PAWN_COLOR.BLACK, Pawn.PAWN_TYPE.PAWN));
                    } else {
                        newRow.add(null);
                    }
                }

                // 4 last rows
                else if (row > 5) {
                    if ((column % 2 != 0 && row % 2 == 0) || (column % 2 == 0 && row % 2 != 0)) {
                        newRow.add(new Pawn(Pawn.PAWN_COLOR.WHITE, Pawn.PAWN_TYPE.PAWN));
                    } else {
                        newRow.add(null);
                    }
                }

                // 2 center paws
                else {
                    newRow.add(null);
                }
            }

            this.board.add(newRow);
        }
    }

    public void debugBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (this.board.get(row).get(column) != null) {
                    switch (this.board.get(row).get(column).getColor()) {
                        case WHITE -> System.out.print("W ");
                        case BLACK -> System.out.print("B ");
                        default -> System.out.print("? ");
                    }
                } else {
                    System.out.print(". ");
                }
            }

            System.out.print("\n");
        }
    }
}
