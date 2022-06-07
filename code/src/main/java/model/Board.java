package model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static int COLUMNS = 10;
    public static int ROWS = 10;
    private List<List<Pawn>> pawns;
    private List<List<Boolean>> pawnsMoves;
    private Pawn selectedPawn;

    public Board() {
        this.pawns = new ArrayList<List<Pawn>>(ROWS);
        this.pawnsMoves = new ArrayList<List<Boolean>>(ROWS);
        this.initBoard();
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

        // Pawns moves
        for (int row = 0; row < ROWS; row++) {
            ArrayList<Boolean> newRow = new ArrayList<Boolean>(COLUMNS);

            for (int column = 0; column < COLUMNS; column++) {
                newRow.add(false);
            }

            this.pawnsMoves.add(newRow);
        }
    }

    public Pawn getPawn(int row, int column) {
        return this.pawns.get(row).get(column);
    }

    public boolean canBeSelected(Pawn pawn) {
        return true;
    }
    public void setSelected(Pawn pawn) {
        this.selectedPawn = pawn;

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

        pawnsMoves.get(row).set(column, true);
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
