package model;

import view.BoardView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    public static int COLUMNS = 10;
    public static int ROWS = 10;
    public enum CASE_MOVE { NONE, SELECTED, MOVED }
    public enum CASE_TYPE { NONE, PAWN, MOVEMENT }
    private final List<List<Pawn>> pawns;
    private final List<List<CASE_MOVE>> pawnsMoves;
    private Pawn selectedPawn;
    private Pawn.PAWN_COLOR turn;
    public BoardView boardView;

    public Board() {
        this.pawns = new ArrayList<>(ROWS);
        this.pawnsMoves = new ArrayList<>(ROWS);
        this.initBoard();
        // this.customBoard();
        this.resetPawnsMoves();
        this.turn = Pawn.PAWN_COLOR.WHITE;
    }

    public void setBoardView(BoardView boardView){
        this.boardView = boardView;
    }

    public void reset() {
        this.clearBoard();
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

        // Initialization of pawns moves
        for (int row = 0; row < ROWS; row++) {
            ArrayList<CASE_MOVE> newRow = new ArrayList<CASE_MOVE>(COLUMNS);
            for (int column = 0; column < COLUMNS; column++) {
                newRow.add(CASE_MOVE.NONE);
            }
            this.pawnsMoves.add(newRow);
        }
    }

    public CASE_TYPE getCaseType(int row, int column) {
        if (this.pawns.get(row).get(column) != null) {
            return CASE_TYPE.PAWN;
        }

        if (this.pawnsMoves.get(row).get(column) == CASE_MOVE.MOVED) {
            return CASE_TYPE.MOVEMENT;
        }

        return CASE_TYPE.NONE;
    }

    public void move(int row, int column) {
        Coordinates originalCoordinates = this.getPawnPosition(selectedPawn);
        this.pawns.get(row).set(column, selectedPawn);
        this.pawns.get(originalCoordinates.getRow()).set(originalCoordinates.getColumn(), null);

        // Check if pawn become queen
        if (selectedPawn.getType() == Pawn.PAWN_TYPE.PAWN) {
            if (selectedPawn.getColor() == Pawn.PAWN_COLOR.WHITE && row == 0) {
                selectedPawn.setType(Pawn.PAWN_TYPE.QUEEN);
                turn = Pawn.PAWN_COLOR.BLACK;
            } else if (selectedPawn.getColor() == Pawn.PAWN_COLOR.BLACK && row == 9) {
                selectedPawn.setType(Pawn.PAWN_TYPE.QUEEN);
                turn = Pawn.PAWN_COLOR.WHITE;
            }
        }

        // Check if pawn jumped
        // Top left
        if (row - originalCoordinates.getRow() < 0 && column - originalCoordinates.getColumn() < 0) {
            int i = row + 1;
            int j = column + 1;

            while (i != originalCoordinates.getRow() || j != originalCoordinates.getColumn()) {
                if (this.pawns.get(i).get(j) != null) {
                    this.pawns.get(i).set(j, null);
                    boardView.setCheckersLeft(turn);
                }
                i++;
                j++;
            }
        }

        // Top right
        if (row - originalCoordinates.getRow() < 0 && column - originalCoordinates.getColumn() > 0) {
            int i = row + 1;
            int j = column - 1;

            while (i != originalCoordinates.getRow() || j != originalCoordinates.getColumn()) {
                if (this.pawns.get(i).get(j) != null) {
                    this.pawns.get(i).set(j, null);
                    boardView.setCheckersLeft(turn);
                }
                i++;
                j--;
            }
        }

        // Bottom left
        if (row - originalCoordinates.getRow() > 0 && column - originalCoordinates.getColumn() < 0) {
            int i = row - 1;
            int j = column + 1;

            while (i != originalCoordinates.getRow() || j != originalCoordinates.getColumn()) {
                if (this.pawns.get(i).get(j) != null) {
                    this.pawns.get(i).set(j, null);
                    boardView.setCheckersLeft(turn);
                }
                i--;
                j++;
            }
        }

        // Bottom right
        if (row - originalCoordinates.getRow() > 0 && column - originalCoordinates.getColumn() > 0) {
            int i = row - 1;
            int j = column - 1;

            while (i != originalCoordinates.getRow() || j != originalCoordinates.getColumn()) {
                if (this.pawns.get(i).get(j) != null) {
                    this.pawns.get(i).set(j, null);
                    boardView.setCheckersLeft(turn);
                }
                i--;
                j--;
            }
        }

        // Temp change turn
        if (turn == Pawn.PAWN_COLOR.WHITE) {
            turn = Pawn.PAWN_COLOR.BLACK;
        } else {
            turn = Pawn.PAWN_COLOR.WHITE;
        }
        boardView.setTurn(turn);

        this.resetPawnsMoves();
    }

    public void resetPawnsMoves() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                this.pawnsMoves.get(row).set(column, CASE_MOVE.NONE);
            }
        }
    }

    public Pawn.PAWN_COLOR getTurn(){
        return this.turn;
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
        Coordinates pawnPosition = getPawnPosition(pawn);
        int row = pawnPosition.getRow();
        int column = pawnPosition.getColumn();

        // Calculate pawns moves
        this.resetPawnsMoves();

        // Select pawn
        this.pawnsMoves.get(row).set(column, CASE_MOVE.SELECTED);

        // Pawns
        if (pawn.getType() == Pawn.PAWN_TYPE.PAWN && pawn.getColor() == Pawn.PAWN_COLOR.WHITE) {
            // White pawns

            // Free space
            if (row - 1 >= 0 && column - 1 >= 0 && this.pawns.get(row - 1).get(column - 1) == null) {
                this.pawnsMoves.get(row - 1).set(column - 1, CASE_MOVE.MOVED);
            }

            if (row - 1 >= 0 && column + 1 <= 9 && this.pawns.get(row - 1).get(column + 1) == null) {
                this.pawnsMoves.get(row - 1).set(column + 1, CASE_MOVE.MOVED);
            }

            // Pawn
            if (row - 2 >= 0 && column - 2 >= 0 && this.pawns.get(row - 1).get(column - 1) != null && this.pawns.get(row - 1).get(column - 1).getColor() == Pawn.PAWN_COLOR.BLACK  && this.pawns.get(row - 2).get(column - 2) == null) {
                this.pawnsMoves.get(row - 2).set(column - 2, CASE_MOVE.MOVED);
            }

            if (row - 2 >= 0 && column + 2 <= 9 && this.pawns.get(row - 1).get(column + 1) != null && this.pawns.get(row - 1).get(column + 1).getColor() == Pawn.PAWN_COLOR.BLACK && this.pawns.get(row - 2).get(column + 2) == null) {
                this.pawnsMoves.get(row - 2).set(column + 2, CASE_MOVE.MOVED);
            }
        } else if (pawn.getType() == Pawn.PAWN_TYPE.PAWN && pawn.getColor() == Pawn.PAWN_COLOR.BLACK) {
            // Black pawns

            // Free space
            if (row + 1 <= 9 && column - 1 >= 0 && this.pawns.get(row + 1).get(column - 1) == null) {
                this.pawnsMoves.get(row + 1).set(column - 1, CASE_MOVE.MOVED);
            }

            if (row + 1 <= 9 && column + 1 <= 9 && this.pawns.get(row + 1).get(column + 1) == null) {
                this.pawnsMoves.get(row + 1).set(column + 1, CASE_MOVE.MOVED);
            }

            // Pawn
            if (row + 2 <= 9 && column - 2 >= 0 && this.pawns.get(row + 1).get(column - 1) != null && this.pawns.get(row + 1).get(column - 1).getColor() == Pawn.PAWN_COLOR.WHITE && this.pawns.get(row + 2).get(column - 2) == null) {
                this.pawnsMoves.get(row + 2).set(column - 2, CASE_MOVE.MOVED);
            }

            if (row + 2 <= 9 && column + 2 <= 9 && this.pawns.get(row + 1).get(column + 1) != null && this.pawns.get(row + 1).get(column + 1).getColor() == Pawn.PAWN_COLOR.WHITE && this.pawns.get(row + 2).get(column + 2) == null) {
                this.pawnsMoves.get(row + 2).set(column + 2, CASE_MOVE.MOVED);
            }
        } else if (pawn.getType() == Pawn.PAWN_TYPE.QUEEN) {
            // Check top left
            int i = row;
            int j = column;

            while (i >= 0 && j >= 0) {
                this.pawnsMoves.get(i).set(j, CASE_MOVE.MOVED);

                i--;
                j--;
            }

            // Check top right
            i = row;
            j = column;

            while (i >= 0 && j <= 9) {
                this.pawnsMoves.get(i).set(j, CASE_MOVE.MOVED);

                i--;
                j++;
            }

            // Check bottom left
            i = row;
            j = column;

            while (i <= 9 && j >= 0) {
                this.pawnsMoves.get(i).set(j, CASE_MOVE.MOVED);

                i++;
                j--;
            }

            // Check bottom right
            i = row;
            j = column;

            while (i <= 9 && j <= 9) {
                this.pawnsMoves.get(i).set(j, CASE_MOVE.MOVED);

                i++;
                j++;
            }
        }
    }

    /* public void customBoard() {
        // Initial creation of the game board
        for (int row = 0; row < ROWS; row++) {
            ArrayList<Pawn> newRow = new ArrayList<Pawn>(COLUMNS);

            for (int column = 0; column < COLUMNS; column++) {
                if (column == 3 && row == 1) {
                    newRow.add(new Pawn(Pawn.PAWN_COLOR.WHITE, Pawn.PAWN_TYPE.PAWN));
                } else if (column == 7 && row == 8) {
                    newRow.add(new Pawn(Pawn.PAWN_COLOR.BLACK, Pawn.PAWN_TYPE.PAWN));
                } else if (column == 6 && row == 4) {
                    newRow.add(new Pawn(Pawn.PAWN_COLOR.BLACK, Pawn.PAWN_TYPE.PAWN));
                } else if (column == 7 && row == 5) {
                    newRow.add(new Pawn(Pawn.PAWN_COLOR.WHITE, Pawn.PAWN_TYPE.PAWN));
                } else {
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
    }*/

    public Coordinates getPawnPosition(Pawn pawn) {
        Coordinates coordinates = new Coordinates();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (this.pawns.get(i).get(j) == pawn) {
                    coordinates.setRow(i);
                    coordinates.setColumn(j);
                    return coordinates;
                }
            }
        }

        return null;
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
