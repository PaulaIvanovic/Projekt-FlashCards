

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;


public class Settings extends JFrame implements Template{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel scButtons;
	private JTextField textField;
	private JTextField textField_1;
	

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
		setTitle("SETTINGS");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		 // Create panel for round buttons with images
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Use FlowLayout for center alignment
        buttonPanel.setOpaque(false); // Make buttonPanel transparent
        contentPane.add(buttonPanel, BorderLayout.NORTH);

        // Array of image paths 
        String[] imagePaths = {
            "/nika.png",
            "/slika.jpg",
            "/slika.jpg",
            "/slika.jpg",
            "/slika.jpg",
            "/slika.jpg"
        };

        int buttonSize = 80; // Size of each round button

        for (int i = 0; i < Math.min(imagePaths.length, 6); i++) {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePaths[i]));
            Image img = icon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);

            JButton roundButton = new JButton(scaledIcon);
            roundButton.setPreferredSize(new Dimension(buttonSize, buttonSize)); // Set button size
            roundButton.setBorderPainted(false); // Remove button border
            roundButton.setContentAreaFilled(false); // Remove default button background
            buttonPanel.add(roundButton);
        }
		
		JPanel scButtons = new JPanel();
		scButtons.setBackground(backgroundColor);
		scButtons.setLayout(new BorderLayout());
		
		JPanel buttons = new JPanel();
		buttons.setBackground(backgroundColor);
		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
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
		
		//button change username
		JButton changeUsername = new JButton("Change username");
		changeUsername.setForeground(Color.BLACK);
		changeUsername.setFont(secFont);
		changeUsername.setBounds(317, 369, 203, 33);
		changeUsername.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		changeUsername.setBackground(new Color(248, 248, 255));
		contentPane.add(changeUsername);
		
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
		
		//button change password
		JButton changePassword = new JButton("Change password");
		changePassword.setPreferredSize(new Dimension(63, 21));
		changePassword.setMinimumSize(new Dimension(63, 21));
		changePassword.setMaximumSize(new Dimension(63, 21));
		changePassword.setForeground(Color.BLACK);
		changePassword.setBackground(new Color(248, 248, 255));
		changePassword.setFont(secFont);
		changePassword.setBounds(36, 446, 203, 33);
		changePassword.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.add(changePassword, BorderLayout.SOUTH);
		
		//button cancel
		JButton cancel = new JButton("Cancel");
		cancel.setForeground(Color.BLACK);
		cancel.setBackground(new Color(248, 248, 255));
		cancel.setFont(secFont);
		
		//button save changes
		JButton save = new JButton("Save changes");
		save.setForeground(Color.BLACK);
		save.setBackground(new Color(248, 248, 255));
		save.setFont(secFont);
		
		buttons.add(cancel);
		buttons.add(save);
		
	
	
		scButtons.add(buttons, BorderLayout.EAST);
		contentPane.add(scButtons, BorderLayout.SOUTH);
	
	}
}
