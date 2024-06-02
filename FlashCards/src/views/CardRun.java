package views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import databaseInfo.UserInfo;

public class CardRun extends JFrame implements GlobalDesign {
    private static final long serialVersionUID = 1L;    
    private JPanel contentPane;
    private JPanel mainPanel;
    private RoundedButton cardComponent;


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
    
    boolean side = true;	//true on question side, false on answer side

    static String ID;

    public CardRun() {
        this(0, 0, 0, 0, "");
    }

    public CardRun(int x, int y, int width, int height, String ID) {
        this.ID = ID;
        
        java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
        ImageIcon Icon = new ImageIcon(IconURL);
        setIconImage(Icon.getImage());

        screenSize = new ScreenDimensions();

        this.setTitle("STARTING GROUP OF CARDS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(screenSize.minimumWindowWidth, screenSize.minimumWindowHeight));

        checkBounds(x, y, width, height);
        this.setBounds(xPositionWindow, yPositionWindow, windowWidth, windowHeight);
        windowCreate();

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

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(0, 0, windowWidth, windowHeight - 80);
        mainPanel.setBorder(new EmptyBorder(50, 10, 5, 5));
        mainPanel.setOpaque(false);

        contentPane = new JPanel();
        contentPane.setBackground(backgroundColor);        
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JPanel toolbarPanel = new JPanel(new BorderLayout());
        toolbarPanel.setBackground(toolbarColor);
        toolbarPanel.setBounds(0, 0, windowWidth, 80);
        toolbarPanel.setLayout(new BorderLayout());
        toolbarPanel.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10));
        contentPane.add(toolbarPanel, BorderLayout.NORTH);

        JLabel mainTitleLabel = new JLabel("Starting group of cards");      
        mainTitleLabel.setFont(WindowElementResize.mainFont);
        mainTitleLabel.setForeground(Color.WHITE);
        toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
        flowLayout.setHgap(10); 
        buttonPanel.setLayout(flowLayout);

        int buttonDimension = (int) (windowWidth * 0.025);
        int biggerButtonDimension = (int) (windowWidth * 0.035);

        RoundButton editButton = new RoundButton("", buttonDimension, buttonDimension);
        editButton.setBackground(toolbarColor);
        editButton.setButtonIcon("icons/PlayCardsIcon.png", buttonDimension, buttonDimension);
        editButton.setBorder(null);
        buttonPanel.add(editButton);

