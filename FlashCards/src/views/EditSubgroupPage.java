package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import databaseInfo.UserInfo;

public class EditSubgroupPage extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel mainTitleLabel;

	//responsive starting coordinates
	public int START_X;
	public int START_Y;
	
	public String name;
    
    int windowWidth;
    int windowHeight;
    int xPositionWindow;
    int yPositionWindow;
    
    ScreenDimensions screenSize;
	int minimumWindowWidth;
	int minimumWindowHeight;
	
	int previousWidth;
	int previousHeight;
    
    public EditSubgroupPage() {
    	this(0,0,0,0, "");
    };
    
    public EditSubgroupPage(int x, int y, int width, int height, String name) {
    	this.name = name;
    	//sets icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());

    	screenSize = new ScreenDimensions();
    	
    	this.setTitle("EDIT SUBGROUPS OF CARDS PAGE");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	//window starts fullscreen & has a defined minimum size
    	this.setExtendedState(JFrame.MAXIMIZED_BOTH);		
    	this.setMinimumSize(new Dimension(screenSize.minimumWindowWidth, screenSize.minimumWindowHeight));
		
    	checkBounds(x, y, width, height);
    	this.setBounds(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
    	
    	updateView();
    	
    	
    	//function for resizing components
    	addComponentListener(new ComponentAdapter() {
    	    public void componentResized(ComponentEvent e) {
    	        Dimension newSize = e.getComponent().getSize();
    	          	       
    	        if (windowWidth != newSize.width || windowHeight != newSize.height) {
    	        	if(newSize.width <= dimensions.minimumWindowWidth && newSize.height <= dimensions.minimumWindowHeight) {
    	        		windowWidth = dimensions.minimumWindowWidth;
        	            windowHeight = dimensions.minimumWindowHeight;
    	        	} else if (newSize.width <= dimensions.minimumWindowWidth && newSize.height > dimensions.minimumWindowHeight) {
    	        		windowWidth = dimensions.minimumWindowWidth;
        	            windowHeight = newSize.height;
    	        	} else if (newSize.height <= dimensions.minimumWindowHeight && newSize.width > dimensions.minimumWindowWidth) {
    	        		windowWidth = newSize.width;
        	            windowHeight = dimensions.minimumWindowHeight;
    	        	} else if (newSize.width == dimensions.screenWidth && newSize.height == dimensions.screenHeight) {
    	        		windowWidth = dimensions.screenWidth;
    	        		windowHeight = dimensions.screenHeight;
    	        	} else if (newSize.width == dimensions.screenWidth && newSize.height != dimensions.screenHeight) {
    	        		windowWidth = dimensions.screenWidth;
    	        		windowHeight = newSize.height;
    	        	} else if (newSize.width != dimensions.screenWidth && newSize.height == dimensions.screenHeight) {
    	        		windowWidth = newSize.width;
    	        		windowHeight = dimensions.screenHeight;
    	        	} else {
    	        		windowWidth = newSize.width;
    	        		windowHeight = newSize.height;
    	        	}
    	        	
    	        	 updateView(); 
    	        
    	        }
    	    }
    	});
    	
    	
    	//check if moved
    			addComponentListener(new ComponentAdapter() {
    	            @Override
    	            public void componentMoved(ComponentEvent e) {
    	        		xPositionWindow = e.getComponent().getX();
    	        		yPositionWindow = e.getComponent().getY();
    	            }
    	        });	 
    			
    			
    			addWindowStateListener(new WindowStateListener() {
    	            @Override
    	            public void windowStateChanged(WindowEvent e) {
    	                if (e.getNewState() != JFrame.ICONIFIED && e.getNewState() != JFrame.MAXIMIZED_BOTH) {
    	                	setSize(dimensions.minimumWindowWidth, dimensions.minimumWindowHeight);
    	                	windowWidth = dimensions.minimumWindowWidth;
    	                	windowHeight = dimensions.minimumWindowHeight;
    	                }
    	                updateView();
    	                
    	            }
    	        });
    	    }	
    
    
    	
    
    public void windowCreate() {
    	setVisible(true);
    	
    	//window dimensions to ensure responsiveness
        windowWidth = getWidth();
        windowHeight = getHeight();
        views.WindowElementResize.getFontForWindowSize(windowHeight);

        //main panel
        contentPane = new JPanel();
        contentPane.setBackground(backgroundColor);		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		//toolbar with window name & needed buttons:
		JPanel toolbarPanel = new JPanel(new BorderLayout());
		toolbarPanel.setBackground(toolbarColor);
		toolbarPanel.setBounds(0, 0, windowWidth, 85);
		toolbarPanel.setLayout(new BorderLayout());
		toolbarPanel.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10));
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		
			mainTitleLabel = new JLabel("Editing - " + name);      
			mainTitleLabel.setFont(WindowElementResize.mainFont);			
			mainTitleLabel.setForeground(Color.WHITE);
			toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setOpaque(false);
			FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
			flowLayout.setHgap(15); 
			buttonPanel.setLayout(flowLayout);

			//variables for button dimensions
			int buttonDimension = (int) (windowWidth * 0.025);
			int biggerButtonDimension = (int) (windowWidth * 0.035);
			
				RoundedButton cancelButton = new RoundedButton("Cancel");
				cancelButton.setPreferredSize(new Dimension(95, 40));
				buttonPanel.add(cancelButton);
				
				//cancel button leads back to GroupOfCardsPage
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UserInfo.getSubgroups();
						
						SubgroupOfCardsPage SubgroupsWindow = new SubgroupOfCardsPage(xPositionWindow, yPositionWindow, windowWidth, windowHeight, name);
						SubgroupsWindow.setVisible(true);
						dispose();
					}
				});
	
				RoundedButton saveButton = new RoundedButton("Save all");
				saveButton.setPreferredSize(new Dimension(95, 40));
				buttonPanel.add(saveButton);
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UserInfo.saveEditAllSubgroups();
						
						SubgroupOfCardsPage SubgroupsWindow = new SubgroupOfCardsPage(xPositionWindow, yPositionWindow, windowWidth, windowHeight, name);
						SubgroupsWindow.setVisible(true);
						dispose();
					}
				});


				//settings button
				RoundButton settingsButton = new RoundButton("", buttonDimension,buttonDimension);
				settingsButton.setButtonIcon("icons/settingsIcon.png", buttonDimension,buttonDimension);
				settingsButton.setBackground(toolbarColor);
				settingsButton.setForeground(backgroundColor);
				settingsButton.setBorder(null);
				buttonPanel.add(settingsButton);
				
				//settings button leads to Settings page
				settingsButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Settings settingsWindow = new Settings(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
						settingsWindow.setVisible(true);
						dispose();
					}
				});
				
				//user icon / button in toolbar
				RoundButton userIcon = new RoundButton("",biggerButtonDimension, biggerButtonDimension);
				userIcon.setButtonIcon("Pictures/" + UserInfo.profilePic, biggerButtonDimension, biggerButtonDimension);
				userIcon.setBackground(toolbarColor);
				userIcon.setBorder(null);
				//userIcon.setEnabled(false);
				buttonPanel.add(userIcon);
				
			toolbarPanel.add(buttonPanel, BorderLayout.EAST);
			
			//scrollbar to get to all subgroups
		    JScrollPane scrollPane = new JScrollPane(editSubgroupsCollection());	
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		    contentPane.add(scrollPane, BorderLayout.CENTER);
    }
    
    public JPanel editSubgroupsCollection() {
        JPanel editSubgroupsPanel = new JPanel(new GridBagLayout()); // main panel (all groups)
        editSubgroupsPanel.setBackground(backgroundColor);

        //layout to arrange each groupPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        int numCols = Math.min(UserInfo.subGroupNames.size(), 2);
        int colCounter = 0;

        for (int i = 0; i < UserInfo.subGroupNames.size(); i++) {
        	JPanel groupPanel = createSubgroupPanel(UserInfo.subGroupNames.get(i), UserInfo.subGroupColors.get(i), UserInfo.subGroupIDs.get(i));         
            editSubgroupsPanel.add(groupPanel, gbc);
            gbc.gridx++;
            colCounter++;

            // if maximum number of columns reached, move to next row
            if (colCounter == numCols) {
                colCounter = 0;
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }

        return editSubgroupsPanel;
    }
    
    private JPanel createSubgroupPanel(String subgroupName, Color subgroupColor, String ID) {

    	//window dimensions to assure responsiveness
    	int frameWidth = windowWidth;
        int frameHeight = windowHeight;

        //width and height of each rectangle (based in window size)
        int rectangleWidth =(int) (0.18 * frameWidth);
        int rectangleHeight = (int) (0.28 * frameHeight);

        JPanel subgroupPanel = new JPanel(new BorderLayout()); // panel for 1 group, divided into 2 sides
        subgroupPanel.setBackground(backgroundColor);

        //rectangle representing subgroup
	    RoundedButton subgroupOfCards = new RoundedButton("");
	    subgroupOfCards.setText(subgroupName);
	    subgroupOfCards.help = ID;
	    subgroupOfCards.setBackground(subgroupColor);
	    subgroupOfCards.setFont(WindowElementResize.mediumFont);
	    subgroupOfCards.setPreferredSize(new Dimension(rectangleWidth, rectangleHeight));
        subgroupPanel.add(subgroupOfCards, BorderLayout.WEST);
        

        //right panel with text field, color picker & save and delete buttons
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(backgroundColor);
        rightPanel.setBorder(new EmptyBorder(5, 15, 5, 5));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 6, 0); // vertical gap between components
        
        //message 
      	JLabel lblNewLabel_3 = new JLabel("");
      	lblNewLabel_3.setFont(tinyFont);
      	lblNewLabel_3.setForeground(textRed);
        gbc.gridx = 0;
        gbc.gridy = 0;
        rightPanel.add(lblNewLabel_3, gbc);

	    JLabel subgroupNameLabel = new JLabel("Subgroup name:");
	    subgroupNameLabel.setForeground(Color.WHITE);
	    subgroupNameLabel.setFont(smallFont);
	    gbc.gridx = 0;
        gbc.gridy = 1;
	        
	    rightPanel.add(subgroupNameLabel, gbc);

	    RoundTextField enterSubgroupNameField = new RoundTextField(0);
	    int columns = (int) Math.min(25, frameWidth * 0.023); 
	        
	    enterSubgroupNameField.setColumns(columns);
	    enterSubgroupNameField.setText("Enter the new name of subgroup");
	    enterSubgroupNameField.setFont(inputText);
	    enterSubgroupNameField.setForeground(Color.GRAY);
	    enterSubgroupNameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // padding

        
        //to change the text when clicked on input field
	    enterSubgroupNameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (enterSubgroupNameField.getText().equals("Enter the new name of subgroup")) {
                	enterSubgroupNameField.setText("");
                	enterSubgroupNameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (enterSubgroupNameField.getText().isEmpty()) {
                	enterSubgroupNameField.setText("Enter the new name of subgroup");
                	enterSubgroupNameField.setForeground(Color.GRAY);
                }
            }
        });
        
	    enterSubgroupNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	boolean backspace = e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
            	String inputText = enterSubgroupNameField.getText();
            	// Set the text with the key typed
                char keyChar = e.getKeyChar();
                int totalChar = enterSubgroupNameField.getText().length();
                if(totalChar < charLimit && !backspace) {
                	lblNewLabel_3.setText("");
                	if (Character.isLetterOrDigit(keyChar)  || isSpecialCharacter(keyChar)) {
                		String text = String.valueOf(keyChar);
                		subgroupOfCards.setText(inputText + text);
                	}
                	UserInfo.changesubGroupName(subgroupOfCards.help, inputText + String.valueOf(keyChar));
                }else if (backspace) {
                	lblNewLabel_3.setText("");
                	if (!inputText.isEmpty()) {
                		// Remove the last character
                		inputText = inputText.substring(0, inputText.length() - 1);
                	}
            		if(inputText.length() <= charLimit) {
            			subgroupOfCards.setText(inputText);
            			UserInfo.changesubGroupName(subgroupOfCards.help, inputText);
            		}
                }else {
                	if(inputText.length() > charLimit) {
                		//user tries to go over the limit, dont show letters
                		// Remove the last character
                		inputText = inputText.substring(0, inputText.length() - 1);
                	}
                	enterSubgroupNameField.setText(inputText);
                	lblNewLabel_3.setText("Subgroup name is too long");
                }
            }
            
            private boolean isSpecialCharacter(char c) {
                // Define special characters you want to allow
                String specialCharacters = "!@#$%^&*()_+{}|:<>?";

                // Check if the character is in the list of special characters
                return specialCharacters.indexOf(c) != -1;
            }
        });

        
        gbc.gridy++;
        rightPanel.add(enterSubgroupNameField, gbc);

	        JLabel chooseColorLabel = new JLabel("Choose a color:");
	        chooseColorLabel.setForeground(Color.WHITE);
	        chooseColorLabel.setFont(smallFont);
	        
	        gbc.gridy++;
	    
	    rightPanel.add(chooseColorLabel, gbc);
	    
	    	

	    //panel containing colors to pick from
        JPanel circlePanel = new JPanel(new GridLayout(2, 5, 5, 5));
        circlePanel.setBackground(backgroundColor);

	        for (int i = 0; i < 16; i++) {
	            RoundButton colorCircle = new RoundButton("");
	            colorCircle.setBackground(groupColorOptions[i]);
	            colorCircle.setPreferredSize(new Dimension((int) (0.035 * frameHeight), (int) (0.035 * frameHeight)));
	            colorCircle.setBorder(null);
	        circlePanel.add(colorCircle);
	        }
        
        gbc.gridy++;

        rightPanel.add(circlePanel, gbc);
        
        // every colorCircle sets new color of subgroupOfCards to its color
        for (Component component : circlePanel.getComponents()) {
            if (component instanceof RoundButton) {
                RoundButton circleButton = (RoundButton) component;
                circleButton.addActionListener(e -> {
                    Color buttonColor = circleButton.getBackground();
                    subgroupOfCards.setBackground(buttonColor);
                    
                    UserInfo.changesubGroupColor(subgroupOfCards.help, buttonColor);
                });
            }
        }


        //panel with the save changes & delete group buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);
        
	        RoundedButton deleteSubgroupButton = new RoundedButton("Delete");
	        deleteSubgroupButton.setBackground(Color.RED);
	        deleteSubgroupButton.setFont(buttonText);
	        deleteSubgroupButton.setPreferredSize(new Dimension(80, 30));
	        buttonPanel.add(deleteSubgroupButton);
	        deleteSubgroupButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int response = JOptionPane.showConfirmDialog(
                            null, 
                            "Are you sure you want to delete the subgroup " + subgroupName + "? This change cannot be undone", 
                            "Confirm Deletion", 
                            JOptionPane.YES_NO_OPTION
                    );
                    
                    if (response == JOptionPane.YES_OPTION) {
                        UserInfo.deleteSubgroup(subgroupOfCards.help);
                        contentPane.removeAll();
                        // add your elements
                        contentPane.revalidate();
                        contentPane.repaint();
                        updateView();
                    }
                }
            });


	        RoundedButton saveChangesButton = new RoundedButton("Save");
	        saveChangesButton.setFont(buttonText);
	        saveChangesButton.setPreferredSize(new Dimension(80, 30));
	        buttonPanel.add(saveChangesButton);
	        saveChangesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	lblNewLabel_3.setText("");
                    // retrieve the text from the input field
                    String newName = enterSubgroupNameField.getText();
                    if(newName.length() > charLimit) {
                    	newName = newName.substring(0, charLimit);
                    }
                    if(!(newName.equalsIgnoreCase("Enter the new name of subgroup") || newName.isEmpty())) {
                    	// set the new name to the groupOfCards
                    	subgroupOfCards.setText(newName);
                    	UserInfo.changesubGroupName(subgroupOfCards.help, newName);
                    	// clear the input field
                    	enterSubgroupNameField.setText("Enter the new name of subgroup");
                    }
                	enterSubgroupNameField.setForeground(Color.GRAY);
                	UserInfo.saveEditsubGroup(subgroupOfCards.help);
                }
            });
	

        
        gbc.gridy++;

        rightPanel.add(buttonPanel, gbc);

        subgroupPanel.add(rightPanel, BorderLayout.CENTER);

        return subgroupPanel;
    
    }
    
    
    //moves starting point of components based on window size
    public void updateView() {
		 START_X = (int)(windowWidth * 0.05);
		 START_Y = (int)(windowHeight * 0.175); 
		windowCreate();
	}
    
    
    //function for checking bounds
  	public void checkBounds(int x, int y, int width, int height) {
  		//with tolerances
  		if(x <= -10 || y <= -10) {
  			xPositionWindow = 0;
  			yPositionWindow = 0;
  		}else {
  			xPositionWindow = x;
  			yPositionWindow = y;
  		}
  		
  		if(width <= 0 || height <= 0) { //if the screen is less than the minimum allowed size
  			//with tolerances
  			windowWidth = dimensions.screenWidth;
  			windowHeight = dimensions.screenHeight;
  			setExtendedState(JFrame.MAXIMIZED_BOTH);
  		}else if (width <= dimensions.minimumWindowWidth && height <= dimensions.minimumWindowHeight) {
  			windowWidth = dimensions.minimumWindowWidth;
  			windowHeight = dimensions.minimumWindowHeight;
  		}else if (width <= dimensions.minimumWindowWidth && height > dimensions.minimumWindowHeight) {
      		windowWidth = dimensions.minimumWindowWidth;
              windowHeight = height;
      	}else if (height <= dimensions.minimumWindowHeight && width > dimensions.minimumWindowWidth) {
      		windowWidth = width;
              windowHeight = dimensions.minimumWindowHeight;
      	}else {
  			windowWidth = width;
  			windowHeight = height;
  		}
  	
  		//with tolerances
  				if (x <= -10 || y <= -10) {
  					xPositionWindow = 0;
  					yPositionWindow = 0;
  				} else {
  					xPositionWindow = x;
  					yPositionWindow = y;
  				}
  	}
  	
/*  	
  	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditSubgroupPage frame = new EditSubgroupPage(0, 0, 0, 0);
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
*/
}