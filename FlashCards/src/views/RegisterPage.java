package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Shape;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class RegisterPage extends JFrame implements GlobalDesign{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JTextField emailField;
	private JPasswordField pass;

	 //launching a GUI application
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage frame = new RegisterPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public RegisterPage() {
		//adding a name to the title bar
		setTitle("REGISTER PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); 
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	       int screenHeight = screenSize.height;
	       int screenWidth = screenSize.width;
	        
	       int desiredHeight = (int) (screenHeight * 0.8);
	       int desiredWidth = (int) (screenWidth * 0.4);

	       setSize(desiredWidth, desiredHeight);
	       setLocationRelativeTo(null);
	       setVisible(true);

		
		contentPane = new JPanel();
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(70, 80, 496, 464);
		contentPane.add(panel_1);
		panel_1.setBackground(new Color(69, 62, 130));
		panel_1.setLayout(null);
		
		//username:
		JLabel usernameText = new JLabel("Username:");
		usernameText.setFont(secFont);
		usernameText.setForeground(Color.WHITE);
		usernameText.setBounds(82, 165, 160, 25);
		panel_1.add(usernameText);
		
		
		//password:
		JLabel textpass = new JLabel("Password:");
		textpass.setFont(secFont);
		textpass.setForeground(Color.WHITE);
		textpass.setBounds(82, 278, 85, 13);
		panel_1.add(textpass);
		
		//message Already have an account?
		JLabel lblNewLabel_2 = new JLabel("Already have an account?");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(82, 391, 207, 13);
		panel_1.add(lblNewLabel_2);
		
		//login button function
		RoundedButton loginBtn = new RoundedButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage login = new LoginPage();
				login.setVisible(true);
				dispose();
			}
		});
		loginBtn.setForeground(Color.BLACK);
		loginBtn.setBounds(270, 392, 85, 21);
		panel_1.add(loginBtn);
		
		//email:
		JLabel emailText = new JLabel("Email:");
		emailText.setFont(secFont);
		emailText.setForeground(Color.WHITE);
		emailText.setForeground(new Color(255, 255, 255));
		emailText.setBounds(82, 226, 85, 13);
		panel_1.add(emailText);
		
		RoundTextField user = new RoundTextField(10);
		user.setColumns(10);
		user.setBounds(82, 195, 273, 25);
		user.setMargin(new Insets(5, 10, 5, 10));
		panel_1.add(user);
		
		JLabel userPlaceholder = new JLabel("Enter your username");
		userPlaceholder.setForeground(Color.GRAY);
		userPlaceholder.setBounds(82, 195, 273, 19);
		panel_1.add(userPlaceholder);

		user.setText("Enter your username");

		user.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		        if (user.getText().equals("Enter your username")) {
		            user.setText(""); // Briše tekst samo ako je "Enter your username" prisutno
		        }
		        userPlaceholder.setVisible(false); // Sakriva labelu kada je polje za unos korisničkog imena fokusirano
		    }

		    public void focusLost(FocusEvent e) {
		        if (user.getText().isEmpty()) {
		            user.setText("Enter your username"); // Postavljanje teksta samo ako nema unosa
		            userPlaceholder.setVisible(true); // Ponovno prikazuje labelu ako nema unosa u polju za korisničko ime
		        }
		    }
		});
		
		RoundTextField emailField = new RoundTextField(10);
		emailField.setColumns(10);
		emailField.setBounds(82, 247, 273, 25);
		emailField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panel_1.add(emailField);
		
		JLabel emailPlaceholder = new JLabel("Enter your email");
		emailPlaceholder.setForeground(Color.GRAY);
		emailPlaceholder.setBounds(82, 247, 273, 19);
		panel_1.add(emailPlaceholder); 

		//text "Enter your email" in textfield
		emailField.setText("Enter your email");

		emailField.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		        if (emailField.getText().equals("Enter your email")) {
		            emailField.setText("");
		        }
		        emailPlaceholder.setVisible(false); 
		    }

		    public void focusLost(FocusEvent e) {
		        if (emailField.getText().isEmpty()) {
		            emailField.setText("Enter your email");
		            emailPlaceholder.setVisible(true); 
		        }
		    }
		});
		
		RoundPasswordField pass = new RoundPasswordField(10);
		pass.setBounds(82, 299, 273, 25);
		panel_1.add(pass);

		JLabel passPlaceholder = new JLabel("Enter your password");
		passPlaceholder.setForeground(Color.GRAY);
		passPlaceholder.setBounds(82, 299, 273, 19);
		panel_1.add(passPlaceholder); 
		
		// text "Enter your password" in textfield
		pass.setEchoChar((char) 0); 
		pass.setText("Enter your password");

		pass.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		        pass.setEchoChar('\u2022'); 
		        if (new String(pass.getPassword()).equals("Enter your password")) {
		            pass.setText(""); 
		        }
		        passPlaceholder.setVisible(false); 
		    }

		    public void focusLost(FocusEvent e) {
		        if (pass.getPassword().length == 0) {
		            pass.setEchoChar((char) 0);
		            pass.setText("Enter your password"); 
		            passPlaceholder.setVisible(true);
		        }
		    }
		});

		pass.getDocument().addDocumentListener(new DocumentListener() {
		    public void changedUpdate(DocumentEvent e) {
		        updatePasswordVisibility();
		    }

		    public void removeUpdate(DocumentEvent e) {
		        updatePasswordVisibility();
		    }

		    public void insertUpdate(DocumentEvent e) {
		        updatePasswordVisibility();
		    }

		    private void updatePasswordVisibility() {
		        if (new String(pass.getPassword()).isEmpty()) {
		            passPlaceholder.setVisible(true);
		        } else {
		            passPlaceholder.setVisible(false);
		        }
		    }
		});

		
		
		JLabel lblNewLabel = new JLabel("FLASH CARDS");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(mainTitle);
		lblNewLabel.setBounds(100, 30, 374, 81);
		panel_1.add(lblNewLabel);
		
		//added logo photo
		JLabel photoLabel = new JLabel("");
		Image IMG = new ImageIcon(this.getClass().getResource("logo.png")).getImage().getScaledInstance(90, 90,Image.SCALE_DEFAULT);
		photoLabel.setIcon(new ImageIcon(IMG));
		photoLabel.setBounds(10, 30, 78, 81);
		panel_1.add(photoLabel);
		
		//message these fields can not be empty
		JLabel lblNewLabel_3 = new JLabel("* these fields can not be empty");
		lblNewLabel_3.setBounds(82, 330, 192, 25);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_3.setForeground(textRed);
		
		//register button function
		RoundedButton registerBtn = new RoundedButton("Register");
		registerBtn.setBounds(270, 355, 85, 21);
		panel_1.add(registerBtn);
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		registerBtn.setForeground(Color.BLACK);
	}
	}