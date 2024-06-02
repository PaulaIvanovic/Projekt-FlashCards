package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import databaseInfo.UserInfo;



public class AddGroupOfCards extends JFrame implements GlobalDesign{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField groupName;
	public String chosenColor;
	
	ScreenDimensions dimensions = new ScreenDimensions();


	public AddGroupOfCards(GroupOfCardsPage parent) {
		//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());
		
		//calculated variables for window height and width
	    int desiredHeight = (int) (dimensions.screenHeight * 0.435);
	    int desiredWidth = (int) (dimensions.screenWidth * 0.4);
		
	  
        // Set the custom title bar
        setUndecorated(true);
        setTitle("NEW GROUP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, desiredWidth, desiredHeight);
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
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
		JLabel mainTitleLabel = new JLabel(" New group");      
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
            	parent.updateView();
            	parent.setVisible(true);
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
        
        //adding label for group name
        JLabel lblGroupName = new JLabel("Group name:");
        lblGroupName.setFont(secFont);
        lblGroupName.setForeground(Color.WHITE);
        lblGroupName.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.03), (int)(desiredWidth*0.3), (int)(desiredHeight*0.085));
        centerPanel.add(lblGroupName);
        
        //message "these fields can not be empty"
      	JLabel lblInfo = new JLabel("");
      	lblInfo.setBounds((int)(desiredWidth * 0.03), (int)(desiredHeight * 0.725), (int)(desiredWidth * 0.5), (int)(desiredHeight * 0.04));
      	centerPanel.add(lblInfo);
      	lblInfo.setFont(tinyFont);
      	lblInfo.setForeground(textRed);
        
        //group name input
        groupName = new RoundTextField(0);
        groupName.setFont(inputText);
        groupName.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.16), (int)(desiredWidth*0.8), (int)(desiredHeight*0.085));
        groupName.setText("Enter group name");
        
        //text inside of name field
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
        
        groupName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	boolean backspace = e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
            	String inputText = groupName.getText();
            	// Set the text with the key typed
                char keyChar = e.getKeyChar();
                int totalChar = groupName.getText().length();
                if(totalChar < charLimit || backspace) {
                	lblInfo.setText("");
                }else {
                	if(inputText.length() > charLimit) {
                		//user tries to go over the limit, dont show letters
                		// Remove the last character
                		inputText = inputText.substring(0, inputText.length() - 1);
                	}
                	groupName.setText(inputText);
                	lblInfo.setText("Group name is too long");
                }
            }
        });
            
        centerPanel.add(groupName);
        
        
        
        //adding label for group color
        JLabel lblGroupColor = new JLabel("Choose group color:");
        lblGroupColor.setFont(secFont);
        lblGroupColor.setForeground(Color.WHITE);
        lblGroupColor.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.29), (int)(desiredWidth*0.33), (int)(desiredHeight*0.085));
        centerPanel.add(lblGroupColor);
        
        // Create an instance of ColorfulButtons
        ColorfulButtons colorfulButtons = new ColorfulButtons();
        colorfulButtons.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.39), (int)(desiredWidth*0.93), (int)(desiredHeight*0.3)); // Adjust the bounds as needed
        centerPanel.add(colorfulButtons);
       
        
        // Add Save and Cancel buttons
        RoundedButton btnSave = new RoundedButton("Save");
        btnSave.setFont(smallFont);
        btnSave.setForeground(Color.BLACK);
        btnSave.setBackground(new Color(248, 248, 255));
        btnSave.setBounds((int)(desiredWidth*0.62), (int)(desiredHeight*0.76), (int)(desiredWidth*0.17), (int)(desiredHeight*0.085));
        centerPanel.add(btnSave);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(checkSelection()) {
            		if(!lblInfo.getText().equals("Group name is too long")) {
                		UserInfo.addGroup(groupName.getText(), chosenColor);
                		parent.updateView();
                		parent.setVisible(true);
                    	dispose();
            		}
            	}else {
            		lblInfo.setText("Warning: Information incomplete. Make sure no field is empty.");
            	}
            }

			private boolean checkSelection() {
				if(groupName.getText() == "" || groupName.getText().equalsIgnoreCase("Enter group name") || groupName.getText() == null || chosenColor == null) {
					return false;
				}
				return true;
			}
        });

        RoundedButton btnCancel = new RoundedButton("Cancel");
        btnCancel.setFont(smallFont);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setBackground(new Color(248, 248, 255));
        btnCancel.setBounds((int)(desiredWidth*0.8), (int)(desiredHeight*0.76), (int)(desiredWidth*0.17), (int)(desiredHeight*0.085));
        centerPanel.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	parent.updateView();
            	parent.setVisible(true);
                dispose();
            }
        });
		
	}
	
	class ColorfulButtons extends JPanel {

	    public ColorfulButtons() {
	        setLayout(new FlowLayout(FlowLayout.LEFT)); // Buttons will be aligned to the left
	       
	      //calculated variables for window height and width
		    int desiredHeight = (int) (dimensions.screenHeight * 0.435);
		    int desiredWidth = (int) (dimensions.screenWidth * 0.4);
			
		    JPanel buttonLayout = new JPanel(new GridLayout(2, 8, 10, 10));
		    buttonLayout.setOpaque(false); // Make the panel transparent
	        // Create buttons with different colors
	        for (int i = 0; i < 16; i++) {
	            RoundButton button = new RoundButton(" "); // Adjust button size as needed
	            button.setBackground(groupColorOptions[i]);
	            button.setPreferredSize(new Dimension((int) (0.075 * desiredWidth), (int) (0.125 * desiredHeight)));
	            button.setBorder(null);
	            button.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				Color color = button.getBackground();
	    				int red = color.getRed();
	    		        int green = color.getGreen();
	    		        int blue = color.getBlue();
	    		        
	    		        // Convert the RGB values to hexadecimal format
	    		        chosenColor = String.format("0x%02X%02X%02X", red, green, blue);
	    			}
	    		});
	            buttonLayout.add(button);
	        }

	        setOpaque(false); // Make the panel transparent
	        add(buttonLayout);
	    }

	    
	}
	
	/*
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
	}*/
}


