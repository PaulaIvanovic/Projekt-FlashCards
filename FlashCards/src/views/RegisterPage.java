package views;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
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
        setTitle("REGISTER PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 530);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(69, 62, 130));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(69, 62, 130));
        panel.setBounds(188, 29, 496, 464);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblFlashCards = new JLabel("FLASH CARDS");
        lblFlashCards.setForeground(new Color(255, 255, 255));
        lblFlashCards.setFont(new Font("Nirmala UI", Font.BOLD, 54));
        lblFlashCards.setBounds(112, 30, 374, 81);
        panel.add(lblFlashCards);

        JLabel photoLabel = new JLabel("");
        Image IMG = new ImageIcon(this.getClass().getResource("/logo.png")).getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        photoLabel.setIcon(new ImageIcon(IMG));
        photoLabel.setBounds(10, 30, 78, 81);
        panel.add(photoLabel);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblUsername.setBounds(88, 126, 160, 25);
        panel.add(lblUsername);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEmail.setBounds(88, 190, 160, 25);
        panel.add(lblEmail);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPassword.setBounds(88, 254, 160, 25);
        panel.add(lblPassword);

        usernameField = new JTextField();
        usernameField.setBounds(88, 161, 250, 19);
        panel.add(usernameField);
        usernameField.setColumns(10);

        emailField = new JTextField();
        emailField.setBounds(88, 225, 250, 19);
        panel.add(emailField);
        emailField.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Already have an account?");
        lblNewLabel_2.setForeground(new Color(255, 0, 0));
        lblNewLabel_2.setBounds(47, 396, 205, 13);
        panel.add(lblNewLabel_2);

        passwordField = new JPasswordField();
        passwordField.setBounds(88, 289, 250, 19);
        panel.add(passwordField);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add registration functionality here
            }
        });
        btnRegister.setBounds(262, 339, 85, 21);
        panel.add(btnRegister);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginPage login = new LoginPage();
                login.setVisible(true);
                dispose();
            }
        });
        btnLogin.setBounds(262, 392, 85, 21);
        panel.add(btnLogin);
    }
}
