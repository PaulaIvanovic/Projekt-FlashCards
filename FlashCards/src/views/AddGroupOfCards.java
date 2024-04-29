package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class AddGroupOfCards extends JFrame implements GlobalDesign{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 private JTextField groupName;
	
	  ScreenDimensions dimensions = new ScreenDimensions();


	public AddGroupOfCards() {
		//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/nika.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());
		
		//calculated variables for window height and width
	    int desiredHeight = (int) (dimensions.screenHeight * 0.8);
	    int desiredWidth = (int) (dimensions.screenWidth * 0.4);

		//adding a name to the title bar
        setTitle("NEW GROUP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 530);
        setResizable(false); 
	    setSize(desiredWidth, desiredHeight);
	    setLocationRelativeTo(null);
	    setVisible(true);  
	    
	    //main panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(backgroundColor);
		setLocationRelativeTo(null); //middle of the screen
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		//toolbar panel on top of the window
		JPanel toolbarPanel = new JPanel(new BorderLayout());
		toolbarPanel.setBackground(toolbarColor);
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
				
		//toolbar label (name of page)
		JLabel mainTitleLabel = new JLabel(" New group");      
		mainTitleLabel.setFont(smallFont);
		mainTitleLabel.setForeground(Color.WHITE);
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		// panel for buttons in toolbar
		JPanel tbPane = new JPanel();
		tbPane.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		flowLayout.setHgap(10); 
		tbPane.setLayout(flowLayout);
				
		//exit button in toolbar
		RoundButton exitButton = new RoundButton("", 30 ,30);
		exitButton.setButtonIcon("Pictures/nika.png", 30, 30);
		exitButton.setBackground(toolbarColor);
		exitButton.setBorder(null);
		tbPane.add(exitButton);
	
		toolbarPanel.add(tbPane, BorderLayout.EAST);
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		
		//panel for labels and buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(0, 0, 600, 300);
        contentPane.add(centerPanel);
        centerPanel.setBackground(new Color(69, 62, 130));
        centerPanel.setLayout(null);
        
        //adding label for group name
        JLabel lblGroupName = new JLabel("Group name:");
        lblGroupName.setFont(secFont);
        lblGroupName.setForeground(Color.WHITE);
        lblGroupName.setBounds(20, 20, 200, 30);
        centerPanel.add(lblGroupName);
        
        //group name input
        groupName = new RoundTextField(0);
        groupName.setFont(inputText);
        groupName.setBounds(20, 55, 400, 30);
        groupName.setText("Enter group name");
        
        //text inside of username field
        groupName.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (groupName.getText().equals("Enter group name")) {
                	groupName.setText(""); 
                }
            }

            public void focusLost(FocusEvent e) {
                if (groupName.getText().isEmpty()) {
                	groupName.setText("Enter group name");
                }
            }
        });
        centerPanel.add(groupName);
        
        //adding label for group color
        JLabel lblGroupColor = new JLabel("Choose group color:");
        lblGroupColor.setFont(secFont);
        lblGroupColor.setForeground(Color.WHITE);
        lblGroupColor.setBounds(20, 100, 200, 30);
        centerPanel.add(lblGroupColor);
        
        // Create an instance of ColorfulButtons
        ColorfulButtons colorfulButtons = new ColorfulButtons();
        colorfulButtons.setBounds(20, 135, 560, 50); // Adjust the bounds as needed
        centerPanel.add(colorfulButtons);
		
	}
	
	class ColorfulButtons extends JPanel {

	    public ColorfulButtons() {
	        setLayout(new FlowLayout(FlowLayout.LEFT)); // Buttons will be aligned to the left

	        // Array of colors for buttons
	        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
	                          Color.BLUE, Color.CYAN, Color.MAGENTA, Color.PINK};

	        // Create buttons with different colors
	        for (int i = 0; i < 8; i++) {
	            RoundButton button = new RoundButton(" ", 40, 40); // Adjust button size as needed
	            button.setBackground(colors[i]);
	            button.addActionListener(new ButtonClickListener());
	            add(button);
	        }

	        setOpaque(false); // Make the panel transparent
	    }

	    private class ButtonClickListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            RoundButton source = (RoundButton) e.getSource();
	            //JOptionPane.showMessageDialog(null, "You clicked a color button", "Button Clicked", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGroupOfCards frame = new AddGroupOfCards();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


