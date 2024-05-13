package views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


public class CardRun extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;
	private JPanel mainPanel;
    
    private final int HORIZONTAL_GAP_PERCENTAGE = 2; 
    private final int VERTICAL_GAP_PERCENTAGE = 3; 
    
    private final int MAX_RECTANGLE_HEIGHT = 150;
    private final int MAX_RECTANGLE_WIDTH = 300;
    
	public int groupsPerPage;
	public int START_X;
	public int START_Y;
	
	private final int ARC_WIDTH = 30;
    private final int ARC_HEIGHT = 30;
    
    int windowWidth;
    int windowHeight;
    int xPositionWindow;
    int yPositionWindow;
    
    ScreenDimensions screenSize;
	int minimumWindowWidth;
	int minimumWindowHeight;
	
	int previousWidth;
	int previousHeight;
	
	private int currentPage = 0;
    
    public CardRun() {
    	this(0,0,0,0);
    }
    
    public CardRun(int x, int y, int width, int height) {
    	//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());

    	screenSize = new ScreenDimensions();
    	
    	this.setTitle("STARTING GROUP OF CARDS");
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
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(0, 0, windowWidth, windowHeight-80);
        mainPanel.setBorder(new EmptyBorder(50,10,5,5));
        mainPanel.setOpaque(false); // Make buttonPanel transparent
        
        
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
		JLabel mainTitleLabel = new JLabel("Starting group of cards");      
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
		int saveButtonDimension = (int) (windowWidth * 0.010);
		
		//edit button in toolbar
		RoundButton editButton = new RoundButton("", buttonDimension ,buttonDimension );
		editButton.setBackground(toolbarColor);
		editButton.setButtonIcon("icons/PlayCardsIcon.png",  buttonDimension, buttonDimension);
		editButton.setBorder(null);
		buttonPanel.add(editButton);

		//add new group button in toolbar
		RoundButton addGroupButton = new RoundButton("",  buttonDimension, buttonDimension);
		addGroupButton.setButtonIcon("icons/ReturnArrowIcon.png",  buttonDimension, buttonDimension);
		addGroupButton.setBackground(toolbarColor);
		addGroupButton.setForeground(backgroundColor);
		addGroupButton.setBorder(null);
		buttonPanel.add(addGroupButton);
		
		//settings button in toolbar
		RoundButton settingsButton = new RoundButton("", buttonDimension,buttonDimension);
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
		
		RoundedButton save = new RoundedButton("Save changes");
		save.setFont(WindowElementResize.buttonText);
		save.setBounds((int)(windowWidth * 0.45), (int)(windowHeight * 0.800), (int)(windowWidth*0.115), (int)(windowHeight * 0.040));
		save.setPreferredSize(new Dimension(100,50));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardRun GroupsWindow = new CardRun(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
				GroupsWindow.setVisible(true);
				dispose();
			}
		});
		RoundedButton A = new RoundedButton("EVO");
		A.setFont(buttonText);
        A.setForeground(Color.BLACK);
        A.setBounds((int)(windowWidth * 0.15), (int)(windowHeight * 0.250), (int)(windowWidth * 0.700), (int)(windowHeight * 0.500));
        mainPanel.add(A);
		toolbarPanel.add(buttonPanel, BorderLayout.EAST);
		mainPanel.add(save);
		
		// Create an instance of ColorfulButtons
        ColorfulButtons colorfulButtons = new ColorfulButtons();
        colorfulButtons.setBounds((int)(windowWidth * 0.03), (int)(windowHeight * 0.09), (int)(windowWidth * 0.99), (int)(windowHeight * 0.3)); // Adjust the bounds as needed
        mainPanel.add(colorfulButtons);
        
        // panel with all required buttons
     	JPanel navigationLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, (int)(windowWidth * 0.05), (int)(windowHeight * 0.25)));
     	navigationLeft.setOpaque(false);
     	navigationLeft.setBounds((int)(windowWidth * 0.1), (int)(windowHeight * 0.45), buttonDimension*3, buttonDimension*3);
     	
     	
        RoundButton moveLeft = new RoundButton("", buttonDimension*2, buttonDimension*2);
        moveLeft.setButtonIcon("icons/LeftArrowIcon.png", buttonDimension*2, buttonDimension*2);
        moveLeft.setBackground(backgroundColor);
        moveLeft.setForeground(backgroundColor);
        moveLeft.setBorder(null);
    	navigationLeft.add(moveLeft);
    	
    	mainPanel.add(navigationLeft, BorderLayout.WEST);
    	
        
        // panel with all required buttons
     	JPanel navigationright = new JPanel(new FlowLayout(FlowLayout.RIGHT, (int)(windowWidth * 0.05), (int)(windowHeight * 0.25)));
     	navigationright.setOpaque(false);
     	navigationright.setBounds((int)(windowWidth * 0.85), (int)(windowHeight * 0.5), buttonDimension*3, buttonDimension*3);
        
        RoundButton moveRight = new RoundButton("", buttonDimension*2, buttonDimension*2);
    	moveRight.setButtonIcon("icons/RightArrowIcon.png", buttonDimension*2, buttonDimension*2);
    	moveRight.setBackground(backgroundColor);
    	moveRight.setForeground(backgroundColor);
    	moveRight.setBorder(null);
    	navigationright.add(moveRight);
    	
    	mainPanel.add(navigationright, BorderLayout.EAST);
    	contentPane.add(mainPanel, BorderLayout.CENTER);
    }
    
	//updates sizes of elements and window
	public void updateView() {
		 START_X = (int)(windowWidth * 0.05);
		 START_Y = (int)(windowHeight * 0.175); 
		windowCreate();
	}
	
	//function for checking bounds
	public void checkBounds(int x, int y, int width, int height){
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
	
	class MainPanel extends JPanel {

	    public MainPanel() {
	        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS orientation

	        // Add an empty JPanel at the top
	        JPanel topPanel = new JPanel();
	        topPanel.setPreferredSize(new Dimension(0, 1000)); // Set the height of the empty panel
	        add(topPanel, BorderLayout.NORTH);

	        // Add the ColorfulButtons panel below the empty panel
	        ColorfulButtons colorfulButtons = new ColorfulButtons();
	        add(colorfulButtons);
	    }

	}
	
	class ColorfulButtons extends JPanel {

	    public ColorfulButtons() {
	        setLayout(new FlowLayout(FlowLayout.LEFT)); // Buttons will be aligned to the left

	        //calculated variables for window height and width
	        int desiredHeight = (int) (dimensions.screenHeight * 0.435);
	        int desiredWidth = (int) (dimensions.screenWidth * 0.4);

	        JPanel buttonLayout = new JPanel(new GridLayout(0, 30, 10, 10)); // Change the GridLayout parameters
	        buttonLayout.setOpaque(false); // Make the panel transparent

	        // Create buttons with different colors
	        for (int i = 1; i <= 90; i++) {
	            RoundButton button = new RoundButton(String.valueOf(i)); // Adjust button size as needed
	            button.setBackground(Color.gray);
	            button.setPreferredSize(new Dimension((int) (0.075 * desiredWidth), (int) (0.125 * desiredHeight)));
	            button.setBorder(null);
	            button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    Color color = button.getBackground();
	                    int red = color.getRed();
	                    int green = color.getGreen();
	                    int blue = color.getBlue();
	                }
	            });
	            buttonLayout.add(button);
	        }

	        setOpaque(false); // Make the panel transparent
	        mainPanel.add(buttonLayout, BorderLayout.NORTH);
	    }

	}


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CardRun frame = new CardRun(0, 0, 0, 0);
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

