package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

import views.ScreenDimensions;

class RoundedButton extends JButton {
	
		ScreenDimensions dimensions = new ScreenDimensions();
		int windowWidth;
		int windowHeight;
	
        public RoundedButton(String label) {
            super(label);
            this.windowWidth = dimensions.screenWidth;
            this.windowHeight = dimensions.screenHeight;
            setProperties();
        }
        
        public RoundedButton(String label, int windowWidth, int windowHeight) {
            super(label);
            this.windowWidth = windowWidth;
            this.windowHeight = windowHeight;
            setProperties();
        }
        
        public void setProperties() {
            Dimension size = getPreferredSize();
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);
            setContentAreaFilled(false);
        }

        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(Color.lightGray);
            } else {
                g.setColor(getBackground());
            }
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, (int)(windowWidth * 0.015), (int)(windowHeight * 0.025));
            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, (int)(windowWidth * 0.015), (int)(windowHeight * 0.025));
        }

        Shape shape;

        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, (int)(windowWidth * 0.015), (int)(windowHeight * 0.025));
            }
            return shape.contains(x, y);
        }
    }
