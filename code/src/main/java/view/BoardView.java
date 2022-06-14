package view;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BoardView extends JFrame {

    private JLabel background;
    private Board board;
    private Button playButton;
    private TextField textUser1;
    private TextField textUser2;
    private JLabel labelUser1;
    private JLabel labelUser2;
    private JButton reversedButton;
    protected GridView gridView;

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
        try {
            this.gridView = new GridView(this.board, this);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(-1);
        }

        playButton = new Button("Play");
        reversedButton = new JButton("Reverse");
        textUser1 = new TextField();
        textUser2 = new TextField();
        labelUser1 = new JLabel("Name of player 1 :");
        labelUser2 = new JLabel("Name of player 2 :");

        if (true) {
            reversedButton.addActionListener(new ButtonListener(this.gridView));
            reversedButton.setMaximumSize(new Dimension(100, 100));

            layout.setLayout(new BorderLayout(2, 2));
            layout.add(this.gridView, BorderLayout.CENTER);
            layout.add(reversedButton, BorderLayout.EAST);
        } else {
            this.setSize(450, 300);
            this.setResizable(true);
            layout.setLayout(null);

            layout.add(playButton);
            playButton.setSize(150, 60);
            playButton.setLocation(50, 200);

            layout.add(labelUser1);
            labelUser1.setSize(180, 40);
            labelUser1.setLocation(20, 110);
            labelUser1.setForeground(Color.WHITE);
            labelUser1.setFont(new Font("sans-serif", Font.BOLD, 18));

            layout.add(labelUser2);
            labelUser2.setSize(180, 40);
            labelUser2.setLocation(20, 140);
            labelUser2.setForeground(Color.WHITE);
            labelUser2.setFont(new Font("sans-serif", Font.BOLD, 18));

            layout.add(textUser1);
            textUser1.setSize(150, 25);
            textUser1.setLocation(200, 120);


            ImageIcon imageIcon = new ImageIcon("src/main/resources/homepage.png");
            JLabel label = new JLabel(imageIcon);
            layout.add(label);
            label.setSize(500, 500);
            label.setLocation(-25, -100);
        }

        this.setContentPane(layout);
    }

    public class ButtonListener implements ActionListener {

        private Object parameter;

        public ButtonListener() {
            this.parameter = null;
        }

        public ButtonListener(Object parameter) {
            this.parameter = parameter;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == reversedButton && this.parameter instanceof GridView) {
                GridView gridView = (GridView) this.parameter;
                gridView.reverse();
            }
        }
    }
}