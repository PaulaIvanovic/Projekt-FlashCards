package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GroupPage extends JFrame implements GlobalDesign{
    public GroupPage(String text, Color bgColor) {
        setTitle(text + " group page");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //closes only this window, not the whole app
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(bgColor);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
        
		//dummy text to test clickable rectangles
        JLabel label = new JLabel(text);
        label.setFont(secFont);
        label.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(label, BorderLayout.CENTER);
    }
}
