package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import views.WindowElementResize;


public class GroupOfCardsPage extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;

	private final int ARC_WIDTH = 30;
    private final int ARC_HEIGHT = 30;    
    private final int START_X = 80;
    private final int START_Y = 150;
    private final int HORIZONTAL_GAP_PERCENTAGE = 3; 
    private final int VERTICAL_GAP_PERCENTAGE = 3; 
    private final int MAX_RECTANGLE_HEIGHT = 180;
    private final int MAX_RECTANGLE_WIDTH = 320;
	public int groupsPerPage;
	private int currentPage = 0;
    
    int windowWidth;
    int windowHeight;
    int xPositionWindow;
    int yPositionWindow;
    
    ScreenDimensions screenSize;
	int minimumWindowWidth;
	int minimumWindowHeight;
    
    public GroupOfCardsPage() {
    	this(0,0,0,0);
    }
    
    public GroupOfCardsPage(int x, int y, int width, int height) {
    	//set icon for app
    	System.out.println("glavni g : " + width +"x"+height);
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());

    	screenSize = new ScreenDimensions();
    	
    	this.setTitle("GROUP OF CARDS PAGE");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setMinimumSize(new Dimension(screenSize.minimumWindowWidth, screenSize.minimumWindowHeight)); // Minimum width = 300, Minimum height = 200
		checkBounds(x, y, width, height);
    	this.setBounds(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
    	System.out.println("drugi g: " + width +"x"+height);
    	updateView();

    	//function for resizing components
    	addComponentListener(new ComponentAdapter() {
    	    public void componentResized(ComponentEvent e) {
    	        Dimension newSize = e.getComponent().getSize();
    	        
    	       
    	        if (windowWidth != newSize.width || windowHeight != newSize.height) {
    	        	if(newSize.width <= dimensions.minimumWindowWidth && newSize.height <= dimensions.minimumWindowHeight) {
    	        		windowWidth = dimensions.minimumWindowWidth;
        	            windowHeight = dimensions.minimumWindowHeight;
    	        	}else if(newSize.width <= dimensions.minimumWindowWidth && newSize.height > dimensions.minimumWindowHeight) {
    	        		windowWidth = dimensions.minimumWindowWidth;
        	            windowHeight = newSize.height;
    	        	}else if(newSize.height <= dimensions.minimumWindowHeight && newSize.width > dimensions.minimumWindowWidth) {
    	        		windowWidth = newSize.width;
        	            windowHeight = dimensions.minimumWindowHeight;
    	        	}else if(newSize.width == dimensions.screenWidth && newSize.height == dimensions.screenHeight) {
    	        		windowWidth = dimensions.screenWidth;
    	        		windowHeight = dimensions.screenHeight;
    	        	}else if(newSize.width == dimensions.screenWidth && newSize.height != dimensions.screenHeight) {
    	        		windowWidth = dimensions.screenWidth;
    	        		windowHeight = newSize.height;
    	        	}else if(newSize.width != dimensions.screenWidth && newSize.height == dimensions.screenHeight) {
    	        		windowWidth = newSize.width;
    	        		windowHeight = dimensions.screenHeight;
    	        	}else {
    	        		windowWidth = newSize.width;
    	        		windowHeight = newSize.height;
    	        	}
    	        	System.out.println(windowHeight +"x"+windowHeight);
    	            updateView();
    	            
    	        }
    	    }
    	});
    	
    	
    	this.addComponentListener(new ComponentAdapter(){
	        public void componentResized(ComponentEvent e){
	            Dimension d=GroupOfCardsPage.this.getSize();
	            Dimension minD=GroupOfCardsPage.this.getMinimumSize();
	            if(d.width<minD.width) {
	            	d.width=minD.width;
	            }
	                
	            if(d.height<minD.height) {
	            	 d.height=minD.height;
	            }
	               
	            GroupOfCardsPage.this.setSize(d);
	        }
    	});
  

    	//listener for window state changes
    	addWindowStateListener(new WindowAdapter() {
    	    public void windowStateChanged(WindowEvent e) {
    	        if ((e.getNewState() & JFrame.MAXIMIZED_BOTH) != 0) {
    	            updateView();
    	        }else if (e.getNewState() == JFrame.NORMAL) {
    	            // Window is restored
    	            // Perform any actions needed when window is restored
    	            // For example, update the view
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
    	

        //main panel
        contentPane = new JPanel();
        contentPane.setBackground(backgroundColor);		
        windowWidth = getWidth();
        windowHeight = getHeight();
        views.WindowElementResize.getFontForWindowSize(windowHeight);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		setItemPerPage();
		
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
		
		//variables for button dimenions
		int buttonDimension = (int) (windowWidth * 0.025);
		int biggerButtonDimension = (int) (windowWidth * 0.035);

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
				System.out.println(windowWidth +"x"+windowHeight);
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
		 
		//rectangles representing groups of cards
        DrawGroupRectangles drawRectangles = new DrawGroupRectangles();
        contentPane.add(drawRectangles, BorderLayout.CENTER);
        
        
        //panel with buttons to navigate through pages
        JPanel navigationPanel = new JPanel();
        navigationPanel.setOpaque(false);
		FlowLayout navFlowLayout = new FlowLayout(FlowLayout.CENTER);
		navFlowLayout.setHgap(10); 
		navigationPanel.setLayout(navFlowLayout);
		
		//round button for going back
        RoundButton arrowLeft = new RoundButton("",biggerButtonDimension, biggerButtonDimension);
        arrowLeft.setButtonIcon("icons/LeftArrowIcon.png", biggerButtonDimension, biggerButtonDimension);
        arrowLeft.setBackground(backgroundColor);
        arrowLeft.setBorder(null);
        navigationPanel.add(arrowLeft);
        
        //page label
        JLabel currentPageLabel = new JLabel("1");
        currentPageLabel.setFont(WindowElementResize.secFont);
        currentPageLabel.setForeground(Color.white);
        navigationPanel.add(currentPageLabel);
        
        //round button for going to the next page
        RoundButton arrowRight = new RoundButton("", biggerButtonDimension, biggerButtonDimension);
        arrowRight.setButtonIcon("icons/RightArrowIcon.png",biggerButtonDimension, biggerButtonDimension);
        arrowRight.setBackground(backgroundColor);
        arrowRight.setBorder(null);
        navigationPanel.add(arrowRight);
        
        contentPane.add(navigationPanel, BorderLayout.SOUTH);

        //mouse listener to detect when a group rectangle is clicked
        drawRectangles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedIndex = getClickedRectangleIndex(e.getX(), e.getY());
                if (clickedIndex != -1) {
                    // Opens a new window with the corresponding group name
                	dispose();
                	//new GroupPage(groupNames[clickedIndex], groupColors[clickedIndex]).setVisible(true);
                }
            }
        });
        
        
        //mouse listeners for arrows (navigation through pages):
        arrowLeft.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                currentPageLabel.setText(Integer.toString(currentPage + 1));
                drawRectangles.repaint(); // Repaint the component to reflect the updated current page
            }
        });
        arrowRight.addActionListener(e -> {
            int totalPages = (int) Math.ceil((double) groupNames.length / groupsPerPage );
            if (currentPage < totalPages - 1) {
                currentPage++;
                currentPageLabel.setText(Integer.toString(currentPage + 1));
                drawRectangles.repaint(); // Repaint the component to reflect the updated current page
            }
        });
        
        
    }
    
	//updates sizes of elements and window
	public void updateView() {
		windowCreate();
	}
	
	//define how many groups and subgroups we can display on page
	public void setItemPerPage() {
		if(windowHeight > 1000) {
			groupsPerPage = 15;
		}else {
			groupsPerPage = 10;	
		}
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
				if(x <= -10 || y <= -10) {
					xPositionWindow = 0;
					yPositionWindow = 0;
				}else {
					xPositionWindow = x;
					yPositionWindow = y;
				}
	}
	
	

    class DrawGroupRectangles extends JComponent {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(backgroundColor);
            g2.fillRect(0, 0, windowWidth, windowHeight);
            g2.setFont(WindowElementResize.mediumFont);

            //frame sizes
            int frameWidth = windowWidth;
            int frameHeight = windowHeight;
            int horizontalGap = (int) (frameWidth * HORIZONTAL_GAP_PERCENTAGE / 100.0);
            int verticalGap = (int) (frameHeight * VERTICAL_GAP_PERCENTAGE / 100.0);


            //calculates the index range for the current page
            int startIndex = currentPage * groupsPerPage;
            int endIndex = Math.min(startIndex + groupsPerPage, groupNames.length);

            //calculates the number of rows and columns for the current page
            int numCols = Math.min(groupNames.length - startIndex, 5);
            int numRows = (numCols - 1) / 5 + 1;

            //calculates the width and height of each rectangle
            int rectangleWidth = Math.min((frameWidth - START_X * 2 - (numCols - 1) * horizontalGap) / numCols, MAX_RECTANGLE_WIDTH);
            int rectangleHeight = Math.min((frameHeight - START_Y * 2 - (numRows - 1) * verticalGap) / numRows, MAX_RECTANGLE_HEIGHT);


            for (int i = startIndex; i < endIndex; i++) {
                int row = (i - startIndex) / 5;
                int col = (i - startIndex) % 5;

                int x = START_X + col * (rectangleWidth + horizontalGap);
                int y = START_Y + row * (rectangleHeight + verticalGap);

                // To make the rectangles have rounded edges
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(x, y, rectangleWidth, rectangleHeight, ARC_WIDTH, ARC_HEIGHT);

                g2.setColor(groupColors[i]);
                g2.fill(roundedRectangle);
                g2.setColor(Color.BLACK);

                String text = groupNames[i];
                drawCenteredString(g2, text, x, y, rectangleWidth, rectangleHeight);
               }     
        	}
        }

        //writes out name of group (centered within the rectangle)
        private void drawCenteredString(Graphics2D g2, String text, int x, int y, int width, int height) {
            FontMetrics metrics = g2.getFontMetrics();
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();
            int centerX = x + (width - textWidth) / 2;
            int centerY = y + (height - textHeight) / 2 + metrics.getAscent();
            g2.drawString(text, centerX, centerY);
        }

      // gets index of clicked group, to use it when displaying group name in new window
      // makes sure mouse is inside rectangle coordinates (outside clicks don't count)
      private int getClickedRectangleIndex(int mouseX, int mouseY) {
        int frameWidth = windowWidth;
        int frameHeight = windowHeight;
        int horizontalGap = (int) (frameWidth * HORIZONTAL_GAP_PERCENTAGE / 100.0);
        int verticalGap = (int) (frameHeight * VERTICAL_GAP_PERCENTAGE / 100.0);

        //calculates the index range for the current page
        int startIndex = currentPage * groupsPerPage;
        int endIndex = Math.min(startIndex + groupsPerPage, groupNames.length);

        //calculates the number of rows and columns for the current page
       int numCols = Math.min(groupNames.length - startIndex, 5);
       int numRows = (numCols - 1) / 5 + 1;

       //calculates the width and height of each rectangle
        int rectangleWidth = Math.min((frameWidth - START_X * 2 - (numCols - 1) * horizontalGap) / numCols, MAX_RECTANGLE_WIDTH);
        int rectangleHeight = Math.min((frameHeight - START_Y * 2 - (numRows - 1) * verticalGap) / numRows, MAX_RECTANGLE_HEIGHT);

        for (int i = startIndex; i < endIndex; i++) {
        	int row = (i - startIndex) / 5;
        	int col = (i - startIndex) % 5;

        	int x = START_X + col * (rectangleWidth + horizontalGap);
        	int y = START_Y + row * (rectangleHeight + verticalGap);

        	if (mouseX >= x && mouseX <= x + rectangleWidth && mouseY >= y && mouseY <= y + rectangleHeight) {
        	    return i;
        	}
        }
       // if clicked outside the rectangles
       return -1; 
     }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GroupOfCardsPage frame = new GroupOfCardsPage(0, 0, 0, 0);
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
