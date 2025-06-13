package client;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        setTitle("Online Pictionary");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);

        DrawingPanel drawingPanel = new DrawingPanel();
        ChatPanel chatPanel = new ChatPanel();

        add(drawingPanel, BorderLayout.CENTER);
        add(chatPanel, BorderLayout.EAST);

        setVisible(true);
    }
}