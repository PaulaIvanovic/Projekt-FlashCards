package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EditSubgroupPage extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	//responsive starting coordinates
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
    
    public EditSubgroupPage() {
    	this(0,0,0,0);
    };
    
    public EditSubgroupPage(int x, int y, int width, int height) {
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
		
			JLabel mainTitleLabel = new JLabel("Group <GroupName> subgroups - edit");      
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
	
				RoundedButton saveButton = new RoundedButton("Save all");
				saveButton.setPreferredSize(new Dimension(95, 40));
				buttonPanel.add(saveButton);
				
				RoundButton editButton = new RoundButton("",buttonDimension ,buttonDimension );
				editButton.setBackground(toolbarColor);
				editButton.setButtonIcon("icons/EditIcon.png", buttonDimension, buttonDimension);
				editButton.setBorder(null);
				buttonPanel.add(editButton);

				//button to add a new subgroup
				RoundButton addSubgroupButton = new RoundButton("",  buttonDimension, buttonDimension);
				addSubgroupButton.setButtonIcon("icons/addIcon.png",  buttonDimension, buttonDimension);
				addSubgroupButton.setBackground(toolbarColor);
				addSubgroupButton.setForeground(backgroundColor);
				addSubgroupButton.setBorder(null);
				buttonPanel.add(addSubgroupButton);

				//settings button
				RoundButton settingsButton = new RoundButton("", buttonDimension,buttonDimension);
				settingsButton.setButtonIcon("icons/settingsIcon.png", buttonDimension,buttonDimension);
				settingsButton.setBackground(toolbarColor);
				settingsButton.setForeground(backgroundColor);
				settingsButton.setBorder(null);
				buttonPanel.add(settingsButton);
				
				RoundButton userIcon = new RoundButton("", biggerButtonDimension, biggerButtonDimension);
				userIcon.setButtonIcon("icons/UserIconBasic.png", biggerButtonDimension, biggerButtonDimension);
				userIcon.setBackground(toolbarColor);
				userIcon.setBorder(null);
				buttonPanel.add(userIcon);

				toolbarPanel.add(buttonPanel, BorderLayout.EAST);
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
}


