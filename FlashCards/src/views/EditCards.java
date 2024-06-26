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
import java.awt.event.KeyListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import databaseInfo.UserInfo;
import services.ColorUtils;
import views.AddCardsManual.ColorfulButtons;
import views.AddCardsManual.ColorfulButtons.ButtonClickListener;



public class EditCards extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;

    
    private final int HORIZONTAL_GAP_PERCENTAGE = 2; 
    
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
    
    public EditCards() {
    	this(0,0,0,0, "");
    }
    
    public EditCards(int x, int y, int width, int height, String name) {
    	this.name = name;
    	//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());

    	screenSize = new ScreenDimensions();
    	
    	this.setTitle("EDIT CARDS PAGE");
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
    	UserInfo.getCards();
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
		JLabel mainTitleLabel = new JLabel("Edit cards - " + name);      
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

		//cancel button leads back to CardsDisplayWindow
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfo.getCards();
				
				CardsDisplayWindow cardsWindow = new CardsDisplayWindow(xPositionWindow, yPositionWindow, windowWidth, windowHeight, name);
				cardsWindow.setVisible(true);
				dispose();
			}
		});
		
		RoundedButton saveButton = new RoundedButton("Save all");
		saveButton.setPreferredSize(new Dimension(95, 35));
		buttonPanel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfo.saveEditAllCards();
				
				CardsDisplayWindow cardsWindow = new CardsDisplayWindow(xPositionWindow, yPositionWindow, windowWidth, windowHeight, name);
				cardsWindow.setVisible(true);
				dispose();
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
        
        
        int numCols = Math.min(UserInfo.cardQuestion.size(), 2);
        int colCounter = 0;

        for (int i = 0; i < UserInfo.cardQuestion.size(); i++) {
        	JPanel groupPanel = createCardsPanel(UserInfo.cardQuestion.get(i), UserInfo.cardAnswer.get(i), UserInfo.cardColors.get(i), UserInfo.cardID.get(i));         
        	editCardsPanel.add(groupPanel, gbc);
            gbc.gridx++;
            colCounter++;

            // if maximum number of columns reached, move to next row
            if (colCounter == numCols) {
                colCounter = 0;
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }

        return editCardsPanel;
    }
    
    private JPanel createCardsPanel(String question, String answer,  Color cardColor, String ID) {
        int frameWidth = windowWidth;
        int frameHeight = windowHeight;
        int horizontalGap = (int) (frameWidth * HORIZONTAL_GAP_PERCENTAGE / 100.0);

        // Calculate the number of columns and rows based on the groupNames array
        int numCols = Math.min(UserInfo.cardQuestion.size(), 3);
        int numRows = (int) Math.ceil((double) UserInfo.cardQuestion.size() / numCols);

        // Calculate the width and height for each rectangle
        int rectangleWidth = (frameWidth - (numCols) * horizontalGap) / numCols;
        int rectangleHeight = (frameHeight - (numRows-1) * horizontalGap) / numRows;
        rectangleHeight = (int)(frameHeight*0.475);

        

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // FlowLayout for label and text field
        panel.setPreferredSize(new Dimension(rectangleWidth, rectangleHeight));
        panel.setBackground(cardColor); // Set individual group color if needed
        
        
        //message "these fields can not be empty"
      	JLabel lblInfo = new JLabel("");
      	lblInfo.setPreferredSize(new Dimension((int) (rectangleWidth * 0.9), (int) (rectangleHeight * 0.05))); // Set height
      	lblInfo.setFont(tinyFont);
      	lblInfo.setForeground(ColorUtils.getContrastingTextColor(cardColor));
        panel.add(lblInfo);
        

        // Create a label
        JLabel lblQ = new JLabel("Question:");
        lblQ.setFont(secFont);
        lblQ.setForeground(ColorUtils.getContrastingTextColor(cardColor));
        panel.add(lblQ);

        // Create a text field
        RoundMultilineText  qInput = new RoundMultilineText(question); // Adjust the column count as needed
        qInput.setFont(inputText);
        qInput.setLineWrap(true);
        qInput.setWrapStyleWord(true);
        qInput.setPreferredSize(new Dimension((int) (rectangleWidth * 0.9), (int) (rectangleHeight * 0.2))); // Set height
        
            
        qInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	boolean backspace = e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
            	String inputText = qInput.getText();
            	// Set the text with the key typed
                char keyChar = e.getKeyChar();
                int totalChar = qInput.getText().length();
                if(totalChar < cardQcharLimit || backspace) {
                	lblInfo.setText("");
                	
                }else {
                	if(inputText.length() > cardQcharLimit) {
                		//user tries to go over the limit, dont show letters
                		// Remove the last character
                		inputText = inputText.substring(0, inputText.length() - 1);
                	}
                	qInput.setText(inputText);
                	lblInfo.setText("Question is too long");
                }
                UserInfo.changeCardQuestion(ID, qInput.getText());
            }
        });
            
            
            panel.add(qInput);
            
            // Create a label
            JLabel lblA = new JLabel("Answer:");
            lblA.setFont(secFont);
            lblA.setForeground(ColorUtils.getContrastingTextColor(cardColor));
            panel.add(lblA);

            // Create a text field
            RoundMultilineText aInput = new RoundMultilineText(answer); 
            aInput.setFont(inputText);
            aInput.setLineWrap(true); // Enable line wrapping
            aInput.setWrapStyleWord(true); // Wrap on word boundary
            aInput.setPreferredSize(new Dimension((int) (rectangleWidth * 0.9), (int) (rectangleHeight * 0.3))); // Set fixed height
            
            aInput.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e){
                	boolean backspace = e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
                	String inputText = aInput.getText();
                	// Set the text with the key typed
                    char keyChar = e.getKeyChar();
                    int totalChar = aInput.getText().length();
                    if(totalChar < cardAcharLimit || backspace) {
                    	lblInfo.setText("");
                    }else {
                    	if(inputText.length() > cardAcharLimit) {
                    		//user tries to go over the limit, dont show letters
                    		// Remove the last character
                    		inputText = inputText.substring(0, inputText.length() - 1);
                    	}
                    	aInput.setText(inputText);
                    	lblInfo.setText("Answer is too long");
                    }
                    UserInfo.changeCardAnswer(ID, aInput.getText());
                }
            });
            panel.add(aInput);
            
            //adding label for color
            JLabel lblColor = new JLabel("Choose new color:");
            lblColor.setFont(secFont);
            lblColor.setForeground(ColorUtils.getContrastingTextColor(cardColor));
            panel.add(lblColor);
            
            // Create an instance of ColorfulButtons
            ColorfulButtons colorfulButtons = new ColorfulButtons(panel, ID);
            //colorfulButtons.setBounds((int)(desiredWidth*0.33), (int)(desiredHeight*0.65), (int)(desiredWidth*0.93), (int)(desiredHeight*0.17)); // Adjust the bounds as needed
            panel.add(colorfulButtons);
            
            // Add Save and Delete buttons
            JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // FlowLayout for label and text field
            panelButtons.setPreferredSize(new Dimension(rectangleWidth, rectangleHeight));
            panelButtons.setBackground(cardColor); // Set individual group color if needed
            panel.add(panelButtons);
            
            RoundedButton btnDelete = new RoundedButton("Delete");
            btnDelete.setFont(smallFont);
            btnDelete.setBackground(Color.RED);
            btnDelete.setPreferredSize(new Dimension(95, 35));
            panelButtons.add(btnDelete);
            btnDelete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int response = JOptionPane.showConfirmDialog(
                            null, 
                            "Are you sure you want to delete this card? This change cannot be undone", 
                            "Confirm Deletion", 
                            JOptionPane.YES_NO_OPTION
                    );
                    
                    if (response == JOptionPane.YES_OPTION) {
                        UserInfo.deleteCard(ID);
                        contentPane.removeAll();
                        // add your elements
                        contentPane.revalidate();
                        contentPane.repaint();
                        updateView();
                    }
                }
            });
            
            RoundedButton btnSave = new RoundedButton("Save");
            btnSave.setFont(smallFont);
            btnSave.setForeground(Color.BLACK);
            btnSave.setBackground(new Color(248, 248, 255));
            btnSave.setPreferredSize(new Dimension(95, 35));
            
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	UserInfo.saveEditCard(ID);
                }
            });
            
            panelButtons.add(btnSave);

        

        return panel;
    }

    
	class ColorfulButtons extends JPanel {

		private JPanel parentPanel;
		public Color chosenColor;
		
	    public ColorfulButtons(JPanel parentPanel, String ID) {
	        this.parentPanel = parentPanel;
	    	setLayout(new FlowLayout(FlowLayout.LEFT)); // Buttons will be aligned to the left
	        
	        //calculated variables for window height and width
		    int desiredHeight = (int) (dimensions.screenHeight * 0.435);
		    int desiredWidth = (int) (dimensions.screenWidth * 0.4);
			
            // Create a button with a default color
		    RoundButton button = new RoundButton(" ", (int)(desiredWidth*0.063), (int)(desiredHeight*0.103)); // Adjust button size as needed
            button.setBackground(Color.WHITE);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	RoundButton source = (RoundButton) e.getSource();
                	Color selectedColor = JColorChooser.showDialog(parentPanel, "Choose a color", source.getBackground());
                	if (selectedColor != null) {
                		// Update the background color of the button
                		int red = selectedColor.getRed();
                		int green = selectedColor.getGreen();
                		int blue = selectedColor.getBlue();
    		        
                		chosenColor = selectedColor;
                		
                		UserInfo.changeCardColor(ID, chosenColor);
                		source.setBackground(selectedColor);
                	}
                }
	    });
            add(button);
	        setOpaque(false); // Make the panel transparent
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
/*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	EditCards frame = new EditCards(0, 0, 0, 0, "");
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
}