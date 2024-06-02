package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PopUpWindow extends JFrame implements GlobalDesign{

    public PopUpWindow(String title, String text, int width, int height) {
    	views.WindowElementResize.getFontForWindowSize(width);
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());
		

		setResizable(false); 
    	setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //closes only this window, not the whole app
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();	
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(backgroundColor);
        
		//dummy text to test clickable rectangles
        JLabel label = new JLabel("<html><font color = 'white'"+text+"</font></html>");
        label.setBounds(5, 5, width, height);
        label.setFont(WindowElementResize.mediumFont);
        label.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(label, BorderLayout.CENTER);
    }
}
