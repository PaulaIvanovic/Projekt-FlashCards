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
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import databaseInfo.UserInfo;



public class AddSubgroupOfCards extends JFrame implements GlobalDesign{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField groupName, generateSubgroup;
	
	public String chosenColor;
	String fileName;
	
	ScreenDimensions dimensions = new ScreenDimensions();


	public AddSubgroupOfCards(SubgroupOfCardsPage parent) {
		// Set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());
		
		// Calculated variables for window height and width
	    int desiredHeight = (int) (dimensions.screenHeight * 0.435);
	    int desiredWidth = (int) (dimensions.screenWidth * 0.4);
		
	  
        // Set the custom title bar
        setUndecorated(true);
        setTitle("NEW SUBGROUP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, desiredWidth, desiredHeight);
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        setResizable(false); 
        setLocationRelativeTo(null);
        setVisible(true);  
     
	    // Create a main panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(backgroundColor);
		setLocationRelativeTo(null); //middle of the screen
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		
		// Add toolbar panel on top of the window
		JPanel toolbarPanel = new JPanel(new BorderLayout());
		toolbarPanel.setBackground(toolbarColor);
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
					
		// Set class for font size
		WindowElementResize.getFontForWindowSize(desiredHeight);
		
		// Set toolbar label (name of page)
		JLabel mainTitleLabel = new JLabel(" New subgroup");      
		mainTitleLabel.setFont(WindowElementResize.mainTitle);
		mainTitleLabel.setForeground(Color.WHITE);
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		// Create panel for buttons in toolbar
		JPanel tbPane = new JPanel();
		tbPane.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		tbPane.setLayout(flowLayout);
				
		// Add exit button in toolbar
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
		
		// Create panel for labels and buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(0, 0, desiredWidth, desiredHeight);
        contentPane.add(centerPanel);
        centerPanel.setBackground(new Color(69, 62, 130));
        centerPanel.setLayout(null);
        
        // Add label for subgroup name
        JLabel lblGroupName = new JLabel("Subroup name:");
        lblGroupName.setFont(secFont);
        lblGroupName.setForeground(Color.WHITE);
        lblGroupName.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.025), (int)(desiredWidth*0.3), (int)(desiredHeight*0.075));
        centerPanel.add(lblGroupName);
        
        // Set message "these fields can not be empty"
      	JLabel lblInfo = new JLabel("");
      	lblInfo.setBounds((int)(desiredWidth * 0.03), (int)(desiredHeight * 0.725), (int)(desiredWidth * 0.5), (int)(desiredHeight * 0.04));
      	centerPanel.add(lblInfo);
      	lblInfo.setFont(tinyFont);
      	lblInfo.setForeground(textRed);
        