        RoundButton returnArr = new RoundButton("", buttonDimension, buttonDimension);
        returnArr.setButtonIcon("icons/ReturnArrowIcon.png", buttonDimension, buttonDimension);
        returnArr.setBackground(toolbarColor);
        returnArr.setForeground(backgroundColor);
        returnArr.setBorder(null);
        buttonPanel.add(returnArr);
        returnArr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UserInfo.saveCardsStats();
                CardsDisplayWindow cdw = new CardsDisplayWindow(xPositionWindow, yPositionWindow, windowWidth, windowHeight, UserInfo.getname(false));
                dispose();
                cdw.setVisible(true);
            }
        });

        RoundButton settingsButton = new RoundButton("", buttonDimension, buttonDimension);
        settingsButton.setButtonIcon("icons/settingsIcon.png", buttonDimension, buttonDimension);
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

        RoundButton userIcon = new RoundButton("", biggerButtonDimension, biggerButtonDimension);
        userIcon.setButtonIcon("Pictures/" + UserInfo.profilePic, biggerButtonDimension, biggerButtonDimension);
        userIcon.setBackground(toolbarColor);
        userIcon.setBorder(null);
        buttonPanel.add(userIcon);

        RoundedButton save = new RoundedButton("End");
        save.setFont(WindowElementResize.buttonText);
        save.setBounds((int)(windowWidth * 0.45), (int)(windowHeight * 0.800), (int)(windowWidth * 0.115), (int)(windowHeight * 0.040));
        save.setPreferredSize(new Dimension(100, 50));
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UserInfo.saveCardsStats();
                CardsDisplayWindow cdw = new CardsDisplayWindow(xPositionWindow, yPositionWindow, windowWidth, windowHeight, UserInfo.getname(false));
                dispose();
                cdw.setVisible(true);
            }
        });
        
        
        if (side) {
        	//question on top
        	cardComponent = new RoundedButton(UserInfo.cardQuestion.get(UserInfo.cardID.indexOf(ID)));
        } else {
        	//answer on top
        	cardComponent = new RoundedButton(UserInfo.cardAnswer.get(UserInfo.cardID.indexOf(ID)));
        }
        cardComponent.setFont(buttonText);
        cardComponent.setForeground(Color.BLACK);
        cardComponent.setBounds((int)(windowWidth * 0.15), (int)(windowHeight * 0.250), (int)(windowWidth * 0.700), (int)(windowHeight * 0.500));
        
        //when the card gets clicked, side flips
        cardComponent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardComponent.setText(UserInfo.cardAnswer.get(UserInfo.cardID.indexOf(ID)));
            	side = false;
            	updateView();
            }
        });
        
        mainPanel.add(cardComponent);
        toolbarPanel.add(buttonPanel, BorderLayout.EAST);
        mainPanel.add(save);

        ColorfulButtons colorfulButtons = new ColorfulButtons(this);
        colorfulButtons.setBounds((int)(windowWidth * 0.03), (int)(windowHeight * 0.09), (int)(windowWidth * 0.99), (int)(windowHeight * 0.3));
        mainPanel.add(colorfulButtons);

        JPanel navigationLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, (int)(windowWidth * 0.05), (int)(windowHeight * 0.25)));
        navigationLeft.setOpaque(false);
        navigationLeft.setBounds((int)(windowWidth * 0.1), (int)(windowHeight * 0.45), buttonDimension * 3, buttonDimension * 3);

        RoundButton moveLeft = new RoundButton("", buttonDimension * 2, buttonDimension * 2);
        moveLeft.setBackground(backgroundColor);
        moveLeft.setForeground(backgroundColor);
        moveLeft.setBorder(null);
        navigationLeft.add(moveLeft);

        mainPanel.add(navigationLeft, BorderLayout.WEST);

        JPanel navigationRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, (int)(windowWidth * 0.05), (int)(windowHeight * 0.25)));
        navigationRight.setOpaque(false);
        navigationRight.setBounds((int)(windowWidth * 0.85), (int)(windowHeight * 0.5), buttonDimension * 3, buttonDimension * 3);

        RoundButton moveRight = new RoundButton("", buttonDimension * 2, buttonDimension * 2);
        moveRight.setBackground(backgroundColor);
        moveRight.setForeground(backgroundColor);
        moveRight.setBorder(null);
        navigationRight.add(moveRight);
        
        CardRun window = this;
        if(side) {
        	 moveLeft.setButtonIcon("icons/LeftArrowIcon.png", buttonDimension * 2, buttonDimension * 2);
        	 moveLeft.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     //set visited to visited
                	 UserInfo.visited.set(UserInfo.card, 1);
                	 if(UserInfo.card-1 >= 0) {
                		 UserInfo.card--;
                		 switchCard(window, UserInfo.cardIDLineup.get(UserInfo.card));
                	 }
                     updateView();
                   }
               });
        	 moveRight.setButtonIcon("icons/RightArrowIcon.png", buttonDimension * 2, buttonDimension * 2);
        	 moveRight.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     //set visited to visited
                	 UserInfo.visited.set(UserInfo.card, 1);
                	 checkSwitch(true);
                 	switchCard(window, UserInfo.cardIDLineup.get(UserInfo.card));
                     updateView();
                   }
               });
        }else {
        	///jednu od ovih slika treba postavit kao x a jednu kao tocno
       	 	moveLeft.setButtonIcon("Pictures/default.png", buttonDimension * 2, buttonDimension * 2);
       	 	moveLeft.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 //set visited to correct answer
            	 UserInfo.visited.set(UserInfo.card, 2);
            	 checkSwitch(true);
            	switchCard(window, UserInfo.cardIDLineup.get(UserInfo.card));
                 updateView();
               }
       	 	});
       	 	moveRight.setButtonIcon("icons/CloseIcon.png", buttonDimension * 2, buttonDimension * 2);
       	 	moveRight.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                //set visited to wrong answer
            	 UserInfo.visited.set(UserInfo.card, 3);
            	 checkSwitch(true);
            	switchCard(window, UserInfo.cardIDLineup.get(UserInfo.card));
                 updateView();
               }
       	 	});
        }

        mainPanel.add(navigationRight, BorderLayout.EAST);
        contentPane.add(mainPanel, BorderLayout.CENTER);
    }

    public void updateView() {
        START_X = (int)(windowWidth * 0.05);
        START_Y = (int)(windowHeight * 0.175); 
        windowCreate();
    }

    public void checkBounds(int x, int y, int width, int height) {
        if (x <= -10 || y <= -10) {
            xPositionWindow = 0;
            yPositionWindow = 0;
        } else {
            xPositionWindow = x;
            yPositionWindow = y;
        }

        if (width <= 0 || height <= 0) {
            windowWidth = dimensions.screenWidth;
            windowHeight = dimensions.screenHeight;
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else if (width <= dimensions.minimumWindowWidth && height <= dimensions.minimumWindowHeight) {
            windowWidth = dimensions.minimumWindowWidth;
            windowHeight = dimensions.minimumWindowHeight;
        } else if (width <= dimensions.minimumWindowWidth && height > dimensions.minimumWindowHeight) {
            windowWidth = dimensions.minimumWindowWidth;
            windowHeight = height;
        } else if (height <= dimensions.minimumWindowHeight && width > dimensions.minimumWindowWidth) {
            windowWidth = width;
            windowHeight = dimensions.minimumWindowHeight;
        } else {
            windowWidth = width;
            windowHeight = height;
        }

        if (x <= -10 || y <= -10) {
            xPositionWindow = 0;
            yPositionWindow = 0;
        } else {
            xPositionWindow = x;
            yPositionWindow = y;
        }
    }

    public void switchCard(CardRun window, String newID) {
    	side = true;
        try {
            ID = newID;
            cardComponent.setText(UserInfo.cardQuestion.get(UserInfo.cardID.indexOf(newID)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.revalidate();
        window.repaint();
    }
    
    public void checkSwitch(boolean forward) {
    	if(!UserInfo.visited.contains(0) && !UserInfo.visited.contains(1)) {
    		//try to go forward if there are cards left
       	 	if(UserInfo.card+1 >= UserInfo.cardIDLineup.size()) {
       	 		//start from begining
       	 		UserInfo.card = -1; 	//becaurse we add 1
       	 	}
       	 	UserInfo.card++;
    	}
    	if(forward) {
    		//try to go forward if there are cards left
       	 	if(UserInfo.card+1 >= UserInfo.cardIDLineup.size()) {
       	 		//start from begining
       	 		UserInfo.card = -1; 	//becaurse we add 1
       	 	}
       	 	
       	 	//check if cards are visited/uopened
       	 	if(UserInfo.visited.get(UserInfo.card+1) == 0 || UserInfo.visited.get(UserInfo.card+1) == 1) {//visited or unopened
       	 		if(UserInfo.card == -1) {
       	 			UserInfo.card = UserInfo.cardIDLineup.size()-1;
       	 			changeButtonCol();
       	 			UserInfo.card = -1;
       	 		}
   	           	UserInfo.card++;
        		UserInfo.visited.set(UserInfo.card, 4);
   	           	changeButtonCol();
           	 	return;
       	 	}else {
       	 		//if not visited/unopened find first which is
       	 		for(int i = UserInfo.card+1; i < UserInfo.cardIDLineup.size(); i++) {
       	       	 	if(UserInfo.visited.get(i) == 0 || UserInfo.visited.get(i) == 1) {//visited or unopened
       	       	 		changeButtonCol();
       	           	 	UserInfo.card = i;
                		UserInfo.visited.set(UserInfo.card, 4);
       	           	 	changeButtonCol();
       	           	 	return;
       	       	 	}
       	 		}
       	 		//if still not found go to beginning
       	 		for(int i = 0; i < UserInfo.cardIDLineup.size(); i++) {
       	       	 	if(UserInfo.visited.get(i) == 0 || UserInfo.visited.get(i) == 1) {//visited or unopened
       	       	 		changeButtonCol();
       	           	 	UserInfo.card = i;
                		UserInfo.visited.set(UserInfo.card, 4);
       	           	 	changeButtonCol();
       	           	 	return;
       	       	 	}
       	 		}
       	 	}
    	}else {
       	 	if(UserInfo.card-1 >= 0) {
       	 		UserInfo.card--;
    		 }
    	}
    	
    }
    
    public void changeButtonCol() {
    	RoundButton newColor = ColorfulButtons.buttonList.get(UserInfo.card);
    	if (UserInfo.visited.get(UserInfo.card) == 1) {
    		newColor.setBackground(Color.blue);
        } else if(UserInfo.visited.get(UserInfo.card) == 2){
        	newColor.setBackground(Color.green);
        }else if (UserInfo.visited.get(UserInfo.card) == 3){
        	newColor.setBackground(Color.red);
        }else if (UserInfo.visited.get(UserInfo.card) == 0){
        	newColor.setBackground(Color.gray);
        }else {
        	newColor.setBackground(Color.white);
        }
    }

 // Define the ColorfulButtons class
    class ColorfulButtons extends JPanel {
        private JPanel buttonLayout; // Store reference to the layout panel
        public static ArrayList<RoundButton> buttonList = new ArrayList();

        public ColorfulButtons(CardRun window) {
            setLayout(new FlowLayout(FlowLayout.LEFT));

            int desiredHeight = (int) (dimensions.screenHeight * 0.435);
            int desiredWidth = (int) (dimensions.screenWidth * 0.4);

            buttonLayout = new JPanel(new GridLayout(0, 30, 10, 10));
            buttonLayout.setOpaque(false);

            for (int i = 0; i < UserInfo.cardIDLineup.size(); i++) {
                RoundButton button = new RoundButton(String.valueOf(i+1));

                if (UserInfo.visited.get(i) == 1) {
                    button.setBackground(Color.blue);
                } else if(UserInfo.visited.get(i) == 2){
                    button.setBackground(Color.green);
                }else if (UserInfo.visited.get(i) == 3){
                    button.setBackground(Color.red);
                }else if (i == UserInfo.card){
                    button.setBackground(Color.white);
                }else {
                    button.setBackground(Color.gray);
                }
                button.setPreferredSize(new Dimension((int) (0.075 * desiredWidth), (int) (0.125 * desiredHeight)));
                button.setBorder(null);
                int current = i;
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	RoundButton startingPoint = buttonList.get(UserInfo.card);
                    	
                    	if(UserInfo.visited.get(UserInfo.card) == 0) {
                    		UserInfo.visited.set(UserInfo.card, 1);
                    	}
                    	//change button color
                    	if(UserInfo.visited.get(UserInfo.card) == 2) {
                    		startingPoint.setBackground(Color.green);
                    	}else if (UserInfo.visited.get(UserInfo.card) == 3) {
                    		startingPoint.setBackground(Color.red);
                    	}else if(UserInfo.visited.get(UserInfo.card) == 1){
                    		startingPoint.setBackground(Color.blue);
                    	}else{
                    		startingPoint.setBackground(Color.white);
                    	}
                    	
                    	RoundButton endPoint = (RoundButton) e.getSource();
                    	endPoint.setBackground(Color.white);

                        UserInfo.card = current;
                        window.switchCard(window, UserInfo.cardIDLineup.get(current));
                    }
                });
                buttonLayout.add(button);
                buttonList.add(button);
            }

            setOpaque(false);
            mainPanel.add(buttonLayout, BorderLayout.NORTH);
        }

        // Method to get components from the button layout panel
        public Component[] getButtonComponents() {
            return buttonLayout.getComponents();
        }
    }
}
