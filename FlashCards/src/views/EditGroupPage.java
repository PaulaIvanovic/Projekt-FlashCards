package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import databaseInfo.UserInfo;



public class EditGroupPage extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	
	public int START_X;
	public int START_Y;
    
    int windowWidth;
    int windowHeight;
    int xPositionWindow;
    int yPositionWindow;
    
    ScreenDimensions screenSize;
	int minimumWindowWidth;
	int minimumWindowHeight;
	
	int previousWidth;
	int previousHeight;
    
    public EditGroupPage() {
    	this(0,0,0,0);
    }
    
    public EditGroupPage(int x, int y, int width, int height) {
    	//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());

    	screenSize = new ScreenDimensions();
    	
    	this.setTitle("EDIT GROUPS OF CARDS PAGE");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	this.setExtendedState(JFrame.MAXIMIZED_BOTH);		//window starts with maximum size
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
    	
        windowWidth = getWidth();
        windowHeight = getHeight();
        views.WindowElementResize.getFontForWindowSize(windowHeight);

        //main panel
        contentPane = new JPanel();
        contentPane.setBackground(backgroundColor);		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		//toolbar on top of the window:
		JPanel toolbarPanel = new JPanel(new BorderLayout());
		toolbarPanel.setBackground(toolbarColor);
		toolbarPanel.setBounds(0, 0, windowWidth, 80);
		toolbarPanel.setLayout(new BorderLayout());
		toolbarPanel.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10));
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		
		//toolbar label (name of page)
		JLabel mainTitleLabel = new JLabel("Groups of cards - edit");      
		mainTitleLabel.setFont(WindowElementResize.mainFont);
		mainTitleLabel.setForeground(Color.WHITE);
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		// panel with all required buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		flowLayout.setHgap(15); 
		buttonPanel.setLayout(flowLayout);
	
		RoundedButton cancelButton = new RoundedButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(95, 35));
		buttonPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfo.getGroups();
				
				GroupOfCardsPage GroupsWindow = new GroupOfCardsPage(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
				GroupsWindow.setVisible(true);
				dispose();
			}
		});
		
		RoundedButton saveButton = new RoundedButton("Save all");
		saveButton.setPreferredSize(new Dimension(95, 35));
		buttonPanel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfo.saveEditAllGroups();
				
				GroupOfCardsPage GroupsWindow = new GroupOfCardsPage(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
				GroupsWindow.setVisible(true);
				dispose();
			}
		});

		//variables for button dimensions
		int buttonDimension = (int) (windowWidth * 0.025);
		int biggerButtonDimension = (int) (windowWidth * 0.035);
		
		
		//settings button in toolbar
		RoundButton settingsButton = new RoundButton("", buttonDimension,buttonDimension);
		settingsButton.setButtonIcon("icons/settingsIcon.png", buttonDimension,buttonDimension);
		settingsButton.setBackground(toolbarColor);
		settingsButton.setForeground(backgroundColor);
		settingsButton.setBorder(null);
		buttonPanel.add(settingsButton);
		
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings settingsWindow = new Settings(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
				settingsWindow.setVisible(true);
				dispose();
			}
		});
		
		//user icon / button in toolbar
		RoundButton userIcon = new RoundButton("", biggerButtonDimension, biggerButtonDimension);
		userIcon.setButtonIcon("icons/UserIconBasic.png", biggerButtonDimension, biggerButtonDimension);
		userIcon.setBackground(toolbarColor);
		userIcon.setBorder(null);
		userIcon.setEnabled(false);
		buttonPanel.add(userIcon);
		
		toolbarPanel.add(buttonPanel, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane(editGroupsCollection());	
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        contentPane.add(scrollPane, BorderLayout.CENTER);
    }
		
    public JPanel editGroupsCollection() {
        JPanel editGroupsPanel = new JPanel(new GridBagLayout()); // main panel (all groups)
        editGroupsPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        
        int numCols = Math.min(UserInfo.groupNames.size(), 2);
        int colCounter = 0;
      
        for (int i = 0; i < UserInfo.groupNames.size(); i++) {
        	JPanel groupPanel = createGroupPanel(UserInfo.groupNames.get(i), UserInfo.groupColors.get(i), UserInfo.groupIDs.get(i));         
            editGroupsPanel.add(groupPanel, gbc);
            gbc.gridx++;
            colCounter++;
            
            // if maximum number of columns reached, move to next row
            if (colCounter == numCols) {
                colCounter = 0;
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }

        return editGroupsPanel;
    }

    // Method to create a panel for a group
    private JPanel createGroupPanel(String groupName, Color groupColor, String ID) {
    		int frameWidth = windowWidth;
            int frameHeight = windowHeight;
         
            // Calculate the maximum width and height for each rectangle
            int rectangleWidth =(int) (0.18 * frameWidth);
            int rectangleHeight = (int) (0.28 * frameHeight);

            JPanel groupPanel = new JPanel(new BorderLayout()); // panel for 1 group, divided into 2 sides
            groupPanel.setBackground(backgroundColor);

            // Rectangle representing group
            RoundedButton groupOfCards = new RoundedButton("");
            groupOfCards.setMultiLineButtonText(groupName);
            groupOfCards.help = ID;
            groupOfCards.setBackground(groupColor);
            groupOfCards.setFont(WindowElementResize.mediumFont);
            groupOfCards.setPreferredSize(new Dimension(rectangleWidth, rectangleHeight));
            groupPanel.add(groupOfCards, BorderLayout.WEST);
            
            
            JPanel rightPanel = new JPanel(new GridBagLayout());
            rightPanel.setBackground(backgroundColor);
            rightPanel.setBorder(new EmptyBorder(5, 15, 5, 5));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 0, 6, 0); // vertical gap between components

            JLabel groupNameLabel = new JLabel("Group name:");
            groupNameLabel.setForeground(Color.WHITE);
            groupNameLabel.setFont(smallFont);
            gbc.gridx = 0;
            gbc.gridy = 0;
            rightPanel.add(groupNameLabel, gbc);
            
            
            RoundTextField enterGroupNameField = new RoundTextField(0);
            int columns = (int) Math.min(25, frameWidth * 0.023); 

            enterGroupNameField.setColumns(columns);
            enterGroupNameField.setFont(inputText);
            enterGroupNameField.setText("Enter the new name of group");
            enterGroupNameField.setForeground(Color.GRAY);
            enterGroupNameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // padding
            

            
            //to change the text when clicked on input field
            enterGroupNameField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (enterGroupNameField.getText().equals("Enter the new name of group")) {
                        enterGroupNameField.setText("");
                        enterGroupNameField.setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (enterGroupNameField.getText().isEmpty()) {
                        enterGroupNameField.setText("Enter the new name of group");
                        enterGroupNameField.setForeground(Color.GRAY);
                    }
                }
            });
            
            enterGroupNameField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e){
                	// Set the text with the key typed
                    char keyChar = e.getKeyChar();
                    if (Character.isLetterOrDigit(keyChar)  || isSpecialCharacter(keyChar)) {
                        String text = String.valueOf(keyChar);
                        groupOfCards.setMultiLineButtonText(enterGroupNameField.getText() + text);
                    } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        String currentText = enterGroupNameField.getText();
                        if (!currentText.isEmpty()) {
                            // Remove the last character
                            currentText = currentText.substring(0, currentText.length() - 1);
                            groupOfCards.setMultiLineButtonText(currentText);
                        }
                    }
                    UserInfo.changeGroupName(groupOfCards.help, groupOfCards.getText());
                }
                
                private boolean isSpecialCharacter(char c) {
                    // Define special characters you want to allow
                    String specialCharacters = "!@#$%^&*()_+{}|:<>?";

                    // Check if the character is in the list of special characters
                    return specialCharacters.indexOf(c) != -1;
                }
            });

            
            gbc.gridy++;
            rightPanel.add(enterGroupNameField, gbc);

            JLabel chooseColorLabel = new JLabel("Choose a color:");
            chooseColorLabel.setForeground(Color.WHITE);
            chooseColorLabel.setFont(smallFont);
            gbc.gridy++;
            rightPanel.add(chooseColorLabel, gbc);

            JPanel circlePanel = new JPanel(new GridLayout(2, 5, 5, 5));
            circlePanel.setBackground(backgroundColor);

            //color selection circles
            for (int i = 0; i < 16; i++) {
                RoundButton circle = new RoundButton("");
                circle.setBackground(groupColorOptions[i]);
                circle.setPreferredSize(new Dimension((int) (0.035 * frameHeight), (int) (0.035 * frameHeight)));
                circle.setBorder(null);
                circlePanel.add(circle);
            }

            // Action listener for color selection circles
            for (Component component : circlePanel.getComponents()) {
                if (component instanceof RoundButton) {
                    RoundButton circleButton = (RoundButton) component;
                    circleButton.addActionListener(e -> {
                        Color buttonColor = circleButton.getBackground();
                        groupOfCards.setBackground(buttonColor);
;
	    		        UserInfo.changeGroupColor(groupOfCards.help, buttonColor);
                    });
                }
            }

            gbc.gridy++;
            rightPanel.add(circlePanel, gbc);
            
            //delete & save buttons for each group
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(backgroundColor);
            
            RoundedButton deleteGroupButton = new RoundedButton("Delete");
            deleteGroupButton.setBackground(Color.RED);
            deleteGroupButton.setFont(buttonText);
            deleteGroupButton.setPreferredSize(new Dimension((int)(windowWidth*0.075), (int)(windowWidth*0.0175)));
            buttonPanel.add(deleteGroupButton);

            RoundedButton saveChangesButton = new RoundedButton("Save");
            saveChangesButton.setFont(buttonText);
            saveChangesButton.setPreferredSize(new Dimension((int)(windowWidth*0.075), (int)(windowWidth*0.0175)));
            buttonPanel.add(saveChangesButton);
            saveChangesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // retrieve the text from the input field
                    String newName = enterGroupNameField.getText(); 
                    if(newName.equalsIgnoreCase("Enter the new name of group") || newName.isEmpty()) {
                    	PopUpWindow info = new PopUpWindow("WARNING", "Warning: Group name is missing.",(int) (windowWidth*0.2), (int)(windowHeight*0.35));
                    	info.setVisible(true);
                    }else {
                    	// set the new name to the groupOfCards
                    	groupOfCards.setMultiLineButtonText(newName);
                    	// clear the input field
                    	enterGroupNameField.setText("");
                    	enterGroupNameField.setForeground(Color.GRAY);
                    	UserInfo.changeGroupName(groupOfCards.help, newName);
                    	UserInfo.saveEditGroup(groupOfCards.help);
                    }
                }
            });

            gbc.gridy++;
            rightPanel.add(buttonPanel, gbc);

            groupPanel.add(rightPanel, BorderLayout.CENTER);

            return groupPanel;
        }
    
    
	//updates sizes of elements and window
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
		}else if(width <= dimensions.minimumWindowWidth && height <= dimensions.minimumWindowHeight) {
			windowWidth = dimensions.minimumWindowWidth;
			windowHeight = dimensions.minimumWindowHeight;
		}else if(width <= dimensions.minimumWindowWidth && height > dimensions.minimumWindowHeight) {
    		windowWidth = dimensions.minimumWindowWidth;
            windowHeight = height;
    	}else if(height <= dimensions.minimumWindowHeight && width > dimensions.minimumWindowWidth) {
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

}