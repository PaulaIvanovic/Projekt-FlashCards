package views;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import views.ScreenDimensions;
import databaseInfo.PullFrom;

public class LoginPage extends JFrame implements GlobalDesign{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField user;
    private JPasswordField pass;
    private JLabel passPlaceholder; 
    
    ScreenDimensions dimensions = new ScreenDimensions();
   
    public LoginPage() {
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

        setTitle("LOGIN PAGE");
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
        user.setText("Enter your name");
        
        //text inside of username field
        user.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (user.getText().equals("Enter your name")) {
                    user.setText(""); 
                }
            }

            public void focusLost(FocusEvent e) {
                if (user.getText().isEmpty()) {
                    user.setText("Enter your name");
                }
            }
        });
        panel_1.add(user);
        
        
        
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

        passPlaceholder = new JLabel("Enter your password");
        passPlaceholder.setForeground(Color.GRAY);
        panel_1.add(passPlaceholder); 

        // "Enter your password" in text field
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
        
        //login button function
        RoundedButton loginBtn = new RoundedButton("Login");
        loginBtn.setFont(buttonText);
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setBounds((int)(desiredWidth * 0.4955), (int)(desiredHeight * 0.475), (int)(desiredWidth * 0.15), (int)(desiredHeight * 0.035));
        panel_1.add(loginBtn);
        loginBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                GroupOfCardsPage GroupsWindow = new GroupOfCardsPage();
        		GroupsWindow.setVisible(true);
        		dispose();
            }
        });
     

        
        //register button function
        RoundedButton registerBtn = new RoundedButton("Register");
        registerBtn.setFont(buttonText);
        registerBtn.setForeground(Color.BLACK);
        registerBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                RegisterPage register = new RegisterPage();
                register.setVisible(true);
                dispose();
            }
        });
        registerBtn.setBounds((int)(desiredWidth * 0.4955), (int)(desiredHeight * 0.5675), (int)(desiredWidth * 0.15), (int)(desiredHeight * 0.035));
        panel_1.add(registerBtn);
        

        //message "these fields can not be empty"
      	JLabel lblNewLabel_3 = new JLabel("* these fields can not be empty");
      	lblNewLabel_3.setBounds((int)(desiredWidth * 0.1), (int)(desiredHeight * 0.45), (int)(desiredWidth * 0.315), (int)(desiredHeight * 0.035));
      	panel_1.add(lblNewLabel_3);
      	lblNewLabel_3.setFont(tinyFont);
      	lblNewLabel_3.setForeground(textRed);
      	
      	//message "Don't have an account?"
      	JLabel lblNewLabel_2 = new JLabel("Don't have an account?");
      	lblNewLabel_2.setFont(smallFont);
      	lblNewLabel_2.setForeground(new Color(255, 255, 255));
      	lblNewLabel_2.setBounds((int)(desiredWidth * 0.1), (int)(desiredHeight * 0.565), (int)(desiredWidth * 0.335), (int)(desiredHeight * 0.035));
      	panel_1.add(lblNewLabel_2);
    }
    
    
    //launching a GUI application
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage frame = new LoginPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}

