package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private ArrayList<Point> points = new ArrayList<>();

    public DrawingPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 600));

        MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                points.add(e.getPoint());
                repaint();
            }

            public void mouseDragged(MouseEvent e) {
                points.add(e.getPoint());
                repaint();
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public void clearDrawing() {
        points.clear();
        repaint();
    }
}