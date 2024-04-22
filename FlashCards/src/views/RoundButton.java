package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RoundButton extends JButton {

	private static final long serialVersionUID = 1L;

	public RoundButton(String label, int width, int height) {
	    super(label);
	    Dimension size = getPreferredSize();
	    size.width = width;
	    size.height = height;
	    setPreferredSize(size);
	    
	    setContentAreaFilled(false);    // content area won't be filled with background color
	}
	
	//method to paint the button
    protected void paintComponent(Graphics g) {		
        if (getModel().isArmed()) {					
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 60, 60);	//makes a rounded rectangle
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 60, 60);
    }
    
    //function to get the image location, scale it down to fit the button, then set it as icon
    protected void setButtonIcon(String imageName, int scaledWidth, int scaledHeight) {
 
        java.net.URL IconURL = getClass().getResource(imageName);
        ImageIcon Icon = new ImageIcon(IconURL);
      
        Image resizedChangesIcon = Icon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        ImageIcon resizedChangesIconImage = new ImageIcon(resizedChangesIcon);
        
        this.setIcon(resizedChangesIconImage); 
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    Shape shape;

    public boolean contains(int x, int y) {		//determines if (x,y) is contained within the button (hit detection)
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 60, 60);
        }
        return shape.contains(x, y);
    }
}
