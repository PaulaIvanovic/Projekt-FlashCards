package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import views.ScreenDimensions;


public class Settings extends JFrame implements GlobalDesign{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	int buttonDimension;
	int biggerButtonDimension;
	int windowHeight;
	int windowWidth;
	int xPositionWindow;
	int yPositionWindow;
	
	ScreenDimensions dimensions;

	
	public Settings() {
    	this(0,0,0,0);
    }
    
    public Settings(int x, int y, int width, int height) {
    	//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());
		
		dimensions = new ScreenDimensions();
		
		this.setTitle("SETTINGS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(dimensions.minimumWindowWidth, dimensions.minimumWindowHeight)); // Minimum width = 300, Minimum height = 200
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
    	
		//main panel
		contentPane = new JPanel();
		contentPane.setBackground(backgroundColor);
		windowWidth = getWidth();
        windowHeight = getHeight();
        views.WindowElementResize.getFontForWindowSize(windowHeight);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		//toolbar on top of the window
		JPanel toolbarPanel = new JPanel(new BorderLayout());
		toolbarPanel.setBackground(toolbarColor);
		toolbarPanel.setBounds(0, 0, windowWidth, 80);
		toolbarPanel.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10));
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		
		//toolbar label (name of page)
		JLabel mainTitleLabel = new JLabel("Settings");      
		mainTitleLabel.setFont(WindowElementResize.mainFont);
		mainTitleLabel.setForeground(Color.WHITE);
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		// panel for buttons
		JPanel tbPane = new JPanel();
		tbPane.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		flowLayout.setHgap(10); 
		tbPane.setLayout(flowLayout);
		
		//button dimensions
    	buttonDimension = (int) (windowWidth * 0.025);
		biggerButtonDimension = (int) (windowWidth * 0.035);

