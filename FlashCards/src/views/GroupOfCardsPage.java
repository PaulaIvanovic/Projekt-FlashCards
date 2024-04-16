package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class GroupOfCardsPage extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;

    public GroupOfCardsPage() {
        this.setTitle("GROUP OF CARDS PAGE");
        this.setSize(900, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);	//to center the frame on screen
        
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
		
		// panel with all required buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		flowLayout.setHgap(10); 
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
		
		toolbarPanel.add(buttonPanel, BorderLayout.EAST);
		
		
		//rectangles representing groups of cards
		contentPane.add(new DrawGroupRectangles());
		
		
    }
    
    //class to draw group rectangles (5 rectangles in 1 row)
    class DrawGroupRectangles extends JComponent {
    	  
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            
            int rectWidth = 200;
            int rectHeight = 120;
            int gap = 70; 
            int startX = 100; 
            int startY = 150; 
            
            //to make rectangle edges rounded
            int arcWidth = 30;
            int arcHeight = 30;

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    int x = startX + j * (rectWidth + gap);
                    int y = startY + i * (rectHeight + gap);
                    
                    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(x, y, rectWidth, rectHeight, arcWidth, arcHeight);
                    
                    g2.setColor(groupColors[i * 5 + j]);
                    g2.fill(roundedRectangle);
                }
            }
        }
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
