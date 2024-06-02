package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import java.awt.Insets;

public class RoundMultilineText extends JTextArea {

    private Color borderColor = Color.BLACK;
    private Insets customInsets;
    ScreenDimensions dimensions = new ScreenDimensions();
    public int windowHeight;
    public int windowWidth;

    public RoundMultilineText(String text) {
        super(text);
        windowWidth = dimensions.screenWidth;
        windowHeight = dimensions.screenHeight;
        customInsets = new Insets(10, 10, 10, 10); // Top, Left, Bottom, Right padding
        setProperties();
    }
    
    
    public RoundMultilineText(String text, int rows, int columns) {
        super(text, rows, columns);
        windowWidth = dimensions.screenWidth;
        windowHeight = dimensions.screenHeight;
        customInsets = new Insets(10, 10, 10, 10); // Top, Left, Bottom, Right padding
        setProperties();
    }

    public void setProperties() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // No external border
    }

    @Override
    public Insets getInsets() {
        return customInsets;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int arcWidth = 20;
        int arcHeight = 20;

        // Draw the rounded rectangle background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, arcWidth, arcHeight);

        // Draw the rounded rectangle border
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, width - 1, height - 1, arcWidth, arcHeight);

        // Dispose graphics context
        g2.dispose();

        // Call the parent class's paintComponent to handle the text rendering
        super.paintComponent(g);
    }
}