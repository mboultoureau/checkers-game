package view;

import model.Board;
import model.Pawn;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GridView extends JPanel implements MouseListener {

    private BufferedImage whitePawnImg;
    private BufferedImage blackPawnImg;
    private BufferedImage whiteQueenImg;
    private BufferedImage blackQueenImg;
    private Board board;
    private BoardView boardView;
    private boolean reversed;

    public GridView(Board board, BoardView boardView) throws IOException {
        this.board = board;
        this.boardView = boardView;
        this.reversed = false;
        whitePawnImg = ImageIO.read(new File("src/main/resources/white-pawn.png"));
        blackPawnImg = ImageIO.read(new File("src/main/resources/black-pawn.png"));
        whiteQueenImg = ImageIO.read(new File("src/main/resources/white-queen.png"));
        blackQueenImg = ImageIO.read(new File("src/main/resources/black-queen.png"));

        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (reversed) {
            displayReversedBoard(g);
            displayReversedPawns(g);
        } else {
            displayBoard(g);
            displayPawns(g);
        }
    }

    /**
     * Display the reversed board and moves
     * @param g The graphics renderer
     */
    private void displayReversedBoard(Graphics g) {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if (board.isMovable(row, column)) {
                    g.setColor(Color.YELLOW);
                    g.fillRect((9 - column) * 50, (9 - row) * 50, 50, 50);
                } else if (board.isSelected(row, column)) {
                    g.setColor(Color.RED);
                    g.fillRect((9 - column) * 50, (9 - row) * 50, 50, 50);
                } else if (row % 2 == 0 && column % 2 == 0 || row % 2 != 0 && column % 2 != 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect((9 - column) * 50, (9 - row) * 50, 50, 50);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect((9 - column) * 50, (9 - row) * 50, 50, 50);
                }
            }
        }
    }

    /**
     * Display the board and moves
     * @param g The graphics renderer
     */
    private void displayBoard(Graphics g) {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if (board.isMovable(row, column)) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(column * 50, row * 50, 50, 50);
                } else if (board.isSelected(row, column)) {
                    g.setColor(Color.RED);
                    g.fillRect(column * 50, row * 50, 50, 50);
                } else if (row % 2 == 0 && column % 2 == 0 || row % 2 != 0 && column % 2 != 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(column * 50, row * 50, 50, 50);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(column * 50, row * 50, 50, 50);
                }
            }
        }
    }

    /**
     * Display pawns
     * @param g The graphics renderer
     */
    private void displayPawns(Graphics g) {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if (board.getPawn(row, column) != null) {
                    Pawn pawn = board.getPawn(row, column);
                    displayPawn(pawn, g, column, row);
                }
            }
        }
    }

    /**
     * Display reversed pawns
     * @param g The graphics renderer
     */
    private void displayReversedPawns(Graphics g) {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if (board.getPawn(row, column) != null) {
                    Pawn pawn = board.getPawn(row, column);
                    displayPawn(pawn, g, (9 - column), (9 - row));
                }
            }
        }
    }

    /**
     * Display one pawn
     * @param pawn      An entity Pawn
     * @param g         The graphics renderer
     * @param column    Column number
     * @param row       Row number
     */
    private void displayPawn(Pawn pawn, Graphics g, int column, int row) {
        Pawn.PAWN_COLOR color = pawn.getColor();
        Pawn.PAWN_TYPE type = pawn.getType();

        if (type == Pawn.PAWN_TYPE.PAWN && color == Pawn.PAWN_COLOR.WHITE) {
                g.drawImage(whitePawnImg, column * 50, row * 50, column * 50 + 50, row * 50 + 50, 0, 0, 100, 100, null);
        } else if (type == Pawn.PAWN_TYPE.PAWN && color == Pawn.PAWN_COLOR.BLACK) {
                g.drawImage(blackPawnImg, column * 50, row * 50, column * 50 + 50, row * 50 + 50, 0, 0, 100, 100, null);
        } else if (type == Pawn.PAWN_TYPE.QUEEN && color == Pawn.PAWN_COLOR.WHITE) {
            g.drawImage(whiteQueenImg, column * 50, row * 50, column * 50 + 50, row * 50 + 50, 0, 0, 100, 100, null);
        } else if (type == Pawn.PAWN_TYPE.QUEEN && color == Pawn.PAWN_COLOR.BLACK) {
            g.drawImage(blackQueenImg, column * 50, row * 50, column * 50 + 50, row * 50 + 50, 0, 0, 100, 100, null);
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        int column = e.getX() / 50;
        int row = e.getY() / 50;

        if (reversed) {
            column = 9 - column;
            row = 9 - row;
        }

        switch (board.getCaseType(row, column)) {
            case PAWN:
                if (board.canBeSelected(board.getPawn(row, column))) {
                    board.setSelected(board.getPawn(row, column));
                }
                break;
            case MOVEMENT:
                board.move(row, column);
                break;
        }

        boardView.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public void reverse() {
        this.setReversed(!this.isReversed());
        this.boardView.repaint();
    }
}