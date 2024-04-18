package views;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LoginPage extends JFrame implements GlobalDesign{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField user;
    private JPasswordField pass;
    private JLabel passPlaceholder; 

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
   
    public LoginPage() {
    	//adding a name to the title bar
        setTitle("LOGIN PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 530);
        setResizable(false); 

        contentPane = new JPanel();
        contentPane = (JPanel) getContentPane();
        contentPane.setBackground(backgroundColor);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
       
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(159, 19, 496, 464);
        contentPane.add(panel_1);
        panel_1.setBackground(new Color(69, 62, 130));
        panel_1.setLayout(new GridBagLayout());
        panel_1.setLayout(null);
        
        //username:
        JLabel usernameText = new JLabel("Username:");
        usernameText.setFont(new Font("Tahoma", Font.BOLD, 12));
        usernameText.setForeground(Color.WHITE);
        usernameText.setBounds(82, 165, 160, 25);
        panel_1.add(usernameText);
        
        //password:
        JLabel textpass = new JLabel("Password:");
        textpass.setFont(new Font("Tahoma", Font.BOLD, 12));
        textpass.setForeground(Color.WHITE);
        textpass.setBounds(82, 229, 85, 13);
        panel_1.add(textpass);
        
        //message Already have an account?
        JLabel lblNewLabel_2 = new JLabel("Already have an account?");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBounds(82, 391, 207, 13);
        panel_1.add(lblNewLabel_2);
        
        //login button
        RoundedButton loginBtn = new RoundedButton("Login");
        loginBtn.setForeground(Color.BLACK);
        //loginBtn.setFont(secFont);
        loginBtn.setBounds(270, 330, 85, 21);
        panel_1.add(loginBtn);
        
        //register button function
        RoundedButton registerBtn = new RoundedButton("Register");
        registerBtn.setForeground(Color.BLACK);
        registerBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                RegisterPage register = new RegisterPage();
                register.setVisible(true);
                dispose();
            }
        });
        registerBtn.setBounds(270, 388, 85, 21);
        panel_1.add(registerBtn);
        
        user = new RoundTextField(10);
        user.setBounds(82, 195, 273, 25);
        user.setText("Enter your name");
        
        //text inside of username: field
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
        
        RoundPasswordField pass = new RoundPasswordField(10);
        pass.setBounds(82, 252, 273, 25);
        pass.setEchoChar('\u2022');
        panel_1.add(pass);

        passPlaceholder = new JLabel("Enter your password");
        passPlaceholder.setForeground(Color.GRAY);
        passPlaceholder.setBounds(82, 252, 273, 19);
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
        lblNewLabel.setBounds(112, 30, 374, 81);
        panel_1.add(lblNewLabel);
        
        //message these fields can not be empty
      	JLabel lblNewLabel_3 = new JLabel("* these fields can not be empty");
      	lblNewLabel_3.setBounds(82, 280, 192, 25);
      	panel_1.add(lblNewLabel_3);
      	lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 12));
      	lblNewLabel_3.setForeground(textRed);
      		
        JLabel photoLabel = new JLabel("");
        Image IMG = new ImageIcon(this.getClass().getResource("logo.png")).getImage().getScaledInstance(90, 90,Image.SCALE_DEFAULT);
		photoLabel.setIcon(new ImageIcon(IMG));
        photoLabel.setBounds(10, 30, 78, 81);
        panel_1.add(photoLabel);
        
        centerFrame(this);
    }
    
    //center a window on the screen
    private static void centerFrame(Window window) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getSize();
        int x = (screenSize.width - windowSize.width) / 2;
        int y = (screenSize.height - windowSize.height) / 2;
        window.setLocation(x, y);
    }
    
}

