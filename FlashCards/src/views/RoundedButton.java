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

private static final int MAX_CHARS_PER_LINE = 40;
private static final String HTML_TEMPLATE = "<html><div style='display: flex; justify-content: center; align-items: center; height: 100%%; width: 100%%; text-align: center;'>%s</div></html>";

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
        /*
        @Override
        public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            // Wrap text if it exceeds MAX_CHARS_PER_LINE characters
            String text = getText();
            if (text != null && text.length() > MAX_CHARS_PER_LINE) {
                FontMetrics fm = getFontMetrics(getFont());
                int lineHeight = fm.getHeight();
                int lines = (int) Math.ceil((double) text.length() / MAX_CHARS_PER_LINE);
            }
            return size;
        }*/
        
        public static StringBuilder insertBrAfterNChars(String str) {
            StringBuilder sb = new StringBuilder();
            int length = str.length();

            for (int i = 0; i < length; i++) {
                sb.append(str.charAt(i));
                if ((i + 1) % MAX_CHARS_PER_LINE == 0 && i != length - 1) {
                    sb.append("<br>");
                }
            }

            return sb;
        }
        
        @Override
        public void setText(String text) {
            String htmlText = String.format(HTML_TEMPLATE, text);
            super.setText(htmlText);
        }

        private String insertLineBreaks(String text) {
            StringBuilder wrappedText = new StringBuilder();
            
            //break lines by space
            String[] words = text.split(" ");
            int count = 0;
            for (String word : words) {
                if (count + word.length() > MAX_CHARS_PER_LINE) {
                    wrappedText.append("<br>");
                    count = 0;
                }
                wrappedText.append(word).append(" ");
                count += word.length() + 1; // Add 1 for the space between words
            }
            
            //if no space break by char only
            if(!text.contains(" ")) {
            	wrappedText = insertBrAfterNChars(text);
            }
            return wrappedText.toString();
        }
    
    }