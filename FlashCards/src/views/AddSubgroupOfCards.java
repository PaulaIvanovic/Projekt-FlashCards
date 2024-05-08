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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class AddSubgroupOfCards extends JFrame implements GlobalDesign{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 private JTextField groupName, generateSubgroup;
	
	  ScreenDimensions dimensions = new ScreenDimensions();


	public AddSubgroupOfCards() {
		//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/nika.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());
		
		//calculated variables for window height and width
	    int desiredHeight = (int) (dimensions.screenHeight * 0.535);
	    int desiredWidth = (int) (dimensions.screenWidth * 0.5);
		
	  
        // Set the custom title bar
        setUndecorated(true);
        setTitle("NEW SUBGROUP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, desiredWidth, desiredHeight);
        setResizable(false); 
        setLocationRelativeTo(null);
        setVisible(true);  
     
	    //main panel
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
					
		//class for font size
		WindowElementResize.getFontForWindowSize(desiredHeight);
		
		//toolbar label (name of page)
		JLabel mainTitleLabel = new JLabel(" New subgroup");      
		mainTitleLabel.setFont(WindowElementResize.mainTitle);
		mainTitleLabel.setForeground(Color.WHITE);
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		// panel for buttons in toolbar
		JPanel tbPane = new JPanel();
		tbPane.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		tbPane.setLayout(flowLayout);
				
		//exit button in toolbar
		RoundButton exitButton = new RoundButton("",(int)(desiredWidth*0.05) ,(int)(desiredHeight*0.085));
		exitButton.setButtonIcon("icons/CloseIcon.png", (int)(desiredWidth*0.05), (int)(desiredHeight*0.085));
		exitButton.setBackground(toolbarColor);
		exitButton.setBorder(null);
		exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
		tbPane.add(exitButton);
		
		toolbarPanel.add(tbPane, BorderLayout.EAST);
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		
		//panel for labels and buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(0, 0, desiredWidth, desiredHeight);
        contentPane.add(centerPanel);
        centerPanel.setBackground(new Color(69, 62, 130));
        centerPanel.setLayout(null);
        
        //adding label for subgroup name
        JLabel lblGroupName = new JLabel("Subroup name:");
        lblGroupName.setFont(secFont);
        lblGroupName.setForeground(Color.WHITE);
        lblGroupName.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.025), (int)(desiredWidth*0.3), (int)(desiredHeight*0.075));
        centerPanel.add(lblGroupName);
        
        //group name input
        groupName = new RoundTextField(0);
        groupName.setFont(inputText);
        groupName.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.10), (int)(desiredWidth*0.8), (int)(desiredHeight*0.075));
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
        
        //adding label for subgroup color
        JLabel lblGroupColor = new JLabel("Choose subgroup color:");
        lblGroupColor.setFont(secFont);
        lblGroupColor.setForeground(Color.WHITE);
        lblGroupColor.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.2), (int)(desiredWidth*0.33), (int)(desiredHeight*0.075));
        centerPanel.add(lblGroupColor);
        
        // Create an instance of ColorfulButtons
        ColorfulButtons colorfulButtons = new ColorfulButtons();
        colorfulButtons.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.28), (int)(desiredWidth*0.93), (int)(desiredHeight*0.17)); // Adjust the bounds as needed
        centerPanel.add(colorfulButtons);
        
        //adding label for generating subgroups
        JLabel lblGenerate = new JLabel("Generate automatic subgroup from file:");
        lblGenerate.setFont(secFont);
        lblGenerate.setForeground(Color.WHITE);
        lblGenerate.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.43), (int)(desiredWidth*0.6), (int)(desiredHeight*0.085));
        centerPanel.add(lblGenerate);
        
        //group name input
        generateSubgroup = new RoundTextField(0);
        generateSubgroup.setFont(inputText);
        generateSubgroup.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.52), (int)(desiredWidth*0.6), (int)(desiredHeight*0.075));
        generateSubgroup.setText("Insert file name");
        centerPanel.add(generateSubgroup);
        
        // button load
        RoundedButton btnLoad = new RoundedButton("Load");
        btnLoad.setFont(smallFont);
        btnLoad.setForeground(Color.BLACK);
        btnLoad.setBackground(new Color(248, 248, 255));
        btnLoad.setBounds((int)(desiredWidth*0.7), (int)(desiredHeight*0.52), (int)(desiredWidth*0.15), (int)(desiredHeight*0.075));
        centerPanel.add(btnLoad);
        
        // Add Save and Cancel buttons
        RoundedButton btnSave = new RoundedButton("Save");
        btnSave.setFont(smallFont);
        btnSave.setForeground(Color.BLACK);
        btnSave.setBackground(new Color(248, 248, 255));
        btnSave.setBounds((int)(desiredWidth*0.62), (int)(desiredHeight*0.76), (int)(desiredWidth*0.15), (int)(desiredHeight*0.075));
        centerPanel.add(btnSave);

        RoundedButton btnCancel = new RoundedButton("Cancel");
        btnCancel.setFont(smallFont);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setBackground(new Color(248, 248, 255));
        btnCancel.setBounds((int)(desiredWidth*0.8), (int)(desiredHeight*0.76), (int)(desiredWidth*0.15), (int)(desiredHeight*0.075));
        centerPanel.add(btnCancel);
		
	}
	
	class ColorfulButtons extends JPanel {

	    public ColorfulButtons() {
	        setLayout(new FlowLayout(FlowLayout.LEFT)); // Buttons will be aligned to the left
	        
	      //calculated variables for window height and width
		    int desiredHeight = (int) (dimensions.screenHeight * 0.435);
		    int desiredWidth = (int) (dimensions.screenWidth * 0.4);
			

	        // Array of colors for buttons
	        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
	                          Color.BLUE, Color.CYAN, Color.MAGENTA, Color.PINK};

	        // Create buttons with different colors
	        for (int i = 0; i < 8; i++) {
	            RoundButton button = new RoundButton(" ", (int)(desiredWidth*0.083), (int)(desiredHeight*0.143)); // Adjust button size as needed
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
					AddSubgroupOfCards frame = new AddSubgroupOfCards();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


