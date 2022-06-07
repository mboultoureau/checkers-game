package view;

import model.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

        layout.add(new GridView());

        this.setContentPane(layout);
    }
}