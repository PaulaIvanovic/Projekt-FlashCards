package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PopUpWindow extends JFrame{

    public PopUpWindow(String text, int width, int height) {
    	
    	views.WindowElementResize.getFontForWindowSize(height);
    	setTitle(text);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //closes only this window, not the whole app
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();	
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
        
		//dummy text to test clickable rectangles
        JLabel label = new JLabel(text);
        label.setFont(WindowElementResize.mainFont);
        label.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(label, BorderLayout.CENTER);
    }
}
