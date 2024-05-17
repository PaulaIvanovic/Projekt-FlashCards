package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import databaseInfo.UserInfo;
import views.AddCardsManual.ColorfulButtons;

public class AddCardsManual extends JFrame implements GlobalDesign{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField qInput, aInput;
	public String chosenColor;
	
	  ScreenDimensions dimensions = new ScreenDimensions();


	public AddCardsManual(CardsDisplayWindow parent) {
		//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());
		
		//calculated variables for window height and width
	    int desiredHeight = (int) (dimensions.screenHeight * 0.435);
	    int desiredWidth = (int) (dimensions.screenWidth * 0.4);
		
	  
        // Set the custom title bar
        setUndecorated(true);
        setTitle("NEW CARD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, desiredWidth, desiredHeight);
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        setResizable(false); 
        setLocationRelativeTo(null);
        setVisible(true);  
     
	    //main panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(backgroundColor);
		setLocationRelativeTo(null); //middle of the screen
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		
		//toolbar panel on top of the window
		JPanel toolbarPanel = new JPanel(new BorderLayout());
		toolbarPanel.setBackground(toolbarColor);
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
					
		//class for font size
		WindowElementResize.getFontForWindowSize(desiredHeight);
		
		//toolbar label (name of page)
		JLabel mainTitleLabel = new JLabel(" New card");      
		mainTitleLabel.setFont(WindowElementResize.mainTitle);
		mainTitleLabel.setForeground(Color.WHITE);
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		// panel for buttons in toolbar
		JPanel tbPane = new JPanel();
		tbPane.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		tbPane.setLayout(flowLayout);
				
		//exit button in toolbar
		RoundButton exitButton = new RoundButton("",(int)(desiredWidth*0.05) ,(int)(desiredHeight*0.085));
		exitButton.setButtonIcon("icons/CloseIcon.png", (int)(desiredWidth*0.05), (int)(desiredHeight*0.085));
		exitButton.setBackground(toolbarColor);
		exitButton.setBorder(null);
		exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	parent.updateView();
            	parent.setVisible(true);
            	dispose();
            }
        });
		tbPane.add(exitButton);
		
		toolbarPanel.add(tbPane, BorderLayout.EAST);
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		
		//panel for labels and buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(0, 0, desiredWidth, desiredHeight);
        contentPane.add(centerPanel);
        centerPanel.setBackground(new Color(69, 62, 130));
        centerPanel.setLayout(null);
        
        //message "these fields can not be empty"
      	JLabel lblInfo = new JLabel("");
      	lblInfo.setBounds((int)(desiredWidth * 0.03), (int)(desiredHeight * 0.725), (int)(desiredWidth * 0.5), (int)(desiredHeight * 0.04));
      	centerPanel.add(lblInfo);
      	lblInfo.setFont(tinyFont);
      	lblInfo.setForeground(textRed);
        
        
        //adding label for q
        JLabel lblQ = new JLabel("Question:");
        lblQ.setFont(secFont);
        lblQ.setForeground(Color.WHITE);
        lblQ.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.03), (int)(desiredWidth*0.3), (int)(desiredHeight*0.085));
        centerPanel.add(lblQ);
        
        //question input
        qInput = new RoundTextField(0);
        qInput.setFont(inputText);
        qInput.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.13), (int)(desiredWidth*0.9), (int)(desiredHeight*0.15));
        qInput.setText("Enter your question");
        
        //text inside of username field
        qInput.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (qInput.getText().equals("Enter your question")) {
                	qInput.setText(""); 
                }
            }

            public void focusLost(FocusEvent e) {
                if (qInput.getText().isEmpty()) {
                	qInput.setText("Enter your question");
                }
            }
        });
        
        qInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	boolean backspace = e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
            	String inputText = qInput.getText();
            	// Set the text with the key typed
                char keyChar = e.getKeyChar();
                int totalChar = qInput.getText().length();
                if(totalChar < charLimit || backspace) {
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
            }
        });
        centerPanel.add(qInput);
        
        //adding label for a
        JLabel lblA = new JLabel("Answer:");
        lblA.setFont(secFont);
        lblA.setForeground(Color.WHITE);
        lblA.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.30), (int)(desiredWidth*0.3), (int)(desiredHeight*0.085));
        centerPanel.add(lblA);
        
        //answer input
        aInput = new RoundTextField(0);
        aInput.setFont(inputText);
        aInput.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.40), (int)(desiredWidth*0.9), (int)(desiredHeight*0.2));
        aInput.setText("Enter your answer");
        
        //text inside of username field
        aInput.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (aInput.getText().equals("Enter your answer")) {
                	aInput.setText(""); 
                }
            }

            public void focusLost(FocusEvent e) {
                if (aInput.getText().isEmpty()) {
                	aInput.setText("Enter your answer");
                }
            }
        });
        
        aInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	boolean backspace = e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
            	String inputText = aInput.getText();
            	// Set the text with the key typed
                char keyChar = e.getKeyChar();
                int totalChar = aInput.getText().length();
                if(totalChar < charLimit || backspace) {
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
            }
        });
        centerPanel.add(aInput);
        
        //adding label for color
        JLabel lblColor = new JLabel("Choose new color:");
        lblColor.setFont(secFont);
        lblColor.setForeground(Color.WHITE);
        lblColor.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.665), (int)(desiredWidth*0.35), (int)(desiredHeight*0.085));
        centerPanel.add(lblColor);
        
        // Create an instance of ColorfulButtons
        ColorfulButtons colorfulButtons = new ColorfulButtons(centerPanel);
        colorfulButtons.setBounds((int)(desiredWidth*0.33), (int)(desiredHeight*0.65), (int)(desiredWidth*0.93), (int)(desiredHeight*0.17)); // Adjust the bounds as needed
        centerPanel.add(colorfulButtons);
        
        
        
        // Add Save and Cancel buttons
        RoundedButton btnSave = new RoundedButton("Save");
        btnSave.setFont(smallFont);
        btnSave.setForeground(Color.BLACK);
        btnSave.setBackground(new Color(248, 248, 255));
        btnSave.setBounds((int)(desiredWidth*0.62), (int)(desiredHeight*0.76), (int)(desiredWidth*0.17), (int)(desiredHeight*0.085));
        centerPanel.add(btnSave);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(checkSelection()) {
            		if(!lblInfo.getText().equals("Question is too long") && !lblInfo.getText().equals("Answer is too long")) {
                		UserInfo.addCard(qInput.getText(), aInput.getText(), chosenColor);
                		parent.updateView();
                		parent.setVisible(true);
                    	dispose();
            		}
            	}else {
            		lblInfo.setText("Warning: Information incomplete. Make sure no field is empty.");
            	}
            }

			private boolean checkSelection() {
				if(qInput.getText() == "" || qInput.getText().equalsIgnoreCase("Enter your question") || aInput.getText() == "" || aInput.getText().equalsIgnoreCase("Enter your answer") || chosenColor == null) {
					return false;
				}
				return true;
			}
        });

        RoundedButton btnCancel = new RoundedButton("Cancel");
        btnCancel.setFont(smallFont);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setBackground(new Color(248, 248, 255));
        btnCancel.setBounds((int)(desiredWidth*0.8), (int)(desiredHeight*0.76), (int)(desiredWidth*0.17), (int)(desiredHeight*0.085));
        centerPanel.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	parent.updateView();
            	parent.setVisible(true);
                dispose();
            }
        });
		
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
    				int red = selectedColor.getRed();
    		        int green = selectedColor.getGreen();
    		        int blue = selectedColor.getBlue();
    		        
    		        chosenColor = String.format("0x%02X%02X%02X", red, green, blue);
	    	
	                source.setBackground(selectedColor);
	        }
	    }
	}
	
	    
	}
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCardsManual frame = new AddCardsManual();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
}


