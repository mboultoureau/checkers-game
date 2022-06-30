package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JPanel implements ActionListener {

    private GameController controller;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel errorLabel;
    private TextField player1TextField;
    private TextField player2TextField;
    private JButton playButton;

    public MenuView(GameController controller) {
        this.controller = controller;
        playButton = new JButton("Play");
        player1Label = new JLabel("Name of player 1 :");
        player2Label = new JLabel("Name of player 2 :");
        errorLabel = new JLabel("");
        player1TextField = new TextField();
        player2TextField = new TextField();

        setLayout(null);

        playButton.setSize(150, 60);
        playButton.setLocation(150, 210);
        playButton.setBackground(Color.WHITE);
        playButton.addActionListener(this);
        add(playButton);

        player1Label.setSize(200, 40);
        player1Label.setLocation(20, 110);
        player1Label.setForeground(Color.WHITE);
        player1Label.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
        add(player1Label);

        player2Label.setSize(200, 40);
        player2Label.setLocation(20, 140);
        player2Label.setForeground(Color.WHITE);
        player2Label.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
        add(player2Label);

        errorLabel.setSize(400, 40);
        errorLabel.setLocation(80, 170);
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
        add(errorLabel);

        player1TextField.setSize(180, 25);
        player1TextField.setLocation(220, 115);
        add(player1TextField);

        player2TextField.setSize(180, 25);
        player2TextField.setLocation(220, 145);
        add(player2TextField);

        ImageIcon imageIcon = new ImageIcon("src/main/resources/homepage.png");
        JLabel label = new JLabel(imageIcon);
        label.setSize(500, 500);
        label.setLocation(-25, -100);
        add(label);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            String player1Name = this.player1TextField.getText();
            String player2Name = this.player2TextField.getText();

            boolean response = controller.updatePlayersNames(player1Name, player2Name);
            if (!response) {
                this.errorLabel.setText("Names must not be empty");
            } else {
                controller.launchGame();
            }
        }
    }
}
