package views;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class LoginPage extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField user;
    private JPasswordField pass;

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
        setTitle("LOGIN PAGE");
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

        user = new JTextField();
        user.setBounds(82, 195, 273, 19);
        panel.add(user);
        user.setColumns(10);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(270, 319, 85, 21);
        panel.add(btnLogin);

        pass = new JPasswordField();
        pass.setBounds(82, 246, 273, 19);
        panel.add(pass);

        JLabel usernameText = new JLabel("Username:");
        usernameText.setFont(new Font("Tahoma", Font.BOLD, 12));
        usernameText.setForeground(Color.WHITE);
        usernameText.setBounds(82, 161, 160, 25);
        panel.add(usernameText);

        JLabel textpass = new JLabel("Password:");
        textpass.setFont(new Font("Tahoma", Font.BOLD, 12));
        textpass.setForeground(Color.WHITE);
        textpass.setBounds(82, 224, 85, 13);
        panel.add(textpass);

        JLabel lblNewLabel_2 = new JLabel("Do not hane an account?");
        lblNewLabel_2.setForeground(new Color(255, 0, 0));
        lblNewLabel_2.setBounds(47, 396, 205, 13);
        panel.add(lblNewLabel_2);

        JButton btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterPage register = new RegisterPage();
                register.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setBounds(270, 388, 85, 21);
        panel.add(btnNewButton);

        JLabel lblNewLabel_3 = new JLabel("FLASH CARDS");
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Nirmala UI", Font.BOLD, 54));
        lblNewLabel_3.setBounds(112, 30, 374, 81);
        panel.add(lblNewLabel_3);

        JLabel photoLabel = new JLabel("");
        Image IMG = new ImageIcon(this.getClass().getResource("/logo.png")).getImage().getScaledInstance(90, 90,Image.SCALE_DEFAULT);
        photoLabel.setIcon(new ImageIcon(IMG));
        photoLabel.setBounds(10, 30, 78, 81);
        panel.add(photoLabel);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
