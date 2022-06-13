package view;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
        layout.setLayout(new GridLayout(1, 1));

        playButton = new Button("Play");
        textUser1 = new TextField();
        textUser2 = new TextField();
        labelUser1 = new JLabel("Name of player 1 :");
        labelUser2 = new JLabel("Name of player 2 :");

        if (true) {
            layout.setLayout(new GridLayout(1, 1));
            try {
                layout.add(new GridView(this.board, this));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.setSize(450, 200);
            this.setResizable(false);
            layout.setLayout(null);

            ImageIcon imageIcon = new ImageIcon("/resources/homepage.png");
            JLabel label = new JLabel(imageIcon);
            layout.add(label);
            label.setSize(500, 500);
            label.setLocation(0, 0);
            layout.add(labelUser1);
            labelUser1.setSize(150, 40);
            labelUser1.setLocation(10, 10);
        }

        this.setContentPane(layout);
    }
}