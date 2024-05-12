package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

import views.ScreenDimensions;

class RoundedButton extends JButton {
	
		ScreenDimensions dimensions = new ScreenDimensions();
		int windowWidth;
		int windowHeight;
		String help; //used for storing groupIDs
	
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
        
        // Method to set multi-line text on button
        public void setMultiLineButtonText(String text) {
        	JButton button = this;
        	// Get button width
            int buttonWidth = button.getWidth();
            // Get font metrics
            FontMetrics fontMetrics = button.getFontMetrics(button.getFont());
            // Calculate text width
            int textWidth = fontMetrics.stringWidth(text);

            // Check if text fits within button width
            if (textWidth >= buttonWidth) {
                // Calculate number of characters per line
                int charsPerLine = text.length();
                int spaceIndex = text.lastIndexOf(' ', buttonWidth / fontMetrics.charWidth(' '));
                if (spaceIndex != -1) {
                    charsPerLine = spaceIndex + 1;
                }
                // Calculate number of lines
                int numLines = (int) Math.ceil((double) text.length() / charsPerLine);
                // Create multi-line text
                StringBuilder multiLineText = new StringBuilder();
                int start = 0;
                for (int i = 0; i < numLines; i++) {
                    int end = Math.min(start + charsPerLine, text.length());
                    multiLineText.append(centerText(text.substring(start, end), buttonWidth, fontMetrics));
                    multiLineText.append("<br>");
                    start = end;
                }
                // Set multi-line text on button
                button.setText("<html>" + multiLineText.toString() + "</html>");
            } else {
                // Text fits within button width, set as it is
                button.setText(text);
            }
        }

        // Method to center text within given width
        private static String centerText(String text, int width, FontMetrics fontMetrics) {
        	   StringBuilder centeredText = new StringBuilder();

        	    // Calculate the width of the text
        	    int textWidth = fontMetrics.stringWidth(text);

        	    // Calculate the number of spaces needed for padding on each side
        	    int padding = (width - textWidth) / 2;

        	    // Add padding spaces before the text
        	    for (int i = 0; i < padding; i++) {
        	        centeredText.append(' ');
        	    }

        	    // Append the text
        	    centeredText.append(text);

        	    return centeredText.toString();
        }
    }
