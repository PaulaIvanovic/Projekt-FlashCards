package views;

import java.awt.Component;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class InfoButtonPopUp {
	
	public static void setButton(String message, JButton button) {
		button.setEnabled(false);
		button.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	showPopup(message, button);
		    }
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	hidePopup();
		    }
		});
	}
	
	public static void showPopup(String message, Component component) {
        hidePopup();
        JWindow popup = new JWindow();
        popup.setName("popup"); 
        
        JLabel label = new JLabel("<html>" + message.replaceAll("\n", "<br>") + "</html>");
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setHorizontalAlignment(JLabel.CENTER); 
        label.setVerticalAlignment(JLabel.CENTER);
        
        popup.add(label);
        popup.pack();
    
        Point buttonLocation = component.getLocationOnScreen();

        popup.setLocation(buttonLocation.x - popup.getWidth(), buttonLocation.y);
    
        popup.setVisible(true);
    } 
	public static void hidePopup() {
        for (Window window : Window.getWindows()) {
            if (window instanceof JWindow && window.getName() != null && window.getName().equals("popup")) {
                window.dispose();
            }
        }
    }


}
