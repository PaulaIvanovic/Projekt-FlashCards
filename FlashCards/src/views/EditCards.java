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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import views.AddCardsManual.ColorfulButtons;



public class EditCards extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;
	private RoundTextField qInput, aInput;
    
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
    
    public EditCards() {
    	this(0,0,0,0);
    }
    
    public EditCards(int x, int y, int width, int height) {
    	//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());

    	screenSize = new ScreenDimensions();
    	
    	this.setTitle("EDIT CARDS IN SUBGROUPS");
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
		JLabel mainTitleLabel = new JLabel("Subgroup questions - Edit cards");      
		mainTitleLabel.setFont(WindowElementResize.mainFont);
		mainTitleLabel.setForeground(Color.WHITE);
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		//variables for button dimensions
		int buttonDimension = (int) (windowWidth * 0.025);
		int biggerButtonDimension = (int) (windowWidth * 0.035);
				
		
		// panel with all required buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		flowLayout.setHgap(10); 
		buttonPanel.setLayout(flowLayout);
		
		//cancel and save buttons
		RoundedButton cancelButton = new RoundedButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(95, 35));
		buttonPanel.add(cancelButton);
		
		RoundedButton saveButton = new RoundedButton("Save all");
		saveButton.setPreferredSize(new Dimension(95, 35));
		buttonPanel.add(saveButton);
		
		
		//edit button in toolbar
		RoundButton editButton = new RoundButton("",buttonDimension ,buttonDimension );
		editButton.setBackground(toolbarColor);
		editButton.setButtonIcon("icons/EditIcon.png",  buttonDimension, buttonDimension);
		editButton.setBorder(null);
		buttonPanel.add(editButton);

		//add new group button in toolbar
		RoundButton addsubGroupButton = new RoundButton("",  buttonDimension, buttonDimension);
		addsubGroupButton.setButtonIcon("icons/addIcon.png",  buttonDimension, buttonDimension);
		addsubGroupButton.setBackground(toolbarColor);
		addsubGroupButton.setForeground(backgroundColor);
		addsubGroupButton.setBorder(null);
		buttonPanel.add(addsubGroupButton);
		
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
		
		JScrollPane scrollPane = new JScrollPane(editCardsCollection());	
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        contentPane.add(scrollPane, BorderLayout.CENTER);
	
    }
    
    public JPanel editCardsCollection() {
        JPanel editCardsPanel = new JPanel(new GridBagLayout()); // main panel (all groups)
        editCardsPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.CENTER; // Align components to the center
        
        
        //int numCols = Math.min(groupNames.length, 2);
        int numCols = 3;
        int colCounter = 0;
        
        // Calculate the number of rows needed based on the number of groups
        int numRows = (int) Math.ceil((double) groupNames.length / 3);
       
        
        for (int i = 1; i < groupNames.length; i++) {
        	 JPanel groupPanel = createCardsPanel(groupNames[i], groupColors[i]);

             // Add the panel to the main panel using GridBagConstraints
             editCardsPanel.add(groupPanel, gbc);

             // Update GridBagConstraints for next panel
             gbc.gridx++;
             if (gbc.gridx == 3) { // If three panels have been added, move to the next row
                 gbc.gridx = 0;
                 gbc.gridy++;
            }
        }

        return editCardsPanel;
    }
    
    private JPanel createCardsPanel(String groupName, Color groupColor) {
        int frameWidth = windowWidth;
        int frameHeight = windowHeight;
        int horizontalGap = (int) (frameWidth * HORIZONTAL_GAP_PERCENTAGE / 100.0);

        // Calculate the number of columns and rows based on the groupNames array
        int numRows = Math.min(groupNames.length, 2);
        int numCols = (int) Math.ceil((double) groupNames.length / numRows);

        // Calculate the width and height for each rectangle
        int rectangleWidth = (frameWidth - (numCols) * horizontalGap) / numCols;
        int rectangleHeight = (frameHeight - (numRows) * horizontalGap) / numRows;

        // Increase the dimensions for larger panels
        int enlargedWidth = (int) (rectangleWidth * 6);
        int enlargedHeight = (int) (rectangleHeight * 1.2); 
        
        JPanel groupPanel = new JPanel(new GridLayout(numRows, numCols, horizontalGap, horizontalGap)); // panel for 1 group, divided into 3 sides
        groupPanel.setBackground(backgroundColor);

        // Add panels for each group
        for (int i = 0; i < groupNames.length; i++) {
        	JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // FlowLayout for label and text field
            panel.setPreferredSize(new Dimension(enlargedWidth, enlargedHeight));
            panel.setBackground(groupColor); // Set individual group color if needed

            // Create a label
            JLabel lblQ = new JLabel("Question:");
            lblQ.setFont(secFont);
            lblQ.setForeground(Color.WHITE);
            panel.add(lblQ);

            // Create a text field
            qInput = new RoundTextField(40); // Adjust the column count as needed
            qInput.setFont(inputText);
            qInput.setText("Enter your question");
            qInput.setPreferredSize(new Dimension(enlargedWidth, 80)); // Set the preferred size of the text field
            // Text inside of the username field
            qInput.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                    if (qInput.getText().equals("Edit your question")) {
                        qInput.setText("");
                    }
                }

                public void focusLost(FocusEvent e) {
                    if (qInput.getText().isEmpty()) {
                        qInput.setText("Edit your question");
                    }
                }
            });
            panel.add(qInput);
            
            // Create a label
            JLabel lblA = new JLabel("Answer:");
            lblA.setFont(secFont);
            lblA.setForeground(Color.WHITE);
            panel.add(lblA);

            // Create a text field
            aInput = new RoundTextField(40); // Adjust the column count as needed
            aInput.setFont(inputText);
            aInput.setText("Enter your question");
            aInput.setPreferredSize(new Dimension(enlargedWidth, 160)); // Set the preferred size of the text field
            // Text inside of the username field
            aInput.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                    if (aInput.getText().equals("Edit your answer")) {
                        aInput.setText("");
                    }
                }

                public void focusLost(FocusEvent e) {
                    if (aInput.getText().isEmpty()) {
                        aInput.setText("Edit your answer");
                    }
                }
            });
            panel.add(aInput);
            
            //adding label for color
            JLabel lblColor = new JLabel("Choose new color:");
            lblColor.setFont(secFont);
            lblColor.setForeground(Color.WHITE);
            panel.add(lblColor);
            
            // Create an instance of ColorfulButtons
            ColorfulButtons colorfulButtons = new ColorfulButtons(panel);
            //colorfulButtons.setBounds((int)(desiredWidth*0.33), (int)(desiredHeight*0.65), (int)(desiredWidth*0.93), (int)(desiredHeight*0.17)); // Adjust the bounds as needed
            panel.add(colorfulButtons);
            
            // Add Save and Delete buttons
            JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // FlowLayout for label and text field
            panelButtons.setPreferredSize(new Dimension(enlargedWidth, enlargedHeight));
            panelButtons.setBackground(groupColor); // Set individual group color if needed
            panel.add(panelButtons);
            
            RoundedButton btnSave = new RoundedButton("Save");
            btnSave.setFont(smallFont);
            btnSave.setForeground(Color.BLACK);
            btnSave.setBackground(new Color(248, 248, 255));
            btnSave.setPreferredSize(new Dimension(95, 35));
            panelButtons.add(btnSave);

            RoundedButton btnDelete = new RoundedButton("Cancel");
            btnDelete.setFont(smallFont);
            btnDelete.setForeground(Color.BLACK);
            btnDelete.setBackground(new Color(248, 248, 255));
            btnDelete.setPreferredSize(new Dimension(95, 35));
            panelButtons.add(btnDelete);
            
            
            

            groupPanel.add(panel);
        }

        return groupPanel;
    }

    
	class ColorfulButtons extends JPanel {

		private JPanel parentPanel;
		
	    public ColorfulButtons(JPanel parentPanel) {
	        this.parentPanel = parentPanel;
	    	setLayout(new FlowLayout(FlowLayout.LEFT)); // Buttons will be aligned to the left
	        
	        //calculated variables for window height and width
		    int desiredHeight = (int) (dimensions.screenHeight * 0.435);
		    int desiredWidth = (int) (dimensions.screenWidth * 0.4);
			
            // Create a button with a default color
		    RoundButton button = new RoundButton(" ", (int)(desiredWidth*0.063), (int)(desiredHeight*0.103)); // Adjust button size as needed
            button.setBackground(Color.WHITE);
            button.addActionListener(new ButtonClickListener());
            add(button);

	        setOpaque(false); // Make the panel transparent
	    }

	    private class ButtonClickListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            RoundButton source = (RoundButton) e.getSource();
	            Color selectedColor = JColorChooser.showDialog(parentPanel, "Choose a color", source.getBackground());
	            if (selectedColor != null) {
	                // Update the background color of the button
	                source.setBackground(selectedColor);
	        }
	    }
	}   
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
                	EditCards frame = new EditCards(0, 0, 0, 0);
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}