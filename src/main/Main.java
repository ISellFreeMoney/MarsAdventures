package main;

import javax.swing.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Mars Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        gamePanel.startGameThread();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}