package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TimerPanel extends JPanel {

    private int seconds = 0;
    private Timer timer;

    public TimerPanel() {
        setPreferredSize(new Dimension(300, 200));
        setBackground(Color.BLUE);
        startTimer();
    }

    private void startTimer() {
        timer = new Timer(1000, (ActionEvent e) -> {
            seconds++;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Time: " + seconds + "s", 425, 36);
    }
}