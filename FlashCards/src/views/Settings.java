package views;



import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Settings extends JFrame implements GlobalDesign{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		//int height = (int)screenSize.getHeight();
		int width = (int)screenSize.getWidth();
		

		//main panel
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		//toolbar on top of the window:
		JPanel toolbarPanel = new JPanel(new BorderLayout());
		toolbarPanel.setBackground(toolbarColor);
		toolbarPanel.setBounds(0, 0, getWidth(), 80);
		toolbarPanel.setLayout(new BorderLayout());
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		
		//toolbar label (name of page)
		JLabel mainTitleLabel = new JLabel("Settings");      
		mainTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		mainTitleLabel.setForeground(Color.WHITE);
		mainTitleLabel.setBorder(new EmptyBorder(15, 15, 15, 15));
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		// panel for buttons
		JPanel tbPane = new JPanel();
		tbPane.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		flowLayout.setHgap(10); 
		tbPane.setLayout(flowLayout);

		//return button in toolbar
		RoundButton returnButton = new RoundButton("", 35, 35);
		returnButton.setButtonIcon("Pictures/ReturnArrowIcon.png", 27, 27);
		returnButton.setForeground(backgroundColor);
		tbPane.add(returnButton);
		
		// user icon / button in toolbar
		RoundButton userPicture = new RoundButton("", 50, 50);
		userPicture.setButtonIcon("Pictures/UserIconBasic.png", 27, 27);
		userPicture.setForeground(backgroundColor);
		tbPane.add(userPicture);
		
		toolbarPanel.add(tbPane, BorderLayout.EAST);
		
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		
		
		
		
		 // Create panel for round buttons with images
        JPanel mainPanel = new JPanel(); // Use FlowLayout for center alignment
        mainPanel.setBounds(0, 0, getWidth(), getHeight() - 80);
        //buttonPanel.setLayout(new BorderLayout());
        mainPanel.setOpaque(false); // Make buttonPanel transparent

        // Array of image paths 
        String[] imagePaths = {
            "1.jpg",
            "2.jpg",
            "3.jpeg",
            "4.jpg",
            "5.jpg",
            "6.jpg"
        };

       // int buttonSize = 80; // Size of each round button

/*
        for (int i = 0; i < Math.min(imagePaths.length, 6); i++) {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePaths[i]));
            Image img = icon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);

            RoundButton roundButton = new RoundButton(scaledIcon);
            roundButton.setPreferredSize(new Dimension(buttonSize, buttonSize)); // Set button size
            roundButton.setBorderPainted(false); // Remove button border
            roundButton.setContentAreaFilled(false); // Remove default button background
            buttonPanel.add(roundButton);
        }
*/	
        
     // label izaberi sliku
    	JLabel lblChoose = new JLabel("Choose picture:");
    	lblChoose.setForeground(new Color(255, 255, 255));
    	lblChoose.setFont(mainFont);
    	lblChoose.setBounds(260, 10, 197, 24);
		mainPanel.add(lblChoose);
        

        for (int i = 0; i < Math.min(imagePaths.length, 6); i++) {
        	int firstPicWidth = (int)(width*0.125);
        	int firstPicHeight = (int)(width*0.125);
        	int PicWidth = (int)(width*0.1);
        	int PicHeight = (int)(width*0.1);
        	int distance = (int)(width*0.15);
        	
        	if(i == 0) {//samo slika
        		RoundButton userIcon = new RoundButton("", 0 ,0);
            	userIcon.setButtonIcon("Pictures/" + imagePaths[i], firstPicWidth, firstPicHeight);
            	userIcon.setBounds(36+(i)*distance, 20, firstPicWidth, firstPicHeight);
            	userIcon.setOpaque(false);
            	userIcon.setContentAreaFilled(false);
            	userIcon.setBorderPainted(false);
            	userIcon.setBorder(BorderFactory.createEmptyBorder());
            	userIcon.setFocusPainted(false);
            	userIcon.setBackground(new Color(0,0,0,0));
            	mainPanel.add(userIcon);   	
        	}else {//button i sivi rb i plavi na oznacenim
           		RoundButton userIcon = new RoundButton("", 0, 0);
            	userIcon.setButtonIcon("Pictures/" + imagePaths[i], PicWidth, PicHeight);
            	userIcon.setBounds(40+(i)*distance, 50, PicWidth, PicHeight);
            	userIcon.setOpaque(false);
            	userIcon.setContentAreaFilled(false);
            	userIcon.setBorderPainted(false);
            	userIcon.setBackground(new Color(0,0,0,0));
            	mainPanel.add(userIcon);
        	}
        }
        
  
 
		
		JPanel buttons = new JPanel();
		buttons.setBackground(backgroundColor);
		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		mainPanel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(mainFont);
		lblUsername.setBounds(36, 341, 197, 24);
		mainPanel.add(lblUsername);
		
		textField = new JTextField();
		textField.setFont(secFont);
		textField.setBounds(36, 369, 228, 33);
		mainPanel.add(textField);
		textField.setColumns(10);
		
		//button change username
		JButton changeUsername = new JButton("Change username");
		changeUsername.setForeground(Color.BLACK);
		changeUsername.setFont(secFont);
		changeUsername.setBounds(317, 369, 205, 33);
		changeUsername.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		changeUsername.setBackground(new Color(248, 248, 255));
		mainPanel.add(changeUsername);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(mainFont);
		lblEmail.setBounds(36, 259, 350, 24);
		mainPanel.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setBounds(36, 293, 490, 33);
		mainPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lbPassword = new JLabel("Password:");
		lbPassword.setForeground(new Color(255, 255, 255));
		lbPassword.setFont(mainFont);
		lbPassword.setBounds(36, 412, 177, 24);
		mainPanel.add(lbPassword);
		
		//button change password
		JButton changePassword = new JButton("Change password");
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		/* changePassword.setPreferredSize(new Dimension(20, 21));
		changePassword.setMinimumSize(new Dimension(20, 21));
		changePassword.setForeground(Color.BLACK);
		changePassword.setBackground(new Color(248, 248, 255));
		changePassword.setFont(secFont);
		changePassword.setBounds(10, 107, 197, 33);
		changePassword.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mainPanel.add(changePassword);
		*/
		
		//JButton changePassword = new JButton("Change passwords");
		changePassword.setForeground(Color.BLACK);
		changePassword.setFont(secFont);
		changePassword.setBounds(36, 450, 490, 33);
		changePassword.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		changePassword.setBackground(new Color(248, 248, 255));
		mainPanel.add(changePassword);
		
		// panel for save and cancel buttons
		JPanel scButtons = new JPanel();
		scButtons.setBackground(backgroundColor);
		scButtons.setBounds(0, 0, getWidth(), 80);
		scButtons.setLayout(new BorderLayout());
		
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
		
	
		contentPane.add(mainPanel, BorderLayout.CENTER);
		scButtons.add(buttons, BorderLayout.EAST);
		contentPane.add(scButtons, BorderLayout.SOUTH);
	}
}