		//return button in toolbar
		RoundButton returnButton = new RoundButton("", buttonDimension,buttonDimension);
		returnButton.setButtonIcon("Pictures/ReturnArrowIcon.png", buttonDimension,buttonDimension);
		returnButton.setBackground(toolbarColor);
		returnButton.setBorder(null);
		tbPane.add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroupOfCardsPage GroupsWindow = new GroupOfCardsPage(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
				GroupsWindow.setVisible(true);
				dispose();
			}
		});
		
		
		// user icon / button in toolbar
		RoundButton userPicture = new RoundButton("", biggerButtonDimension, biggerButtonDimension);
		userPicture.setButtonIcon("Pictures/UserIconBasic.png", biggerButtonDimension, biggerButtonDimension);
		userPicture.setBackground(toolbarColor);
		userPicture.setBorder(null);
		userPicture.setEnabled(false);
		tbPane.add(userPicture);
		
		toolbarPanel.add(tbPane, BorderLayout.EAST);
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		
		
		
		
		//panel for round buttons with images
        JPanel mainPanel = new JPanel(); // Use FlowLayout for center alignment
        mainPanel.setBounds(0, 0, windowWidth, windowHeight-80);
        mainPanel.setBorder(new EmptyBorder(0,0,5,5));
        mainPanel.setOpaque(false); // Make buttonPanel transparent
        mainPanel.setLayout(null);


        //label choose picture
    	JLabel lblChoose = new JLabel("Choose new profile picture:");
    	lblChoose.setForeground(new Color(255, 255, 255));
    	lblChoose.setFont(WindowElementResize.secFont);
    	lblChoose.setBounds((int)(windowWidth*0.21), (int)(windowHeight*0.01), (int)(windowWidth*0.215), (int)(windowHeight*0.075));
		mainPanel.add(lblChoose);
        

        for (int i = 0; i < profilePictures.length; i++) {
        	int firstPicWidth = (int)(windowWidth*0.175);
        	int firstPicHeight = (int)(windowHeight*0.3);
        	int PicWidth = (int)(windowWidth*0.1);
        	int PicHeight = (int)(windowHeight*0.175);
        	int distance = (int)(windowWidth*0.125);
        	int treshold = 1;
        	
        	//first chosen picture
        	if(i == 0) {
        		RoundButton userIcon = new RoundButton("", firstPicWidth, firstPicHeight);
            	userIcon.setButtonIcon("Pictures/" + "UserIconBasic.png", firstPicWidth, firstPicHeight);
            	userIcon.setBounds((int)(windowWidth*0.015)+(i)*distance, (int)(windowHeight*0.055), firstPicWidth, firstPicHeight);
            	userIcon.setBorder(null);
            	userIcon.setBackground(backgroundColor);
            	userIcon.setForeground(backgroundColor);
            	mainPanel.add(userIcon);   	
        	}
        	//different pictures to chose new profile
        	else {
           		RoundButton userIcon = new RoundButton("", 0, 0);
            	userIcon.setButtonIcon("Pictures/" + profilePictures[i-1], PicWidth, PicHeight);
            	userIcon.setBounds((int)(windowWidth*0.085)*treshold+(i)*distance, (int)(windowHeight*0.085), PicWidth, PicHeight);
            	userIcon.setBorder(null);
            	userIcon.setBackground(backgroundColor);
            	userIcon.setForeground(backgroundColor);
            	mainPanel.add(userIcon);
            	treshold=1;
        	}
        }
        
        //label for username
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(WindowElementResize.secFont);
		lblUsername.setBounds((int)(windowWidth*0.02), (int)(windowHeight*0.525), (int)(windowWidth*0.13), (int)(windowHeight*0.04));
		mainPanel.add(lblUsername);
		
		//text field for username
		textField = new RoundTextField(0);
		textField.setText("Current username"); 
		textField.setEnabled(false);
		textField.setFont(WindowElementResize.secFont);
		textField.setBounds((int)(windowWidth*0.02), (int)(windowHeight*0.575), (int)(windowWidth*0.17), (int)(windowHeight*0.04));
		mainPanel.add(textField);
		
		
		//button change username
		RoundedButton changeUsername = new RoundedButton("Change username", windowWidth, windowHeight);
		changeUsername.setForeground(Color.BLACK);
		changeUsername.setFont(WindowElementResize.secFont);
		changeUsername.setBounds((int)(windowWidth*0.2), (int)(windowHeight*0.575), (int)(windowWidth*0.1775), (int)(windowHeight*0.04));
		changeUsername.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		changeUsername.setBackground(new Color(248, 248, 255));
		mainPanel.add(changeUsername);
		
		
		//label email
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(WindowElementResize.secFont);
		lblEmail.setBounds((int)(windowWidth*0.02), (int)(windowHeight*0.4), (int)(windowWidth*0.13), (int)(windowHeight*0.04));
		mainPanel.add(lblEmail);
		
		//email text field
		textField_1 = new RoundTextField(0);
		textField_1.setEnabled(false);
		textField_1.setText("This is your email");
		textField_1.setFont(WindowElementResize.secFont);
		textField_1.setForeground(Color.BLACK);
		textField_1.setBounds((int)(windowWidth*0.02), (int)(windowHeight*0.45), (int)(windowWidth*0.356), (int)(windowHeight*0.04));
		mainPanel.add(textField_1);

		//label for password
		JLabel lbPassword = new JLabel("Password:");
		lbPassword.setForeground(new Color(255, 255, 255));
		lbPassword.setFont(WindowElementResize.secFont);
		lbPassword.setBounds((int)(windowWidth*0.02), (int)(windowHeight*0.65), (int)(windowWidth*0.13), (int)(windowHeight*0.04));
		mainPanel.add(lbPassword);
		
		//button change password
		RoundedButton changePassword = new RoundedButton("Change password", windowWidth, windowHeight);
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		changePassword.setForeground(Color.BLACK);
		changePassword.setFont(WindowElementResize.secFont);
		changePassword.setBounds((int)(windowWidth*0.02), (int)(windowHeight*0.7), (int)(windowWidth*0.356), (int)(windowHeight*0.04));
		changePassword.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		changePassword.setBackground(new Color(248, 248, 255));
		mainPanel.add(changePassword);
			
		
		//button cancel
		RoundedButton cancel = new RoundedButton("Cancel", windowWidth, windowHeight);
		cancel.setFont(WindowElementResize.buttonText);
		cancel.setBounds((int)(windowWidth * 0.750), (int)(windowHeight * 0.825), (int)(windowWidth*0.1), (int)(windowHeight * 0.035));
		mainPanel.add(cancel);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroupOfCardsPage GroupsWindow = new GroupOfCardsPage(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
				GroupsWindow.setVisible(true);
				dispose();
			}
		});
				
		//button save changes
		RoundedButton save = new RoundedButton("Save changes", windowWidth, windowHeight);
		save.setFont(WindowElementResize.buttonText);
		save.setBounds((int)(windowWidth * 0.860), (int)(windowHeight * 0.825), (int)(windowWidth*0.115), (int)(windowHeight * 0.035));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroupOfCardsPage GroupsWindow = new GroupOfCardsPage(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
				GroupsWindow.setVisible(true);
				dispose();
			}
		});
				
		mainPanel.add(save);
		contentPane.add(mainPanel, BorderLayout.CENTER);
	}

    //updates sizes of elements and window
	public void updateView() {
		windowWidth = getWidth();
	    windowHeight = getHeight();
		views.WindowElementResize.getFontForWindowSize(windowHeight);
		windowCreate();
	}
	
	//function for checking bounds
	public void checkBounds(int x, int y, int width, int height) {
		//with tolarence
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
	}
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
}