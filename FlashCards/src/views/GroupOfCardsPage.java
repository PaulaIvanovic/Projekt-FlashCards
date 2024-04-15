package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class GroupOfCardsPage extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;
	private RoundButton editButton;



    public GroupOfCardsPage() {
        this.setTitle("GROUP OF CARDS PAGE");
        this.setSize(900, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 800, 530);
        
        //main panel
        contentPane = new JPanel();
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
		JLabel mainTitleLabel = new JLabel("My groups of cards");      
		mainTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		mainTitleLabel.setForeground(Color.WHITE);
		mainTitleLabel.setBorder(new EmptyBorder(15, 15, 15, 15));
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		// Create a panel to hold the buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		flowLayout.setHgap(10); // Set horizontal gap between buttons
		buttonPanel.setLayout(flowLayout);

		//edit button in toolbar
		RoundButton editButton = new RoundButton("", 35, 35);
		editButton.setButtonIcon("edit.png");
		editButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		editButton.setForeground(backgroundColor);
		buttonPanel.add(editButton);

		//add new group button in toolbar
		RoundButton addGroupButton = new RoundButton("", 35, 35);
		addGroupButton.setButtonIcon("add.png");
		addGroupButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		addGroupButton.setForeground(backgroundColor);
		buttonPanel.add(addGroupButton);
		
		//settings button in toolbar
		RoundButton settingsButton = new RoundButton("", 35, 35);
		settingsButton.setButtonIcon("settings.png");
		settingsButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		settingsButton.setForeground(backgroundColor);
		buttonPanel.add(settingsButton);
		
		//user icon / button in toolbar
		RoundButton userIcon = new RoundButton("", 50, 50);
		userIcon.setButtonIcon("user.png");
		userIcon.setFont(new Font("Tahoma", Font.BOLD, 10));
		userIcon.setForeground(backgroundColor);
		buttonPanel.add(userIcon);

		// Add the button panel to the east side of the toolbar panel
		toolbarPanel.add(buttonPanel, BorderLayout.EAST);
		 
    }
    
	public static void main(String[] args){
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
