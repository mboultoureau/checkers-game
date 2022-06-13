package model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static int COLUMNS = 10;
    public static int ROWS = 10;
    public enum CASE_MOVE { NONE, SELECTED, MOVED };
    private List<List<Pawn>> pawns;
    private List<List<CASE_MOVE>> pawnsMoves;
    private Pawn selectedPawn;
    private Pawn.PAWN_COLOR turn;

    public Board() {
        this.pawns = new ArrayList<List<Pawn>>(ROWS);
        this.pawnsMoves = new ArrayList<List<CASE_MOVE>>(ROWS);
        this.initBoard();
        this.resetPawnsMoves();
        this.turn = Pawn.PAWN_COLOR.WHITE;
    }

    public void clearBoard() {
        // Clear pawns
        for (int row = 0; row < ROWS; row++) {
            this.pawns.get(row).clear();
        }
        this.pawns.clear();

        // Clear pawns moves
        for (int row = 0; row < ROWS; row++) {
            this.pawnsMoves.get(row).clear();
        }
        this.pawnsMoves.clear();
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

            this.pawns.add(newRow);
        }

        // Initialization of pawns moves
        for (int row = 0; row < ROWS; row++) {
            ArrayList<CASE_MOVE> newRow = new ArrayList<CASE_MOVE>(COLUMNS);
            for (int column = 0; column < COLUMNS; column++) {
                newRow.add(CASE_MOVE.NONE);
            }
            this.pawnsMoves.add(newRow);
        }
    }

    public void resetPawnsMoves() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                this.pawnsMoves.get(row).set(column, CASE_MOVE.NONE);
            }
        }
    }

    public Pawn getPawn(int row, int column) {
        return this.pawns.get(row).get(column);
    }

    public boolean isMovable(int row, int column) {
        return this.pawnsMoves.get(row).get(column) == CASE_MOVE.MOVED;
    }

    public boolean isSelected(int row, int column) {
        return this.pawnsMoves.get(row).get(column) == CASE_MOVE.SELECTED;
    }

    public boolean canBeSelected(Pawn pawn) {
        if (pawn == null) {
            return false;
        }

        if (pawn.getColor() != turn) {
            return false;
        }

        return true;
    }
    public void setSelected(Pawn pawn) {
        this.selectedPawn = pawn;

        // Find pawn position
        int row = 0;
        int column = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (this.pawns.get(i).get(j) == pawn) {
                    row = i;
                    column = j;
                }
            }
        }

        // Calculate pawns moves
        this.resetPawnsMoves();

        // Select pawn
        this.pawnsMoves.get(row).set(column, CASE_MOVE.SELECTED);

        // White pawns
        if (pawn.getColor() == Pawn.PAWN_COLOR.WHITE) {
            if (row - 1 >= 0 && column - 1 >= 0 && this.pawns.get(row - 1).get(column - 1) == null) {
                this.pawnsMoves.get(row - 1).set(column - 1, CASE_MOVE.MOVED);
            }

            if (row - 1 >= 0 && column + 1 <= 9 && this.pawns.get(row - 1).get(column + 1) == null) {
                this.pawnsMoves.get(row - 1).set(column + 1, CASE_MOVE.MOVED);
            }
        } else {
            if (row + 1 <= 9 && column - 1 >= 0 && this.pawns.get(row + 1).get(column - 1) == null) {
                this.pawnsMoves.get(row + 1).set(column - 1, CASE_MOVE.MOVED);
            }

            if (row + 1 <= 9 && column + 1 <= 9 && this.pawns.get(row + 1).get(column + 1) == null) {
                this.pawnsMoves.get(row + 1).set(column + 1, CASE_MOVE.MOVED);
            }
        }
    }

    public void debugBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (this.pawns.get(row).get(column) != null) {
                    switch (this.pawns.get(row).get(column).getColor()) {
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
