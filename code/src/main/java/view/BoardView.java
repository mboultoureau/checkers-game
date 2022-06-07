package view;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BoardView extends JFrame {

    private JLabel background;
    private Board board;

    public BoardView(Board board) {
        this.board = board;
        this.setTitle("Checkers Game");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.display();
        this.setVisible(true);
    }

    public void display() {
        JPanel layout = new JPanel();
        layout.setLayout(new GridLayout(1, 1));

        GridView gridView = null;
        try {
            gridView = new GridView(board);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        layout.add(gridView);

        this.setContentPane(layout);
    }
}