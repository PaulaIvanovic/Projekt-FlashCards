package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

class RoundedButton extends JButton {
        public RoundedButton(String label) {
            super(label);
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
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }

        Shape shape;

        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
            return shape.contains(x, y);
        }
    }