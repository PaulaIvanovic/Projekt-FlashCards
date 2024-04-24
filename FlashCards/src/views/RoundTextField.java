package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import views.ScreenDimensions;

public class RoundTextField extends JTextField {

    private Shape shape;
    private Color borderColor = Color.BLACK;
    
    ScreenDimensions dimensions = new ScreenDimensions();
	public int windowHeight;
	public int windowWidth;
    
    public RoundTextField(int size) {
        super(size);
        windowWidth = dimensions.screenWidth;
        windowHeight = dimensions.screenHeight;
        setProperties();
    }
    
    public RoundTextField(int size, int windowWidth, int windowHeight) {
        super(size);
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        setProperties();
    }
    
    public void setProperties() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, (int)(windowWidth * 0.015), (int)(windowHeight * 0.02));
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(borderColor);
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, (int)(windowWidth * 0.015), (int)(windowHeight * 0.02));
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, (int)(windowWidth * 0.015), (int)(windowHeight * 0.02));
        }
        return shape.contains(x, y);
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }
}