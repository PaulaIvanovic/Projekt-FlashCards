package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import databaseInfo.UserInfo;


public class GroupOfCardsPage extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;
	
	//global class variables
	private JPanel contentPane;
    
    private final int HORIZONTAL_GAP_PERCENTAGE = 2; 
    private final int MAX_RECTANGLE_HEIGHT = 150;
    private final int MAX_RECTANGLE_WIDTH = 300;
    
	public int groupsPerPage;
	public int START_X;
	public int START_Y;
	public int windowWidth;
	public int windowHeight;
	public int xPositionWindow;
	public int yPositionWindow;
	public int minimumWindowWidth;
	public int minimumWindowHeight;
	public int previousWidth;
	public int previousHeight;
	
	GroupOfCardsPage currentWindow;
    ScreenDimensions screenSize;
	
    //constructor used for testing and initial login
    public GroupOfCardsPage() {
    	this(0,0,0,0);
    }
    
    //constructor for returning to window
    public GroupOfCardsPage(int x, int y, int width, int height) {
    	//define currentWindow (current) for navigation between windows
		currentWindow = this;
		
		//set app icon
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());
		
		//get screen dimensions
    	screenSize = new ScreenDimensions();
    	
    	//set frame details
    	this.setTitle("GROUP OF CARDS PAGE");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setExtendedState(JFrame.MAXIMIZED_BOTH);		//window starts with maximum size
    	this.setMinimumSize(new Dimension(screenSize.minimumWindowWidth, screenSize.minimumWindowHeight));
    	setResizeListener(this);
		
    	//set frame current size
    	checkBounds(x, y, width, height);
    	this.setBounds(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
    	updateView();
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
  		
  		if(width <= 0 || height <= 0) { //if the screen is less then the minimum allowed size
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
    
  	//updates sizes of elements and window
  	public void updateView() {
  		START_X = (int)(windowWidth * 0.05);
  		START_Y = (int)(windowHeight * 0.175); 
  		windowCreate();
  	}
  	
  	//function for resizing components
    private void setResizeListener(GroupOfCardsPage groupOfCardsPage) {
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
		
		//check state
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

    //create window with all elements
	public void windowCreate() {
    	setVisible(true);
    	
    	//get users groups
    	UserInfo.getGroups();
    	
    	//get window size
        windowWidth = getWidth();
        windowHeight = getHeight();
        
        //get font size based on window
        views.WindowElementResize.getFontForWindowSize(windowHeight);

        //main panel initialization
        contentPane = new JPanel();
        contentPane.setBackground(backgroundColor);		
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		//toolbar on top of the window:
		JPanel toolbarPanel = new JPanel(new BorderLayout());
		toolbarPanel.setBackground(toolbarColor);
		toolbarPanel.setBounds(0, 0, windowWidth, 80);
		toolbarPanel.setLayout(new BorderLayout());
		toolbarPanel.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10));
		
		//toolbar label (name of page)
		JLabel mainTitleLabel = new JLabel(UserInfo.username + "'s groups of cards");      
		mainTitleLabel.setFont(WindowElementResize.mainFont);
		mainTitleLabel.setForeground(Color.WHITE);
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		//layout for toolbar buttons
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		flowLayout.setHgap(10); 
		
		// panel with all required buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(flowLayout);
		
		//variables for button dimensions
		int buttonDimension = (int) (windowWidth * 0.025);
		int biggerButtonDimension = (int) (windowWidth * 0.035);
		
		//info button in toolbar
		RoundButton infobutton = new RoundButton("", buttonDimension, buttonDimension);
		InfoButtonPopUp.setButton(GROUP_INFO, infobutton);
		infobutton.setBackground(toolbarColor);
		infobutton.setButtonIcon("icons/infopic.png",  buttonDimension, buttonDimension);
		infobutton.setBorder(null);
		buttonPanel.add(infobutton);

		//edit button in toolbar
		RoundButton editButton = new RoundButton("", buttonDimension, buttonDimension );
		editButton.setBackground(toolbarColor);
		editButton.setButtonIcon("icons/EditIcon.png",  buttonDimension, buttonDimension);
		editButton.setBorder(null);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditGroupPage editGroup = new EditGroupPage(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
				dispose();
				editGroup.setVisible(true);
			}
		});
		buttonPanel.add(editButton);
		
		//check edit button availability
		//when there is no groups to edit the button is disabled
		if(UserInfo.groupNames.size() <= 0) {
			editButton.setEnabled(false);
		}else {
			editButton.setEnabled(true);
		}

		//add new group button in toolbar
		RoundButton addGroupButton = new RoundButton("",  buttonDimension, buttonDimension);
		addGroupButton.setButtonIcon("icons/addIcon.png",  buttonDimension, buttonDimension);
		addGroupButton.setBackground(toolbarColor);
		addGroupButton.setForeground(backgroundColor);
		addGroupButton.setBorder(null);
		addGroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGroupOfCards addGroup = new AddGroupOfCards(currentWindow);
				addGroup.setVisible(true);
			}
		});
		buttonPanel.add(addGroupButton);
		
		//settings button in toolbar
		RoundButton settingsButton = new RoundButton("", buttonDimension, buttonDimension);
		settingsButton.setButtonIcon("icons/settingsIcon.png", buttonDimension, buttonDimension);
		settingsButton.setBackground(toolbarColor);
		settingsButton.setForeground(backgroundColor);
		settingsButton.setBorder(null);
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings settingsWindow = new Settings(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
				settingsWindow.setVisible(true);
				dispose();
			}
		});
		buttonPanel.add(settingsButton);
		
		//profile picture for user
		RoundButton userIcon = new RoundButton("", biggerButtonDimension, biggerButtonDimension);
		userIcon.setButtonIcon("Pictures/" + UserInfo.profilePic, biggerButtonDimension, biggerButtonDimension);
		userIcon.setBackground(toolbarColor);
		userIcon.setBorder(null);
		buttonPanel.add(userIcon);
		
		//add buttons to toolbar
		toolbarPanel.add(buttonPanel, BorderLayout.EAST);
		
		//add toolbar to main panel
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		 
		//scroll pane for groups
		JScrollPane scrollPane = new JScrollPane(groupsCollection());	
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        contentPane.add(scrollPane, BorderLayout.CENTER);  
    }
    
    public JPanel groupsCollection() {
    	//panel for groups
        JPanel groupsPanel = new JPanel(new GridBagLayout());
        groupsPanel.setBackground(backgroundColor);
        
        //gridbag constraints for gblayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //frame sizes
        int frameWidth = windowWidth;
        int frameHeight = windowHeight;
        int horizontalGap = (int) (frameWidth * HORIZONTAL_GAP_PERCENTAGE / 100.0);
        
        //define number of groups column
        int groupCol = 5;
        if(UserInfo.groupNames.size() > 0) {
        	groupCol = UserInfo.groupNames.size();
        }
        
        //calculates the number of rows and columns for the current page
        int numCols = Math.min(groupCol, 5);
        int numRows = (numCols - 1) / 5 + 1;

        //calculates the width and height of each rectangle
        int rectangleWidth = Math.min((frameWidth - START_X * 2 - (numCols - 1) * horizontalGap) / numCols, MAX_RECTANGLE_WIDTH);
        int availableHeight = frameHeight - (int)(frameHeight * 0.2);
        int rectangleHeight = Math.min(availableHeight / numRows, MAX_RECTANGLE_HEIGHT);
        
        //add groups to the panel
        for (int i = 0; i < UserInfo.groupNames.size(); i++) {
        	//create group as button
            RoundedButton groupOfCards = new RoundedButton("");
            groupOfCards.setText(UserInfo.groupNames.get(i));
            groupOfCards.setFont(WindowElementResize.mediumFont);
            groupOfCards.setBackground(UserInfo.groupColors.get(i));
            groupOfCards.setPreferredSize(new Dimension(rectangleWidth, rectangleHeight)); // Set height to 200
            groupOfCards.setForeground(Color.BLACK);
            
            //save group name and place
            String name = UserInfo.groupNames.get(i);
            int position = i;
            
            //listens for clicks on group to open its page
            groupOfCards.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				//save opened group ID
    				UserInfo.groupID = UserInfo.groupIDs.get(position);
    				
    				//show subgroups
    				SubgroupOfCardsPage subGroup = new SubgroupOfCardsPage(xPositionWindow, yPositionWindow, windowWidth, windowHeight, name);
    				subGroup.setVisible(true);
    				dispose();
    			}
    		});
            
            gbc.anchor = GridBagConstraints.NORTHWEST;

            //adds padding from the edges of the panel
            if (gbc.gridx == 0) {
                gbc.insets = new Insets(10, 10, 10, 10); 
            } else if (gbc.gridx == 4) {
                gbc.insets = new Insets(10, 10, 10, 10); 
            } else {
                gbc.insets = new Insets(10, 10, 10, 10); 
            }

            groupsPanel.add(groupOfCards, gbc);
            gbc.gridx++;
            
            // start a new row after every 5 groups
            if ((i+1) % 5 == 0) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }
        
        //button for adding a group when there are none
        if(UserInfo.groupNames.size() <= 0) {
        	 RoundedButton addGroup = new RoundedButton("");
        	 addGroup.setText("No groups found! Add a group<br>" + "<b>+</b>");
        	 addGroup.setForeground(Color.WHITE);
        	 addGroup.setFont(WindowElementResize.mediumFont);
        	 addGroup.setPreferredSize(new Dimension(rectangleWidth, rectangleHeight));
        	 //make transparent
        	 addGroup.setBackground(transparent);
        	 
        	 //listens for clicks - adding a group
        	 addGroup.addActionListener(new ActionListener() {
        		 public void actionPerformed(ActionEvent e) {
     				AddGroupOfCards addGroup = new AddGroupOfCards(currentWindow);
     				addGroup.setVisible(true);
     			}
     		});
            
        	groupsPanel.add(addGroup, gbc);
        }
        return groupsPanel;
    }
}