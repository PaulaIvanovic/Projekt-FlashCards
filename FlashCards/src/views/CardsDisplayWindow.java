package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import databaseInfo.UserInfo;
import services.ColorUtils;


public class CardsDisplayWindow extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;
    
    private final int HORIZONTAL_GAP_PERCENTAGE = 2; 
    
    private final int MAX_RECTANGLE_HEIGHT = 325;
    private final int MAX_RECTANGLE_WIDTH = 325;
    
	public int START_X;
	public int START_Y;
	
	CardsDisplayWindow parent;
	
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
    
    public CardsDisplayWindow() {
    	this(0,0,0,0, "");
    }
    
    public CardsDisplayWindow(int x, int y, int width, int height, String name) {
    	this.name = name;
    	parent = this;
    	//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());

    	screenSize = new ScreenDimensions();
    	
    	this.setTitle("CARDS PAGE");
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
    	
    	// listener for window state changes
        addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if ((e.getNewState() & JFrame.MAXIMIZED_BOTH) == 0) {
                    // store the previous size when the window is not maximized
                    previousWidth = getWidth();
                    previousHeight = getHeight();
                }
            }
        });
        
    	//when minimize/maximize button is clicked, 
        //window goes to 50% of its original (fullscreen) size
    	this. addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if ((getExtendedState() & JFrame.MAXIMIZED_BOTH) == 0) {
                    setSize(previousWidth / 2, previousHeight / 2);
                 
                    revalidate();
                    repaint();
                }
            }
        });
    	
    	//listener for window state changes
    	addWindowStateListener(new WindowAdapter() {
    	    public void windowStateChanged(WindowEvent e) {
    	        if ((e.getNewState() & JFrame.MAXIMIZED_BOTH) != 0) {
    	            updateView();
    	        }else if (e.getNewState() == JFrame.NORMAL) {
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
  
    }
    
	public void windowCreate() {
    	setVisible(true);
    	UserInfo.getCards();
    	
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
		JLabel mainTitleLabel = new JLabel("Subgroup - " + name);      
		mainTitleLabel.setFont(WindowElementResize.mainFont);
		mainTitleLabel.setForeground(Color.WHITE);
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		// panel with all required buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		flowLayout.setHgap(10); 
		buttonPanel.setLayout(flowLayout);
		
		//variables for button dimensions
		int buttonDimension = (int) (windowWidth * 0.025);
		int biggerButtonDimension = (int) (windowWidth * 0.035);
		
		//download in toolbar
		RoundButton downloadButton = new RoundButton("",buttonDimension ,buttonDimension );
		downloadButton.setBackground(toolbarColor);				
		downloadButton.setButtonIcon("icons/DownloadIcon.png",  buttonDimension, buttonDimension);
		downloadButton.setBorder(null);
		buttonPanel.add(downloadButton);
		
		//play in toolbar
		RoundButton playButton = new RoundButton("",buttonDimension ,buttonDimension );
		playButton.setBackground(toolbarColor);				
		playButton.setButtonIcon("icons/PlaycardsIcon.png",  buttonDimension, buttonDimension);
		playButton.setBorder(null);
		buttonPanel.add(playButton);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfo.generateCardLineup();
				CardRun cards = new CardRun(xPositionWindow, yPositionWindow, windowWidth, windowHeight, UserInfo.getFirstCardID());
				dispose();
				cards.setVisible(true);
			}
		});
		
		//return button in toolbar
		RoundButton returnButton = new RoundButton("",buttonDimension ,buttonDimension );
		returnButton.setBackground(toolbarColor);
		returnButton.setButtonIcon("icons/ReturnArrowIcon.png",  buttonDimension, buttonDimension);
		returnButton.setBorder(null);
		buttonPanel.add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubgroupOfCardsPage subGroup = new SubgroupOfCardsPage(xPositionWindow, yPositionWindow, windowWidth, windowHeight, UserInfo.getname(true));
				dispose();
				subGroup.setVisible(true);
			}
		});
		
		//edit button in toolbar
		RoundButton editButton = new RoundButton("",buttonDimension ,buttonDimension );
		editButton.setBackground(toolbarColor);
		editButton.setButtonIcon("icons/EditIcon.png",  buttonDimension, buttonDimension);
		editButton.setBorder(null);
		buttonPanel.add(editButton);
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditCards editCards = new EditCards(xPositionWindow, yPositionWindow, windowWidth, windowHeight, name);
				dispose();
				editCards.setVisible(true);
			}
		});
		//when there is nothing to edit the button is disabled
		if(UserInfo.cardQuestion.size() <= 0) {
			editButton.setEnabled(false);
			playButton.setEnabled(false);
		}else {
			editButton.setEnabled(true);
			playButton.setEnabled(true);
		}

		//add new group button in toolbar
		RoundButton addCardButton = new RoundButton("",  buttonDimension, buttonDimension);
		addCardButton.setButtonIcon("icons/addIcon.png",  buttonDimension, buttonDimension);
		addCardButton.setBackground(toolbarColor);
		addCardButton.setForeground(backgroundColor);
		addCardButton.setBorder(null);
		buttonPanel.add(addCardButton);
		CardsDisplayWindow parent = this;
		addCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCardsManual addCard = new AddCardsManual(parent);
				addCard.setVisible(true);
			}
		});
		
		//settings button in toolbar
		RoundButton settingsButton = new RoundButton("",buttonDimension,buttonDimension);
		settingsButton.setButtonIcon("icons/settingsIcon.png",buttonDimension,buttonDimension);
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
		RoundButton userIcon = new RoundButton("",biggerButtonDimension, biggerButtonDimension);
		userIcon.setButtonIcon("Pictures/" + UserInfo.profilePic, biggerButtonDimension, biggerButtonDimension);
		userIcon.setBackground(toolbarColor);
		userIcon.setBorder(null);
		//userIcon.setEnabled(false);
		buttonPanel.add(userIcon);
		
		
		toolbarPanel.add(buttonPanel, BorderLayout.EAST);
		 
		
		JScrollPane scrollPane = new JScrollPane(groupsCollection());	
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        contentPane.add(scrollPane, BorderLayout.CENTER);  
    }
    
    public JPanel groupsCollection() {
        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //frame sizes
        int frameWidth = windowWidth;
        int frameHeight = windowHeight;
        int horizontalGap = (int) (frameWidth * HORIZONTAL_GAP_PERCENTAGE / 100.0);
        
        int help = 5;
        //calculates the number of rows and columns for the current page
        if(UserInfo.cardColors.size() > 0) {
        	help = UserInfo.cardColors.size();
        	//make sure it is not 0
        }
        
        int numCols = Math.min(help, 5);
        int numRows = (numCols - 1) / 5 + 1;

        //calculates the width and height of each rectangle
        int rectangleWidth = Math.min((frameWidth - START_X * 2 - (numCols - 1) * horizontalGap) / numCols, MAX_RECTANGLE_WIDTH);
        int availableHeight = frameHeight - (int)(frameHeight * 0.2);
        int rectangleHeight = Math.min(MAX_RECTANGLE_HEIGHT, availableHeight / 4);
        

        //add groups to the panel
        for (int i = 0; i < UserInfo.cardColors.size(); i++) {
        	RoundedButton cardArea = new RoundedButton("");
        	cardArea.setText(UserInfo.cardQuestion.get(i));
        	cardArea.setBackground(UserInfo.cardColors.get(i));
        	cardArea.setFont(WindowElementResize.secFont);
        	cardArea.setPreferredSize(new Dimension(rectangleWidth, rectangleHeight)); // Set height 
        	cardArea.setForeground(ColorUtils.getContrastingTextColor(UserInfo.cardColors.get(i)));

            String question = UserInfo.cardQuestion.get(i);
            Color color = UserInfo.cardColors.get(i);
            int index = i;
            cardArea.addMouseListener(new MouseAdapter() {
                private boolean showingAnswer = false; // Track if the answer is currently being shown

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (showingAnswer) {
                        cardArea.setText(UserInfo.cardQuestion.get(index));
                    } else {
                        cardArea.setText(UserInfo.cardAnswer.get(index));
                    }
                    showingAnswer = !showingAnswer; // Toggle the state
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

            cardPanel.add(cardArea, gbc);
            gbc.gridx++;
            
            // start a new row after every 5 groups
            if ((i+1) % 5 == 0) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }
        
        if(UserInfo.cardColors.size() <= 0) {
       	 RoundedButton addGroup = new RoundedButton("");
       	 addGroup.setText("No cards found! Add a card<br>" + "<b>+</b>");
       	 addGroup.setForeground(Color.WHITE);
       	 addGroup.setBackground(new Color(0,0,0, 128));
       	 addGroup.setFont(WindowElementResize.mediumFont);
       	 addGroup.setPreferredSize(new Dimension(rectangleWidth, rectangleHeight)); // Set height 
       	 //listens for clicks on group to open its page
       	 addGroup.addActionListener(new ActionListener() {
       		 public void actionPerformed(ActionEvent e) {
    				AddCardsManual addCard = new AddCardsManual(parent);
    				addCard.setVisible(true);
    			}
    		});
            
            
       	cardPanel.add(addGroup, gbc);
       }
        
        
        return cardPanel;
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

	/*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CardsDisplayWindow frame = new CardsDisplayWindow(0, 0, 0, 0, " ");
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
}