        // Add group name input
        groupName = new RoundTextField(0);
        groupName.setFont(inputText);
        groupName.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.11), (int)(desiredWidth*0.8), (int)(desiredHeight*0.075));
        groupName.setText("Enter subgroup name");
        
        // Set text inside of username field
        groupName.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (groupName.getText().equals("Enter subgroup name")) {
                	groupName.setText(""); 
                }
            }

            public void focusLost(FocusEvent e) {
                if (groupName.getText().isEmpty()) {
                	groupName.setText("Enter subgroup name");
                }
            }
        });
        
        // Function for inserting group name
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
                		// user tries to go over the limit, dont show letters
                		// Remove the last character
                		inputText = inputText.substring(0, inputText.length() - 1);
                	}
                	groupName.setText(inputText);
                	lblInfo.setText("Group name is too long");
                }
            }
        });
            
        
        centerPanel.add(groupName);
        
        // Add label for subgroup color
        JLabel lblGroupColor = new JLabel("Choose subgroup color:");
        lblGroupColor.setFont(secFont);
        lblGroupColor.setForeground(Color.WHITE);
        lblGroupColor.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.22), (int)(desiredWidth*0.43), (int)(desiredHeight*0.075));
        centerPanel.add(lblGroupColor);
        
        // Create an instance of ColorfulButtons
        ColorfulButtons colorfulButtons = new ColorfulButtons();
        colorfulButtons.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.3), (int)(desiredWidth*0.93), (int)(desiredHeight*0.3)); // Adjust the bounds as needed
        centerPanel.add(colorfulButtons);
        
        // Add label for generating subgroups
        JLabel lblGenerate = new JLabel("Generate automatic subgroup from file:");
        lblGenerate.setFont(secFont);
        lblGenerate.setForeground(Color.WHITE);
        lblGenerate.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.575), (int)(desiredWidth*0.75), (int)(desiredHeight*0.085));
        centerPanel.add(lblGenerate);
        
        // Add field for generating groups
        generateSubgroup = new RoundTextField(0);
        generateSubgroup.setFont(inputText);
        generateSubgroup.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.675), (int)(desiredWidth*0.6), (int)(desiredHeight*0.075));
        generateSubgroup.setText("Insert file name");
        
        generateSubgroup.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (generateSubgroup.getText().equals("Insert file name")) {
                	generateSubgroup.setText(""); 
                }
            }
            public void focusLost(FocusEvent e) {
                if (generateSubgroup.getText().isEmpty()) {
                	generateSubgroup.setText("Insert file name");
                }
            }
        });
        
        generateSubgroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileName = generateSubgroup.getText(); // update fileName when text is entered or changed
            }
        });
        centerPanel.add(generateSubgroup);
        
        // Add load button
        RoundedButton btnLoad = new RoundedButton("Load");
        btnLoad.setFont(smallFont);
        btnLoad.setForeground(Color.BLACK);
        btnLoad.setBackground(new Color(248, 248, 255));
        btnLoad.setBounds((int)(desiredWidth*0.7), (int)(desiredHeight*0.675), (int)(desiredWidth*0.15), (int)(desiredHeight*0.075));
        
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new file chooser instance
                JFileChooser fileChooser = new JFileChooser();

                // Show open dialog to select a file
                int result = fileChooser.showOpenDialog(AddSubgroupOfCards.this);

                // Check if a file is selected
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Get the selected file
                    File selectedFile = fileChooser.getSelectedFile();
                    // Process the selected file (e.g., display its path)
                    JOptionPane.showMessageDialog(AddSubgroupOfCards.this, "Selected file: " + selectedFile.getAbsolutePath());
                    //saving in string
                    generateSubgroup.setText(selectedFile.getName());
                }
            }
        });
        
        centerPanel.add(btnLoad);
        
        // Add Save and Cancel buttons
        RoundedButton btnSave = new RoundedButton("Save");
        btnSave.setFont(smallFont);
        btnSave.setForeground(Color.BLACK);
        btnSave.setBackground(new Color(248, 248, 255));
        btnSave.setBounds((int)(desiredWidth*0.62), (int)(desiredHeight*0.76), (int)(desiredWidth*0.15), (int)(desiredHeight*0.075));
        centerPanel.add(btnSave);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(checkSelection()) {
            		if(!lblInfo.getText().equals("Group name is too long")) {
                		UserInfo.addsubGroup(groupName.getText(), chosenColor);
                		parent.updateView();
                		parent.setVisible(true);
                    	dispose();
            		}
            	}else {
            		lblInfo.setText("Warning: Information incomplete. Make sure no field is empty.");
            	}
            }

			private boolean checkSelection() {
				if(groupName.getText() == "" || groupName.getText().equalsIgnoreCase("Enter subgroup name") || groupName.getText() == null || chosenColor == null) {
					return false;
				}
				return true;
			}
        });

        RoundedButton btnCancel = new RoundedButton("Cancel");
        btnCancel.setFont(smallFont);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setBackground(new Color(248, 248, 255));
        btnCancel.setBounds((int)(desiredWidth*0.8), (int)(desiredHeight*0.76), (int)(desiredWidth*0.15), (int)(desiredHeight*0.075));
        centerPanel.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	parent.updateView();
            	parent.setVisible(true);
                dispose();
            }
        });
		
	}
	
	// Class for choosing costum colors
	class ColorfulButtons extends JPanel {

	    public ColorfulButtons() {
	        setLayout(new FlowLayout(FlowLayout.LEFT)); // Buttons will be aligned to the left
	       
	        // Calculated variables for window height and width
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
	
}


