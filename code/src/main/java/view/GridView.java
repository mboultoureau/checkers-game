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
    private Board board;

    public GridView(Board board) throws IOException {
        this.board = board;
        whitePawnImg = ImageIO.read(new File("src/main/resources/white-pawn.png"));
        blackPawnImg = ImageIO.read(new File("src/main/resources/black-pawn.png"));

        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if (board.isMovable(row, column)) {
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
                    if (board.getPawn(row, column).getColor() == Pawn.PAWN_COLOR.WHITE) {
                        g.drawImage(whitePawnImg, column * 50, row * 50, column * 50 + 50, row * 50 + 50, 0, 0, 100, 100, null);
                    } else {
                        g.drawImage(blackPawnImg, column * 50, row * 50, column * 50 + 50, row * 50 + 50, 0, 0, 100, 100, null);
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = e.getX() / 500;
        int column = e.getY() / 500;

        if (board.canBeSelected(board.getPawn(row, column))) {
            board.setSelected(board.getPawn(row, column));
        }
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