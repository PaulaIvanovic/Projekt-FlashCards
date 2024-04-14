package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GroupOfCardsPage extends JFrame  implements GlobalDesign {
	private static final long serialVersionUID = 1L;
	 private JPanel contentPane;
	
	
	public GroupOfCardsPage() {
		setTitle("GROUP OF CARDS PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 530);
        
        contentPane = new JPanel();
        contentPane = (JPanel) getContentPane(); 
        contentPane.setBackground(new Color(69, 62, 130));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(159, 19, 496, 464);
        contentPane.add(panel_1);
        panel_1.setBackground(new Color(69, 62, 130));
        panel_1.setLayout(new GridBagLayout());
        panel_1.setLayout(null);
	}
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GroupOfCardsPage frame = new GroupOfCardsPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
