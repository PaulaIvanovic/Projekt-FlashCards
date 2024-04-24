package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

import views.ScreenDimensions;

public class RoundPasswordField extends JPasswordField {
    private Shape shape;
    private Color borderColor = Color.BLACK;
    ScreenDimensions dimensions = new ScreenDimensions();

    public RoundPasswordField(int size) {
        super(size);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, (int)(dimensions.screenWidth * 0.015), (int)(dimensions.screenHeight * 0.025));
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(borderColor);
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, (int)(dimensions.screenWidth * 0.015), (int)(dimensions.screenHeight * 0.025));
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, (int)(dimensions.screenWidth * 0.015), (int)(dimensions.screenHeight * 0.025));
        }
        return shape.contains(x, y);
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }
}
