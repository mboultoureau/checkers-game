package view;

import javax.swing.*;
import java.awt.*;

public class GridView extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if (row % 2 == 0 && column % 2 == 0 || row % 2 != 0 && column % 2 != 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(column * 50, row * 50, 50, 50);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(column * 50, row * 50, 50, 50);
                }
            }
        }
    }
}