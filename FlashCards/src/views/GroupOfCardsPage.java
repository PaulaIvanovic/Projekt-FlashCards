package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JWindow;


public class GroupOfCardsPage extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;
    
    private final int HORIZONTAL_GAP_PERCENTAGE = 2; 
    
    private final int MAX_RECTANGLE_HEIGHT = 150;
    private final int MAX_RECTANGLE_WIDTH = 300;
    
	public int groupsPerPage;
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
	
	private void showPopup(String message, Component component) {
        hidePopup();
        JWindow popup = new JWindow();
        popup.setName("popup"); 
        
        JLabel label = new JLabel("<html>" + message.replaceAll("\n", "<br>") + "</html>");
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setHorizontalAlignment(JLabel.CENTER); 
        label.setVerticalAlignment(JLabel.CENTER);
        
        popup.add(label);
        popup.pack();
    
        Point buttonLocation = component.getLocationOnScreen();

        popup.setLocation(buttonLocation.x - popup.getWidth(), buttonLocation.y);
    
        popup.setVisible(true);
    } 
	void hidePopup() {
        for (Window window : Window.getWindows()) {
            if (window instanceof JWindow && window.getName() != null && window.getName().equals("popup")) {
                window.dispose();
            }
        }
    }

    public GroupOfCardsPage() {
    	this(0,0,0,0);
    }
    
    private static final String PAGE_INFO = "<html><b>Informacije o stranici:</b><br>"
            + "Ovo je stranica koja prikazuje grupe karata.<br>"
            + "Korisnik može dodavati nove grupe, uređivati postojeće i pregledavati detalje svake grupe.<br>"
            + "Za više informacija, pritisnite gumb 'Informacije'.</html>";
    
    public GroupOfCardsPage(int x, int y, int width, int height) {
    	//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());

    	screenSize = new ScreenDimensions();
    	
    	this.setTitle("GROUP OF CARDS PAGE");
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
    	

    	// Listener for window state changes
        addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if ((e.getNewState() & JFrame.MAXIMIZED_BOTH) == 0) {
                    // Store the previous size when the window is not maximized
                    previousWidth = getWidth();
                    previousHeight = getHeight();
                }
            }
        });
        
    	////when minimize/maximize button is clicked, window goes to 50% of its original (fullscreen) size
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
		JLabel mainTitleLabel = new JLabel("My groups of cards");      
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
		
		//edit button in toolbar
		RoundButton infobutton = new RoundButton("",buttonDimension ,buttonDimension );
		infobutton.setBackground(toolbarColor);
		infobutton.setButtonIcon("icons/infopic.png",  buttonDimension, buttonDimension);
		infobutton.setBorder(null);
		buttonPanel.add(infobutton);
		
		// Dodajte sljedeći kod ispod inicijalizacije infobutton-a
		infobutton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	showPopup(PAGE_INFO, infobutton);
		    }
		    
		    @Override
		    public void mouseExited(MouseEvent e) {
		        hidePopup();
		    }
		});
		

		//edit button in toolbar
		RoundButton editButton = new RoundButton("",buttonDimension ,buttonDimension );
		editButton.setBackground(toolbarColor);
		editButton.setButtonIcon("icons/EditIcon.png",  buttonDimension, buttonDimension);
		editButton.setBorder(null);
		buttonPanel.add(editButton);

		//add new group button in toolbar
		RoundButton addGroupButton = new RoundButton("",  buttonDimension, buttonDimension);
		addGroupButton.setButtonIcon("icons/addIcon.png",  buttonDimension, buttonDimension);
		addGroupButton.setBackground(toolbarColor);
		addGroupButton.setForeground(backgroundColor);
		addGroupButton.setBorder(null);
		buttonPanel.add(addGroupButton);
		
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
		userIcon.setButtonIcon("icons/UserIconBasic.png", biggerButtonDimension, biggerButtonDimension);
		userIcon.setBackground(toolbarColor);
		userIcon.setBorder(null);
		buttonPanel.add(userIcon);
		
		toolbarPanel.add(buttonPanel, BorderLayout.EAST);
		 
		
		JScrollPane scrollPane = new JScrollPane(groupsCollection());	
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        contentPane.add(scrollPane, BorderLayout.CENTER);  
    }
    
    public JPanel groupsCollection() {
        JPanel groupsPanel = new JPanel(new GridBagLayout());
        groupsPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //frame sizes
        int frameWidth = windowWidth;
        int frameHeight = windowHeight;
        int horizontalGap = (int) (frameWidth * HORIZONTAL_GAP_PERCENTAGE / 100.0);

        //calculates the number of rows and columns for the current page
        int numCols = Math.min(groupNames.length, 5);
        int numRows = (numCols - 1) / 5 + 1;

        //calculates the width and height of each rectangle
        int rectangleWidth = Math.min((frameWidth - START_X * 2 - (numCols - 1) * horizontalGap) / numCols, MAX_RECTANGLE_WIDTH);
        int availableHeight = frameHeight - (int)(frameHeight * 0.2);
        int rectangleHeight = Math.min(availableHeight / numRows, MAX_RECTANGLE_HEIGHT);
        

        //add groups to the panel
        for (int i = 1; i < groupNames.length; i++) {
            RoundedButton groupOfCards = new RoundedButton(groupNames[i]);
            groupOfCards.setBackground(groupColors[i]);
            groupOfCards.setFont(WindowElementResize.mediumFont);
            groupOfCards.setPreferredSize(new Dimension(rectangleWidth, rectangleHeight)); // Set height to 200
            
            String name = groupNames[i];
            Color color = groupColors[i];
            
            //listens for clicks on group to open its page
            groupOfCards.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				new GroupPage(name, color).setVisible(true);;
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
            if (i % 5 == 0) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }
        return groupsPanel;
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

    public static void main(String[] args) {
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