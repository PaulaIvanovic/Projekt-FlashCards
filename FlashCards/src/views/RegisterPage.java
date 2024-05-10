package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import services.Register;

public class RegisterPage extends JFrame implements GlobalDesign{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JTextField emailField;
	private JPasswordField pass;
	
	ScreenDimensions dimensions = new ScreenDimensions();
	
	
	public RegisterPage() {
		//set icon for app
        java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
        ImageIcon Icon = new ImageIcon(IconURL);
        setIconImage(Icon.getImage());
        
		//calculated variables for window height and width
        int desiredHeight = (int) (dimensions.screenHeight * 0.8);
        int desiredWidth = (int) (dimensions.screenWidth * 0.4);

        // Create a custom title bar panel
        JPanel titleBarPanel = new JPanel();
        titleBarPanel.setBackground(new Color(69, 62, 130)); // Set the background color of the title bar
        titleBarPanel.setLayout(new BorderLayout());

        // Add a close button
        JButton closeButton = new JButton("X");
        closeButton.setBackground(new Color(69, 62, 130)); 
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorder(null); 
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        titleBarPanel.add(closeButton, BorderLayout.EAST);

        // Set the custom title bar
        setUndecorated(true);
        getContentPane().add(titleBarPanel, BorderLayout.NORTH);
        
        setTitle("REGISTER PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, desiredWidth, desiredHeight);
		setResizable(false); 
	    setLocationRelativeTo(null);
	    setVisible(true);

		//main panel
		contentPane = new JPanel();
		contentPane = (JPanel) getContentPane();
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//panel for labels and buttons
		JPanel panel_1 = new JPanel();
		panel_1.setBounds((int)(desiredWidth * 0.115), (int)(desiredHeight * 0.115), desiredWidth, desiredHeight);
        contentPane.add(panel_1);
		panel_1.setBackground(new Color(69, 62, 130));
		panel_1.setLayout(null);

		//adding the logo
        JLabel photoLabel = new JLabel("");
        Image IMG = new ImageIcon(this.getClass().getResource("Pictures/logo.png"))
        		.getImage().getScaledInstance((int)(desiredWidth * 0.125), (int)(desiredHeight * 0.11),Image.SCALE_SMOOTH);
        photoLabel.setIcon(new ImageIcon(IMG));
        photoLabel.setBounds((int)(desiredWidth * 0.01), (int)(desiredHeight * 0.05), (int)(desiredWidth * 0.115), (int)(desiredHeight * 0.115));
        panel_1.add(photoLabel);
		
        //adding label for title
        JLabel lblNewLabel = new JLabel("FLASH CARDS");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(mainTitle);
        lblNewLabel.setBounds((int)(desiredWidth * 0.15), (int)(desiredHeight * 0.05), (int)(desiredWidth * 0.6), (int)(desiredHeight * 0.115));
        panel_1.add(lblNewLabel);
		
		
        //adding label for username
        JLabel usernameText = new JLabel("Username:");
        usernameText.setFont(secFont);
        usernameText.setForeground(Color.WHITE);
        usernameText.setBounds((int)(desiredWidth * 0.1), (int)(desiredHeight * 0.235), (int)(desiredWidth * 0.26), (int)(desiredHeight * 0.035));
        panel_1.add(usernameText);
          
		//username input
        user = new RoundTextField(0);
        user.setFont(inputText);
        user.setBounds((int)(desiredWidth * 0.1), (int)(desiredHeight * 0.285), (int)(desiredWidth * 0.55), (int)(desiredHeight * 0.04));
		panel_1.add(user);
		
		//username placeholder
		JLabel userPlaceholder = new JLabel("Enter your username");
		userPlaceholder.setForeground(Color.GRAY);
		panel_1.add(userPlaceholder);

		user.setText("Enter your username");

		user.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		        if (user.getText().equals("Enter your username")) {
		            user.setText(""); //deleting the text only if "Enter your username" is there
		        }
		        userPlaceholder.setVisible(false); //hiding the label when the input text field for username is focused
		    }

		    public void focusLost(FocusEvent e) {
		        if (user.getText().isEmpty()) {
		            user.setText("Enter your username"); //automatic text if there is no input 
		            userPlaceholder.setVisible(true); //repeatedly showing the label if there is no input in username field
		        }
		    }
		});
	
        
        //adding label for email
      	JLabel emailText = new JLabel("Email:");
      	emailText.setFont(secFont);
      	emailText.setForeground(Color.WHITE);
      	emailText.setForeground(new Color(255, 255, 255));
      	emailText.setBounds((int)(desiredWidth * 0.1), (int)(desiredHeight * 0.465), (int)(desiredWidth * 0.26), (int)(desiredHeight * 0.035));
        panel_1.add(emailText);
		
        //making round text field for email
		RoundTextField emailField = new RoundTextField(0);
		emailField.setFont(inputText);
		emailField.setBounds((int)(desiredWidth * 0.1), (int)(desiredHeight * 0.515), (int)(desiredWidth * 0.55), (int)(desiredHeight * 0.04));
		panel_1.add(emailField);

        
        //adding label for entering the email
		JLabel emailPlaceholder = new JLabel("Enter your email");
		emailPlaceholder.setForeground(Color.GRAY);
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
		
		//adding label for password
        JLabel textpass = new JLabel("Password:");
        textpass.setFont(secFont);
        textpass.setForeground(Color.WHITE);
        textpass.setBounds((int)(desiredWidth * 0.1), (int)(desiredHeight * 0.35), (int)(desiredWidth * 0.26), (int)(desiredHeight * 0.035));
        panel_1.add(textpass);
        
        //making round password field
		RoundPasswordField pass = new RoundPasswordField(0);
	    pass.setFont(inputText);
	    pass.setBounds((int)(desiredWidth * 0.1), (int)(desiredHeight * 0.4), (int)(desiredWidth * 0.55), (int)(desiredHeight * 0.04));
	    pass.setEchoChar('\u2022');
	    panel_1.add(pass);
	    
	    //making label for entering the password
		JLabel passPlaceholder = new JLabel("Enter your password");
		passPlaceholder.setForeground(Color.GRAY);
		panel_1.add(passPlaceholder); 
		
		//text "Enter your password" in textfield
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

		//password insert function
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


        //message these fields can not be empty
      	JLabel lblNewLabel_3 = new JLabel("");
      	lblNewLabel_3.setBounds((int)(desiredWidth * 0.1), (int)(desiredHeight * 0.565), (int)(desiredWidth * 0.7), (int)(desiredHeight * 0.035));
      	panel_1.add(lblNewLabel_3);
      	lblNewLabel_3.setFont(tinyFont);
      	lblNewLabel_3.setForeground(textRed);
      	
		
		//message Already have an account?
        JLabel lblNewLabel_2 = new JLabel("Already have an account?");
        lblNewLabel_2.setFont(smallFont);
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBounds((int)(desiredWidth * 0.1), (int)(desiredHeight * 0.665), (int)(desiredWidth * 0.335), (int)(desiredHeight * 0.035));
        panel_1.add(lblNewLabel_2);
		
		//register button function
		RoundedButton registerBtn = new RoundedButton("Register");
		registerBtn.setFont(buttonText);
		registerBtn.setBounds((int)(desiredWidth * 0.4955), (int)(desiredHeight * 0.61), (int)(desiredWidth * 0.15), (int)(desiredHeight * 0.035));
        panel_1.add(registerBtn);
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = user.getText();
				String email = emailField.getText();
				String password = new String(pass.getPassword());
				Register r = new Register(username, password, email);
				lblNewLabel_3.setText(r.AddUser());// calling register logic
            	if (r.message.equals("Registration completed.")) {
                    // Show message and then open the next window
                    //JOptionPane.showMessageDialog(null, r.message, "Registration", JOptionPane.INFORMATION_MESSAGE);
                    GroupOfCardsPage groupOfCardsPage = new GroupOfCardsPage();
                    // Optionally, close the current window
                    ((JFrame) SwingUtilities.getWindowAncestor(panel_1)).dispose();
                } 
			}
		});
		registerBtn.setForeground(Color.BLACK);
		
		
		// ActionListener for text fields
        ActionListener textFieldListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Trigger button click event when Enter is pressed in any text field
            	registerBtn.doClick();
            }
        };
        
        pass.addActionListener(textFieldListener);
        user.addActionListener(textFieldListener);
        emailField.addActionListener(textFieldListener);
		
		//login button function
		RoundedButton loginBtn = new RoundedButton("Login");
		loginBtn.setFont(buttonText);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage login = new LoginPage();
				login.setVisible(true);
				dispose();
			}
		});
		loginBtn.setForeground(Color.BLACK);
		loginBtn.setBounds((int)(desiredWidth * 0.4955), (int)(desiredHeight * 0.665), (int)(desiredWidth * 0.15), (int)(desiredHeight * 0.035));
        panel_1.add(loginBtn);
		
	}
	
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
}