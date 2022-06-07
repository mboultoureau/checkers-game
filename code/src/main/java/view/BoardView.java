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
    private Button playButton;
    private TextField textUser1;
    private TextField textUser2;
    private JLabel labelUser1;
    private JLabel labelUser2;

    public BoardView(Board board) {
        this.board = board;
        this.setTitle("Checkers Game");
        this.setSize(700, 530);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.display();
        this.setVisible(true);
    }

    public void display() {
        JPanel layout = new JPanel();
        playButton = new Button("Play");
        textUser1 = new TextField();
        textUser2 = new TextField();
        labelUser1 = new JLabel("Name of player 1 :");
        labelUser2 = new JLabel("Name of player 2 :");

        if (false) {
            layout.setLayout(new GridLayout(1, 1));
            layout.add(new GridView());
        } else {
            this.setSize(450, 200);
            layout.setLayout(new GridLayout(3, 2, 20, 20));
            layout.add(labelUser1);
            layout.add(textUser1);
            layout.add(labelUser2);
            layout.add(textUser2);
            layout.add(playButton);
        }

        this.setContentPane(layout);
    }
}