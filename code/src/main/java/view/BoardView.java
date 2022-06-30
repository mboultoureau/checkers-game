package view;

import controller.GameController;
import model.Board;
import model.Game;
import model.Player;
import model.Pawn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BoardView extends JFrame {
    private GameController controller;
    private JLabel background;
    private Board board;
    private JButton playButton;
    private JButton restartButton;
    private JButton menuButton;
    private TextField textUser1 = new TextField();
    private TextField textUser2 = new TextField();
    private JLabel labelUser1;
    private JLabel labelUser2;
    private JButton reversedButton;
    protected GridView gridView;

    private boolean isPlaying;
    private JLabel user1Name;
    private JLabel user2Name;
    private JLabel userTurn;
    private int user1Checkers = 20;
    private int user2Checkers = 20;

    public BoardView(Board board, GameController controller) {
        this.controller = controller;
        this.board = board;
        this.isPlaying = false;

        try {
            this.gridView = new GridView(this.board, this);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(-1);
        }

        this.setTitle("Checkers Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.display();
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public Board getBoard() {
        return board;
    }

    public void display() {
        JPanel layout = new JPanel();
        layout.setLayout(new GridLayout(1, 1));

        restartButton = new JButton("Restart");
        playButton = new JButton("Play");
        reversedButton = new JButton("Reverse");
        menuButton = new JButton("Back to menu");
        labelUser1 = new JLabel("Name of player 1 :");
        labelUser2 = new JLabel("Name of player 2 :");

        if (isPlaying) {
            this.setSize(810, 530);

            // Sidebar
            JPanel sidebar = new JPanel();
            sidebar.setLayout(new GridLayout(3, 2));

            reversedButton.addActionListener(new ButtonListener(this));

            restartButton.addActionListener(new ButtonListener(this));

            menuButton.addActionListener(new ButtonListener(this));

            sidebar.add(userTurn);
            sidebar.add(reversedButton);
            sidebar.add(user1Name);
            sidebar.add(restartButton);
            sidebar.add(user2Name);
            sidebar.add(menuButton);


            layout.setLayout(new BorderLayout(2, 2));
            layout.add(this.gridView, BorderLayout.CENTER);
            layout.add(sidebar, BorderLayout.EAST);
        } else {
            this.setSize(450, 300);
            this.setResizable(true);

            MenuView menuView = new MenuView(this.controller);
            layout.add(menuView);
        }

        this.setContentPane(layout);
    }

    public String getPlayer1Name() {
        return this.textUser1.getText();
    }

    public String getPlayer2Name() {
        return this.textUser1.getText();
    }

    public GridView getGridView() {
        return gridView;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
    public void setTurn(Pawn.PAWN_COLOR color){
        if (color == Pawn.PAWN_COLOR.BLACK){
            userTurn.setText(textUser1.getText() + " turn");
            userTurn.setForeground(Color.black);
        } else {
            userTurn.setText(textUser2.getText() + " turn");
            userTurn.setForeground(Color.red);
        }
    }

    public void update() {
        this.display();
        this.revalidate();
        this.repaint();
    }

    public void setCheckersLeft(Pawn.PAWN_COLOR color){
        if (color == Pawn.PAWN_COLOR.BLACK){
            user2Checkers--;
        } else {
            user1Checkers--;
        }
        user1Name.setText("<html>" + textUser1.getText() + "<br>Checkers remaining : " + user1Checkers + "</html>");
        user2Name.setText("<html>" + textUser2.getText() + "<br>Checkers remaining : " + user2Checkers + "</html>");
    }


    public class ButtonListener implements ActionListener {

        private BoardView boardView;

        public ButtonListener(BoardView boardView) {
            user1Name = new JLabel("<html>" + textUser1.getText() + "<br>Checkers remaining : " + user1Checkers + "</html>");
            user1Name.setForeground(Color.black);
            user2Name = new JLabel("<html>" + textUser2.getText() + "<br>Checkers remaining : " + user2Checkers + "</html>");
            user2Name.setForeground(Color.red);
            userTurn = new JLabel( textUser2.getText() + " turn");
            userTurn.setForeground(Color.red);
            this.boardView = boardView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (this.boardView == null) {
                return;
            }

            if (e.getSource() == reversedButton) {
                this.boardView.getGridView().reverse();
            } else if (e.getSource() == restartButton) {
                user1Checkers = 20;
                user2Checkers = 20;
                user1Name.setText("<html>" + textUser1.getText() + "<br>Checkers remaining : " + user1Checkers + "</html>");
                user2Name.setText("<html>" + textUser2.getText() + "<br>Checkers remaining : " + user2Checkers + "</html>");
                this.boardView.getBoard().reset();
                this.boardView.revalidate();
                this.boardView.repaint();
            } else if (e.getSource() == menuButton) {
                user1Checkers = 20;
                user2Checkers = 20;
                this.boardView.setPlaying(false);
                this.boardView.getBoard().reset();
                this.boardView.display();
                this.boardView.revalidate();
                this.boardView.repaint();
            }
        }
    }
}