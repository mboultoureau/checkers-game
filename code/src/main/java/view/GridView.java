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

    public GridView(Board board, BoardView boardView) throws IOException {
        this.board = board;
        this.boardView = boardView;
        whitePawnImg = ImageIO.read(new File("src/main/resources/white-pawn.png"));
        blackPawnImg = ImageIO.read(new File("src/main/resources/black-pawn.png"));
        whiteQueenImg = ImageIO.read(new File("src/main/resources/white-queen.png"));
        blackQueenImg = ImageIO.read(new File("src/main/resources/black-queen.png"));

        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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

        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if (board.getPawn(row, column) != null) {
                    Pawn pawn = board.getPawn(row, column);

                    if (pawn.getType() == Pawn.PAWN_TYPE.PAWN) {
                        if (pawn.getColor() == Pawn.PAWN_COLOR.WHITE) {
                            g.drawImage(whitePawnImg, column * 50, row * 50, column * 50 + 50, row * 50 + 50, 0, 0, 100, 100, null);
                        } else {
                            g.drawImage(blackPawnImg, column * 50, row * 50, column * 50 + 50, row * 50 + 50, 0, 0, 100, 100, null);
                        }
                    } else {
                        if (pawn.getColor() == Pawn.PAWN_COLOR.WHITE) {
                            g.drawImage(whiteQueenImg, column * 50, row * 50, column * 50 + 50, row * 50 + 50, 0, 0, 100, 100, null);
                        } else {
                            g.drawImage(blackQueenImg, column * 50, row * 50, column * 50 + 50, row * 50 + 50, 0, 0, 100, 100, null);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int column = e.getX() / 50;
        int row = e.getY() / 50;

        System.out.println("Clicked on " + row + " " + column);

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
}