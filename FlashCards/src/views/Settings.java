package views;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.Component;


public class Settings extends JFrame implements GlobalDesign{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel scButtons;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnOdustani;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Settings() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("SETTINGS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		scButtons = new JPanel();
		scButtons.setBackground(backgroundColor);
		//scButtons.setLayout(new BorderLayout());
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(mainFont);
		lblUsername.setBounds(36, 341, 197, 24);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setFont(secFont);
		textField.setBounds(36, 369, 228, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(mainFont);
		lblEmail.setBounds(36, 259, 98, 24);
		contentPane.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setBounds(36, 293, 330, 33);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lbPassword = new JLabel("Password:");
		lbPassword.setForeground(new Color(255, 255, 255));
		lbPassword.setFont(mainFont);
		lbPassword.setBounds(36, 412, 177, 24);
		contentPane.add(lbPassword);
		
		JLabel lblDefaultSlika = new JLabel("default slika");
		//tu nesto ne dela
		/* Image defaultSlika = new ImageIcon(this.getClass().getResource("default.png")).getImage();
		lblDefaultSlika.setIcon(new ImageIcon(defaultSlika)); */
		lblDefaultSlika.setBounds(36, 49, 81, 102);
		contentPane.add(lblDefaultSlika);
		
		contentPane.add(scButtons,BorderLayout.SOUTH);
	
	
	}
}
